package com.example.hostelweb.controller;

import com.example.hostelweb.Repos.PersonHistoryRepo;
import com.example.hostelweb.Repos.RoomReservationRepo;
import com.example.hostelweb.domain.Person;
import com.example.hostelweb.domain.Person_history;
import com.example.hostelweb.domain.Room_reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/personalArea")
public class personalAccountController {

    private final RoomReservationRepo roomReservationRepo;
    private final PersonHistoryRepo personHistoryRepo;

    @Autowired
    public personalAccountController(RoomReservationRepo roomReservationRepo, PersonHistoryRepo personHistoryRepo) {
        this.roomReservationRepo = roomReservationRepo;
        this.personHistoryRepo = personHistoryRepo;
    }

    @GetMapping("/myAccount")
    public String myAccount(@AuthenticationPrincipal Person person, Model model){
        model.addAttribute("person", person);
        return "personal-area";
    }

    @GetMapping("/reservations")
    public String myReservations(Model model, @AuthenticationPrincipal Person person){
        Collection<Room_reservation> reservations = roomReservationRepo.findByPerson(person);
        model.addAttribute("reservations", reservations);
        return "reservations";
    }

    @GetMapping("/reservations/{id}")
    public String deleteReservation(@PathVariable("id") String reservation_id, @AuthenticationPrincipal Person person){
        Room_reservation room_reservation = roomReservationRepo.findById(Integer.parseInt(reservation_id)).orElseThrow();
        if(room_reservation.getPerson().getPersonPassportData().equals(person.getPersonPassportData())) {
            roomReservationRepo.deleteById(Integer.parseInt(reservation_id));
        }
        return "redirect:/personalArea/reservations";
    }

    @GetMapping("/history")
    public String myHistory(Model model, @AuthenticationPrincipal Person person){
        Collection<Person_history> histories = personHistoryRepo.findAllByPerson(person);
        model.addAttribute("history", histories);
        return "history";
    }
}
