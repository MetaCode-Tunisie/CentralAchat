package tn.esprit.centralpurchasing.Controller;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Currency;
import tn.esprit.centralpurchasing.Services.IServiceCurrency;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

@RestController
public class CurrencyController<OkHttpClient> {
    @Autowired
    IServiceCurrency ServiceCurrency;
    private final DatabaseReader databaseReader;
    public CurrencyController(DatabaseReader databaseReader) {
        this.databaseReader = databaseReader;
    }



    @PostMapping("/ajouterCurrency")
    public void ajouterCurrency(@RequestBody Currency currency){
        ServiceCurrency.ajouterCurrency(currency);
    }
    @GetMapping("/AfficherCurrency")
    public List<Currency> getAllCurrency() {
        return ServiceCurrency.getAllCurrency();
    }

   @PutMapping("/{idCurrency}")
    public Currency  updateCurrency(@PathVariable Long idCurrency, @RequestBody Currency currency) {
      return   ServiceCurrency.updateCurrency(idCurrency,currency);
    }
        private static final String API_KEY = "Ha2F8dPoX9oxiwRhFUo8IOayTjMKnX5m";
        private static final String API_URL = "https://api.exchangeratesapi.io/latest";

    @GetMapping("/convert")
    public double convertCurrency(@RequestParam String to, @RequestParam double amount, HttpServletRequest request) {
        String from = getCurrencyCodeFromIp(request);
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient().newBuilder().build();
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



