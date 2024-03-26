package edu.controller;

import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;

public class AddUser {

    public User addUser(User user) {

        GenericDao dao = new GenericDao(User.class);
        dao.insert(user);
        return user;
    }
}
