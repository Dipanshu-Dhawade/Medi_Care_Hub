package com.Hospital_Management_System.service.Impl;

import com.Hospital_Management_System.enums.RoomType;
import com.Hospital_Management_System.model.Bed;
import com.Hospital_Management_System.model.Room;
import com.Hospital_Management_System.repository.RoomRepository;
import com.Hospital_Management_System.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;


    @Override
    public Room addRoom(JSONObject room) {
        Room room1= new Room();
        room1.setFloor(Integer.parseInt(room.get("floor").toString()));
        room1.setRoomNumber(room.get("roomNumber").toString());
        boolean roomType = roomRepository.existsByRoomNumber(room.get("roomNumber").toString());
        if(roomType) throw   new RuntimeException("Room  number is already");
        room1.setRoomType(RoomType.valueOf(room.get("roomType").toString()));
        return roomRepository.save(room1);
    }

    @Override
    public List<Bed> getAllRoomById(String roomNumber) {
        List<Bed> allBedByRoomId = roomRepository.getAllBedByRoomId(roomNumber);
        if(allBedByRoomId.isEmpty()) throw  new RuntimeException("bed is empty is roomnumber"+roomNumber);
      return allBedByRoomId;
    }


    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }


    @Override
    public List<Object[]> countRoomsB1yType1() {
        List<Object[]> objects = roomRepository.countRoomsB1yType1();
        if(objects.isEmpty()) throw new RuntimeException("objects are not Found");
        return objects;
    }

    @Override
    public Room getRoomById(Long id) {

        return roomRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Room is not found"));
    }

    @Override
    public Room updateRoom(Long id, Room room) {

        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Room is not found"));

        existingRoom.setRoomNumber(room.getRoomNumber());
        existingRoom.setRoomType(room.getRoomType());
        existingRoom.setFloor(room.getFloor());

        return roomRepository.save(existingRoom);
    }

    @Override
    public void deleteRoom(Long id) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Room is not found"));

        roomRepository.delete(room);
    }


}