package com.example.springboot_project.dao;

import com.example.springboot_project.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository <Document, Integer> {

}
