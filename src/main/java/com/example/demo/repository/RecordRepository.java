package com.example.demo.repository;

import com.example.demo.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, String> {
    @Query(value = "SELECT * FROM record r " +
            "JOIN user_record ur ON r.id = ur.record_id " +
            "WHERE  ur.participant_id = :userId AND r.house_id = :houseId AND year(r.date) = :year AND month(r.date) = :month ORDER BY r.date desc ", nativeQuery = true)
    List<Record> findRecordsByParticipants(@Param("userId") Integer userId, @Param("houseId") String houseId, @Param("year") String year, @Param("month") String month);

    @Query(value = "SELECT * FROM record r " +
            "JOIN user_record ur ON r.id = ur.record_id " +
            "WHERE ur.participant_id = :userId AND r.payment_group = :groupId AND r.house_id = :houseId AND year(r.date) = :year AND month(r.date) = :month ORDER BY r.date desc", nativeQuery = true)
    List<Record> findRecordsByParticipantsAndPaymentGroup(@Param("userId") Integer userId, @Param("houseId") String houseId, @Param("groupId") Integer groupId, @Param("year") String year, @Param("month") String month);

    @Query(value = "SELECT * FROM record r " +
            "WHERE r.payer_id = :payerId AND r.payment_group = :groupId AND r.house_id = :houseId AND year(r.date) = :year AND month(r.date) = :month ORDER BY r.date desc", nativeQuery = true)
    List<Record> findRecordsByPayer_IdAndPaymentGroupAndHouse_Id(@Param("payerId") Integer payerId, @Param("groupId") Integer groupId, @Param("houseId") String houseId, @Param("year") String year, @Param("month") String month);

    @Query(value = "SELECT * FROM record r " +
            "WHERE r.payer_id = :payerId AND r.house_id = :houseId AND year(r.date) = :year AND month(r.date) = :month ORDER BY r.date desc", nativeQuery = true)
    List<Record> findRecordsByPayer_IdAndHouse_Id(@Param("payerId") Integer payerId, @Param("houseId") String houseId, @Param("year") String year, @Param("month") String month);

    @Query(value = "SELECT * FROM record r " +
            "WHERE r.payment_group = 0  AND r.house_id = :houseId AND year(r.date) = :year AND month(r.date) = :month ORDER BY r.date desc", nativeQuery = true)
    List<Record> findRecordsByHouseForAllMember(@Param("houseId") String houseId, @Param("year") String year, @Param("month") String month);

    @Query(value = "SELECT distinct CONCAT(LPAD(month(date), 2, '0'), '/', year(date)) AS record_date from record where house_id = :houseId ORDER BY record_date desc", nativeQuery = true)
    List<Object> findDateOfRecord(@Param("houseId") String houseId);

    @Query(value = """
            SELECT sum(record.money/(total.participants)) from  record
            join (select usr1.record_id, count(usr1.participant_id) as  participants from user_record usr1  group by record_id) as total on total.record_id = record.id
            join (select distinct usr2.record_id from user_record usr2 where  usr2.participant_id = :userId) as usr on  total.record_id = usr.record_id where year(date) = :year AND month(date) = :month AND house_id = :houseId""",nativeQuery = true)
    Integer findDebtMoneyByDate(@Param("userId") Integer userId, @Param("houseId") String houseId, @Param("year") String year, @Param("month") String month);
    @Query(value = "SELECT sum(record.money) from  record where payer_id = :userId AND year(date) = :year AND month(date) = :month AND house_id = :houseId",nativeQuery = true)
    Integer findPaidMoneyByDate(@Param("userId") Integer userId, @Param("houseId") String houseId, @Param("year") String year, @Param("month") String month);
    Record getRecordById(String id);
}
