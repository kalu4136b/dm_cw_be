package com.projectmanagement.cw_dm2_be.Repository;

import com.projectmanagement.cw_dm2_be.Model.Item;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, Integer> {
    Optional<Item> findById(int Item_id);
    List<Item> findByCategory(String category);
}
