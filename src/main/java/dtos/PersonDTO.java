/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Person;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tha
 */
public class PersonDTO {
    private long id;
    private String fName;
    private String lName;
    private String phone;
    private Date created;
    private Date lastEdited;
    
    public PersonDTO(String fName, String lName, String phone, Date created, Date lastEdited) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.created = created;
        this.lastEdited = lastEdited;
    }
    
    public static List<PersonDTO> getDtos(List<Person> people){
        List<PersonDTO> personDTOS = new ArrayList();
        people.forEach(person->personDTOS.add(new PersonDTO(person)));
        return personDTOS;
    }


    public PersonDTO(Person person) {
        if(person.getId() != null)
            this.id = person.getId();
        this.fName = person.getfName();
        this.lName = person.getlName();
        this.phone = person.getPhone();
        this.created = person.getCreated();
        this.lastEdited = person.getLastEdited();
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getfName() {
        return fName;
    }
    
    public void setfName(String fName) {
        this.fName = fName;
    }
    
    public String getlName() {
        return lName;
    }
    
    public void setlName(String lName) {
        this.lName = lName;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public Date getCreated() {
        return created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
    
    public Date getLastEdited() {
        return lastEdited;
    }
    
    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }
    
    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", phone='" + phone + '\'' +
                ", created=" + created +
                ", lastEdited=" + lastEdited +
                '}';
    }
}
