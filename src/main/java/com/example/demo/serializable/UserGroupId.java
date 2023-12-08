package com.example.demo.serializable;

import com.example.demo.entity.UserGroup;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserGroupId implements Serializable {
    @Column(name = "group_id")
    Integer groupId;
    @Column(name="userid")
    Integer userId;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroupId that = (UserGroupId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, groupId);
    }
}
