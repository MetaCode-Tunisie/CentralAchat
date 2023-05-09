package tn.esprit.centralpurchasing.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tn.esprit.centralpurchasing.Entities.DashboardData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/dashboard-data")
    @ResponseBody
    public List<DashboardData> getDashboardData(@RequestParam(required = false) String search) {
        List<DashboardData> dashboardDataList = new ArrayList<>();

        // Retrieve all countries and their cities from the database
        String sql = "SELECT country, city, COUNT(*) as visit_count " +
                "FROM iplocation";
        if (search != null && !search.isEmpty()) {
            sql += " WHERE country LIKE '%" + search + "%'"; // Filter by search keyword
        }
        sql += " GROUP BY country, city";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        Map<String, List<String>> countryToCitiesMap = new HashMap<>();
        Map<String, Integer> countryToVisitsMap = new HashMap<>();
        for (Map<String, Object> row : rows) {
            String country = (String) row.get("country");
            String city = (String) row.get("city");
            int visit_count = ((Number) row.get("visit_count")).intValue();

            // Add the city to the list of cities for the corresponding country
            List<String> cities = countryToCitiesMap.getOrDefault(country, new ArrayList<>());
            cities.add(city);
            countryToCitiesMap.put(country, cities);

            // Add the visits to the total visits for the corresponding country
            int visit_Count = countryToVisitsMap.getOrDefault(country, 0);
            visit_Count += visit_count;
            countryToVisitsMap.put(country, visit_Count);
        }

        // Convert the maps to a list of DashboardData objects
        for (Map.Entry<String, List<String>> entry : countryToCitiesMap.entrySet()) {
            String country = entry.getKey();
            List<String> cities = entry.getValue();
            int visit_Count = countryToVisitsMap.get(country);

            DashboardData dashboardData = new DashboardData();
            dashboardData.setCountry(country);
            dashboardData.setNumCities(cities.size());
            dashboardData.setCities(cities);
            dashboardData.setVisits(visit_Count);

            dashboardDataList.add(dashboardData);
        }

        return dashboardDataList;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, @RequestParam(required = false) String search) {
        List<DashboardData> dashboardDataList = getDashboardData(search);
        model.addAttribute("dashboardDataList", dashboardDataList);
        return "dashboard";
    }
}


