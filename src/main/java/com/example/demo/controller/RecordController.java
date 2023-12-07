package com.example.demo.controller;

import com.example.demo.entity.Record;
import com.example.demo.model.dto.RecordBody;
import com.example.demo.service.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordController {
    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getAllRecord(@PathVariable String id){
        List<Record> records = recordService.getAllRecord();
        return ResponseEntity.ok(records);
    }
    @GetMapping("/{houseId}/{userId}")
    public ResponseEntity<?> getAllRecordsByUsersAndHouse(@PathVariable Integer userId,@PathVariable String houseId){
        return ResponseEntity.ok(recordService.getAllRecordByUsersAndHouse(userId,houseId));
    }
    @GetMapping("/{houseId}/{userId}/{groupId}")
    public ResponseEntity<?> getRecordsByUsersAndGroup(@PathVariable Integer userId, @PathVariable Integer groupId, @PathVariable String houseId){
        return ResponseEntity.ok(recordService.getRecordByUsersAndGroup(userId,groupId,houseId));
    }
    @GetMapping("/payer/{houseId}/{payerId}")
    public ResponseEntity<?> getAllRecordsByPayerAndHouse(@PathVariable Integer payerId,@PathVariable String houseId){
        return ResponseEntity.ok(recordService.getAllRecordByPayerAndHouse(payerId,houseId));
    }
    @GetMapping("/payer/{houseId}/{payerId}/{groupId}")
    public ResponseEntity<?> getRecordsByPayerAndGroup(@PathVariable Integer payerId, @PathVariable Integer groupId, @PathVariable String houseId){
        return ResponseEntity.ok(recordService.getRecordByPayerAndGroup(payerId,groupId,houseId));
    }
    @GetMapping("/payer/other/{houseId}/{payerId}")
    public ResponseEntity<?> getRecordsByPayerOther(@PathVariable Integer payerId, @PathVariable String houseId){
        return ResponseEntity.ok(recordService.getRecordByPayerAndOther(payerId, houseId));
    }
    @GetMapping("/other/{houseId}/{userId}")
    public ResponseEntity<?> getRecordsByUserOther(@PathVariable Integer userId, @PathVariable String houseId){
        return ResponseEntity.ok(recordService.getRecordByUsersAndOther(userId, houseId));
    }
    @GetMapping("/house/{houseId}")
    public ResponseEntity<?> getRecordsByUserAndGroup( @PathVariable String houseId){
        return ResponseEntity.ok(recordService.getRecordByHouseForAllMember(houseId));
    }
    @GetMapping("/payer/house/{houseId}/{payerId}")
    public ResponseEntity<?> getRecordsByPayerAndGroup(@PathVariable Integer payerId, @PathVariable String houseId){
        return ResponseEntity.ok(recordService.getRecordByPayerAndHouse(payerId,houseId));
    }
    @PostMapping("/create")
    public String createRecord(@RequestBody RecordBody recordBody){
        recordService.createRecord(recordBody);
        return "done";
    }
}
