package com.spring.data.jpa.example.jpademo.repository;

import com.spring.data.jpa.example.jpademo.entity.EnableEnum;
import com.spring.data.jpa.example.jpademo.entity.Role;
import com.spring.data.jpa.example.jpademo.entity.User;
import com.spring.data.jpa.example.jpademo.vo.UserVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.Assert.*;

public class UserRepositoryTest extends AbstractTest{

    @Autowired private UserRepository userRepository;

    @Autowired private RoleRepository roleRepository;

    @Autowired private CacheManager cacheManager;

    //缓存
    @Test
    public void testCacheManager(){
        User u = new User();
        u.setName("chenwei");
        u.setAge(20);
        User ur = userRepository.save(u);
        assertEquals(u,ur);
        userRepository.findUserByNameCache("chenwei");
         Cache cache = cacheManager.getCache("disname");
        // assertEquals(cache.get("chenwei").get(),u);
    }

    @Test
    public void testFindRoleByUsersMember(){
        User u = new User();
        u.setName("duwei0");
        u.setAge(2);
        User u1 = userRepository.findOne(Example.of(u)).orElse(null);
        List<Role> roles = roleRepository.findRoleByUsersMember(u1);
        assertTrue(!roles.isEmpty());
    }

    @Test
    public void testRolePage(){
        Page<User> maps = roleRepository.findUserInRolePage(PageRequest.of(0,3));
        assertNotNull(maps);
        assertTrue(maps.getTotalElements() > 0);
    }

    @Test
    public void testRoleByAgeAny(){
        List<Role> roleAll = roleRepository.findAll();
        List<Role> roles = roleRepository.findRoleByAgeAny(10);
        assertNotNull(roles);
        assertTrue(!roles.isEmpty( ));
    }

    @Test
    public void testRoleByAgeExit(){
        List<Role> roles = roleRepository.findRoleByAgeExit(8);
        assertNotNull(roles);
        assertTrue(!roles.isEmpty( ));
    }
   @Test
   public void testRoleFindUserInRole(){
       List<User> users = roleRepository.findUserInRole();
       assertTrue(!users.isEmpty());
   }

   @Test
   public void testFindUserByEnadleBoolean(){
       List<User> users = userRepository.findUserByEnadleBoolean(true);
       assertTrue(users.size() == 1);
   }

   @Test
   public void testFindUserByEnableEnum(){

       List<User> users1 = userRepository.findUserByEnableEnum(EnableEnum.ENABLE);
       assertTrue(!users1.isEmpty());
       userRepository.updateUserEnableModifying(EnableEnum.UNENABLE);
       List<User> users = userRepository.findUserByEnableEnum(EnableEnum.ENABLE);
       assertTrue(users.isEmpty());
   }


    @Test
    public void testFindUser(){
          UserVO user1 =  userRepository.findUser(new User());
          assertNull(user1);
    }

    //1 找到包含用户u1的所有角色
    // 2 移除角色中包含的用户u1
    // 3 删除用户u1
    @Test
    public void testDeleteUserAllDelete(){
        User u = new User();
        u.setName("duwei0");
        u.setAge(2);
        User u1 = userRepository.findOne(Example.of(u)).orElse(null);

        List<Role> roles = roleRepository.findRoleByUsersMember(u1);
        assertTrue(!roles.isEmpty());
        for (Role r : roles){
            r.removeUser(u1);
        }
        roleRepository.saveAll(roles);
        userRepository.delete(u1);
        User u2 = userRepository.findOne(Example.of(u)).orElse(null);
        assertNull(u2);
    }

    @Test
    public void testFindUsersByAge(){
        List<UserVO> u1 = userRepository.findUsersByAge(null);
        assertTrue(u1.isEmpty());
        User u = new User();
        u.setAge(20);
        List<UserVO> u2 = userRepository.findUsersByAge(u);
        assertTrue(!u2.isEmpty());
    }





}