package com.tn.shiro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Item implements Serializable {

    @Id
    protected Long id;

    @NotNull
    @Size(
            min = 2,
            max = 255,
            message = "Name is required, minimum 2, maximum 255 characters."
    )
    protected String name;

    @NotNull
    @Size(
            min = 1,
            max = 4000,
            message = "Description is required, minimum 10, maximum 4000 characters."
    )
    protected String description;

    public Item() {
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() { // Optional but useful
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ITEM ID: " + getId() + " NAME: " + getName();
    }

    public void setId(Long id) {
        this.id = id;
    }
}