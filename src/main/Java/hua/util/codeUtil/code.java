package hua.util.codeUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author mxz
 * @Date 2020/5/31
 */
public class code {
    //查询的页数大小
    public static int size = 10;
    //判断手机号
    public static Map<String,Object> isPhone(String phone) {
        Map<String,Object> map = new HashMap<String,Object>();
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            map.put("is",false);
            map.put("msg","手机号不达到11位");
            return map;
        }
        else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
                map.put("is",false);
                map.put("msg","不是手机号");
                return map;
            }
            map.put("is",true);
            return map;
        }
    }
    //六位验证码
    public static String getCode() {
        return "{\"code\":\""+((int)((Math.random()*9+1)*100000))+"\"}";
    }
    //阿里云短信接口
    public static String getSms(String phone,String type, HttpSession session) {
        String accessKeyId = "";
        String accessSecret = "";
        if(phone!=null&&(Boolean) isPhone(phone).get("is"))
        {
            int value = ((int)((Math.random()*9+1)*100000));
            String codeValue = Integer.toString(value);
            String code = "{\"code\":\""+codeValue+"\"}";
            DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
            IAcsClient client = new DefaultAcsClient(profile);
            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", phone);
            request.putQueryParameter("SignName", "");
            request.putQueryParameter("TemplateCode", "");
            request.putQueryParameter("TemplateParam", code);
            try {
                CommonResponse response = client.getCommonResponse(request);
            } catch (Exception e) {
                System.out.println("您操作过于频繁，请等待一分钟后重试");
                return "您操作过于频繁，请等待一分钟后重试";
            }
            session.setAttribute(type, codeValue);
            System.out.println("验证码："+codeValue);
            return "验证码发送成功";
        }
        return (String)isPhone(phone).get("msg");
    }
}