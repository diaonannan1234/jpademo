package com.spring.data.jpa.example.jpademo.auth.repository;

import com.spring.data.jpa.example.jpademo.auth.entityone.Department;
import com.spring.data.jpa.example.jpademo.auth.entityone.Employee;
import com.spring.data.jpa.example.jpademo.auth.vo.EmployeeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,String> ,JpaSpecificationExecutor<Employee> {

    //得到员工姓名的集合
    @Query("select e.name from Employee e")
    List<String> getEmployeeName();

    //得到所有员工关联的部门集合
    @Query("select distinct e.department from Employee e ")
    List<Department>  getEmployeeInDepartment();

    @Query("select e.name,e.salary from Employee e ")
    List<Object[]> getEmployeeNameSalary();


    @Query("select new com.spring.data.jpa.example.jpademo.auth.vo.EmployeeVO" +
            "(e.name,e.salary) from Employee e ")
    List<EmployeeVO> getEmployeeNameSalaryVO();

    //得到所有是经理的员工。
    @Query("select e from Employee e where e.directs is not EMPTY ")
    List<Employee> getManagerAll();
//
//    @Query("select distinct e.name, value( p) from Employee e, in( e.phones) p")
//   Map getE();

    @Query("select e from Employee e join fetch e.address")
    List<Employee> getEmployFetch();

    //薪水在四万到四万五的所有员工
    @Query("select e from Employee e where e.salary between 40000 and 45000")
    List<Employee> getEmployeeBetween();

    //薪水最高的员工列表
    @Query("select e from Employee e where e.salary = " +
            "(select max (emp.salary) from Employee emp)")
    List<Employee> getEmployeeMaxSalary();

    /**
    *只有一个电话号码的员工
     *
     */
    @Query("select e from Employee e where exists (select p from Phone p where p.employee = e and p.type = 'Cell')")
    List<Employee> getEmployeeExists();

    //检索出所有下级员工的薪水均比其高的经理
    @Query("select e from Employee e where e.directs is not EMPTY " +
            "and e.salary < all " +
            "(select d.salary from e.directs d)")
    List<Employee> getEmployeeAllAnySome();
}
