package tn.esprit.centralpurchasing.Services;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import tn.esprit.centralpurchasing.Entities.GeoIP;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface GeoIPLocationService {
    GeoIP getIpLocation(String ip, HttpServletRequest request) throws IOException, GeoIp2Exception;


}
