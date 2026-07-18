package com.cognizant.exercise.week2.springdatajpa.ormlearn.service;

import com.cognizant.exercise.week2.springdatajpa.ormlearn.model.Stock;
import com.cognizant.exercise.week2.springdatajpa.ormlearn.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service(value = "ormlearnStockService")
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Transactional(readOnly = true)
    public List<Stock> getFacebookStockInSeptember2019() {
        Calendar cal = Calendar.getInstance();
        cal.set(2019, Calendar.SEPTEMBER, 1, 0, 0, 0);
        Date startDate = cal.getTime();
        cal.set(2019, Calendar.SEPTEMBER, 30, 23, 59, 59);
        Date endDate = cal.getTime();

        return stockRepository.findByCodeAndDateBetween("FB", startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<Stock> getGoogleStockGreaterThan1250() {
        return stockRepository.findByCodeAndCloseGreaterThan("GOOGL", new BigDecimal("1250.00"));
    }

    @Transactional(readOnly = true)
    public List<Stock> getTop3HighestVolumeStocks() {
        return stockRepository.findTop3ByOrderByVolumeDesc();
    }
}
