package com.example.caveadventure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

/**
 * 地图类
 */
@Document("map")
public class MapEntity {
    @Id
    private Integer userid;
    private Integer nowroomx;
    private Integer nowroomy;
    private List<Integer> route;
    private List<Integer> deadroom;
    private Integer magicroom;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getNowroomx() {
        return nowroomx;
    }

    public void setNowroomx(Integer nowroomx) {
        this.nowroomx = nowroomx;
    }

    public Integer getNowroomy() {
        return nowroomy;
    }

    public void setNowroomy(Integer nowroomy) {
        this.nowroomy = nowroomy;
    }

    public List<Integer> getRoute() {
        return route;
    }

    public void setRoute(List<Integer> route) {
        this.route = route;
    }

    public List<Integer> getDeadroom() {
        return deadroom;
    }

    public void setDeadroom(List<Integer> deadroom) {
        this.deadroom = deadroom;
    }

    public Integer getMagicroom() {
        return magicroom;
    }

    public void setMagicroom(Integer magicroom) {
        this.magicroom = magicroom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapEntity mapEntity = (MapEntity) o;
        return Objects.equals(userid, mapEntity.userid) && Objects.equals(nowroomx, mapEntity.nowroomx) && Objects.equals(nowroomy, mapEntity.nowroomy) && Objects.equals(route, mapEntity.route) && Objects.equals(deadroom, mapEntity.deadroom) && Objects.equals(magicroom, mapEntity.magicroom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, nowroomx, nowroomy, route, deadroom, magicroom);
    }

    @Override
    public String toString() {
        return "MapEntity{" +
                "userid=" + userid +
                ", nowroomx=" + nowroomx +
                ", nowroomy=" + nowroomy +
                ", route=" + route +
                ", deadroom=" + deadroom +
                ", magicroom=" + magicroom +
                '}';
    }
}
