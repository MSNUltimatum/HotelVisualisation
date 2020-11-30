package com.example.hostelweb.controller;

import com.example.hostelweb.Repos.ClientInHotelRepo;
import com.example.hostelweb.Repos.HotelRepo;
import com.example.hostelweb.Repos.RoomsNumbersRepo;
import com.example.hostelweb.domain.ClientInHotel;
import com.example.hostelweb.domain.Hotel;
import com.example.hostelweb.domain.RoomsNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/hotel/edit")
@PreAuthorize("hasAuthority('ADMIN')")
public class HotelEditorController {

    private final HotelRepo hotelRepo;
    private final RoomsNumbersRepo roomsNumbersRepo;
    private final ClientInHotelRepo clientInHotelRepo;

    @Autowired
    public HotelEditorController(HotelRepo hotelRepo, RoomsNumbersRepo roomsNumbersRepo, ClientInHotelRepo clientInHotelRepo) {
        this.hotelRepo = hotelRepo;
        this.roomsNumbersRepo = roomsNumbersRepo;
        this.clientInHotelRepo = clientInHotelRepo;
    }

    @GetMapping("{id}")
    public String hotelEditor(Model model,
                              @PathVariable("id") String hotel_id){
        Hotel hotel = hotelRepo.findById(Integer.parseInt(hotel_id)).orElseThrow();
        model.addAttribute("hotel", hotel);
        return "hotelEditor";
    }

    @PostMapping("{id}")
    public String hotelSave(Model model,
                            @PathVariable("id") String hotel_id,
                            @RequestParam String hotelName,
                            @RequestParam String hotelAddress,
                            @RequestParam String hotelEmail,
                            @RequestParam String hotelBankAcc){
        Hotel hotelFromPath = hotelRepo.findById(Integer.parseInt(hotel_id)).orElseThrow();
        if(!hotelName.isBlank()){
            hotelFromPath.setHotelName(hotelName);
        }

        if(!hotelAddress.isBlank()){
            hotelFromPath.setHotelAdres(hotelAddress);
        }

        if(!hotelEmail.isBlank() &&
                hotelRepo.findByHotelEmailAdress(hotelEmail) == null){
            hotelFromPath.setHotelEmailAdress(hotelEmail);
        }

        if(!hotelBankAcc.isBlank() &&
                hotelRepo.findByHotelBancAcc(hotelBankAcc) == null){
            hotelFromPath.setHotelBancAcc(hotelBankAcc);
        }
        hotelRepo.save(hotelFromPath);
        model.addAttribute("hotel", hotelFromPath);
        return "hotelEditor";
    }

    @GetMapping("getRooms/{id}")
    public String getRooms(Model model,
                           @PathVariable("id") Integer hotel_id,
                           @PageableDefault(size = 15) Pageable pageable){
        Hotel hotel = hotelRepo.findById(hotel_id).orElseThrow();
        Page<RoomsNumbers> allRooms = roomsNumbersRepo.findAllByHotel(hotel_id, pageable);
        model.addAttribute("hotel", hotel);
        model.addAttribute("allHotelRooms", allRooms);
        return "roomsEditor";
    }

    @GetMapping("getClients/{id}")
    public String getClients(Model model, @PageableDefault Pageable pageable, @PathVariable("id") Integer hotel_id){
        Hotel hotel = hotelRepo.findById(hotel_id).orElseThrow();
        Page<ClientInHotel> allClients = clientInHotelRepo.findAllByHotel(hotel_id, pageable);
        model.addAttribute("hotel", hotel);
        model.addAttribute("clients", allClients);
        return "clientsEditor";
    }

    @PostMapping("getClients/{id}/{client_id}")
    public String setClient(@PathVariable("id") String hotel_id,
                            @PathVariable("client_id") String client_id,
                            @RequestParam String bill,
                            @RequestParam String check_in_date,
                            @RequestParam String check_out_date,
                            Model model){
        ClientInHotel clientInHotel = clientInHotelRepo.findById(client_id).orElseThrow();
        if(LocalDate.parse(check_in_date).isBefore(LocalDate.parse(check_out_date))){
            clientInHotel.setCheck_in_date(LocalDate.parse(check_in_date));
            clientInHotel.setCheck_out_date(LocalDate.parse(check_out_date));
        }

        BigDecimal b = new BigDecimal(bill);
        if (b.compareTo(BigDecimal.ZERO) > 0){
            clientInHotel.setBill(b);
        }
        clientInHotelRepo.save(clientInHotel);
        return "redirect:/hotel/edit/getClients/" + hotel_id;
    }

    @GetMapping("/deleteClient/{id}/{client_id}")
    public String deleteClient(@PathVariable("id") String hotel_id,
                               @PathVariable("client_id") String person_id){
        clientInHotelRepo.deleteClient(person_id);
        return "redirect:/hotel/edit/getClients/" + hotel_id;
    }

}
