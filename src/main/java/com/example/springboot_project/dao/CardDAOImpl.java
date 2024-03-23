package com.example.springboot_project.dao;

import com.example.springboot_project.entity.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardDAOImpl implements CardDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Card> getAllCards() {
        List<Card> allCards = entityManager.createQuery("from Card").getResultList();
        return allCards;
    }

    @Override
    public void saveCard(Card card) {
        Card newCard = entityManager.merge(card);
        card.setId(newCard.getId());
    }

    @Override
    public Card getCard(int id) {
        Card card = entityManager.find(Card.class, id);
        return card;
    }

    @Override
    public void deleteCard(int id) {
        entityManager.createQuery("delete from Card where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
