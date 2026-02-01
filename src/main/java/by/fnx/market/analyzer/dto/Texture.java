package by.fnx.market.analyzer.dto;

import lombok.Data;

@Data
public class Texture {

    private String imageUrl;
    private String name;
    private Boolean isStateAllowed;
    private Marketplace marketplace;
}
