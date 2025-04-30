package com.projectmanagement.cw_dm2_be.Controller;


import java.util.List;

import com.projectmanagement.cw_dm2_be.Model.Item;
import com.projectmanagement.cw_dm2_be.Repository.ItemRepository;
import com.projectmanagement.cw_dm2_be.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/items")  // Note: small "i" in items (common convention)
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;

    // GET /api/items
    @GetMapping
    public List<Item> getAllItem() {
        return itemService.getAllItem();
    }

    // GET /api/items/{id}
    @GetMapping("/{id}")
    public Optional<Item> getItemById(@PathVariable int id) {
        return itemService.getItemById(id);
    }

    // POST /api/items
    @PostMapping
    public void addItem(@RequestBody Item item) {
        itemService.addItem(item);
    }

    // PUT /api/items/{id}
    @PutMapping("/{id}")
    public Item updateItem(@PathVariable int id, @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }

    // DELETE /api/items/{id}
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable int id) {
        itemService.deleteItemById(id);
    }

    //Get items by category
    @GetMapping("/category/{categoryName}")
    public List<Item> getItemsByCategory(@PathVariable String categoryName) {
        return itemRepository.findByCategory(categoryName);
    }

}

