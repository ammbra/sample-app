package com.redhat.developers;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.NOT_IMPLEMENTED;
import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;

@Path("actions")
public class HobbyResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(HobbyResource.class);


    @RestClient
    ActivityService service;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PricedHobby getRandomPricedHobby() {
        return service.getActivity();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PricedHobby getAccessibleHobby(@QueryParam("minaccessibility") double minaccessibility, @QueryParam("maxaccessibility") double maxaccessibility) {
        return service.getActivityByAccessibility(minaccessibility, maxaccessibility);
    }

    @GET
    @Path("{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHobbyByType(@PathParam("type") String type) {
       return switch(type){
           case "recreational" -> Response.status(OK)
                        .entity(service.getActivityByType(type)).build();
           case "drawing" -> Response.status(NO_CONTENT)
                       .entity(BasicHobby.empty()).build();
           default -> {
               BasicHobby hobby = service.getActivityByType(type);
               yield ((hobby.participants > 0) ? Response.status(NOT_IMPLEMENTED).build()
                       : invokeServiceUnavailable(type));
           }
        };
    }


    private Response invokeServiceUnavailable(String type) {
        LOGGER.debug(String.format("Type specified is not supported %s", type));
        return Response.status(SERVICE_UNAVAILABLE).entity(BasicHobby.empty()).build();
    }
}