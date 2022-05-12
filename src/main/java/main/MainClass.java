/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entities.User;
import javax.persistence.TypedQuery;
import utils.JpaUtils;

/**
 *
 * @author tiennh
 */
public class MainClass {
    public static void main(String[] args) {
        String jpql = "SELECT obj FROM User obj WHERE id = 1";
        TypedQuery<User> query = JpaUtils.getEntityManager().createQuery(jpql, User.class);
        User u = query.getSingleResult();
        
        System.out.println(u.getUsername());
        System.out.println(u.getPassword());
        System.out.println(u.getRole());
    }
}
