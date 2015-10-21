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

    public UserEntity findbyUsername(String username) {
        UserEntity from = from(UserEntity.class);
        where(from.getUsername()).eq(username);
        List<UserEntity> result = select(from).list(em);
        if(result.isEmpty())
            return null;
        return result.get(0);
    }

    public UserEntity findbyEmail(String email) {
        UserEntity from = from(UserEntity.class);
        where(from.getEmail()).eq(email);
        List<UserEntity> result = select(from).list(em);
        if(result.isEmpty())
            return null;
        return result.get(0);
    }


    public boolean userExists(UserEntity user) {
        UserEntity tempUser;
        try{
            tempUser = findbyUsername(user.getUsername());
            if (user.getUsername().equals(tempUser.getUsername())) {
            return true;
        } else{ 
            tempUser = findbyEmail(user.getEmail());
            if (user.getEmail().equals(tempUser.getEmail())) 
            return true;
        }
        return false;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean save(UserEntity user) {
        if (userExists(user)) {
            return false;
        } else {
            super.save(user);
            return true;
        }
    }

}
