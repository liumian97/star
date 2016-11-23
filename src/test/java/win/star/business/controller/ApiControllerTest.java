package win.star.business.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liumian on 2016/11/19.
 */
public class ApiControllerTest {

    @Test
    public void test(){
        try {
            errorMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }

    public void errorMethod() throws Exception {
        throw new Exception();
    }

}
