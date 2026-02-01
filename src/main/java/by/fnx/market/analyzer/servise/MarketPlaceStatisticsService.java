package by.fnx.market.analyzer.servise;

import java.util.List;

import by.fnx.market.analyzer.dto.Marketplace;

public interface MarketPlaceStatisticsService {

    boolean isTodayStatisticsAvailable();

    void saveTodayStatistics(String clothingItemId, List<Marketplace> statistics);
}
