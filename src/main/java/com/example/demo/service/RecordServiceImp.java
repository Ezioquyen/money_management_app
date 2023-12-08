package com.example.demo.service;

import com.example.demo.entity.House;
import com.example.demo.entity.Record;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRecord;
import com.example.demo.model.dto.RecordBody;
import com.example.demo.repository.HouseRepository;
import com.example.demo.repository.RecordRepository;
import com.example.demo.repository.UserRecordRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.serializable.UserRecordId;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RecordServiceImp implements RecordService{
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private final UserRecordRepository userRecordRepository;


    @Autowired
    public RecordServiceImp(RecordRepository recordRepository, UserRepository userRepository, HouseRepository houseRepository, UserRecordRepository userRecordRepository) {
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
        this.houseRepository = houseRepository;
        this.userRecordRepository = userRecordRepository;
    }

    @Override
    public List<Record> getAllRecord() {
        return recordRepository.findAll();
    }

    @Override
    public List<Record> getAllRecordByUsersAndHouse(Integer userId, String houseId) {
        return recordRepository.findRecordsByParticipants(userId,houseId);
    }

    @Override
    public List<Record> getRecordByHouseForAllMember(String houseId) {
        return recordRepository.findRecordsByHouseForAllMember(houseId);
    }

    @Override
    public List<Record> getAllRecordByPayerAndHouse(Integer payerId, String houseId) {
        return recordRepository.findRecordsByPayer_IdAndHouse_Id(payerId,houseId);
    }

    @Override
    public List<Record> getRecordByPayerAndGroup(Integer payerId, Integer groupId, String houseId) {
        return recordRepository.findRecordsByPayer_IdAndPaymentGroupAndHouse_Id(payerId, groupId, houseId);
    }

    @Override
    public List<Record> getRecordByPayerAndHouse(Integer payerId, String houseId) {
        return recordRepository.findRecordsByPayer_IdAndPaymentGroupAndHouse_Id(payerId, 0 ,houseId);
    }

    @Override
    public List<Record> getRecordByUsersAndOther(Integer userId, String houseId) {
        return recordRepository.findRecordsByParticipantsAndPaymentGroup(userId,houseId,-1);
    }

    @Override
    public List<Record> getRecordByPayerAndOther(Integer payerId, String houseId) {
        return recordRepository.findRecordsByPayer_IdAndPaymentGroupAndHouse_Id(payerId,-1,houseId);
    }
    @Override
    public void createRecord(RecordBody recordBody) {
        User payer = userRepository.findById(recordBody.getPayerId()).orElseThrow();
        House house = houseRepository.findById(recordBody.getHouseId()).orElseThrow();
        Set<User> participants = new HashSet<>(userRepository.findAllById(recordBody.getParticipantIds()));

        Record record = new Record();
        record.setMoney(recordBody.getMoney());
        record.setDate(recordBody.getDate());
        record.setInformation(recordBody.getInformation());
        record.setPaymentGroup(recordBody.getGroup());
        record.setPaid(recordBody.isPaid());
        record.setPayer(payer);
        record.setHouse(house);
        recordRepository.save(record);
        for (User participant : participants) {
            UserRecord userRecord = new UserRecord();
            userRecord.setId(new UserRecordId());
            userRecord.setParticipant(participant);
            userRecord.setRecord(record);
            userRecordRepository.save(userRecord);
        }
    }

    @Override
    public List<Record> getRecordByUsersAndGroup(Integer userId, Integer groupId, String houseId) {
        return recordRepository.findRecordsByParticipantsAndPaymentGroup(userId, houseId, groupId);
    }
}
