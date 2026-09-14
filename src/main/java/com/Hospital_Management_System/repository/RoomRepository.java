package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.model.Bed;
import com.Hospital_Management_System.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {


     boolean existsByRoomNumber(String roomnumber );
//     @Query("""
//    SELECT r.roomType, COUNT(r)
//    FROM Room r
//    GROUP BY r.roomType
//""")
//     List<Object[]> countRoomsByType();

     @Query("select  r.roomType ,count(r) " +
             "from Room r " +
             "GROUP BY r.roomType ")
     List<Object[]> countRoomsB1yType1();


     @Query(""" 
      select b  
      from  Bed b
      where b.room.roomNumber=:roomnumber
      """)
     List<Bed> getAllBedByRoomId( String roomnumber);
}





