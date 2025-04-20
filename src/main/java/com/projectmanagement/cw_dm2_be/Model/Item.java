package com.projectmanagement.cw_dm2_be.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Item {

    @Id
    private int Item_id;
    private String Item_name;
    private String Item_type;
    private String Item_description;
    private Double Item_price;
    private int Gem_Count;
    private String Gem_Type;
}
