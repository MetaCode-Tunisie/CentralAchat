package tn.esprit.centralpurchasing.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URL;

@Service
public class ServiceMapbox implements IServiceMapbox{

    @Override
    public double getInfo(String x1 , String y1 ,String x2 ,String y2) throws IOException {
        URL url = new URL("https://api.mapbox.com/directions/v5/mapbox/driving/"+x1+"%2C"+y1+"%3B"+x2+"%2C"+y2+"?access_token=pk.eyJ1IjoibmFzcmVkZGluZTEyMzQiLCJhIjoiY2xlaDU4OHJyMTdkejNvb3lkenhtOXlyeSJ9.57G8_EmIkUVPPjPNfT71jw");
        ObjectMapper objectMapper = new ObjectMapper();
        Object object = objectMapper.readValue(url, Object.class);
        ObjectMapper mapper = new ObjectMapper();
        String jsonstr = mapper.writeValueAsString(object);
        JsonNode json = mapper.readTree(jsonstr);
        JsonNode routes = json.get("routes");
        JsonNode firstRoute = routes.get(0);
        JsonNode legs = firstRoute.get("legs");
        JsonNode firstLeg = legs.get(0);
        JsonNode distance = firstLeg.get("distance");
// get distance value as double in km
        return distance.asDouble()/1000;
        }
    }



