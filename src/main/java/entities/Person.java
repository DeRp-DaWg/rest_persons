package entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import javax.persistence.*;


@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fName;
    private String lName;
    private String phone;
    private Date created;
    private Date lastEdited;
    
    public Person() {
    }
    
    public Person(String fName, String lName, String phone) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }
    
    @PrePersist
    protected void onCreate() {
        created = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
        lastEdited = new Date();
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
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
}
