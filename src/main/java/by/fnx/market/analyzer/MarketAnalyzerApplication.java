package by.fnx.market.analyzer;

import by.fnx.market.analyzer.servise.MarketPlaceStatisticsService;
import by.fnx.market.analyzer.servise.impl.ClothingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketAnalyzerApplication implements CommandLineRunner {

    @Autowired
    private ClothingItemService clothingItemService;
    @Autowired
    private MarketPlaceStatisticsService marketPlaceStatisticsService;

	static void main(String[] args) {
		SpringApplication.run(MarketAnalyzerApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        if (marketPlaceStatisticsService.isTodayStatisticsAvailable()) {
            return;
        }



        var result = clothingItemService.searchClothes("псы", 1);
        // todo save result to db

        System.out.println(result);
    }
}
