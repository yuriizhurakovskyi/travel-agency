package ua.lviv.travelagency.domain;

public class Hotel {

    private Integer id;
    private String name;
    private Integer rating;
    private String country;
    private String city;
    private Integer roomCount;

    public Hotel() {
    }

    public Hotel(Integer id, String name, Integer rating, String country, String city, Integer roomCount) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.country = country;
        this.city = city;
        this.roomCount = roomCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
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

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", roomCount=" + roomCount +
                '}';
    }
}
