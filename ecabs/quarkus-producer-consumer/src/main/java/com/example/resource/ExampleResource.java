package com.example.resource;

import com.example.messaging.MovieProducer;
import com.example.model.Movie;
import com.example.restclient.MyRemoteService;
import com.example.service.ExampleService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/hello")
public class ExampleResource {

    ExampleService exampleService;
    MovieProducer producer;

    @Inject
    public ExampleResource(ExampleService exampleService, MovieProducer producer) {
        this.exampleService = exampleService;
        this.producer = producer;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<MyRemoteService.Extension> hello() {
        producer.sendMovieToKafka(new Movie("Batman Begins",2006));
        return exampleService.getExtensionsById("io.quarkus:quarkus-rest-client");
    }
}