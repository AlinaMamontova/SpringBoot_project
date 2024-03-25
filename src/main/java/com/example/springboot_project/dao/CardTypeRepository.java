package com.example.springboot_project.dao;

import com.example.springboot_project.entity.CardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardTypeRepository extends JpaRepository<CardType, Integer> {

}
