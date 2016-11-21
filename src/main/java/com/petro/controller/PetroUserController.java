package com.petro.controller;

import com.common.HttpRequest;
import com.petro.service.IAdminUserService;
import com.util.DateUtil;
import com.util.StringUtil;
import com.petro.pojo.PetroUser;
import com.petro.service.IPetroUserService;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Petro系统-用户管理页面Controller
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-9 13:39
 */
@Controller
@RequestMapping("petro")
public class PetroUserController {

    private static final Logger log = LoggerFactory.getLogger(PetroUserController.class);

    @Resource
    private IPetroUserService petroUserService;
    @Resource
    private IAdminUserService adminUserService;

    /**
     * Petro系统-用户登录login页面
     * @return
     */
    @RequestMapping("petroPage")
    public String petroPage() {
        return "petrochemical";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("main")
    @ResponseBody
    public String selectAllPetroUser(String userName, String password, HttpServletRequest request) {
        try{
            Map<String, String> userMap = new HashMap<String, String>();
            userMap.put("userName", userName);
            userMap.put("password", password);
            request.getSession().setAttribute("userName", userName);
            int resultFlag = this.adminUserService.selectAdminUser(userMap);
            return StringUtil.toJSONString(resultFlag);
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 返回petroUserManage.vm页面
     * @param model model添加user数据
     * @return
     */
    @RequestMapping("petroUserManage")
    public String index(Model model) {
        try{
            List<PetroUser> petroUserList = petroUserService.selectAllPetroUser();
            model.addAttribute("userList", petroUserList);
            return "petroUserManage";
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 根据用户id更新相应用户信息
     * @param userId 用户id
     * @param companyName 公司全称
     * @param contactName 联系人
     * @param telNum 联系电话
     * @param wxNum 微信号
     * @param cityName 加盟城市
     * @return
     */
    @RequestMapping("updatePetroUserInfoById")
    @ResponseBody
    public String updateById(int userId, String companyName, String contactName, String telNum, String wxNum, String cityName) {
        try{
            PetroUser petroUser = new PetroUser();
            petroUser.setId(userId);
            petroUser.setCompanyName(companyName);
            petroUser.setContactName(contactName);
            petroUser.setTelNum(telNum);
            petroUser.setWxNum(wxNum);
            petroUser.setCityName(cityName);
            int resultFlag = petroUserService.updatePetroUserById(petroUser);
            return StringUtil.toJSONString(resultFlag);
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 添加新用户
     * @param companyName 公司全称
     * @param contactName 联系人
     * @param telNum 联系电话
     * @param wxNum 微信号
     * @param cityName 加盟城市
     * @return 数据库执行的返回结果
     */
    @RequestMapping("addNewPetroUser")
    @ResponseBody
    public String addNewPetroUser(String companyName, String contactName, String telNum, String wxNum, String cityName) {
        try{
            PetroUser petroUser = new PetroUser();
            petroUser.setCompanyName(companyName);
            petroUser.setContactName(contactName);
            petroUser.setTelNum(telNum);
            petroUser.setWxNum(wxNum);
            petroUser.setCityName(cityName);
            int resultflag = petroUserService.insertPetroUser(petroUser);
            return StringUtil.toJSONString(resultflag);
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @RequestMapping("deletePetroUserById")
    @ResponseBody
    public String deleteById(int userId) {
        try{
            int resultflag = petroUserService.deleteById(userId);
            return StringUtil.toJSONString(resultflag);
        }catch(Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
