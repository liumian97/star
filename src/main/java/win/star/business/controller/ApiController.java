package win.star.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import win.star.business.service.ApiService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liumian on 2016/11/19.
 */
@Controller
public class ApiController {

    @Autowired
    private ApiService apiService;

    @RequestMapping(value = "api/{apiId}",
            produces = "application/json;charset=UTF-8",
            method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String test(HttpServletRequest request,@PathVariable("apiId")String apiId){
        apiService.test(apiId);
        return apiId;
    }

}
