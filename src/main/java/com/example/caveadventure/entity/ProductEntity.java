package com.example.caveadventure.entity;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.Objects;

public class ProductEntity {
    private Integer id;
    private Integer type;/*1.食物 2.武器 3.宝藏 4.NPC*/
    private String name;
    private Integer rare;
    private Integer weight;
    /*前端所需要的介绍*/
    private String description;
    private String ability;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRare() {
        return rare;
    }

    public void setRare(Integer rare) {
        this.rare = rare;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(name, that.name) && Objects.equals(rare, that.rare) && Objects.equals(weight, that.weight) && Objects.equals(description, that.description) && Objects.equals(ability, that.ability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, rare, weight, description, ability);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", rare=" + rare +
                ", weight=" + weight +
                ", description='" + description + '\'' +
                ", ability='" + ability + '\'' +
                '}';
    }
}
