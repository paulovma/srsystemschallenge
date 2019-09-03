package com.srsystems.challenge.city.domain;

import com.srsystems.challenge.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findAllByCapitalOrderByName(Boolean capital);

    City findByIbgeId(Long ibgeId);

    List<City> findAllByStateUf(String state);

    void deleteByIbgeId(Long ibgeId);
}
