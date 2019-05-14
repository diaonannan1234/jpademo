package com.spring.data.jpa.example.jpademo.auth.service;

import com.spring.data.jpa.example.jpademo.auth.entityone.*;
import com.spring.data.jpa.example.jpademo.auth.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("specDemoOne")
public class SpecDemoOne implements SpecDemo{


    @PersistenceContext()
    private EntityManager em;

    @Autowired
    private  EmployeeRepository employeeRepository;

    public List<Employee> findEmployee(String name, String deptName,
                                       String projectName, String city){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp);
        c.distinct(true);
        Join<Employee,Project> project = emp.join("projects", JoinType.LEFT);

        List<Predicate> criteria = new ArrayList<Predicate>();
        if (name != null) {
            ParameterExpression<String> p = cb.parameter(String.class, "name");
            criteria.add(cb.equal(emp.get("name"), p));
        }
        if (deptName != null) {
            ParameterExpression<String> p = cb.parameter(String.class, "dept");
            criteria.add(cb.equal(emp.get("dept").get("name"), p));
        }
        if (projectName != null) {
            ParameterExpression<String> p = cb.parameter(String.class, "project");
            criteria.add(cb.equal(project.get("name"), p));
        }
        if (city != null) {
            ParameterExpression<String> p = cb.parameter(String.class, "city");
            criteria.add(cb.equal(emp.get("address").get("city"), p));
        }

        if (criteria.size() == 0) {
            throw new RuntimeException("no criteria");
        } else if (criteria.size() == 1) {
            c.where(criteria.get(0));
        } else {
            c.where(cb.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<Employee> q = em.createQuery(c);

        if (name != null) { q.setParameter("name", name); }
        if (deptName != null) { q.setParameter("dept", deptName); }
        if (project != null) { q.setParameter("project", projectName); }
        if (city != null) { q.setParameter("city", city); }
        return q.getResultList();
    }


    public List<Employee> findEmployees(String name, String deptName,
                                        String projectName, String city) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        c.select(emp);

        List<Predicate> criteria = new ArrayList<Predicate>();
        if (name != null) {
            ParameterExpression<String> p =
                    cb.parameter(String.class, "name");
            criteria.add(cb.equal(emp.get("name"), p));
        }
        if (deptName != null) {
            ParameterExpression<String> p =
                    cb.parameter(String.class, "dept");
            criteria.add(cb.equal(emp.get("dept").get("name"), p));
        }
        //子查询
        if (projectName != null) {
            Subquery<Integer> sq = c.subquery(Integer.class);
            Root<Project> project = sq.from(Project.class);
            Join<Project,Employee> sqEmp = project.join("employees");
            sq.select(sqEmp.<Integer>get("id"))
                    .where(cb.equal(project.get("name"),
                            cb.parameter(String.class, "project")));
            criteria.add(cb.in(emp.get("id")).value(sq));
        }
        if (city != null) {
            ParameterExpression<String> p =
                    cb.parameter(String.class, "city");
            criteria.add(cb.equal(emp.get("address").get("city"), p));
        }
        if (criteria.size() == 1) {
            c.where(criteria.get(0));
        } else {
            c.where(cb.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<Employee> q = em.createQuery(c);

        if (name != null) { q.setParameter("name", name); }
        if (deptName != null) { q.setParameter("dept", deptName); }
        if (projectName != null) { q.setParameter("project", projectName); }
        if (city != null) { q.setParameter("city", city); }
        return q.getResultList();
    }

    //实体属性里有Map
    public Collection<Object> executeQueryUsingMetamodel() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object> c = cb.createQuery();
        Root<EmployeeMap> emp = c.from(EmployeeMap.class);
        EntityType<EmployeeMap> emp_ = emp.getModel();
        MapJoin<EmployeeMap,String,Phone> phone =
                emp.join(emp_.getMap("phones", String.class, Phone.class));
        c.multiselect(emp.get(emp_.getSingularAttribute("name", String.class)),
                phone.key(),
                phone.value());
        TypedQuery<Object> q = em.createQuery(c);
        return q.getResultList();
    }

    public List<Employee> getEmployeesUsingJpqlQuery() {

        String qString =
                "SELECT e FROM Employee e WHERE e.dept.id IN " +
                        "(SELECT DISTINCT d.id FROM Department d JOIN d.employees emp JOIN emp.projects empProj " +
                        " WHERE empProj.name LIKE \"QA%\")";

        TypedQuery<Employee> q = em.createQuery(qString, Employee.class);
        return q.getResultList();
    }

    public List<Employee> getEmployeesUsingStringBasedQuery() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
        Root<Employee> emp = c.from(Employee.class);
        Subquery<Integer> sq = c.subquery(Integer.class);
        Root<Department> dept = sq.from(Department.class);
        Join<Employee,Project> project =
                dept.join("employees").join("projects");
        sq.select(dept.<Integer>get("id"))
                .distinct(true)
                .where(cb.like(project.<String>get("name"), "QA%"));
        c.select(emp)
                .where(cb.in(emp.get("dept").get("id")).value(sq));

        TypedQuery<Employee> q = em.createQuery(c);
        return q.getResultList();
    }


}
