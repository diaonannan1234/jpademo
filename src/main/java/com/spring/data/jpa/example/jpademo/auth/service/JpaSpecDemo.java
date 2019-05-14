package com.spring.data.jpa.example.jpademo.auth.service;

import com.spring.data.jpa.example.jpademo.auth.entityone.Department;
import com.spring.data.jpa.example.jpademo.auth.entityone.Employee;
import com.spring.data.jpa.example.jpademo.auth.entityone.Project;
import com.spring.data.jpa.example.jpademo.auth.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.Collection;
import java.util.List;

@Service
public class JpaSpecDemo implements JpaSpecDemoI{

    @Autowired private EmployeeRepository employeeRepository;

    public List<Employee> getEmployee(String name,long salary,Specification<Employee> se){

        Specification<Employee> spec = (root,query,cb) -> cb.equal(root.get("salary").as(long.class),salary);
        return employeeRepository.findAll(spec.and(getSpec(name)).or(se));
    }

    private Specification<Employee> getSpec(String name){
        return (root,query,cb) ->{
           return cb.equal(root.get("name").as(String.class),name);
        };
    }

    public List<Employee> getEmployee(String name){
        return employeeRepository.findAll((root,query,cb) -> {
            CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
            Subquery<String> sq = c.subquery(String.class);
            Root<Department> dept = sq.from(Department.class);
            Join<Employee,Project> project =
                    dept.join("employees").join("projects");
            sq.select(dept.<String>get("id"))
                    .distinct(true)
                    .where(cb.like(project.<String>get("name"), "Rel%"));
            return  cb.in(root.get("id")).value(sq);
        });

    }

    public List<Employee>  getEmployeeJoin(){
        Specification<Employee> spec = (root,query,cb)->{
            Join<Employee,Project> dept = root.join("projects");
            return cb.equal(dept.get("name"),"Release1");
        };
        return employeeRepository.findAll(spec);
    }
}
