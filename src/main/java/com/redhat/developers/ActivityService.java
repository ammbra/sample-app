package com.redhat.developers;

import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@RegisterRestClient
@Path("/api/activity")
public interface ActivityService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @CircuitBreaker(failureRatio=0.75, delay = 1000 )
    @Timeout(150)
    @Retry(maxRetries = 4, delay = 100)
    @Fallback(DefaultBasicHobby.class)
    BasicHobby getActivityByType(@QueryParam("type") String type);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 5, delay = 100)
    @Fallback(DefaultPricedHobby.class)
    PricedHobby getActivity();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    CompleteHobby getActivityByAccessibility(@QueryParam("minaccessibility") double minaccessibility, @QueryParam("maxaccessibility") double maxaccessibility);


    record DefaultBasicHobby() implements FallbackHandler<BasicHobby> {
        @Override
        public BasicHobby handle(ExecutionContext executionContext) {
            return BasicHobby.empty();
        }
    }

    record DefaultPricedHobby() implements FallbackHandler<PricedHobby> {
        @Override
        public PricedHobby handle(ExecutionContext executionContext) {
            return PricedHobby.empty();
        }
    }
}
