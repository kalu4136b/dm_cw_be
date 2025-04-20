package com.projectmanagement.cw_dm2_be.Repository;

import com.projectmanagement.cw_dm2_be.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    Optional<Item> findById(int Item_id);
}
