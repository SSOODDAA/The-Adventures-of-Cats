package com.example.caveadventure.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;
/**
 * 角色实体类
 */
@Component
public class RoleEntity implements Serializable {
    private Integer id;
    private String name;
    private Integer heart;
    private Integer baglimit;
    private Float adventure;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeart() {
        return heart;
    }

    public void setHeart(Integer heart) {
        this.heart = heart;
    }

    public Integer getBaglimit() {
        return baglimit;
    }

    public void setBaglimit(Integer badlimit) {
        this.baglimit = badlimit;
    }

    public Float getAdventure() {
        return adventure;
    }

    public void setAdventure(Float adventure) {
        this.adventure = adventure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(heart, that.heart) && Objects.equals(baglimit, that.baglimit) && Objects.equals(adventure, that.adventure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, heart, baglimit, adventure);
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", heart=" + heart +
                ", baglimit=" + baglimit +
                ", adventure=" + adventure +
                '}';
    }
}
