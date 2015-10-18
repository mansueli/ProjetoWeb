/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.projetoweb.repositories;

import edu.utfpr.projetoweb.entities.UserEntity;



/**
 *
 * @author Rodrigo
 */
public class UserRepository extends Repository<UserEntity>{
    private static class UserHolder {
        public static UserRepository INSTANCE = new UserRepository();
    }
    public static UserRepository getInstance() {
        return UserHolder.INSTANCE;
    }
    private UserRepository() {
        super(UserEntity.class);
    }

}
