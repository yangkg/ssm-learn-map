//package com.yangkg.ssm;
//
//
//import com.yangkg.ssm.module.user.pojo.SysUser;
//import com.yangkg.ssm.module.user.service.SysUserService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.annotation.Resource;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
//public class SSMTest {
//
//
//	@Resource
//	private SysUserService sysUserService;
//
//	@Test
//	public void test1() {
//		SysUser sysUser = sysUserService.getById(1L);
//		System.out.println("--------------------------------" + sysUser.toString());
//        System.out.println("Print Info-----------Kyle.yangkg-----------sysUser值=" + sysUser + "," + "当前类=SSMTest.test1()");
//    }
//}
