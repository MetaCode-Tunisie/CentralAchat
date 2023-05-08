package tn.esprit.centralpurchasing.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.GeoIP;
import tn.esprit.centralpurchasing.Entities.IPlocation;
import tn.esprit.centralpurchasing.Repository.IPLocationRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import static java.util.Objects.nonNull;

@Service
public class GeoIPLocationServiceImpl implements GeoIPLocationService {

    private final DatabaseReader databaseReader;
    private static final String UNKNOWN = "UNKNOWN";
    private final IPLocationRepository iPLocationRepository;

    public GeoIPLocationServiceImpl(DatabaseReader databaseReader,
                                    IPLocationRepository iPLocationRepository) {
        this.databaseReader = databaseReader;
        this.iPLocationRepository = iPLocationRepository;
    }


    private String getDeviceDetails(String userAgent) throws IOException {
        String deviceDetails = UNKNOWN;


        return deviceDetails;
    }

    @Override
    public GeoIP getIpLocation(String ip, HttpServletRequest request) throws IOException, GeoIp2Exception {
        GeoIP position = new GeoIP();
        String location;

        InetAddress ipAddress = InetAddress.getByName(ip);

        CityResponse cityResponse = databaseReader.city(ipAddress);
        if (nonNull(cityResponse) && nonNull(cityResponse.getCity())) {

            String continent = (cityResponse.getContinent() != null) ? cityResponse.getContinent().getName() : "";
            String country = (cityResponse.getCountry() != null) ? cityResponse.getCountry().getName() : "";
            String currencyCode = getCurrencyCode(country);

            location = String.format("%s, %s, %s", continent, country, cityResponse.getCity().getName());
            position.setCountry(cityResponse.getCountry().getName());
            position.setCity(cityResponse.getCity().getName());
            position.setFullLocation(location);
            position.setDevice(getDeviceDetails(request.getHeader("user-agent")));
            position.setIpAddress(ip);

            IPlocation ipLocation = iPLocationRepository.findByIpAddress(ip);
            if (ipLocation == null) {
                ipLocation = new IPlocation();
                ipLocation.setIpAddress(ip);
                ipLocation.setVisitCount(1L);
            } else {
                ipLocation.setVisitCount(ipLocation.getVisitCount() + 1);
            }
            ipLocation.setCity(cityResponse.getCity().getName());
            ipLocation.setCountry(cityResponse.getCountry().getName());
            ipLocation.setContinent(continent);
            ipLocation.setCurrencyCode(currencyCode);
            position.setCurrency(currencyCode);// set the currency code in the IPlocation entity
            iPLocationRepository.save(ipLocation);

        }

        return position;
    }

    private String getCurrencyCode(String country) throws IOException {
        String appId = "8281f204ae604b9c87161a7f036e77c5"; // Replace with your Open Exchange Rates App ID
        String url = String.format("https://openexchangerates.org/api/currencies.json?app_id=%s", appId);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new URL(url));

        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            if (field.getValue().asText().equalsIgnoreCase(country)) {
                return field.getKey();
            }
        }
        return "";
    }
}
