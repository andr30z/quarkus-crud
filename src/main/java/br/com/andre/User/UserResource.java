package br.com.andre.User;

import java.util.UUID;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/v1/users")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/{id}")
    public User listById(@PathParam("id") UUID id) {
        return this.userService.findUserById(id);
    }

    @POST
    public User create(UserResourceDto userDto) {
        return this.userService.save(userDto);
    }

    @PUT
    @Path("/{id}")
    public User update(@PathParam("id") UUID id, UserResourceDto userDto) {
        return this.userService.update(id, userDto);
    }
}
