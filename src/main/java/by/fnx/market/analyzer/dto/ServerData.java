package by.fnx.market.analyzer.dto;

import lombok.Data;

@Data
public class ServerData {

    private String serverId;
    private String serverName;
    private Integer soldCount;
    private Integer averagePrice;
    private Integer minPrice;
    private Integer maxPrice;
}
