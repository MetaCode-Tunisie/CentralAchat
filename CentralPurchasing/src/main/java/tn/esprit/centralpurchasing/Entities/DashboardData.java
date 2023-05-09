package tn.esprit.centralpurchasing.Entities;

import java.util.List;

public   class DashboardData {
    private String country;
    private int numCities;
    private List<String> cities;
    private int visits;

    // getters and setters
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumCities() {
        return numCities;
    }

    public void setNumCities(int numCities) {
        this.numCities = numCities;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

public int getVisits(){
        return visits;
}
    public void setVisits(int visitCount) {
    }

}