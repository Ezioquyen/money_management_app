package com.example.demo.service;

import com.example.demo.entity.Record;
import com.example.demo.model.dto.RecordBody;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public interface RecordService {
    public List<RecordBody> getRecordByUsersAndGroup(Integer userId, Integer groupId, String HouseId, String year, String month);
    public List<RecordBody> getAllRecordByUsersAndHouse(Integer userId, String houseId,String year, String month);
    public List<RecordBody> getRecordByHouseForAllMember(String houseId,String year, String month);
    public List<RecordBody> getAllRecordByPayerAndHouse(Integer payerId, String houseId,String year, String month);
    public List<RecordBody> getRecordByPayerAndGroup(Integer payerId, Integer groupId, String houseId,String year, String month);
    public List<RecordBody> getRecordByPayerAndHouse(Integer payerId, String houseId,String year, String month);
    public List<RecordBody> getRecordByUsersAndOther(Integer userId, String houseId,String year, String month);
    public List<RecordBody> getRecordByPayerAndOther(Integer payerId, String houseId,String year, String month);
    public void createRecord(RecordBody recordBody, Integer id);
    public List<Object> findDateOfRecords(String houseId);
    public Integer findPaidMoneyByDate( Integer userId,String houseId,  String year, String month);
    public Integer findDebtMoneyByDate( Integer userId, String houseId, String year, String month);
}
