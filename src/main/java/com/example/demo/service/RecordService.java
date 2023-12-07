package com.example.demo.service;

import com.example.demo.entity.Record;
import com.example.demo.model.dto.RecordBody;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecordService {
    public List<Record> getAllRecord();
    public List<Record> getRecordByUsersAndGroup(Integer userId, Integer groupId, String HouseId);
    public List<Record> getAllRecordByUsersAndHouse(Integer userId, String houseId);
    public List<Record> getRecordByHouseForAllMember(String houseId);
    public List<Record> getAllRecordByPayerAndHouse(Integer payerId, String houseId);
    public List<Record> getRecordByPayerAndGroup(Integer payerId, Integer groupId, String houseId);
    public List<Record> getRecordByPayerAndHouse(Integer payerId, String houseId);
    public List<Record> getRecordByUsersAndOther(Integer userId, String houseId);
    public List<Record> getRecordByPayerAndOther(Integer payerId, String houseId);
    public void createRecord(RecordBody recordBody);
}
