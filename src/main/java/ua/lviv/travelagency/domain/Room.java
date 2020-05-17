package ua.lviv.travelagency.domain;

public class Room {
    private Integer id;
    private Integer capacity;
    private String type;
    private Boolean wifiIncluded;
    private Boolean breakfastIncluded;
    private Double price;
    private Integer hotelId;

    public Room() {
    }

    public Room(Integer capacity, String type, Boolean wifiIncluded, Boolean breakfastIncluded, Double price, Integer hotelId) {
        this.capacity = capacity;
        this.type = type;
        this.wifiIncluded = wifiIncluded;
        this.breakfastIncluded = breakfastIncluded;
        this.price = price;
        this.hotelId = hotelId;
    }

    public Room(Integer id, Integer capacity, String type, Boolean wifiIncluded, Boolean breakfastIncluded, Double price, Integer hotelId) {
        this.id = id;
        this.capacity = capacity;
        this.type = type;
        this.wifiIncluded = wifiIncluded;
        this.breakfastIncluded = breakfastIncluded;
        this.price = price;
        this.hotelId = hotelId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getWifiIncluded() {
        return wifiIncluded;
    }

    public void setWifiIncluded(Boolean wifiIncluded) {
        this.wifiIncluded = wifiIncluded;
    }

    public Boolean getBreakfastIncluded() {
        return breakfastIncluded;
    }

    public void setBreakfastIncluded(Boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", type='" + type + '\'' +
                ", wifiIncluded=" + wifiIncluded +
                ", breakfastIncluded=" + breakfastIncluded +
                ", price=" + price +
                ", hotelId=" + hotelId +
                '}';
    }
}
