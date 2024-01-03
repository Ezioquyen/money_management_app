package com.example.demo.repository;

import com.example.demo.entity.PaymentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaymentGroupRepository extends JpaRepository<PaymentGroup,Integer> {
    public PaymentGroup getPaymentGroupById(Integer id);
    public List<PaymentGroup> getPaymentGroupsByHouse_Id(String id);
    @Query(value = "SELECT * FROM payment_group pg " +
            "JOIN user_group ug ON pg.id = ug.group_id " +
            "WHERE ug.userid = :userId AND pg.house_id = :houseId", nativeQuery = true)
    public List<PaymentGroup> getPaymentGroupByUserAndHouse(@Param("userId") Integer userId,@Param("houseId") String houseId);
}
