package by.fnx.market.analyzer.servise.impl;

import by.fnx.market.analyzer.dto.ClothingItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClothingItemService {

    private final Pattern dataPattern = Pattern.compile("\\\\\\\"results\\\\\\\":(\\[.*?\\])(?:,|})");

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String clothingBaseUrl;

    public ClothingItemService(RestTemplate restTemplate,
                               ObjectMapper objectMapper,
                               @Value("${app.clothing.base-url}") String clothingBaseUrl) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.clothingBaseUrl = clothingBaseUrl;
    }

    // TODO find out why it's not working
    public List<ClothingItem> searchClothes(String searchTerm, int page) {
        // 1. Build the URL with parameters safely
        String url = UriComponentsBuilder.fromUri(URI.create(clothingBaseUrl))
            .queryParam("search", searchTerm)
            .queryParam("page", page)
            .toUriString();

        // 2. Set Headers (Important for scraping!)
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // 3. Execute Request
            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return extractDataFromHtml(response.getBody());
            }

        } catch (Exception e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    private List<ClothingItem> extractDataFromHtml(String html) {
        Matcher matcher = dataPattern.matcher(html);

        if (matcher.find()) {
            String rawJson = matcher.group(1);

            // 4. Clean the escaped JSON string
            String cleanJson = rawJson
                .replace("\\\"", "\"")
                .replace("\\\\", "\\");

            try {
                // 5. Parse to List
                return objectMapper.readValue(cleanJson, new TypeReference<>() {});
            } catch (Exception e) {
                System.err.println("JSON Parse Error: " + e.getMessage());
            }
        }
        return Collections.emptyList();
    }
}