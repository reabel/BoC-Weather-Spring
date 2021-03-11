# Readme

## Local Setup / Building

> Note: ensure JAVA_HOME is set on local environment at minimum version is JDK 1.8

> All commands should be ran from the base project directory

- Ensure a csv file is available to run against (main CSV not provided due to size as well as data).
  - a smaller sample file with semi-random data has been placed in `main/resources/csv/`, this can be named to `eng-climate-summary.csv` or the proper CSV can be placed in this directory under the same file name.
- Run via mvnw file: `./mvnw spring-boot:run`
- application can be found at `localhost:8080/`
  - the terminal should display the port should 8080 not be available. look for `Tomcat started on port(s): %PORT% (http) with context path ''`

## Running Tests

Tests can be ran using `./mvnw test` on the command line

## On-going dev notes

- Locally tested using 1.8.0_282 in Ubuntu VM on windows

- ensure JAVA_HOME is set on local environment

  ie (`export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64`)

  `export JAVA_HOME=/usr/lib/jvm/java/java-1.8.0-openjdk-amd64`

- In ubuntu JAVA_HOME can be echoed via `echo $JAVA_HOME`

- May also need your bin `export PATH=$PATH:$JAVA_HOME/bin`

## Additional Notes on Running / Exporting

- To Run Application Locally: `./mvnw spring-boot:run`
- To export a JAR file (Can also export WAR files): `./mvnw clean package`
  - Jar file is placed within the `target` folder
