package com.spring.data.jpa.example.jpademo.repository;

import com.spring.data.jpa.example.jpademo.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ContractRepository extends JpaRepository<Contract,String> {

    @Transactional
    @Modifying
    @Query("update Contract c set c.contractName =:#{#contract.contractName} where c.id = :#{#contract.id}")
    int updateContractModifying(@Param("contract")Contract contract);

}
