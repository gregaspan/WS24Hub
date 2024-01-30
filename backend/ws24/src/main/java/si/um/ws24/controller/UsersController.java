package si.um.ws24.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import si.um.ws24.model.UsersEntity;
import si.um.ws24.repository.UsersRepository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/users")
public class UsersController {
    @Inject
    UsersRepository usersRepository;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public Optional<UsersEntity> getUserById(@PathParam("id") int id) {
        return usersRepository.getUserById(id);
    }


    @POST
    @Path("/add")
    @Transactional
    public Response addUser(@QueryParam("name") String name, @QueryParam("email") String email, @QueryParam("phonenumber") String phonenumber) {
        UsersEntity user = new UsersEntity();
        user.setName(name);
        user.setEmail(email);
        user.setPhonenumber(phonenumber);
        usersRepository.persist(user);
        return Response.ok().build();
    }


    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllUsers() {
        try (Stream<UsersEntity> stream = usersRepository.getAllUsers()) {
            return stream.map(UsersEntity::toString) // Convert each UsersEntity to String
                    .collect(Collectors.joining(", ")); // Join all strings with a comma
        } // The stream is automatically closed here
    }

    @GET
    @Path("/allServices")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllUsersServices() {
        return usersRepository.getAllUsersServices().toString();
    }





}
