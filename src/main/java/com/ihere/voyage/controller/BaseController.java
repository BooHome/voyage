package com.ihere.voyage.controller;

import com.ihere.voyage.annotation.Paging;
import com.ihere.voyage.annotation.RequestBoohome;
import com.ihere.voyage.entity.Mouse;
import com.ihere.voyage.entity.TestEntity;
import com.ihere.voyage.init.util.IntactCheckUtil;
import com.ihere.voyage.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fengshibo
 * @create 2018-07-04 11:41
 * @desc ${DESCRIPTION}
 **/
@Controller
public class BaseController {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);
    @Value("${value}")
    private String value;

    @RequestMapping("/1")
    @RequestBoohome
    @ResponseBody
    public String value() {
        logger.info("This is 自定义注解 message!");
        return value;
    }


    @RequestMapping("/2")
    @ResponseBody
    public Mouse test2() {
        logger.info("This is 自定义注解 message!");
        Mouse mouse=new Mouse();
        mouse.setName("NAME");
        mouse.setAge(1);
        mouse.setAddress("ADDRESS");
        mouse.setMoney(1.1);
        IntactCheckUtil.check(mouse);
        return mouse;
    }

    @RequestMapping("/3")
    @ResponseBody
    public Mouse test3() {
        logger.info("This is 自定义异常 message!");
        Mouse mouse=new Mouse();
        mouse.setName("NAME");
        IntactCheckUtil.check(mouse);
        return mouse;
    }

    @RequestMapping("/4")
    @ResponseBody
    public Mouse test4() {
        logger.info("This is 异常 message!");
       throw new RuntimeException("这是一个异常");
    }

    /**
     * https://developers.e.qq.com/oauth/authorize?client_id=1106942747&redirect_uri=http://30a033ac.ngrok.io/5&state=&scope=
     * @param authorization_code
     * @param state
     * @return
     */
    @RequestMapping("/5")
    @ResponseBody
    public String test5(String authorization_code,String state){
        String httpurl="https://api.e.qq.com/oauth/token" +
                "?client_id=1106942747" +
                "&client_secret=cFJ7jRdP1sCLsk54" +
                "&grant_type=authorization_code" +
                "&authorization_code=AUTHORIZATION_CODE" +
                "&redirect_uri=http://f8286eff.ngrok.io/5";
        httpurl = httpurl.replace("AUTHORIZATION_CODE", authorization_code);
        String str = HttpUtils.doGet(httpurl);
        logger.info("请求的URL值："+httpurl);
        logger.info("authorization_code值："+authorization_code);
        logger.info("access_token值："+str);
        /**
         * access_token值： {
         * 	"code": 0,
         * 	"message": "",
         * 	"data": {
         * 		"authorizer_info": {
         * 			"account_uin": 1296625836,
         * 			"account_id": 8172200,
         * 			"scope_list": ["ads_management", "user_actions", "audience_management", "ads_insights"]
         *                },
         * 		"access_token": "8219b6393d21bf3660a0f180423e1fa8",
         * 		"refresh_token": "f8a1021b70296064d61bb685d72e601d",
         * 		"access_token_expires_in": 86400,
         * 		"refresh_token_expires_in": 2592000    * 	}
         * }
         */
        return str;
    }
    @GetMapping("/6")
    public String testT(){
        return "/t_bo";
    }

    @RequestMapping(value="/6/post",method=RequestMethod.POST)
    @ResponseBody
    public Object testT6(@RequestBody TestEntity testEntity){
        return testEntity;
    }


    @GetMapping("/7/{num}")
    @Paging
    @ResponseBody
    public String test7(@PathVariable Integer num,Integer a,Integer b){
        return "7:"+num;
    }

    @GetMapping("/8")
    @Paging("com.ihere.voyage.entity.Mouse")
    @ResponseBody
    public Mouse test7(Mouse mouse){
        return mouse;
    }
}