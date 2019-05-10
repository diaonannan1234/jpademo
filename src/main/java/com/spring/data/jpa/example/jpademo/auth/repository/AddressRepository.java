package com.spring.data.jpa.example.jpademo.auth.repository;

import com.spring.data.jpa.example.jpademo.auth.entityone.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,String> {
}
