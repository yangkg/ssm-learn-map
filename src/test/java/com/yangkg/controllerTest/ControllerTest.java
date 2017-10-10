//package com.yangkg.controllerTest;
//
//import com.yangkg.ssm.module.user.controller.SysUserController;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
//
///**
// * 测试Controller
// *
// * @author：Kyle.yangkg
// * @create：2017-06-28 下午 4:51
// * ©copyright：www.aisino.com
// */
//
//@RunWith(SpringJUnit4ClassRunner.class)  //此处调用Spring单元测试类
//@WebAppConfiguration    //调用javaWEB的组件，比如自动注入ServletContext Bean等等
////加载Spring配置文件
//@ContextConfiguration(locations = {"classpath:spring/applicationContext*.xml", "classpath:spring/spring-mvc.xml"})
//
//public class ControllerTest   {
//    @Test
//    public void testSysUserController() throws Exception {
//        SysUserController userController = new SysUserController();
//        MockMvc mockMvc = standaloneSetup(userController).build();
//        mockMvc.perform(get("/sysUserController/showUserToJspById/1")).andExpect(view().name("showUser"));
//        System.out.println("Print Info-----------Kyle.yangkg-----------值=" + mockMvc + "," + "当前类=ControllerTest.testSysUserController()");
//        System.out.println("Print Info-----------Kyle.yangkg-----------userController值=" + userController + "," + "当前类=ControllerTest.testSysUserController()");
//    }
//}
