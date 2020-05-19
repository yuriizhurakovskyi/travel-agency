package ua.lviv.travelagency.domain;

import java.util.Date;
import java.util.Objects;

public class Booking {
    private Integer id;
    private Date date;
    private Integer roomId;
    private Integer userId;

    public Booking() {
    }

    public Booking(Date date, Integer roomId, Integer userId) {
        this.date = date;
        this.roomId = roomId;
        this.userId = userId;
    }

    public Booking(Integer id, Date date, Integer roomId, Integer userId) {
        this.id = id;
        this.date = date;
        this.roomId = roomId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) &&
                Objects.equals(date, booking.date) &&
                Objects.equals(roomId, booking.roomId) &&
                Objects.equals(userId, booking.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, roomId, userId);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", date=" + date +
                ", roomId=" + roomId +
                ", userId=" + userId +
                '}';
    }
}
