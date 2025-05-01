package com.beachape;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        return new Response(
                new ImmutableList(List.of(new Greeting("Hello from Quarkus REST"))));
    }

    // Pretend this is something coming from a 3rd party lib, like Guava
    // ImmutableList
    public record ImmutableList<T>(List<T> items) {
    }

    public record Greeting(String message) {
    }

    public record Response(
            @Schema(
                description = "A list of messages",
                implementation = Greeting.class,
                type = SchemaType.ARRAY)
            ImmutableList<Greeting> greetings) {
    }
}
