package com.example.springboot_project.dao;

import com.example.springboot_project.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Integer> {

}
