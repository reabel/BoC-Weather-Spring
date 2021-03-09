package com.reabel.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(StationRepository repository) {
        // Sample loading
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return args -> {
            log.info("Preloading " + repository
                    .save(new Station("CHEMAINUS", "BC", LocalDate.parse("04/01/2018", dateFormat), 15.1, 26.5, 7.0)));
            log.info("Preloading " + repository.save(
                    new Station("LAKE COWICHAN", "BC", LocalDate.parse("06/01/2018", dateFormat), 13.9, 31.0, 3.0)));
            // needs to be 0.0, will need logic for this
            log.info("Preloading " + repository.save(
                    new Station("DISCOVERY ISLAND", "BC", LocalDate.parse("06/01/2018", dateFormat), 0.0, 22.2, 0.0)));
            log.info("Done preloading");
            // log.info("Preloading " + repository.save(new Station()));
        };
    }
}
