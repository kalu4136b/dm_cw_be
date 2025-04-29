package com.projectmanagement.cw_dm2_be.Repository;

import com.projectmanagement.cw_dm2_be.Model.Item;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.List;


public interface ItemRepository extends MongoRepository<Item, Integer> {
    Optional<Item> findById(int Item_id);

    // Find products by category
    List<Item> findByCategory(String Item_Category);

}
