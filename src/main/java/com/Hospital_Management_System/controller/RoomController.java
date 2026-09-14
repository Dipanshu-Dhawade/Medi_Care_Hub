package com.Hospital_Management_System.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import com.Hospital_Management_System.model.Bed;
import com.Hospital_Management_System.repository.RoomRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hospital_Management_System.model.Room;
import com.Hospital_Management_System.service.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;


    // Add Room
    @PostMapping("/add")
    public ResponseEntity<Room> addRoom(
            @RequestBody String room) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(room);

        return ResponseEntity.ok(
                roomService.addRoom(jsonObject)
        );
    }


    // Get All Rooms
    @GetMapping("/getAll")
    public ResponseEntity<List<Room>> getAllRoom() {
        return ResponseEntity.ok(
                roomService.getAllRoom()
        );
    }

    // Get Room By ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Room> getRoomById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                roomService.getRoomById(id)
        );
    }

    // Update Room
    @PutMapping("/update/{id}")
    public ResponseEntity<Room> updateRoom(
            @PathVariable Long id,
            @RequestBody Room room) {
        return ResponseEntity.ok(
                roomService.updateRoom(id, room)
        );
    }

    // Delete Room
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoom(
            @PathVariable Long id) {
        roomService.deleteRoom(id);

        return ResponseEntity.ok(
                "Room deleted successfully"
        );
    }

    @GetMapping("/getAllBedById/{bednumber}")
    public  ResponseEntity<List<Bed>> getAllBedById(@PathVariable  String bednumber){

          return ResponseEntity.status(HttpStatus.OK).body(roomService.getAllRoomById(bednumber));
    }


    @GetMapping("/CountRoomByType")
    public  ResponseEntity<String> CountRoomByType(){
        List<Object[]> objects = roomService.countRoomsB1yType1();
        return ResponseEntity.status(HttpStatus.OK).body(Arrays.toString(
                objects.get(0))+" "+Arrays.toString
                (objects.get(1)));
    }

}