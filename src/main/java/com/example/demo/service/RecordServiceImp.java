package com.example.demo.service;

import com.example.demo.entity.House;
import com.example.demo.entity.Record;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRecord;
import com.example.demo.model.dto.RecordBody;
import com.example.demo.model.mapper.RecordMapper;
import com.example.demo.repository.HouseRepository;
import com.example.demo.repository.RecordRepository;
import com.example.demo.repository.UserRecordRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.serializable.UserRecordId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RecordServiceImp implements RecordService {
    private final RecordRepository recordRepository;

    private final UserRecordRepository userRecordRepository;


    @Autowired
    public RecordServiceImp(RecordRepository recordRepository, UserRepository userRepository, HouseRepository houseRepository, UserRecordRepository userRecordRepository) {
        this.recordRepository = recordRepository;
        this.userRecordRepository = userRecordRepository;
    }


    @Override
    public List<RecordBody> getAllRecordByUsersAndHouse(Integer userId, String houseId, String year, String month) {
        List<RecordBody> recordBodyList = new ArrayList<>();
        for (Record record : recordRepository.findRecordsByParticipants(userId, houseId, year, month)) {
            recordBodyList.add(RecordMapper.toRecordBody(record));
        }
        return recordBodyList;
    }

    @Override
    public List<RecordBody> getRecordByHouseForAllMember(String houseId, String year, String month) {
        List<RecordBody> recordBodyList = new ArrayList<>();
        for (Record record : recordRepository.findRecordsByHouseForAllMember(houseId, year, month)) {
            recordBodyList.add(RecordMapper.toRecordBody(record));
        }
        return recordBodyList;
    }

    @Override
    public List<RecordBody> getAllRecordByPayerAndHouse(Integer payerId, String houseId, String year, String month) {
        List<RecordBody> recordBodyList = new ArrayList<>();
        for (Record record : recordRepository.findRecordsByPayer_IdAndHouse_Id(payerId, houseId, year, month)) {
            recordBodyList.add(RecordMapper.toRecordBody(record));
        }
        return recordBodyList;
    }

    @Override
    public List<RecordBody> getRecordByPayerAndGroup(Integer payerId, Integer groupId, String houseId, String year, String month) {
        List<RecordBody> recordBodyList = new ArrayList<>();
        for (Record record : recordRepository.findRecordsByPayer_IdAndPaymentGroupAndHouse_Id(payerId, groupId, houseId, year, month)) {
            recordBodyList.add(RecordMapper.toRecordBody(record));
        }
        return recordBodyList;
    }

    @Override
    public List<RecordBody> getRecordByPayerAndHouse(Integer payerId, String houseId, String year, String month) {
        List<RecordBody> recordBodyList = new ArrayList<>();
        for (Record record : recordRepository.findRecordsByPayer_IdAndPaymentGroupAndHouse_Id(payerId, 0, houseId, year, month)) {
            recordBodyList.add(RecordMapper.toRecordBody(record));
        }
        return recordBodyList;
    }

    @Override
    public List<RecordBody> getRecordByUsersAndOther(Integer userId, String houseId, String year, String month) {
        List<RecordBody> recordBodyList = new ArrayList<>();
        for (Record record : recordRepository.findRecordsByParticipantsAndPaymentGroup(userId, houseId, -1, year, month)) {
            recordBodyList.add(RecordMapper.toRecordBody(record));
        }
        return recordBodyList;
    }

    @Override
    public List<RecordBody> getRecordByPayerAndOther(Integer payerId, String houseId, String year, String month) {
        List<RecordBody> recordBodyList = new ArrayList<>();
        for (Record record : recordRepository.findRecordsByPayer_IdAndPaymentGroupAndHouse_Id(payerId, -1, houseId, year, month)) {
            recordBodyList.add(RecordMapper.toRecordBody(record));
        }
        return recordBodyList;
    }

    @Override
    public void saveRecord(RecordBody recordBody, String id) {
        Record record = new Record();

        record.setId(id);
        record.setMoney(recordBody.getMoney());
        record.setDate(recordBody.getDate());
        record.setInformation(recordBody.getInformation());
        record.setPaymentGroup(recordBody.getPaymentGroup());
        record.setPaid(recordBody.isPaid());
        User payer = new User();
        payer.setId(recordBody.getPayerId());
        House house = new House();
        house.setId(recordBody.getHouseId());
        record.setPayer(payer);
        record.setHouse(house);
        recordRepository.save(record);
        for (Integer participantId : recordBody.getParticipantIds()) {
            UserRecord userRecord = new UserRecord();
            UserRecordId userRecordId = new UserRecordId();
            if (!Objects.equals(id, "")) {
                userRecordId.setRecordId(id);
                userRecordId.setParticipantId(participantId);
            }
            userRecord.setId(userRecordId);
            User user = new User();
            user.setId(participantId);
            userRecord.setParticipant(user);
            userRecord.setRecord(record);
            userRecordRepository.save(userRecord);
        }
    }

    @Override
    public List<Object> findDateOfRecords(String houseId) {
        return recordRepository.findDateOfRecord(houseId);
    }

    @Override
    public Integer findPaidMoneyByDate(Integer userId, String houseId, String year, String month) {
        return recordRepository.findPaidMoneyByDate(userId, houseId, year, month)==null?0:recordRepository.findPaidMoneyByDate(userId, houseId, year, month);
    }

    @Override
    public Integer findDebtMoneyByDate(Integer userId, String houseId, String year, String month) {
        return recordRepository.findDebtMoneyByDate(userId, houseId, year, month)==null?0:recordRepository.findDebtMoneyByDate(userId, houseId, year, month);
    }

    @Override
    public RecordBody getRecordById(String id) {

        return RecordMapper.toRecordBody(recordRepository.getRecordById(id));
    }

    @Override
    public List<RecordBody> getRecordByUsersAndGroup(Integer userId, Integer groupId, String houseId, String year, String month) {
        List<RecordBody> recordBodyList = new ArrayList<>();
        for (Record record : recordRepository.findRecordsByParticipantsAndPaymentGroup(userId, houseId, groupId, year, month)) {
            recordBodyList.add(RecordMapper.toRecordBody(record));
        }
        return recordBodyList;
    }
}
