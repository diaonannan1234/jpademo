package com.spring.data.jpa.example.jpademo;

import com.spring.data.jpa.example.jpademo.entity.Role;
import com.spring.data.jpa.example.jpademo.entity.User;
import com.spring.data.jpa.example.jpademo.repository.RoleRepository;
import com.spring.data.jpa.example.jpademo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Arrays;

@SpringBootApplication
public class JpademoApplication {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(JpademoApplication.class, args);
	}

	@PostConstruct
	public void initData() {
		User  user = new User();
				user.setName("duwei");
				user.setAge(20);
				user.setCreateDate(LocalDate.now());
				user.setCreateDateTime(LocalDateTime.now());
				user.setCreateTime(LocalTime.now());
				user.setCreateZoneDateTime(ZonedDateTime.now());
				user.setEnable(true);
		User userR = userRepository.save(user);
		for(int i =0;i<10;i++){
            User u = new User();
            u.setName("duwei"+i);
            u.setAge(++i*2);
			userRepository.save(u);


		}

		User u1 = new User();
		u1.setName("chen");
		u1.setAge(9);
		User u = userRepository.save(u1);
		Role r1 = new Role();
		r1.setName("chenadmin");
		r1.setUsers(Arrays.asList(u1));
		roleRepository.save(r1);

		Role r2 = new Role();
		r2.setName("admin"+1);
		Role role = roleRepository.save(r2);
		role.setUsers(userRepository.findAll().subList(0,3));
		Role role1 = roleRepository.save(role);

		Role r3 = new Role();
		r3.setName("admin2");
		Role role2 = roleRepository.save(r3);
		role2.setUsers(userRepository.findAll().subList(4,7));
		Role role3 = roleRepository.save(role);

	}

}
