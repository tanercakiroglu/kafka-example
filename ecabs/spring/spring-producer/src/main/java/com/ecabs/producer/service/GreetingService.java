package com.ecabs.producer.service;


import com.ecabs.common.model.Greeting;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface GreetingService {

    List<Greeting> getAll() throws JsonProcessingException;
}
