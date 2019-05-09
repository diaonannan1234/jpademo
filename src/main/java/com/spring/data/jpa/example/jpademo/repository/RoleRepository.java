package com.spring.data.jpa.example.jpademo.repository;


import com.spring.data.jpa.example.jpademo.entity.Role;
import com.spring.data.jpa.example.jpademo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface RoleRepository extends JpaRepository<Role,String>{
    //比对入参和角色中的用户列表，如入参在用户列表中则将角色放入结果集。
    @Query("select r from Role r where :user member of r.users")
    List<Role> findRoleByUsersMember(@Param("user")User user);

    //得到所有拥有角色的用户
    @Query("select distinct uu from Role r ,in(r.users) uu")
    List<User> findUserInRole();

    //得到所有拥有角色的用户  返回值分页
    @Query("select distinct  uu from Role r ,in(r.users) uu")
    Page<User> findUserInRolePage(Pageable pageable);

    //找到角色中用户组中用户年龄有一个满足条件（小于入参）
    @Query("select r from Role r where :age > any (select u.age from r.users u)")
    List<Role> findRoleByAgeAny(@Param("age")Integer age);

    //找到角色中用户组中所有用户年龄满足条件（小于入参）
    @Query("select r from Role r where :age > ALL (select u.age from r.users u)")
    List<Role> findRoleByAgeAll(@Param("age")Integer age);

    //找到角色中用户组中用户年龄大于入参的所有用户，查出的用户存在的角色出现在结果集中。
    @Query("select r from Role r where  exists (select u.age from r.users u where u.age > :age)")
    List<Role> findRoleByAgeExit(@Param("age")Integer age);





    //@EntityGraph(value="account.all",type=EntityGraphType.FETCH)
    //public Account findOneByUsername(String username);
    //调用findOneByUsername即能够查询改Account，并且会同时查询出student、staff、roles、roles.pages。查看控制台的话会发现就生成一条sql语句，即只查询一次。

    //@Temporal    @Temporal(TemporalType.TIMESTAMP)  @Temporal(TemporalType.DATE)

    //@Lock

    //@Modifying

    //QueryHints


}
