package com.example.service;

import com.example.restclient.MyRemoteService;

import java.util.Set;

public interface ExampleService {

    Set<MyRemoteService.Extension> getExtensionsById(String id);
}
