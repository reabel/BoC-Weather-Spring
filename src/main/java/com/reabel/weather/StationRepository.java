package com.reabel.weather;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

interface StationRepository extends JpaRepository<Station, Long> {
    List<Station> findByOrderByDateDesc();

    List<Station> findByOrderByDateAsc();
}
