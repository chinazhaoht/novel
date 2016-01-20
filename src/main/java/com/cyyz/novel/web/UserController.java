package com.cyyz.novel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String login(HttpServletRequest request,HttpServletResponse response){
        //String id = request.getRequestedSessionId();
        System.out.println(request);
        Cookie[] cookies = request.getCookies();
        request.getSession().getId();
        String sessionId= "sessionId";
        int id ;
        for (Cookie cookie : cookies) {
            if(sessionId.equals(cookie.getName())){
                id = Integer.parseInt(cookie.getValue());
            }
        }
        return "hello";
    }
    public static void main(String[] args){

    }

}
