package com.homework6.controller;

import com.homework6.bean.UserEntity;
import com.homework6.dao.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * controller -- UserController
 */
@Controller
@RequestMapping(value = "/")
public class UserController {
    //通过请求响应传递参数
    @RequestMapping(value = "/toLogin")
    public ModelAndView toLogin(HttpServletRequest arg0,
                                HttpServletResponse arg1) throws Exception {
        //跳转到视图
        return new ModelAndView("index.jsp");
    }

    @RequestMapping(value = "/toRegister")
    public ModelAndView toRegister(HttpServletRequest arg0,
                                   HttpServletResponse arg1) throws Exception {
        return new ModelAndView("register.jsp");
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        String username = arg0.getParameter("username");
        String password = arg0.getParameter("password");
        UserDAO userDAO = new UserDAO();
        boolean isLogin = userDAO.login(username, password);
        if (isLogin) {
            System.out.println("== Login Success !");
            return new ModelAndView("success.jsp");
        } else {
            System.out.println("== Login Failure !");
            return new ModelAndView("defeat.jsp");
        }
    }

    @RequestMapping(value = "/register")
    public ModelAndView register(HttpServletRequest arg0,
                                 HttpServletResponse arg1) throws Exception {
        String username = arg0.getParameter("username");
        String password = arg0.getParameter("password");
        UserEntity userEntity = new UserEntity(username, password);
        UserDAO userDAO = new UserDAO();
        boolean isRegister = userDAO.insertUser(userEntity);
        if (isRegister)
            System.out.println("== Register Success !");
        else
            System.out.println("== Register Failure !");
        return new ModelAndView("index.jsp");
    }

    @RequestMapping(value = "/userUpdate")
    public ModelAndView userUpdate(HttpServletRequest arg0,
                                   HttpServletResponse arg1) throws Exception {
        int userid = Integer.parseInt(arg0.getParameter("userid"));
        String username = arg0.getParameter("username");
        String password = arg0.getParameter("password");
        UserEntity userEntity = new UserEntity(userid, username, password);
        UserDAO userDAO = new UserDAO();
        boolean isUpdateUser = userDAO.updateUser(userEntity);
        if (isUpdateUser)
            System.out.println("== UpdateUser Success !");
        else
            System.out.println("== UpdateUser Failure !");
        return new ModelAndView("success.jsp");
    }

    //通过 url 传递参数
    @RequestMapping(value = "/userDelete/{userid}")
    public ModelAndView userDelete(@PathVariable("userid") Integer userid) {
        UserDAO userDAO = new UserDAO();
        boolean isDeleteUser = userDAO.deleteUser(userid);
        if (isDeleteUser)
            System.out.println("== DeleteUser Success !");
        else
            System.out.println("== DeleteUser Failure !");
        return new ModelAndView("success.jsp");
    }
}