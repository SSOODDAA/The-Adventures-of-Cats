package com.example.caveadventure.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用户实体类
 */
@Component
public class UserEntity implements Serializable {
    private String username;
    private String pwd;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return username.equals(that.username) && pwd.equals(that.pwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, pwd);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
