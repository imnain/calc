package org.testing.calc.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.testing.calc.dao.entity.Calc;

@Repository
public interface CalcRepository extends JpaRepository<Calc, Integer> {

    Calc findByKeyValue(String keyValue);
}

