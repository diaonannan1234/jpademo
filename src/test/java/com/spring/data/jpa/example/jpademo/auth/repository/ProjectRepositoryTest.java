package com.spring.data.jpa.example.jpademo.auth.repository;

import com.spring.data.jpa.example.jpademo.auth.entityone.Project;
import com.spring.data.jpa.example.jpademo.repository.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ProjectRepositoryTest extends AbstractTest{

    @Autowired private ProjectRepository projectRepository;
    @Test
    public void testGetProjectEmployeesNotEmpty(){
        List<Project> projects = projectRepository.getProjectEmployeesNotEmpty();

        assertTrue(!projects.isEmpty());
    }

    @Test
    public void testGetProjectTypeDesignOrQuality(){
        List<Project> projects = projectRepository.getProjectTypeDesignOrQuality();
        assertTrue(!projects.isEmpty());
    }
}