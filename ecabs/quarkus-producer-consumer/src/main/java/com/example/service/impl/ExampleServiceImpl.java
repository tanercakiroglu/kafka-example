package com.example.service.impl;

import com.example.restclient.MyRemoteService;
import com.example.service.ExampleService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;

@Singleton
public class ExampleServiceImpl implements ExampleService {

    @Inject
    @RestClient
    MyRemoteService myRemoteService;

    public Set<MyRemoteService.Extension> getExtensionsById(String id){
        return myRemoteService.getExtensionsById("io.quarkus:quarkus-rest-client");
    }
}
