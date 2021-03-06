package com.reabel.weather;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Station {

    private @Id @GeneratedValue Long id;
    private String stationName;
    private String province;
    private LocalDate date;
    // private double meanTemp;
    // private double highestMonthlyMaxTemp;
    // private double lowestMonthlyMinTemp;
    private String meanTemp;
    private String highestMonthlyMaxTemp;
    private String lowestMonthlyMinTemp;

    // Constructors / Initializers
    Station() {
    }

    Station(String stationName, String province, LocalDate date, String meanTemp, String highestMonthlyMaxTemp,
            String lowestMonthlyMinTemp) {
        this.stationName = stationName;
        this.province = province;
        this.date = date;
        this.meanTemp = meanTemp;
        this.highestMonthlyMaxTemp = highestMonthlyMaxTemp;
        this.lowestMonthlyMinTemp = lowestMonthlyMinTemp;
    }

    // Accessors and Mutators (Getters and Setters)
    public Long getId() {
        return this.id;
    }

    public String getStationName() {
        return this.stationName;
    }

    public String getProvince() {
        return this.province;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDateFormattedString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.date.format(dateFormat);
    }

    public String getMeanTemp() {
        return this.meanTemp;
    }

    public String getHighestMonthlyMaxTemp() {
        return this.highestMonthlyMaxTemp;
    }

    public String getLowestMonthlyMinTemp() {
        return this.lowestMonthlyMinTemp;
    }

    /*
     * Currently there's no front end method of adding new Stations from the
     * frontend however this could be added in the future.
     */
    public void setId(Long id) {
        this.id = id;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setMeanTemp(String meanTemp) {
        this.meanTemp = meanTemp;
    }

    public void setHighestMonthlyMaxTemp(String highestMonthlyMaxTemp) {
        this.highestMonthlyMaxTemp = highestMonthlyMaxTemp;
    }

    public void setLowestMonthlyMinTemp(String lowestMonthlyMinTemp) {
        this.lowestMonthlyMinTemp = lowestMonthlyMinTemp;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Station))
            return false;
        Station Station = (Station) o;
        return Objects.equals(this.id, Station.id) && Objects.equals(this.stationName, Station.stationName)
                && Objects.equals(this.province, Station.province);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.stationName, this.province);
    }

    @Override
    public String toString() {
        return "Station{" + "id=" + this.id + ", name='" + this.stationName + '\'' + ", role='" + this.province + '\''
                + '}';
    }
}
