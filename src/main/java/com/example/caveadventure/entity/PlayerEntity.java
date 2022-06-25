package com.example.caveadventure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 游戏中玩家实体类
 */
@Document("player")
public class PlayerEntity implements Serializable {
    @Id
    private Integer userid;
    private Integer roleid;
    private Integer heart;
    private Integer baglimit;
    private Double adventure;
    private List<ProductEntity> product;
    private int endtime;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
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

    public void setBaglimit(Integer baglimit) {
        this.baglimit = baglimit;
    }

    public Double getAdventure() {
        return adventure;
    }

    public void setAdventure(Double adventure) {
        this.adventure = adventure;
    }

    public List<ProductEntity> getProduct() {
        return product;
    }

    public void setProduct(List<ProductEntity> product) {
        this.product = product;
    }

    public int getEndtime() {
        return endtime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerEntity that = (PlayerEntity) o;
        return endtime == that.endtime && Objects.equals(userid, that.userid) && Objects.equals(roleid, that.roleid) && Objects.equals(heart, that.heart) && Objects.equals(baglimit, that.baglimit) && Objects.equals(adventure, that.adventure) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, roleid, heart, baglimit, adventure, product, endtime);
    }

    @Override
    public String toString() {
        return "PlayerEntity{" +
                "userid=" + userid +
                ", roleid=" + roleid +
                ", heart=" + heart +
                ", baglimit=" + baglimit +
                ", adventure=" + adventure +
                ", product=" + product +
                ", endtime=" + endtime +
                '}';
    }
}
