package com.ecabs.producer.controller;

import com.ecabs.common.model.Greeting;
import com.ecabs.common.model.WrapperCollectionResponse;
import com.ecabs.common.model.WrapperResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v0")
public interface GreetingOperations {

    @GetMapping("/")
    WrapperCollectionResponse<Greeting> getAll() throws JsonProcessingException;

    @GetMapping("/{id}")
    WrapperResponse<Greeting> getById(@PathVariable int id);

    @PostMapping("/save")
    WrapperResponse save(@RequestBody Greeting greeting);
}
