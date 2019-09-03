package com.srsystems.challenge.state.domain;

import com.srsystems.challenge.city.domain.response.StateCityQuantityResponse;
import com.srsystems.challenge.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    @Query("SELECT new com.srsystems.challenge.city.domain.response.StateCityQuantityResponse(s.uf, COUNT(c.id) as qtt) FROM City c JOIN c.state s WHERE s.uf <> 'DF' GROUP BY s.uf ORDER BY qtt DESC")
    public List<StateCityQuantityResponse> getAmountOfCitiesByState();
}
