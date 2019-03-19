package cn.et.controller;

import cn.et.dao.StudentEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class HibernateTest {
    //获取加载配置管理类 不给参数就默认加载hibernate.cfg.xml文件，
    private static final Configuration config = new Configuration().configure().addClass(StudentEntity.class);
    //创建Session工厂对象
    private static final SessionFactory sessionFactory = config.buildSessionFactory();
    //得到Session对象
    private static final Session session = sessionFactory.openSession();

    public static void main(String[] args) {
//        getQBCStudent();     //查询
//        insertStudent();  //添加
//        deleteStudent();  //删除
//        updateStudent();  //修改
        getHQLStudent();

    }

    /**
     * 添加
     */
    public static void insertStudent(){
        //使用Hibernate操作数据库，都要开启事务,得到事务对象
        session.beginTransaction();

        StudentEntity studentEntity =new StudentEntity();
        studentEntity.setSid("aa");
        studentEntity.setGid("bb");
        studentEntity.setSage("30");
        studentEntity.setSname("kkk");
        studentEntity.setSsex("女");

        //调用添加的方法
        session.save(studentEntity);

        // 事务提交
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    /**
     * 删除
     */
    public static void deleteStudent(){
        // 开启事务
        session.beginTransaction();
        StudentEntity studentEntity =new StudentEntity();
        studentEntity.setSid("aa");

        //调用删除的方法
        session.delete(studentEntity);

        // 事务提交
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    /**
     * 修改
     */
    public static void updateStudent(){
        // 开启事务
        session.beginTransaction();

        StudentEntity studentEntity =new StudentEntity();
        studentEntity.setSid("2");
        studentEntity.setSage("200");

        //调用修改的方法
        session.update(studentEntity);

        // 事务提交
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    /**
     * QBC查询
     */
    public static void getQBCStudent(){
        //创建关于StudentEntity对象的criteria对象
        Criteria criteria = session.createCriteria(StudentEntity.class);

        //添加条件
        criteria.add(Restrictions.eq("ssex", "男"));
        criteria.add(Restrictions.like("sname","%小%"));

        //查询全部数据
        List<StudentEntity> list = criteria.list();

        for (StudentEntity student : list){
            System.out.println(student.getSid()+"=="+student.getSname()+"=="+student.getGid()+"=="+student.getSage()+"=="+student.getSsex());
        }
    }

    /**
     * HQL查询
     */
    public static void getHQLStudent(){
        Query query =session.createQuery("from StudentEntity where sname=?");
        query.setParameter(0,"张三");
        List<StudentEntity> list = query.list();
        for (StudentEntity student : list){
            System.out.println(student.getSid()+"=="+student.getSname()+"=="+student.getGid()+"=="+student.getSage()+"=="+student.getSsex());
        }
    }
}
