package com.example.springboot_project.dao;

import com.example.springboot_project.entity.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDAOImpl implements ClientDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Client> getAllClients() {
        return entityManager.createQuery("from Client").getResultList();
    }

    @Override
    public void saveClient(Client client) {
        Client newClient = entityManager.merge(client);
        client.setId(newClient.getId());
    }

    @Override
    public Client getClient(int id) {
        return entityManager.find(Client.class, id);
    }

    @Override
    public void deleteClient(int id) {
        entityManager.createQuery("delete from Client where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
