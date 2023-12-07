package com.example.demo.service;

import com.example.demo.entity.House;
import com.example.demo.entity.Record;
import com.example.demo.entity.User;
import com.example.demo.model.dto.RecordBody;
import com.example.demo.repository.HouseRepository;
import com.example.demo.repository.RecordRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    public RecordServiceImp(RecordRepository recordRepository, UserRepository userRepository, HouseRepository houseRepository) {
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
        this.houseRepository = houseRepository;
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
    @Transactional
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
        record.setParticipants(participants);
        recordRepository.save(record);
        for (User participant : participants) {
            String sql = "INSERT INTO user_record (record_id, participant_id) VALUES (:recordId, :participantId)";
            entityManager.createNativeQuery(sql)
                    .setParameter("recordId", record.getId())
                    .setParameter("participantId", participant.getId())
                    .executeUpdate();
        }
    }

    @Override
    public List<Record> getRecordByUsersAndGroup(Integer userId, Integer groupId, String houseId) {
        return recordRepository.findRecordsByParticipantsAndPaymentGroup(userId, houseId, groupId);
    }
}
