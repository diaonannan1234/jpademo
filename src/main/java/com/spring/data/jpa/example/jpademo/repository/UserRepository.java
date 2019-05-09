package com.spring.data.jpa.example.jpademo.repository;

import com.spring.data.jpa.example.jpademo.entity.EnableEnum;
import com.spring.data.jpa.example.jpademo.vo.UserVO;
import com.spring.data.jpa.example.jpademo.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {

    @CacheEvict(value = "disname",key = "#p0.name")
    <S extends User> S save(S entity);

    @Cacheable("disname")
    @Query("select u from User u where u.name = :name")
    List<User> findUserByNameCache(@Param("name") String name);

    //user 不能为空
    @Query("select new com.spring.data.jpa.example.jpademo.vo.UserVO(u.id,u.name,u.age)  from  User u where u.name = :#{#user.name} ")
    UserVO findUser(@Param("user") User user);

    @Query("select new com.spring.data.jpa.example.jpademo.vo.UserVO(u.id,u.name,u.age)  from User u where u.age = :#{#user == null?0:#user.age}")
    List<UserVO> findUsersByAge(@Param("user") User user);

    @Query("select u from User u where u.enable = :enable")
     List<User> findUserByEnadleBoolean(@Param("enable")Boolean enable);

    @Query("select u from User u where u.enableEnum = :enableEnum")
    List<User> findUserByEnableEnum(@Param("enableEnum")EnableEnum enableEnum);

    @Transactional
    @Modifying
    @Query("update User u set u.age = :#{#user.age} where u.id = :#{#user.id}")
    int updateUserAgeModifying(@Param("user")User user);

    //批量更新用户状态为入参值
    @Transactional
    @Modifying
    @Query("update User u set u.enableEnum = :enableEnum ")
    int updateUserEnableModifying(@Param("enableEnum")EnableEnum enableEnum);


}
