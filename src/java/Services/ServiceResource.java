/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import javax.json.Json;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author mariangelperez
 */
public class ServiceResource {

    private String id;

    /**
     * Creates a new instance of ServiceResource
     */
    private ServiceResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the ServiceResource
     */
    public static ServiceResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of ServiceResource class.
        return new ServiceResource(id);
    }

    /**
     * Retrieves representation of an instance of Services.ServiceResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Json getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Json content) {
    }

   
    @DELETE
    public void delete() {
    }

}
