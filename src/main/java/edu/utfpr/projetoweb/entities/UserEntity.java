/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.projetoweb.entities;

import static edu.utfpr.projetoweb.utils.Encryption.getUserHash;
import static edu.utfpr.projetoweb.utils.RandomOrg.getRand;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Rodrigo
 */
@Entity
public class UserEntity implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String username;
    
    @Column 
    private String email;
    
    @Column 
    private String avatarURL;
    
    @Column
    private String passwordHash;
    
    @Column
    private int salt;

    /**
     *
     */
    public UserEntity() {
    }
    /***
     * 
     * @param username string
     * @param email string
     * @param avatarURL string
     * @param password string
     */
    public UserEntity(String username, String email, String avatarURL, String password){
       this.username = username;
       this.email = email;
       this.avatarURL = avatarURL;
       setPasswordHash(password);
   }
   public UserEntity(String username, String email, String avatarURL, String passwordHash, int salt){
       this.username = username;
       this.email = email;
       this.avatarURL = avatarURL;
       this.passwordHash = passwordHash;
       this.salt = salt;
   }

    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean setPasswordHash(String password) {
        try{
        int tempSalt = getRand();
        String hash = getUserHash(password, tempSalt);
        this.passwordHash = hash;
        setSalt(tempSalt);
        return true;
        }catch(Exception e){
            return false;
        }
    }

    public int getSalt() {
        return salt;
    }
    public  void setSalt(int salt){
        this.salt = salt;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
