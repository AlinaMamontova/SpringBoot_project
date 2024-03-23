package com.example.springboot_project.dao;

import com.example.springboot_project.entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Account> getAllAccounts() {
        return entityManager.createQuery("from Account").getResultList();
    }

    @Override
    public void saveAccount(Account account) {
        Account newAccount = entityManager.merge(account);
        account.setId(newAccount.getId());
    }

    @Override
    public Account getAccount(int id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public void deleteAccount(int id) {
        entityManager.createQuery("delete from Account where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
