# jpademo
 Spring Data JPA Demo
 
# UserRepository
- 部分修改。
   -     @Modifying
         @Query("update User u set u.age = :#{#user.age} where u.id = :#{#user.id}")
- 批量修改、 有条件修改
    -     @Modifying
          @Query("update User u set u.enableEnum = :enableEnum ")
        
- 批量删除、有条件删除。

# JdbcRepository
- 使有JDBC模板。

# JpaSpecDemoI

- 多个Specification联接  JpaSpecDemoI
- 演示子查询 JpaSpecDemoI
- Specification  Join JpaSpecDemoI

# UserRepository

- 缓存 CacheManager 
    -      @CacheEvict(value = "disname",key = "#p0.name")
           @Cacheable("disname")@Query("select u from User u where u.name = :name")
- 对象入参
    -      @Query("select new com.spring.data.jpa.example.jpademo.vo.UserVO(u.id,u.name,u.age)  
                   from User u where u.age = :#{#user == null?0:#user.age}")
- Boolean入参
   -       @Query("select u from User u where u.enable = :enable")
-  Emum 入参
   -       @Query("select u from User u where u.enableEnum = :enableEnum")
   
# DepartmentRepository

- 检索出有两个员工的部门
   -      @Query("select d from Department d where size(d.employees) = 2 ")
- 部门的平均薪水
   -      @Query("select d.name,avg(e.salary) from Department d join d.employees e group by d.name")
   
# EmployeeRepository
- 在结果集使有Map
  -       @Query("select e.name ,value(e.phones) from Employee e")
  -       @Query("select e.name,key(e.phones) ,value(e.phones) from Employee e")
- 得到员工姓名的集合
  -       @Query("select e.name from Employee e")
- 得到所有员工关联的部门集合
   -      @Query("select distinct e.department from Employee e ")
-  检索出员工姓名和薪水，结果集用数组表示。
   -       @Query("select e.name,e.salary from Employee e ")
-  检索出员工姓名和薪水，结果集用EmployeeVO对象封装
   -       @Query("select new com.spring.data.jpa.example.jpademo.auth.vo.EmployeeVO" +
                         "(e.name,e.salary) from Employee e ")
-  得到所有是经理的员工。
   -       @Query("select e from Employee e where e.directs is not EMPTY ")
-  使用 join fetch 
   -       @Query("select e from Employee e join fetch e.address")
-  薪水在四万到四万五的所有员工
   -        @Query("select e from Employee e where e.salary between 40000 and 45000")
- 薪水最高的员工列表
  -         @Query("select e from Employee e where e.salary = " +
                  "(select max (emp.salary) from Employee emp)")
- 只有一个电话号码的员工
  -          @Query("select e from Employee e 
                        where exists (select p from Phone p where p.employee = e and p.type = 'Cell')")
- 检索出所有下级员工的薪水均比其高的经理
  -          @Query("select e from Employee e where e.directs is not EMPTY " +
                 "and e.salary < all " +
                 "(select d.salary from e.directs d)")
# ProjectRepository 
- 检索出所有至少存在一个员工的项目.employees是一个对象
   -          @Query("select p from Project p where p.employees is not empty ")
- 检索出设计类型和质量类型的项目
  -           @Query("select p from Project p where type(p) = DesignProject or type(p) = QualityProject")
#  SpecDemoOne
-  使用 EntityManager 完成查询、修改、删除实体。

#  RoleRepository
- 比对入参和角色中的用户列表，如入参在用户列表中则将角色放入结果集。
   -           @Query("select r from Role r where :user member of r.users")
-   得到所有拥有角色的用户
   -           @Query("select distinct uu from Role r ,in(r.users) uu")
-   得到所有拥有角色的用户  返回值分页
   -           @Query("select distinct  uu from Role r ,in(r.users) uu")
-  找到角色中用户组中用户年龄有一个满足条件（小于入参）
   -           @Query("select r from Role r where :age > any (select u.age from r.users u)")
-  找到角色中用户组中所有用户年龄满足条件（小于入参）
   -            @Query("select r from Role r where :age > ALL (select u.age from r.users u)")
-  找到角色中用户组中用户年龄大于入参的所有用户，查出的用户存在的角色出现在结果集中。
   -             @Query("select r from Role r where  exists (select u.age from r.users u where u.age > :age)")