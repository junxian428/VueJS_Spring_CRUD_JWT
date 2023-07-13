package com.example.vuejscrud.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vuejscrud.Entity.Items;
import com.example.vuejscrud.Repository.ItemRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Items> getAllItems() {
        return itemRepository.findAll();
    }

    public Items getItemById(int id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Items createItem(Items item) {
        return itemRepository.save(item);
    }

    public Items updateItem(Items item) {
        return itemRepository.save(item);
    }

    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<Items> getItemsByUserId(String userId) {
        String query = "SELECT i FROM Items i WHERE i.user_id = :user_id";
        TypedQuery<Items> typedQuery = entityManager.createQuery(query, Items.class);
        typedQuery.setParameter("user_id", userId);
        return typedQuery.getResultList();
    }

}
