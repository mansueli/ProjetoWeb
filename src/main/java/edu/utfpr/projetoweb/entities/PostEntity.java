/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.projetoweb.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Rodrigo
 */
@Entity
public class PostEntity implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    public PostEntity() {
        
    }

    public PostEntity(UserEntity user, String title, String imgURL, long likes, String category) {
        this.user = user;
        this.title = title;
        this.imgURL = imgURL;
        this.likes = likes;
        this.category = category;
    }
    public PostEntity(UserEntity user, String title, String imgURL,  String category) {
        this.user = user;
        this.title = title;
        this.imgURL = imgURL;
        this.category = category;
    }
    
    @Column
    private String title;
    
    @Column
    private String imgURL;
    
    @Column 
    private long likes;
    
    @Column
    private String category;

    public long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
}
