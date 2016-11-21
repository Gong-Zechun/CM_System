package com.cm.controller;

import com.cm.pojo.User;
import com.cm.service.IUserService;
import com.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理页面Controller
 * @author AllenGong
 * @version V1.0
 * @date 2016-10-9 11:16
 */
@Controller
@RequestMapping("user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserService userService;

    /**
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("selectById/{userId}")
    public String selectById(@PathVariable("userId")int userId, Model model) {
        try{
            User user = userService.selectById(userId);
            model.addAttribute("user", user);
            return "userManage";
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping("selectAllUser")
    public String selectAllUser(Model model) {
        try{
            List<User> userList = userService.selectAllUser();
            model.addAttribute("userList", userList);
            return "userManage";
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 更新用户信息
     */
    @RequestMapping("updateById")
    @ResponseBody
    public String updateById(int userId, String userName, int age, String email) {
        try{
            User user = new User();
            user.setId(userId);
            user.setAge(age);
            user.setUserName(userName);
            user.setEmail(email);
            int resultFlag = userService.updateById(user);
            return StringUtil.toJSONString(resultFlag);
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 返回用户信息到当前页面
     * @param userId
     * @return
     */
    @RequestMapping("getUserInfo")
    @ResponseBody
    public String getUserInfo(int userId) {
        try{
            User user = userService.selectById(userId);
            return StringUtil.toJSONString(user);
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteById(int userId) {
        try{
            int resultFlag = userService.deleteById(userId);
            return StringUtil.toJSONString(resultFlag);
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping("/addNewUser")
    @ResponseBody
    public String addNewUser(String userName, int age, String email) {
        try{
            User user = new User();
            user.setAge(age);
            user.setUserName(userName);
            user.setEmail(email);
            int resultFlag = userService.insertUser(user);
            return StringUtil.toJSONString(resultFlag);
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }

//    @RequestMapping("/byId")
//    public ModelAndView getUserById(Integer userId){
//        ModelAndView mv = new ModelAndView();
//        User user = userService.selectByPrimaryKey(userId);
//        mv.addObject("user", user);
//        mv.setViewName("message");
//        return mv;
//    }
//
//    @RequestMapping("/byId_1/{userId}")
//    public ModelAndView getUserById_1(@PathVariable("userId") Integer userId){
//        ModelAndView mv = new ModelAndView();
//        User user = userService.selectByPrimaryKey(userId);
//        mv.addObject("user", user);
//        mv.setViewName("message");
//        return mv;
//    }
}
