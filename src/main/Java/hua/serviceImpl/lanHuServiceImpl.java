package hua.serviceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hua.mapper.lanHuMapper;
import hua.model.Consult;
import hua.model.Recruit;
import hua.model.Resume;
import hua.service.lanHuService;
import hua.util.file.fileUtil;
import hua.util.file.imgFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * @author mxz
 * @Date 2020/7/30
 */
@Service
public class lanHuServiceImpl implements lanHuService {
    @Autowired
    private lanHuMapper mapper;
    public Object addResume(Resume resume, HttpSession session) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("status",false);
        //获取验证码
        String imgCode = (String) session.getAttribute("captcha");
        if(resume!=null&&resume.getCode().toLowerCase().equals(imgCode)){
            if(mapper.addResume(resume)==1){
                map.put("status",true);
                map.put("msg","success");
                return map;
            }
            map.put("msg","failure");
            return map;
        }
        map.put("msg","验证码错误");
        return map;
    }
//    public List<Resume> lookResume(Integer currPage,Integer pageSize) {
//        int start = (pageNo-1)* code.size;
//        return mapper.lookResume(start,code.size);
//    }
public PageInfo lookResume(Integer currPage, Integer pageSize) {
    PageHelper.startPage(currPage,pageSize);
    List<Resume>  ResumeList = mapper.lookResume();
    PageInfo PageInfo = new PageInfo(ResumeList);
    return  PageInfo;
}
    public Object addRecruit(Recruit recruit) {
//        UUID uuid = UUID.randomUUID();
//        String id = uuid.toString().replace("-", "");
//        recruit.setNo(id);
        return mapper.addRecruit(recruit);
    }


    //    public List<Recruit> lookRecruit(int pageNo) {
//        int start = (pageNo-1)* code.size;
//        return mapper.lookRecruit(start,code.size);
//    }

    public Object upRecruit(Recruit recruit) {

        return mapper.upRecruit(recruit);
    }
    public Object upConsult(Consult consult){

        return mapper.upConsult(consult);
    }

    public Object consultCode(int no) {
        return mapper.consultCode(no);
    }

    public Object recruitCode(int no) {
        return mapper.recruitCode(no);
    }

    public Object recruitDown(int no) {
        return mapper.recruitDown(no);
    }

    public Object consultDown(int no) {
        return mapper.consultDown(no);
    }

    public void getCaptcha(HttpSession httpSession, HttpServletResponse resp) {
        Random r = new Random();
        char[] chs = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        int NUMBER_OF_CHS = 4;
        int IMG_WIDTH = 70;
        int IMG_HEIGHT = 30;
        BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Color c = new Color(255, 255, 255);
        g.setColor(c);
        g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);
        StringBuffer sb = new StringBuffer();
        Font f = new Font("arial",Font.PLAIN,20);
        g.setFont(f);
        int index;
        for (int i = 0; i < NUMBER_OF_CHS; i++) {
            index = r.nextInt(chs.length);
            g.setColor(new Color(r.nextInt(88), r.nextInt(210), r.nextInt(150)));
            g.drawString(chs[index] + "",15 * i + 3, 18);
            sb.append(chs[index]);
        }
        for(int i=0;i<10;i++) {
            g.drawLine(r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100));
        }
        httpSession.setAttribute("captcha", sb.toString().toLowerCase());
        try {
            ImageIO.write(image, "jpg", resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object upFile(HttpServletRequest request) throws FileNotFoundException {
        return fileUtil.upFile(request,"job");
    }

    public Object upImgFile(HttpServletRequest request) throws FileNotFoundException {
        return imgFileUtil.upFile(request,"img");
    }

    public Object addConsult(Consult consult) {

        return mapper.addConsult(consult);
    }
    public PageInfo lookConsult(Integer currPage,Integer pageSize) {
        PageHelper.startPage(currPage,pageSize);
        List<Consult>  ConsultList= mapper.lookConsult();
        PageInfo PageInfo = new PageInfo(ConsultList);
        return  PageInfo;
    }

    public Object singleRecruit(int no) {

        return mapper.singleRecruit(no);
    }

    public Object singleConsults(int no) {

        return mapper.singleConsults(no);
    }

    public PageInfo RecruitPage(Integer currPage, Integer pageSize){
        PageHelper.startPage(currPage,pageSize);
        List<Recruit>  recruitList= mapper.lookRecruit();
        PageInfo PageInfo = new PageInfo(recruitList);
        return  PageInfo;
    }

}
