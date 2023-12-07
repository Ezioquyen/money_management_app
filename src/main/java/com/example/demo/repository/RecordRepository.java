package com.example.demo.repository;

import com.example.demo.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Integer> {
    @Query(value = "SELECT * FROM record r " +
            "JOIN user_record ur ON r.id = ur.record_id " +
            "WHERE ur.participant_id = :userId AND r.house_id = :houseId", nativeQuery = true)
    public List<Record> findRecordsByParticipants(@Param("userId") int userId, @Param("houseId") String houseId);
    @Query(value = "SELECT * FROM record r " +
            "JOIN user_record ur ON r.id = ur.record_id " +
            "WHERE ur.participant_id = :userId AND r.payment_group = :groupId AND r.house_id = :houseId", nativeQuery = true)
    public List<Record> findRecordsByParticipantsAndPaymentGroup(@Param("userId") int userId, @Param("houseId") String houseId, @Param("groupId") int groupId);
    public List<Record> findRecordsByPayer_IdAndPaymentGroupAndHouse_Id(Integer payerId, Integer group, String houseId);
    public List<Record> findRecordsByPayer_IdAndHouse_Id(Integer payerId, String houseId);
    //done
    @Query(value = "SELECT * FROM record r " +
            "WHERE r.payment_group = 0  AND r.house_id = :houseId", nativeQuery = true)
    public List<Record> findRecordsByHouseForAllMember(String houseId);
}
