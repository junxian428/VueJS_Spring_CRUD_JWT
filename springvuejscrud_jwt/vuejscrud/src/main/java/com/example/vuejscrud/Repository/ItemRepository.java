package com.example.vuejscrud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vuejscrud.Entity.Items;

@Repository
public interface ItemRepository extends JpaRepository<Items, Long> {
}
