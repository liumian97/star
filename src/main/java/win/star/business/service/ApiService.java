package win.star.business.service;

import org.springframework.stereotype.Service;

/**
 * Created by liumian on 2016/11/19.
 */
@Service
public class ApiService {

    public void test(String s) {
        System.out.println("TestService Hello..." + s);
        try {
            Thread.currentThread().sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
