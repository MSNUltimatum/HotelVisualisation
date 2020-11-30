package com.example.hostelweb.controller;

import com.example.hostelweb.Repos.HotelRepo;
import com.example.hostelweb.Repos.RoomsInHotelRepo;
import com.example.hostelweb.Repos.RoomsNumbersRepo;
import com.example.hostelweb.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/hotel")
public class HotelController {
    private final RoomsInHotelRepo roomsInHotelRepo;
    private final HotelRepo hotelRepo;

    @Autowired
    public HotelController(RoomsInHotelRepo roomsInHotelRepo,
                           HotelRepo hotelRepo) {
        this.roomsInHotelRepo = roomsInHotelRepo;
        this.hotelRepo = hotelRepo;
    }

    @GetMapping("/{id}")
    public String getHotel(@PathVariable("id")String hotel_id,
                           @PageableDefault Pageable pageable,
                           @RequestParam(required = false, defaultValue = "") String check_in_date,
                           @RequestParam(required = false, defaultValue = "") String check_out_date,
                           @AuthenticationPrincipal Person person,
                           Model model){
        Hotel hotel = hotelRepo.findById(Integer.parseInt(hotel_id)).orElseThrow();
        model.addAttribute("hotel", hotel);
        model.addAttribute("hotel_id", hotel_id);
        if(check_in_date.isBlank()){
            check_in_date = LocalDate.now().toString();
        }
        if(check_out_date.isBlank()){
            check_out_date = LocalDate.now().plusWeeks(2).toString();
        }
        Page<Object[]> pg = getFreeRooms(pageable, check_in_date, check_out_date, hotel);
        model.addAttribute("page", pg);
        model.addAttribute("check_in_date", check_in_date);
        model.addAttribute("check_out_date", check_out_date);
        model.addAttribute("person", person);
        model.addAttribute("isAdmin", person != null && person.getRoles().contains(Roles.ADMIN));
        return "hotel";
    }

    @GetMapping("/find")
    public String findHotel(Model model,
                            @PageableDefault Pageable pageable){
        Page<Hotel> allByStarCount = hotelRepo.findAllByStarCount( pageable);
        model.addAttribute("hotels", allByStarCount);
        model.addAttribute("isFinder", false);
        return "hotelsPage";
    }

    @GetMapping("/finder")
    public String findHotelWithParams(Model model,
                            @RequestParam(required = false, defaultValue = "") String hotel_name,
                            @RequestParam(required = false, defaultValue = "") String hotel_address,
                            @RequestParam(required = false, defaultValue = "") String check_in_date,
                            @RequestParam(required = false, defaultValue = "") String check_out_date,
                            @PageableDefault Pageable pageable){
        Page<Hotel> finder;
        if(!hotel_name.isBlank() && !hotel_address.isBlank() && !check_in_date.isBlank() && !check_out_date.isBlank()){
            finder = hotelRepo.findAllByHotelNameAndAddressAndDates(hotel_name, hotel_address, check_in_date,check_out_date, pageable);
        } else if(hotel_name.isBlank() && !hotel_address.isBlank() && !check_in_date.isBlank() && !check_out_date.isBlank()){
            finder = hotelRepo.findAllByHotelAddressAndDates(hotel_address, check_in_date,check_out_date,pageable);
        } else if(!hotel_name.isBlank() && hotel_address.isBlank() && !check_in_date.isBlank() && !check_out_date.isBlank()){
            finder = hotelRepo.findAllByHotelNameAndDates(hotel_name, check_in_date, check_out_date, pageable);
        } else if(!hotel_name.isBlank() && !hotel_address.isBlank() && check_in_date.isBlank() && !check_out_date.isBlank()){
            finder = hotelRepo.findAllByHotelNameAndAddressAndDates(hotel_name, hotel_address, LocalDate.now().toString(),check_out_date, pageable);
        } else if(!hotel_name.isBlank() && !hotel_address.isBlank() && !check_in_date.isBlank() && check_out_date.isBlank()) {
            finder = hotelRepo.findAllByHotelNameAndAddressAndDates(hotel_name, hotel_address, check_in_date,LocalDate.parse(check_in_date).plusWeeks(2).toString(), pageable);
        } else if(hotel_name.isBlank() && hotel_address.isBlank() && !check_in_date.isBlank() && !check_out_date.isBlank()) {
            finder = hotelRepo.findAllByDates(check_in_date, check_out_date, pageable);
        } else if(hotel_name.isBlank() && !hotel_address.isBlank() && check_in_date.isBlank() && !check_out_date.isBlank()) {
            finder = hotelRepo.findAllByHotelAddressAndDates(hotel_address, LocalDate.now().toString(),check_out_date,pageable);
        } else if(hotel_name.isBlank() && !hotel_address.isBlank() && !check_in_date.isBlank() && check_out_date.isBlank()) {
            finder = hotelRepo.findAllByHotelAddressAndDates(hotel_address, check_in_date,LocalDate.parse(check_in_date).plusWeeks(2).toString(),pageable);
        } else if(!hotel_name.isBlank() && hotel_address.isBlank() && check_in_date.isBlank() && !check_out_date.isBlank()) {
            finder = hotelRepo.findAllByHotelNameAndDates(hotel_name, LocalDate.now().toString(), check_out_date, pageable);
        } else if(!hotel_name.isBlank() && hotel_address.isBlank() && !check_in_date.isBlank() && check_out_date.isBlank()){
            finder = hotelRepo.findAllByHotelNameAndDates(hotel_name, check_in_date, LocalDate.parse(check_in_date).plusWeeks(2).toString(), pageable);
        } else if(!hotel_name.isBlank() && !hotel_address.isBlank() && check_in_date.isBlank() && check_out_date.isBlank()){
            finder = hotelRepo.findAllByHotelNameLikeAndHotelAdresLike(hotel_name, hotel_address, pageable);
        } else if(!hotel_name.isBlank() && hotel_address.isBlank() && check_in_date.isBlank() && check_out_date.isBlank()){
            finder = hotelRepo.findAllByHotelNameLike(hotel_name, pageable);
        } else if(hotel_name.isBlank() && !hotel_address.isBlank() && check_in_date.isBlank() && check_out_date.isBlank()){
            finder = hotelRepo.findAllByHotelAdresLike(hotel_address, pageable);
        } else {
            return "redirect:/hotel/find";
        }
        model.addAttribute("hotels", finder);
        model.addAttribute("isFinder", true);
        model.addAttribute("hotel_name", hotel_name);
        model.addAttribute("hotel_address", hotel_address);
        model.addAttribute("check_in_date", check_in_date);
        model.addAttribute("check_out_date", check_out_date);
        return "hotelsPage";
    }

    private Page<Object[]> getFreeRooms(Pageable pageable, String check_in_date, String check_out_date, Hotel hotel) {
        Page<Object[]> pg;
        pg = roomsInHotelRepo.findFreeRooms(hotel.getHotelId(),
                check_in_date,
                check_out_date,
                pageable);
        return pg;
    }
}
