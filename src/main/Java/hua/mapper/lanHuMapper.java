package hua.mapper;
import hua.model.Consult;
import hua.model.Recruit;
import hua.model.Resume;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;
/**
 * @author mxz
 * @Date 2020/7/30
 */
public interface lanHuMapper {
    @Insert("INSERT INTO `mysql`.`resume`" +
            "(`name`, `tel`, `email`, `intro`, `time`,`title`,`addr`,`code`)" +
            " VALUES (#{name}, #{tel}, #{email}, #{intro}, now(),#{title},#{addr},1)")
    public int addResume(Resume resume);

    @Insert("INSERT INTO `mysql`.`recruit`(`name`, `addr`, `responsibility`, `type1`, `type2`,`date`,`state`)" +
            " VALUES (#{name}, #{addr}, #{responsibility}, #{type1}, #{type2},now(),1)")
    public int addRecruit(Recruit recruit);
//    @Select("SELECT * FROM `mysql`.`recruit` LIMIT #{start},#{size}")
//    public List<Recruit> lookRecruit(@Param("start") int start,
//                                     @Param("size")int size);
    @Select("SELECT * FROM `mysql`.`recruit` order by date desc ")
    public List<Recruit> lookRecruit();

    @Select("SELECT * FROM `mysql`.`resume` order by time desc ")
    public List<Resume> lookResume();
    //插入咨询
    @Insert("INSERT INTO `mysql`.`consult`(`title`, `content`, `img`,`time`,`state`)" +
            " VALUES (#{title}, #{content}, #{img},now(),1)")
    public int addConsult(Consult consult);
    //查询咨询
    @Select("SELECT * FROM `mysql`.`consult` WHERE state=1 order by time desc")
    public List<Consult> lookConsult();

    @Select("SELECT * FROM `mysql`.`recruit` WHERE no=#{no} ")
    public Recruit singleRecruit(int no);

    @Select("SELECT * FROM `mysql`.`consult` WHERE no=#{no} ")
    public Consult singleConsults(int no);

    @Update("UPDATE  `mysql`.`recruit` set name=#{name},addr=#{addr},responsibility=#{responsibility},type1=#{type1},type2=#{type2} where no=#{no}")
    public int upRecruit(Recruit recruit);

    @Update("UPDATE  `mysql`.`Consult` set title=#{title},content=#{content},img=#{img} where no=#{no}")
    public int upConsult(Consult consult);

    @Update("UPDATE  `mysql`.`recruit` set state=1 where no=#{no}")
    public int recruitCode(int no);

    @Update(" UPDATE  `mysql`.`Consult` set state=1  where no=#{no}")
    public int consultCode(int no);

    @Update("UPDATE  `mysql`.`recruit` set state=0 where no=#{no}")
    public int recruitDown (int no);

    @Update(" UPDATE  `mysql`.`Consult` set state=0  where no=#{no}")
    public int consultDown (int no);
}
