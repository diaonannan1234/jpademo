package com.spring.data.jpa.example.jpademo.auth.repository;

import com.spring.data.jpa.example.jpademo.auth.entityone.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,String> {

    //检索出所有至少存在一个员工的项目.employees是一个对象
    @Query("select p from Project p where p.employees is not empty ")
    List<Project> getProjectEmployeesNotEmpty();

    //检索出设计类型和质量类型的项目
    @Query("select p from Project p where type(p) = DesignProject or type(p) = QualityProject")
    List<Project> getProjectTypeDesignOrQuality();
}
