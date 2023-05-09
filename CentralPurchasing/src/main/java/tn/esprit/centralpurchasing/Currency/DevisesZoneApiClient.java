package tn.esprit.centralpurchasing.Currency;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class DevisesZoneApiClient {
    private final String baseUrl = "https://api.devises.zone/v1/convert";
    private final RestTemplate restTemplate;

    public DevisesZoneApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Double convertCurrency(String fromCurrency, String toCurrency, Double amount) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ConversionRequest> request = new HttpEntity<>(new ConversionRequest(fromCurrency, toCurrency, amount));
        ConversionResponse response = restTemplate.postForObject(baseUrl, request, ConversionResponse.class);
        return response.getResult();
    }

    private static class ConversionRequest {
        private String from;
        private String to;
        private Double amount;

        public ConversionRequest(String from, String to, Double amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public Double getAmount() {
            return amount;
        }
    }

    private static class ConversionResponse {
        private Double result;

        public Double getResult() {
            return result;
        }

        public void setResult(Double result) {
            this.result = result;
        }
    }


}
