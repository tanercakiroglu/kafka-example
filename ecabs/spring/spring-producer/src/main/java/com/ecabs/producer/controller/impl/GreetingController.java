package com.ecabs.producer.controller.impl;

import com.ecabs.producer.controller.GreetingOperations;
import com.ecabs.common.model.Greeting;
import com.ecabs.common.model.WrapperCollectionResponse;
import com.ecabs.common.model.WrapperResponse;
import com.ecabs.producer.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
@RequiredArgsConstructor
public class GreetingController implements GreetingOperations {

    private final GreetingService greetingService;

    @Override
    public WrapperCollectionResponse<Greeting> getAll() {
        return WrapperCollectionResponse.of(greetingService.getAll());
    }

    @Override
    public WrapperResponse<Greeting> getById(int id) {
        return null;
    }

    @Override
    public WrapperResponse save(Greeting greeting) {
        return null;
    }
}
