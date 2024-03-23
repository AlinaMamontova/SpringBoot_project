package com.example.springboot_project.dao;

import com.example.springboot_project.entity.CardType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardTypeDAOImpl implements CardTypeDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CardType> getAllCardTypes() {
        return entityManager.createQuery("from CardType").getResultList();
    }

    @Override
    public void saveCardType(CardType cardType) {
        CardType newCardType = entityManager.merge(cardType);
        cardType.setId(newCardType.getId());

    }

    @Override
    public CardType getCardType(int id) {
        return entityManager.find(CardType.class, id);
    }

    @Override
    public void deleteCardType(int id) {
        entityManager.createQuery("delete from CardType where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
