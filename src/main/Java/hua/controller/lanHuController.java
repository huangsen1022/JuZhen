package hua.controller;
import com.github.pagehelper.PageInfo;
import com.sun.istack.internal.NotNull;
import hua.model.Consult;
import hua.model.JsonReturn;
import hua.model.Recruit;
import hua.model.Resume;
import hua.service.lanHuService;
import hua.util.codeUtil.code;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

/**
 * @author mxz
 * @Date 2020/7/30
 */
@Controller
@ResponseBody
@RequestMapping("/lan")
public class lanHuController {
    @Autowired
    private lanHuService service;
    //申请职位
    @RequestMapping("/addResume")
    public JsonReturn  addResume(Resume resume, HttpSession session){
        if(StringUtils.isBlank(resume.getAddr())&& StringUtils.isBlank(resume.getEmail())&&StringUtils.isBlank(resume.getTel()) ){
            return new JsonReturn (503,"failure");
        }else

       return new JsonReturn (200,"success",service.addResume(resume,session));
    }
    //查看职位
    @RequestMapping("/lookResume")
    public JsonReturn lookResume(Integer currPage,Integer pageSize){
        PageInfo<Resume> pageResume=service.lookResume(currPage,pageSize);
        if(pageResume!=null){

            return new JsonReturn (200,"success",pageResume);
        }
        return new JsonReturn (503,"failure");
    }
    //文件上传
    @RequestMapping("/file")
    //接口 id指的是录入数据库信息后返回的主键id
    public Object upFile(HttpServletRequest request) throws FileNotFoundException {
        return service.upFile(request);
    }
    //发布招聘
    @RequestMapping("/addRecruit")
    @ResponseBody
    public JsonReturn addRecruit(@RequestBody Recruit recruit){

        if(StringUtils.isBlank(recruit.getAddr())&&StringUtils.isBlank(recruit.getResponsibility())&&StringUtils.isBlank(recruit.getName())){
            return new JsonReturn (503,"failure");

        }
        return new JsonReturn (200,"success",service.addRecruit(recruit));
    }
    //查看招聘
    @RequestMapping("/lookRecruit")
    public JsonReturn lookRecruit(Integer currPage,Integer pageSize, HttpServletRequest request){
        PageInfo<Recruit> pageRecruit=service.RecruitPage(currPage,pageSize);
        if(pageRecruit!=null){
            return new JsonReturn (200,"success",pageRecruit);
        }
        return new JsonReturn (503,"failure");
    }

    //获取图片验证码
    @RequestMapping("/getImgCode")
    public void getImgCode(HttpSession session,
                           HttpServletResponse resp){
        service.getCaptcha(session,resp);
    }
    //图片上传
    @RequestMapping("/imgFile")
    //接口 id指的是录入数据库信息后返回的主键id
    public Object imgFile(HttpServletRequest request) throws FileNotFoundException {
        return  service.upImgFile(request);
    }
    //咨询添加
    @RequestMapping("/addConsult")
    @ResponseBody
    public JsonReturn addConsult(@RequestBody Consult consult){
        if(StringUtils.isBlank(consult.getTitle())&&StringUtils.isBlank(consult.getImg())&&StringUtils.isBlank(consult.getContent())){
            return new JsonReturn (503,"failure");

        }
        return new JsonReturn (200,"success", service.addConsult(consult));
    }
    //咨询查询
    @RequestMapping("/lookConsult")
    public JsonReturn lookConsult(Integer currPage,Integer pageSize){
        PageInfo<Consult> pageConsult=service.lookConsult(currPage,pageSize);
        if(pageConsult!=null){
            return new JsonReturn (200,"success",pageConsult);
        }
        return new JsonReturn (503,"failure");
    }

    //查询招聘详情
    @RequestMapping("/singleRecruit")
    public JsonReturn singleRecruit(int no){
        Object RecruitNo=service.singleRecruit(no);
        if(RecruitNo!=null){
            return new JsonReturn (200,"success", RecruitNo);
        }
        return new JsonReturn (503,"failure");
    }

    //查询咨询详情
    @RequestMapping("/singleConsults")
    public JsonReturn singleConsults(int no){
        Object consultsNo=service.singleConsults(no);
        if(consultsNo!=null){
            return new JsonReturn (200,"success", service.singleConsults(no));
        }
        return new JsonReturn (503,"failure");
    }

    //修改招聘信息
    @RequestMapping("/upRecruit")
    public JsonReturn upRecruit(@RequestBody Recruit recruit){
        if(StringUtils.isBlank(recruit.getName())&&StringUtils.isBlank(recruit.getResponsibility())&&StringUtils.isBlank(recruit.getAddr())){
            return new JsonReturn (503,"failure");
        }
        return new JsonReturn (200,"success", service.upRecruit(recruit));
    }

    //修改咨询信息
    @RequestMapping("/upConsult")
    @ResponseBody
    public JsonReturn upConsult(@RequestBody Consult consult){
        if(StringUtils.isBlank(consult.getContent())&&StringUtils.isBlank(consult.getTitle())&&StringUtils.isBlank(consult.getImg())){
            return new JsonReturn (503,"failure");
        }
        return new JsonReturn (200,"success", service.upConsult(consult));
    }

    //招聘上线
    @RequestMapping("/consultCode")
    public JsonReturn consultCode(Integer no){
        Object consult=service.consultCode(no);
        if(consult!=null){
            return new JsonReturn (200,"success",consult);
        }
        return new JsonReturn (503,"failure");
    }

    //咨询上线
    @RequestMapping("/recruitCode")
    public JsonReturn recruitCode(Integer no){
        Object recruit=service.recruitCode(no);
        if(recruit!=null){
            return new JsonReturn (200,"success", recruit);
        }
        return new JsonReturn (503,"failure");
    }
    //招聘下线
    @RequestMapping("/recruitDown")
    public JsonReturn recruitDown (Integer no){
        Object down=service.recruitDown(no);
        if(down!=null){
            return new JsonReturn (200,"success", down);
        }
        return new JsonReturn (503,"failure");
    };
    //咨询下线
    @RequestMapping("/consultDown")
    public JsonReturn consultDown(Integer no){
        Object down=service.consultDown(no);
        if(down!=null){
            return new JsonReturn (200,"success", down);
        }
        return new JsonReturn (503,"failure");
    };
}
