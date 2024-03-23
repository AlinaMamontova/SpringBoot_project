package com.example.springboot_project.dao;

import com.example.springboot_project.entity.Currency;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CurrencyDAOImpl implements CurrencyDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Currency> getAllCurrencies() {
        return entityManager.createQuery("from Currency").getResultList();
    }

    @Override
    public void saveCurrency(Currency currency) {
        Currency newCurrency = entityManager.merge(currency);
        currency.setId(newCurrency.getId());
    }

    @Override
    public Currency getCurrency(int id) {
        return entityManager.find(Currency.class, id);
    }

    @Override
    public void deleteCurrency(int id) {
        entityManager.createQuery("delete from Currency where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
