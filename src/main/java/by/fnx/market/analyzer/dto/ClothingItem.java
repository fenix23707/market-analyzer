package by.fnx.market.analyzer.dto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClothingItem {

    @JsonProperty("_id")
    private String id;

    private String gender;
    private String priceType;
    private String type;
    private Integer price;
    private List<Texture> textures;
    private Integer capacityKg;
    private Boolean isPrivate;
    private Boolean isBuyable;
    private Boolean isTradable;
    private List<String> sources;
    private Instant createdAt;
    private Instant updatedAt;

    @JsonProperty("__v")
    private Integer version;

    private Boolean isFresh;
    private String priceDescription;
}
