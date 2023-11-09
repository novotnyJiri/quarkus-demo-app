/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.debezium.performance.dmt.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.debezium.performance.dmt.service.MainService;
import org.jboss.logging.Logger;

import io.quarkus.runtime.annotations.RegisterForReflection;

@Path("Main")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@RegisterForReflection
public class MainResource {

    @Inject
    MainService mainService;

    private static final Logger LOG = Logger.getLogger(MainResource.class);

    @Path("Test")
    @POST
    public Response insert() {
        LOG.debug("Received TEST request");
        try {
            mainService.testServiceMethod();
            return Response.ok().build();

        }
        catch (Exception ex) {
            return Response.noContent().status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
