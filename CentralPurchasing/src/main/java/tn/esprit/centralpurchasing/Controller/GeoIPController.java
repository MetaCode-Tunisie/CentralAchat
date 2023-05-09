package tn.esprit.centralpurchasing.Controller;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.GeoIP;
import tn.esprit.centralpurchasing.Services.GeoIPLocationService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;

@RestController
public class GeoIPController {

    private final GeoIPLocationService geoIPLocationService;

    public GeoIPController(GeoIPLocationService geoIPLocationService) {
        this.geoIPLocationService = geoIPLocationService;
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @GetMapping("/geoIP/")
    public GeoIP getLocation(HttpServletRequest request) throws IOException, GeoIp2Exception {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return geoIPLocationService.getIpLocation(ipAddress, request);
    }


    @RestController
    @RequestMapping("/currency-converter")

    public static class CurrencyConverterController {
        private static final String API_KEY = "Ha2F8dPoX9oxiwRhFUo8IOayTjMKnX5m";
        private static final String API_URL = "https://api.exchangeratesapi.io/latest";
        private final DatabaseReader databaseReader;

        public CurrencyConverterController(DatabaseReader databaseReader) {
            this.databaseReader = databaseReader;
        }

        @GetMapping("/convert")
        public double convertCurrency(@RequestParam String to, @RequestParam double amount, HttpServletRequest request) {
            String from = getCurrencyCodeFromIp(request);
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request apiRequest = new Request.Builder()
                    .url("https://api.apilayer.com/exchangerates_data/convert?to=" + to + "&from=" + from + "&amount=" + amount)
                    .addHeader("apikey", "Ha2F8dPoX9oxiwRhFUo8IOayTjMKnX5m")
                    .method("GET", null)
                    .build();
            try (Response response = client.newCall(apiRequest).execute()) {
                String responseBody = response.body().string();
                JSONObject jsonObject = new JSONObject(responseBody);
                double convertedAmount = jsonObject.getDouble("result");
                return convertedAmount;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (JSONException e) {
                System.out.println(e.getMessage());
            }

            return 0;
        }

        private String getCurrencyCodeFromIp(HttpServletRequest request) {
            String ipAddress = request.getRemoteAddr();
            try {
                InetAddress inetAddress = InetAddress.getByName(ipAddress);
                CityResponse cityResponse = databaseReader.city(inetAddress);
                if (cityResponse != null && cityResponse.getCountry() != null) {
                    return cityResponse.getCountry().getIsoCode();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return "TND";
        }
    }
}





