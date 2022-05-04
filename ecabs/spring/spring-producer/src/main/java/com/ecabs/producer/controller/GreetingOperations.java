package com.ecabs.producer.controller;

import com.ecabs.common.model.Greeting;
import com.ecabs.common.model.WrapperCollectionResponse;
import com.ecabs.common.model.WrapperResponse;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v0")
public interface GreetingOperations {

    @GetMapping("/")
    WrapperCollectionResponse<Greeting> getAll();

    @GetMapping("/{id}")
    WrapperResponse<Greeting> getById(@PathVariable int id);

    @PostMapping("/save")
    WrapperResponse save(@RequestBody Greeting greeting);
}
