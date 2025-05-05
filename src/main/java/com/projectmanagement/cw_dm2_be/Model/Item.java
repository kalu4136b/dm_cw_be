package com.projectmanagement.cw_dm2_be.Model;

//import jakarta.persistence.Entity;
import jakarta.persistence.Id;



import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "items")


public class Item {

    @Id
    private int Item_id;
    private String Item_name;
    private String Item_description;
    private Double Item_price;
    private int Gem_Count;
    private String Gem_Type;
    private String Item_category;

    public String getItem_category() {
        return Item_category;
    }

    public void setCategory(String item_category) {
        Item_category = item_category;
    }



    public int getItem_id() {
        return Item_id;
    }

    public void setItem_id(int item_id) {
        Item_id = item_id;
    }

    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String item_name) {
        Item_name = item_name;
    }

    public String getItem_description() {
        return Item_description;
    }

    public void setItem_description(String item_description) {
        Item_description = item_description;
    }

    public Double getItem_price() {
        return Item_price;
    }

    public void setItem_price(Double item_price) {
        Item_price = item_price;
    }

    public int getGem_Count() {
        return Gem_Count;
    }

    public void setGem_Count(int gem_Count) {
        Gem_Count = gem_Count;
    }

    public String getGem_Type() {
        return Gem_Type;
    }

    public void setGem_Type(String gem_Type) {
        Gem_Type = gem_Type;
    }




}
