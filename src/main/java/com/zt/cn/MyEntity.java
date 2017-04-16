package com.zt.cn;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by apple on 2017/4/16.
 */
@Entity
@Table(name = "websites")
public class MyEntity {
    @Id
    private int id;
    private String name;
    private String url;
    private int alexa;
    private String country;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getAlexa() {
        return alexa;
    }

    public String getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAlexa(int alexa) {
        this.alexa = alexa;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
