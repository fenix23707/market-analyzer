package by.fnx.market.analyzer.servise.impl;

import java.util.List;

import by.fnx.market.analyzer.dto.Marketplace;
import by.fnx.market.analyzer.repository.MarketPlaceStatisticsRepository;
import by.fnx.market.analyzer.servise.MarketPlaceStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MarketPlaceStatisticsServiceImpl implements MarketPlaceStatisticsService {

    private final MarketPlaceStatisticsRepository marketPlaceStatisticsRepository;

    @Override
    public boolean isTodayStatisticsAvailable() {
        // TODO implement
        return false;
    }

    @Override
    public void saveTodayStatistics(String clothingItemId, List<Marketplace> statistics) {
        // TODO implement
    }
}
