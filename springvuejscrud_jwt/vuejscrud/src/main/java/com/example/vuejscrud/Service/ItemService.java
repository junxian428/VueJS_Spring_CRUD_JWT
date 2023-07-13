package com.example.vuejscrud.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vuejscrud.Entity.Items;
import com.example.vuejscrud.Repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Items> getAllItems() {
        return itemRepository.findAll();
    }

    public Items getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Items createItem(Items item) {
        return itemRepository.save(item);
    }

    public Items updateItem(Items item) {
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
