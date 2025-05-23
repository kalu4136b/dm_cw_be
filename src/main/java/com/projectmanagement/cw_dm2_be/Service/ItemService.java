package com.projectmanagement.cw_dm2_be.Service;

import com.projectmanagement.cw_dm2_be.Model.Item;
import com.projectmanagement.cw_dm2_be.Repository.ItemRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ItemService {

    @Autowired
    private ItemRepository itemRepository;



    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(int id) {
        return itemRepository.findById(id);
    }



    public void deleteItemById(int id) {
        itemRepository.deleteById(id);
    }

    public void addItem(Item item) {
        itemRepository.save(item);
    }

    public Item updateItem(int id, Item item) {
        if (!itemRepository.existsById(id)) {
            throw new RuntimeException("Item with ID " + id + " not found.");
        }
            item.setItem_id(id);
         return itemRepository.save(item);
    }
    public List<Item> getItemsByItem_category(String Item_category) {
        return itemRepository.findByCategory(Item_category);
    }

}
