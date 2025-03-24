package hr.tvz.knezevic.njamapp.model;

import java.time.DayOfWeek;
import java.util.Map;

public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private Map<DayOfWeek, String> workingHours;
    private boolean opened;
    private Double averageDeliveryTime;
    private Double averageCustomerRating;
    private Integer maxNumberOfOrders;

    public Restaurant() {}

    public Restaurant(Long id, String name, String address, String phoneNumber, String email,
                      Map<DayOfWeek, String> workingHours, boolean opened, Double averageDeliveryTime,
                      Double averageCustomerRating, Integer maxNumberOfOrders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.workingHours = workingHours;
        this.opened = opened;
        this.averageDeliveryTime = averageDeliveryTime;
        this.averageCustomerRating = averageCustomerRating;
        this.maxNumberOfOrders = maxNumberOfOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<DayOfWeek, String> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Map<DayOfWeek, String> workingHours) {
        this.workingHours = workingHours;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public Double getAverageDeliveryTime() {
        return averageDeliveryTime;
    }

    public void setAverageDeliveryTime(Double averageDeliveryTime) {
        this.averageDeliveryTime = averageDeliveryTime;
    }

    public Double getAverageCustomerRating() {
        return averageCustomerRating;
    }

    public void setAverageCustomerRating(Double averageCustomerRating) {
        this.averageCustomerRating = averageCustomerRating;
    }

    public Integer getMaxNumberOfOrders() {
        return maxNumberOfOrders;
    }

    public void setMaxNumberOfOrders(Integer maxNumberOfOrders) {
        this.maxNumberOfOrders = maxNumberOfOrders;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", workingHours=" + workingHours +
                ", opened=" + opened +
                ", averageDeliveryTime=" + averageDeliveryTime +
                ", averageCustomerRating=" + averageCustomerRating +
                ", maxNumberOfOrders=" + maxNumberOfOrders +
                '}';
    }
}
