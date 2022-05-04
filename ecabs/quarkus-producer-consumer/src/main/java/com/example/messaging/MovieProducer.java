package com.example.messaging;

import com.example.model.Movie;
import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MovieProducer {

    private final Logger logger = Logger.getLogger(MovieProducer.class);

    @Inject
    @Channel("movies-out")
    Emitter<Record<Integer, String>> emitter;

    public void sendMovieToKafka(Movie movie) {
        emitter.send(Record.of(movie.year, movie.title))
                .whenComplete((result, ex) -> {
                            if (ex != null) {
                                logger.error("error on Kafka topic movie", ex);
                            } else {
                                logger.info("success on kafka topic movie ");
                            }
                        }
                );
    }
}
