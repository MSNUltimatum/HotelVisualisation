package com.example.hostelweb.controller;

import com.example.hostelweb.Repos.RoomReservationRepo;
import com.example.hostelweb.Repos.RoomTypeRepo;
import com.example.hostelweb.domain.Person;
import com.example.hostelweb.domain.Room_reservation;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/bookRoom")
public class BookRoomsController {

    private final RoomTypeRepo roomTypeRepo;
    private final RoomReservationRepo roomReservationRepo;

    @Autowired
    public BookRoomsController(RoomTypeRepo roomTypeRepo, RoomReservationRepo roomReservationRepo) {
        this.roomTypeRepo = roomTypeRepo;
        this.roomReservationRepo = roomReservationRepo;
    }

    @GetMapping
    public String getForm(Model model,
                           @RequestParam Integer hotel_id,
                           @RequestParam String room_type_name,
                           @AuthenticationPrincipal Person person){
        model.addAttribute("hotel_id", hotel_id);
        model.addAttribute("room_type_id", roomTypeRepo.findByRoomTypeName(room_type_name));
        model.addAttribute("room_type_name", room_type_name);
        model.addAttribute("person", person);
        return "reservation";
    }

    @PostMapping
    public String bookRoom(@RequestParam String person_passport_data,
                           @RequestParam String hotel_id,
                           @RequestParam String room_type_id,
                           @RequestParam String check_in_date,
                           @RequestParam String check_out_date){
        if(LocalDate.now().isBefore(LocalDate.parse(check_in_date)) && LocalDate.parse(check_in_date).isBefore(LocalDate.parse(check_out_date))) {
            roomReservationRepo.BOOK_ROOM(check_in_date,
                    check_out_date,
                    person_passport_data,
                    Integer.parseInt(hotel_id),
                    Integer.parseInt(room_type_id));
        }
        return "redirect:/personalArea/myAccount";
    }
}
