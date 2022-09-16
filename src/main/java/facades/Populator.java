/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import entities.Person;

import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

import java.time.Instant;
import java.util.Date;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = PersonFacade.getFacade(emf);
        
        pf.addPerson("Kurt", "Wonnegut", "12345678");
        pf.addPerson("Peter", "Hansen", "12345678");
    }
    
    public static void main(String[] args) {
        populate();
    }
}
