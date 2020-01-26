package com.wenwl.my.shop.web.admin.service.test;

import com.wenwl.my.shop.domain.entity.TbUser;
import com.wenwl.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @author wenwl
 * @className TbUserServiceTest
 * @data 2020/1/17
 * @vserion 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {

    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll(){
        List<TbUser> tbUsers = tbUserService.selectAll();
        tbUsers.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        TbUser tbUser = new TbUser();
        tbUser.setEmail("admin@163.com");
        tbUser.setPassword("admin");
        tbUser.setPhone("15888888888");
        tbUser.setUsername("wenwl");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUserService.insert(tbUser);

    }

    @Test
    public void testDelete(){
        TbUser tbUser = new TbUser();
        tbUser.setId(39L);
        tbUserService.delete(tbUser);
    }

    @Test
    public void testGetById() {
        TbUser tbUser = tbUserService.getById(37L);
        System.out.println(tbUser);
    }

    @Test
    public void testSelectByName() {
        List<TbUser> tbUsers = tbUserService.selectByName("niu");
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }


}