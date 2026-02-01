package by.fnx.market.analyzer.repository;

import java.time.Instant;

public interface MarketPlaceStatisticsRepository {

    boolean isStatisticsAvailableByTimestamp(Instant instant);

}
