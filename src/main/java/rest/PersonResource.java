package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import dtos.PersonsDTO;
import entities.Person;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade FACADE =  PersonFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        PersonsDTO personsDTO = FACADE.getAllPersons();
        return Response.ok().entity(GSON.toJson(personsDTO)).build();
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") int id) {
        PersonDTO personDTO = FACADE.getPerson(id);
        return Response.ok().entity(GSON.toJson(personDTO)).build();
    }
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(String input){
        PersonDTO personDTO = GSON.fromJson(input, PersonDTO.class);
        PersonDTO newPersonDTO = FACADE.addPerson(personDTO.getfName(), personDTO.getlName(), personDTO.getPhone());
        return Response.ok().entity(GSON.toJson(newPersonDTO)).build();
    }
    
    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") int id, String input){
        PersonDTO personDTO = GSON.fromJson(input, PersonDTO.class);
        personDTO.setId(id);
        PersonDTO updatedPersonDTO = FACADE.editPerson(personDTO);
        return Response.ok().entity(GSON.toJson(updatedPersonDTO)).build();
    }
    
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") int id){
        PersonDTO deletedPersonDTO = FACADE.deletePerson(id);
        return Response.ok().entity(GSON.toJson(deletedPersonDTO)).build();
    }
}
