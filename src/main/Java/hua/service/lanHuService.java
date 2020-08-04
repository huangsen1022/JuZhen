package hua.service;

import com.github.pagehelper.PageInfo;
import hua.model.Consult;
import hua.model.Recruit;
import hua.model.Resume;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

/**
 * @author mxz
 * @Date 2020/7/30
 */
public interface lanHuService {
    //申请职位
    public Object addResume(Resume resume, HttpSession session);
    //查看职位
    public PageInfo lookResume(Integer currPage,Integer pageSize);
    //发布招聘
    public Object addRecruit(Recruit recruit);
    //查看招聘
   // public Object lookRecruit(int pageNo);
    //修改招聘
    public Object upRecruit(Recruit recruit);
    //验证码
    public void getCaptcha(HttpSession httpSession,
                           HttpServletResponse resp);
    //文件上传
    public Object upFile(HttpServletRequest request) throws FileNotFoundException;
    //图片上传
    public Object upImgFile(HttpServletRequest request) throws FileNotFoundException;
    //咨询
    public Object addConsult(Consult consult);
    //咨询查询
    public PageInfo lookConsult(Integer currPage,Integer pageSize);

    public Object singleRecruit(int no);

    public Object singleConsults(int no);

    public PageInfo RecruitPage(Integer currPage, Integer pageSize);

   //public Object upRecruit(int no);
    public Object upConsult(Consult consult);

    public Object consultCode(int no);

    public Object recruitCode(int no);

    public Object recruitDown (int no);

    public Object consultDown (int no);
}
