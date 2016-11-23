package win.star.business.controller;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import win.star.business.dao.UserDao;
import win.star.business.po.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew  on 2016/11/23.
 */
public class TestUser {
    private static UserDao userDaoImpl;
    private static ClassPathXmlApplicationContext app;
    private static String collectionName;

    @BeforeClass
    public static void initSpring() {
        try {
            System.out.println(System.getProperty("user.dir"));
            app = new ClassPathXmlApplicationContext(new String[]{"classpath:conf\\spring-mvc.xml","classpath:conf\\spring.xml"});
            userDaoImpl = (UserDao) app.getBean("userDaoImpl");
            collectionName = "users";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {

        //添加一百个user
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setId("" + i);
            user.setAge(i);
            user.setName("zcy" + i);
            user.setPassword("zcy" + i);
            userDaoImpl.insert(user, collectionName);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("maxAge", 50);
        List<User> list = userDaoImpl.findAll(params, collectionName);
        System.out.println("user.count()==" + list.size());
    }
}
