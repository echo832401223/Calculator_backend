package com.calc.repository;

import com.calc.entity.CalcHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalcHistoryRepository extends JpaRepository<CalcHistory, Long> {
}
