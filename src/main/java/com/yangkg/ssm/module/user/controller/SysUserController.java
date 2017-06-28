package com.yangkg.ssm.module.user.controller;

import com.yangkg.ssm.module.user.pojo.SysUser;
import com.yangkg.ssm.module.user.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/sysUserController")
public class SysUserController {

    private static final Logger LOG = LoggerFactory.getLogger(SysUserController.class);

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/showUserToJspById/{userId}")//因为参数名称userId和占位符的名称相同，因此可以去掉@PathVariable("userId")中的value属性（userID）
    public String showUser(Model model, @PathVariable("userId") Long userId) {
        SysUser user = this.sysUserService.getById(userId);
        //model.addAttribute("user", user);
        model.addAttribute(user);
        return "showUser";
    }

    @RequestMapping("/showUserToJSONById/{userId}")
    @ResponseBody
    public SysUser showUser(@PathVariable("userId") Long userId) {
        SysUser user = sysUserService.getById(userId);
        return user;
    }


    @RequestMapping("/test-logback")
    @ResponseBody
    public Date testLogback() {
        LOG.trace("-----------------------------------trace");
        LOG.debug("-----------------------------------debug");
        LOG.info("-----------------------------------info");
        LOG.warn("-----------------------------------warn");
        LOG.error("-----------------------------------error");
        return new Date();
    }


}
