package com.reabel.weather;

import org.springframework.data.jpa.repository.JpaRepository;

interface StationRepository extends JpaRepository<Station, Long> {

}
