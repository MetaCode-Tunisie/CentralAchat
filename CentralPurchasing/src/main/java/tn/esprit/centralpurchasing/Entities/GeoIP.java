package tn.esprit.centralpurchasing.Entities;

public class GeoIP {
    private String ipAddress;
    private String country;
    private String city;
    private String continent;
    private String fullLocation;
    private double latitude;
    private double longitude;
    private String device;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getFullLocation() {
        return fullLocation;
    }

    public void setFullLocation(String fullLocation) {
        this.fullLocation = fullLocation;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "GeoIP{" +
                "ipAddress='" + ipAddress + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", continent='" + continent + '\'' +
                ", fullLocation='" + fullLocation + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", device='" + device + '\'' +
                '}';
    }

    public void setCurrency(String currency) {
    }
}