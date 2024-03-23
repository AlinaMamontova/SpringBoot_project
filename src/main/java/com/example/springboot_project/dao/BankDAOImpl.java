package com.example.springboot_project.dao;

import com.example.springboot_project.entity.Bank;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankDAOImpl implements BankDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Bank> getAllBanks() {
        return entityManager.createQuery("from Bank").getResultList();
    }

    @Override
    public void saveBank(Bank bank) {
        Bank newBank = entityManager.merge(bank);
        bank.setId(newBank.getId());
    }

    @Override
    public Bank getBank(int id) {
        return entityManager.find(Bank.class, id);
    }

    @Override
    public void deleteBank(int id) {
        Query query = entityManager.createQuery("delete from Bank where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
