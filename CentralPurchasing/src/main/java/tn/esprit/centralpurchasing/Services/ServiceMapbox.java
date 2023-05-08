package tn.esprit.centralpurchasing.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapbox.api.geocoding.v5.MapboxGeocoding;
import com.mapbox.api.geocoding.v5.models.GeocodingResponse;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceMapbox implements IServiceMapbox{

    @Override
    public Map<String , String> getInfo(String x1 , String y1 , String x2 , String y2) throws IOException {
        URL url = new URL("https://api.mapbox.com/directions/v5/mapbox/driving/"+x1+"%2C"+y1+"%3B"+x2+"%2C"+y2+"?access_token=pk.eyJ1IjoibmFzcmVkZGluZTEyMzQiLCJhIjoiY2xlaDU4OHJyMTdkejNvb3lkenhtOXlyeSJ9.57G8_EmIkUVPPjPNfT71jw");
        Map<String, String> result = new HashMap<>();
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
        JsonNode duration = firstLeg.get("duration");
// get distance value as double in km

        int seconds = (duration.asInt())%60;
        int minutes = (duration.asInt()/60) %60;
        int hours = (duration.asInt()/(60*60)) %60;

        double d = distance.asDouble()/1000;
        String durationn = hours+" hour"+" "+minutes+" "+"minutes,"+" "+seconds+" "+"Secondes ";
        String distancee = " "+d+" "+"km";
        result.put("Distance" , distancee);
        result.put("Duration" , durationn);
        return result;
    }

}




