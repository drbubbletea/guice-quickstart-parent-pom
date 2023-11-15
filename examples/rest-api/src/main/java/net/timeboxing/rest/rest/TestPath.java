package net.timeboxing.rest.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("test")
public class TestPath {

    @Path("testing")
    @GET
    public String test() {
        return "testing!";
    }
}
