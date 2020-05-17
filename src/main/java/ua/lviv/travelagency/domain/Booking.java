package ua.lviv.travelagency.domain;

import java.util.Date;

public class Booking {
    private Date date;
    private Integer roomId;
    private Integer userId;
    private Integer id;


    public Booking() {
    }

    public Booking(Date date, Integer roomId, Integer userId) {
        this.date = date;
        this.roomId = roomId;
        this.userId = userId;
    }

    public Booking(Date date, Integer roomId, Integer userId, Integer id) {
        this.date = date;
        this.roomId = roomId;
        this.userId = userId;
        this.id = id;
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
    public String toString() {
        return "Booking{" +
                "date=" + date +
                ", roomId=" + roomId +
                ", userId=" + userId +
                ", id=" + id +
                '}';
    }
}
