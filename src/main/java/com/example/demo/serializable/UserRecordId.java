package com.example.demo.serializable;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRecordId implements Serializable {
    @Column(name = "record_id")
    Integer recordId;
    @Column(name="participant_id")
    Integer participantId;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRecordId that = (UserRecordId) o;
        return Objects.equals(participantId, that.participantId) &&
                Objects.equals(recordId, that.recordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participantId, recordId);
    }
}
