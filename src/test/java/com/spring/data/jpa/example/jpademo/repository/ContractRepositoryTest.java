package com.spring.data.jpa.example.jpademo.repository;

import com.spring.data.jpa.example.jpademo.entity.Contract;
import com.spring.data.jpa.example.jpademo.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ContractRepositoryTest extends AbstractTest{

    @Autowired private ContractRepository contractRepository;

    @Autowired private UserRepository userRepository;
    @Test
    @Transactional
    public void testUpdateContractModifying(){

        Contract c = new Contract();
        c.setContractName("contract");
        Contract contract = contractRepository.save(c);
        contract.setContractName("aaa");
        int count = contractRepository.updateContractModifying(contract);
        assertTrue(count == 1);
    }

    @Test
    public void testUpdateUserAgeModifying(){

        User u1 = new User();
        u1.setName("duwei0");
        u1.setAge(2);
        User u = userRepository.findOne(Example.of(u1)).orElse(null);
        u.setAge(80);
        int rows = userRepository.updateUserAgeModifying(u);
        assertEquals(rows,1);
        User uu = userRepository.findById(u.getId()).orElseThrow(()->new NullPointerException());
        assertTrue(uu.getAge()==80);
    }

    @Test
    public void testCreateContract(){
        Contract contract = new Contract();
        contract.setContractName("dsdflsdf");
        contract.setText("ekwicxlkvlskdjfasdf");
        contract.setBigDecimal(new BigDecimal("100000000000000000000000000000000000000000000000000"));
        Contract c = contractRepository.save(contract);
        assertNotNull(c);
    }

}