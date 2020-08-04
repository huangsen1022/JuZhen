package hua.util.file;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author mxz
 * @Date 2020/5/9
 */
public class imgFileUtil {
    /**
     *
     * @param request   输入流
     * @param user_id   用户ID
     * @return
     */
    public static Map upFile(HttpServletRequest request,String user_id) throws FileNotFoundException {
        //上传的文件存放在项目的target目录
        String filePathDir = ResourceUtils.getURL("classpath:").getPath().split("classes")[0]+"images/";
        //1.创建文件对象，配置缓存信息
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2.创建ServletFileUpload对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        //3.设置文件名称的编码（防止乱码）
        upload.setHeaderEncoding("utf-8");
        //结果
        Map map = new HashMap<String,Object>();
        List<FileItem> items=null;
        String md5Value = null;
        //原来的照片名称
        String v = null;
        try {
            items = upload.parseRequest(request);
            //遍历上传的文件
            for (FileItem item:items){
                if(item.isFormField()) {
                    //不是文件
                    String s = item.getFieldName();
                    if("v".equals(s)){
                        v = item.getString("utf-8");
                    }
                }
                else{
                    InputStream fileContent = item.getInputStream();
                    //md5
                    md5Value = DigestUtils.md5Hex(String.valueOf(UUID.randomUUID()));
                    //获取文件的原始名称
                    String originalFilename = item.getName();
                    //获取最后一个.的位置
                    int lastIndexOf = originalFilename.lastIndexOf(".");
                    //获取文件的后缀名
                    String suffix = originalFilename.substring(lastIndexOf);
                    if(suffix.toLowerCase().equals(".png")||suffix.toLowerCase().equals(".jpg")||suffix.toLowerCase().equals(".jpeg")){
                        //文件名
                        String fileName = user_id+"_"+md5Value+suffix;
                        //将文件输入流转变为文件
                        FileUtils.copyInputStreamToFile(fileContent,new File(filePathDir+fileName));
                        map.put("is",true);
                        map.put("msg","图片上传成功");
                        map.put("fileName","http://192.168.1.72:8089/images/"+fileName);
                        return map;
                    }
                    else{
                        map.put("is",false);
                        map.put("msg","请上传规定的图片类型类型(png、jpg、jpeg)");
                        return map;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("is",false);
        map.put("msg","图片上传失败");
        return map;
    }
    //文件删除
    public static boolean deleteFile(String imgName) throws FileNotFoundException {
        String dir = ResourceUtils.getURL("classpath:").getPath().split("classes")[0]+"upImg/";
        String fileName = dir+imgName;
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }
}
