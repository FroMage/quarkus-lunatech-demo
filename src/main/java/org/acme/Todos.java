package org.acme;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.quarkus.panache.common.Sort;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("api")
public class Todos {
    @GET
    public List<Todo> list(){
        return Todo.listAll(Sort.by("title"));
    }
    
    @GET
    @Path("{title}/{page}")
    public List<Todo> search(@PathParam String title, @PathParam int page){
        return Todo.search(title, page);
    }
    
    @Transactional
    @DELETE
    public void clearsCompleted() {
        Todo.deleteCompleted();
    }

    @Transactional
    @POST
    public Todo add(Todo todo) {
        todo.persist();
        return todo;
    }
}
