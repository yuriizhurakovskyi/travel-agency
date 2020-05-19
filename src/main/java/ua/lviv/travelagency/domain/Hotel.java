package ua.lviv.travelagency.domain;

import java.util.Objects;

public class Hotel {

    private Integer id;
    private String name;
    private Integer rating;
    private String country;
    private String city;
    private Integer room_count;
    private Integer agencyId;

    public Hotel() {
    }

    public Hotel(String name, Integer rating, String country, String city, Integer room_count, Integer agencyId) {
        this.name = name;
        this.rating = rating;
        this.country = country;
        this.city = city;
        this.room_count = room_count;
        this.agencyId = agencyId;
    }

    public Hotel(Integer id, String name, Integer rating, String country, String city, Integer room_count, Integer agencyId) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.country = country;
        this.city = city;
        this.room_count = room_count;
        this.agencyId = agencyId;
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

    public Integer getRoom_count() {
        return room_count;
    }

    public void setRoom_count(Integer room_count) {
        this.room_count = room_count;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(id, hotel.id) &&
                Objects.equals(name, hotel.name) &&
                Objects.equals(rating, hotel.rating) &&
                Objects.equals(country, hotel.country) &&
                Objects.equals(city, hotel.city) &&
                Objects.equals(room_count, hotel.room_count) &&
                Objects.equals(agencyId, hotel.agencyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating, country, city, room_count, agencyId);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", room_count=" + room_count +
                ", agencyId=" + agencyId +
                '}';
    }
}
