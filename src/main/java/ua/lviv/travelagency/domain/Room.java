package ua.lviv.travelagency.domain;

public class Room {
    private Integer id;
    private Integer capacity;
    private String type;
    private boolean wifi;
    private boolean breakfast;
    private float price;

    public Room() {
    }

    public Room(Integer capacity, String type, boolean wifi, boolean breakfast, float price) {
        this.capacity = capacity;
        this.type = type;
        this.wifi = wifi;
        this.breakfast = breakfast;
        this.price = price;
    }

    public Room(Integer id, Integer capacity, String type, boolean wifi, boolean breakfast, float price) {
        this.id = id;
        this.capacity = capacity;
        this.type = type;
        this.wifi = wifi;
        this.breakfast = breakfast;
        this.price = price;
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

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", type='" + type + '\'' +
                ", wifi=" + wifi +
                ", breakfast=" + breakfast +
                ", price=" + price +
                '}';
    }
}
