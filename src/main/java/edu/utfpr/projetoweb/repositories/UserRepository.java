/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.projetoweb.repositories;

import edu.utfpr.projetoweb.entities.UserEntity;
import java.util.List;
import static org.torpedoquery.jpa.Torpedo.from;
import static org.torpedoquery.jpa.Torpedo.select;
import static org.torpedoquery.jpa.Torpedo.where;

/**
 *
 * @author Rodrigo
 */
public class UserRepository extends Repository<UserEntity> {

    private static class UserHolder {

        public static UserRepository INSTANCE = new UserRepository();
    }

    public static UserRepository getInstance() {
        return UserHolder.INSTANCE;
    }

    private UserRepository() {
        super(UserEntity.class);
    }

    public UserEntity findByEmail(String email) {
        UserEntity from = from(UserEntity.class);
        where(from.getEmail()).eq(email);
        List<UserEntity> result = select(from).list(em);
        return result.get(0);
    }
    
    public UserEntity findByUsername(String username) {
        UserEntity from = from(UserEntity.class);
        where(from.getUsername()).eq(username);
        List<UserEntity> result = select(from).list(em);
        return result.get(0);
    }

}
