package com.reabel.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.io.File;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(StationRepository repository) throws IOException, CsvException {
        // Sample loading
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return args -> {
            // log.info("Preloading " + repository
            // .save(new Station("CHEMAINUS", "BC", LocalDate.parse("04/01/2018",
            // dateFormat), 15.1, 26.5, 7.0)));
            // log.info("Preloading " + repository.save(
            // new Station("LAKE COWICHAN", "BC", LocalDate.parse("06/01/2018", dateFormat),
            // 13.9, 31.0, 3.0)));
            // // needs to be 0.0, will need logic for this
            // log.info("Preloading " + repository.save(
            // new Station("DISCOVERY ISLAND", "BC", LocalDate.parse("06/01/2018",
            // dateFormat), 0.0, 22.2, 0.0)));
            // log.info("Done preloading");

            // log.info("Preloading " + repository.save(new Station()));
            String curPath = new File("").getAbsolutePath();
            String fileName = curPath.concat("/src/main/resources/csv/eng-climate-summary.csv");
            log.info("path To CSV:" + fileName);
            try (CSVReader reader = new CSVReaderBuilder(new FileReader(fileName)).withSkipLines(1).build()) {
                List<String[]> r = reader.readAll();

                log.info("Loading CSV data in H2");
                r.forEach(x -> {
                    // TODO: alternatively map directly to Station bean.

                    String station = x[0];
                    String province = x[1];
                    LocalDate date = LocalDate.parse(x[2], dateFormat);

                    String meanTemp = x[3];
                    String highestMonthlyMaxTemp = x[4];
                    String LowestMonthlyMinTemp = x[5];

                    /*
                     * Previously mapped Temps to doubles but this presented an issue with default
                     * values
                     */

                    // Double meanTemp = (x[3] != null && x[3].length() > 0) ?
                    // Double.parseDouble(x[3]) : null;
                    // Double highestMonthlyMaxTemp = (x[4] != null && x[4].length() > 0) ?
                    // Double.parseDouble(x[4])
                    // : null;
                    // Double LowestMonthlyMinTemp = (x[5] != null && x[5].length() > 0) ?
                    // Double.parseDouble(x[5]) : null;
                    // log.info("Saving: " + x[0]);
                    repository.save(new Station(station, province, date, meanTemp, highestMonthlyMaxTemp,
                            LowestMonthlyMinTemp));
                });
                log.info("Finished Loading CSV Data");
            } catch (Exception e) {
                log.error(e.toString());
            }
        };
    }
}
