package com.example.demo.serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserHouseId implements Serializable {
    @Column(name = "house_id")
    String houseId;
    @Column(name="userid")
    int userId;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHouseId that = (UserHouseId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(houseId, that.houseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, houseId);
    }
}
