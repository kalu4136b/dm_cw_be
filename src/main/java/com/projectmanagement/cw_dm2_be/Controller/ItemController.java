package com.projectmanagement.cw_dm2_be.Controller;

import com.projectmanagement.cw_dm2_be.Model.Item;
import com.projectmanagement.cw_dm2_be.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @GetMapping
    public List<Item> getAllItem() {
        return itemService.getAllItem();
    }


    @GetMapping("/{id}")
    public Optional<Item> getItemById(@PathVariable int id) {
        return itemService.getItemById(id);
    }


    @PostMapping
    public void addItem(@RequestBody Item item) {
        itemService.addItem(item);
    }


    @PutMapping("/{id}")
    public Item updateItem(@PathVariable int id, @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }


    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable int id) {
        itemService.deleteItemById(id);
    }
}

