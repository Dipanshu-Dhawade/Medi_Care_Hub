package com.Hospital_Management_System.service;

import com.Hospital_Management_System.model.Bed;
import com.Hospital_Management_System.model.Room;
import org.json.simple.JSONObject;

import java.util.List;

public interface RoomService {

    Room addRoom(JSONObject room);

    List<Room> getAllRoom();

    Room getRoomById(Long id);

    Room updateRoom(Long id, Room room);

    void deleteRoom(Long id);

    List<Object[]> countRoomsB1yType1();

    List<Bed> getAllRoomById(String roomnumber);
}

