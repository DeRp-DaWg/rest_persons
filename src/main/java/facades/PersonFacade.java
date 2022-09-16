package facades;

import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements IPersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public PersonDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
//        if (person == null)
//            throw new RenameMeNotFoundException("The Person entity with ID: "+id+" Was not found");
        return new PersonDTO(person);
    }
    
    //TODO Remove/Change this before use
    public long getPersonCount(){
        EntityManager em = getEntityManager();
        try {
            long personCount = (long)em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return personCount;
        } finally {
            em.close();
        }
    }
    
    public List<PersonDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> people = query.getResultList();
        return PersonDTO.getDtos(people);
    }
    
    @Override
    public PersonDTO addPerson(String fName, String lName, String phone) {
        Person person = new Person(
                fName,
                lName,
                phone
        );
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }
    
    @Override
    public PersonDTO deletePerson(int id) {
        EntityManager em = getEntityManager();
        try {
            Person person = em.find(Person.class, (long) id);
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
            return new PersonDTO(person);
        } finally {
            em.close();
        }
    }
    
    @Override
    public PersonDTO getPerson(int id) {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, (long) id);
        return new PersonDTO(person);
    }
    
    @Override
    public PersonsDTO getAllPersons() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> people = query.getResultList();
        PersonsDTO personsDTO = new PersonsDTO(people);
        em.close();
        return personsDTO;
    }
    
    @Override
    public PersonDTO editPerson(PersonDTO p) {
        EntityManager em = getEntityManager();
        try {
            Person person = em.find(Person.class, p.getId());
            person.setfName(p.getfName());
            person.setlName(p.getlName());
            person.setPhone(p.getPhone());
            em.getTransaction().begin();
            PersonDTO newPersonDTO = new PersonDTO(em.merge(person));
            em.getTransaction().commit();
            return newPersonDTO;
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = getFacade(emf);
        pf.getAll().forEach(dto->System.out.println(dto));
    }
}
