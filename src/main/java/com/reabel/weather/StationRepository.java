package com.reabel.weather;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.time.LocalDate;

interface StationRepository extends JpaRepository<Station, Long> {
    List<Station> findByOrderByDateDesc();

    List<Station> findByOrderByDateAsc();

    List<Station> findByDateBetween(LocalDate dateFrom, LocalDate dateTo);
}
