package tn.esprit.centralpurchasing.Services;

import java.io.IOException;

public interface IServiceMapbox {

    public double getInfo(String x1 , String y1 ,String x2 ,String y2) throws IOException;
}
