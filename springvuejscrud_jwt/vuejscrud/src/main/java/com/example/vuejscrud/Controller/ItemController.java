package com.example.vuejscrud.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vuejscrud.Entity.Items;
import com.example.vuejscrud.Service.ItemService;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:8081") // Adjust the allowed origins as per your Vue.js app's URL
public class ItemController {
    


    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Items>> getAllItems(@RequestHeader("Authorization") String bearerToken) {
        System.out.println(bearerToken);
        List<Items> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<Items> createItem(@RequestHeader("Authorization") String bearerToken, @RequestBody Items item) {
        System.out.println(bearerToken);
        Items createdItem = itemService.createItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Items> updateItem(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") Long id, @RequestBody Items updatedItem) {
        System.out.println(bearerToken);

        Items item = itemService.getItemById(id);
        if (item != null) {
            updatedItem.setId(id);
            Items savedItem = itemService.updateItem(updatedItem);
            return ResponseEntity.ok(savedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") Long id) {
        System.out.println(bearerToken);
        Items item = itemService.getItemById(id);
        if (item != null) {
            itemService.deleteItem(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
