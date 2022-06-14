package com.example.caveadventure.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用户实体类
 */
@Component
public class UserEntity implements Serializable {
    private Integer userid;
    private String username;
    private String pwd;
    private Integer best=0;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getBest() {
        return best;
    }

    public void setBest(Integer best) {
        this.best = best;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(userid, user.userid) && Objects.equals(username, user.username) && Objects.equals(pwd, user.pwd) && Objects.equals(best, user.best);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, username, pwd, best);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", best=" + best +
                '}';
    }
}
