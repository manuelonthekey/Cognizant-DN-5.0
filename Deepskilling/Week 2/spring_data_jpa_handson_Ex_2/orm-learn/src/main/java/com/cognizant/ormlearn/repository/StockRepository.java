package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    // All stock details of a given code between two dates (e.g. Facebook, Sep 2019)
    List<Stock> findByCodeAndDateBetween(String code, LocalDate startDate, LocalDate endDate);

    // All stock details of a given code where the closing price was greater than a value
    List<Stock> findByCodeAndCloseGreaterThan(String code, BigDecimal price);

    // Top 3 dates with the highest transaction volume, across all stocks
    List<Stock> findTop3ByOrderByVolumeDesc();

    // Three dates when a given stock's closing price was the lowest
    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
}
