package tn.esprit.centralpurchasing.Services;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IServiceSMS {
    public Boolean SendSMS(String to) throws IOException;
}
