package dtos;

import entities.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonsDTO {
    private List<PersonDTO> all = new ArrayList<>();
    
    public PersonsDTO(List<Person> people) {
        for (Person person : people) {
            all.add(new PersonDTO(person));
        }
    }
    
    public void add(PersonDTO personDTO) {
        all.add(personDTO);
    }
    
    public List<PersonDTO> getAll() {
        return all;
    }
    
    public void setAll(List<PersonDTO> all) {
        this.all = all;
    }
    
    @Override
    public String toString() {
        return "PersonsDTO{" +
                "personDTOS=" + all +
                '}';
    }
}
