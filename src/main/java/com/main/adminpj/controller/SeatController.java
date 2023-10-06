package com.main.adminpj.controller;

import com.main.adminpj.dao.SeatDAO;
import com.main.adminpj.entity.flights;
import com.main.adminpj.model.FlightNSeat;
import com.main.adminpj.model.Seat;
import com.main.adminpj.model.User;
import com.main.adminpj.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SeatController {

    @Autowired
    private UserService userService;

    @Autowired
    private SeatDAO seatDAO;

//admin
    @GetMapping("/seats")
    public String seatList(HttpSession session, Model model){
        User user = getUserFromSession(session, model);
        if(user == null){
           return "redirect:/login?";

        }
        List<Seat> seat = seatDAO.getAllSeat();
        model.addAttribute("seat",seat);
        return "admin/seat";
    }

    // Thêm Seat
    @GetMapping("/seats/add")
    public String viewAddSeat(HttpSession session, Model model){
        User user = getUserFromSession(session, model);
        if(user == null){
            return "redirect:/login?";

        }
        return "admin/add-seat";
    }

    @PostMapping("/seats/add")
    public String addSeat(@ModelAttribute("seat") Seat seat) {
        seatDAO.addSeat(seat);
        return "redirect:/seats";
    }

    @PostMapping("/seats/update")
    public String updateSeats(@RequestParam("selectedSeats") String selectedSeats) {
        String[] seatIds = selectedSeats.split(",");

        for (String seatId : seatIds) {
            int id = Integer.parseInt(seatId);
            Seat seat = seatDAO.getSeatById(id);

            if (seat != null) {
                // Đảo ngược trạng thái reserved của chỗ ngồi
                seat.setReserved(!seat.isReserved());
                seatDAO.updateSeat(seat);
            }
        }

        return "redirect:/seats";
    }



    // Xóa Seat
    @GetMapping("/seats/delete/{id}")
    public String deleteSeat(@PathVariable("id") int id) {
        seatDAO.deleteSeat(id);
        return "redirect:/seats";
    }
//user
    @GetMapping("/seats/{flightNumber}")
    public String showSeats(@PathVariable String flightNumber, Model model) {
        List<Seat> seats = seatDAO.getSeatsByFlightNumber(flightNumber);
        int seatCount = seatDAO.getSeatCountByFlightNumber(flightNumber);
        int reservedSeatCount = seatDAO.getReservedSeatCountByFlightNumber(flightNumber);

        // Giới hạn số lượng chỗ bằng seatCount nếu reservedSeatCount vượt quá seatCount
        if (reservedSeatCount > seatCount) {
            seats = seats.subList(0, seatCount);
        }

        model.addAttribute("seats", seats);
        model.addAttribute("seatCount", seatCount);
        model.addAttribute("reservedSeatCount", reservedSeatCount);
        model.addAttribute("flightNumber", flightNumber);

        return "Seat";

    }

//    @PostMapping("/reserveSeat/{flightNumber}")
//    public String reserveSeat(@RequestParam("seatIds") List<Integer> seatIds,
//                              @PathVariable("flightNumber") String flightNumber,
//                              Model model) {
//        for (int seatId : seatIds) {
//            seatDAO.reserveSeat(seatId); // Đặt chỗ các ghế được chọn
//        }
//
//        model.addAttribute("flightNumber", flightNumber);
//        return "redirect:/seats/" + flightNumber;
//    }
    @PostMapping("/reserveSeat/{flightNumber}")
    public String reserveSeat(@RequestParam("seatIds") Integer seatIds,
                              @PathVariable("flightNumber") String flightNumber,
                              Model model, HttpSession session) {
        flights selectedFlight = (flights) session.getAttribute("selectedFlight");
        User user = getUserFromSession(session, model);
        Seat seat = seatDAO.getSeatById(seatIds);
        if (selectedFlight == null || !selectedFlight.getFlightNumber().equals(flightNumber)) {
            // Nếu không có chuyến bay đã chọn hoặc chuyến bay đã chọn không khớp với flightNumber,
            // chuyển hướng người dùng đến trang chọn chuyến bay
            return "redirect:/flight/search?";
        }
        if(user == null){
            return "redirect:/login?";
        }
        else {
            String flightNumberS = selectedFlight.getFlightNumber();
            String seatNumber= seat.getSeatNumber();
            FlightNSeat newFlightSs = new FlightNSeat(flightNumberS,seatNumber);
            seatDAO.reserveSeat(seatNumber);
            String seat_class = seat.getClassType();
            session.setAttribute("transferredData", newFlightSs);
            session.setAttribute("classtype", seat_class);

        }


        model.addAttribute("flightNumber", flightNumber);
        return "redirect:/bookings/book/" + flightNumber;
    }

    private User getUserFromSession(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // If user is not logged in, redirect to login page
            return null;
        }

        // Get user details from database
        User userDetails = userService.getUserDetails(user.getUsername());

        if (userDetails == null) {
            // If user details are not found, show error message
            model.addAttribute("error", "không tìm thấy thông tin người dùng vui lòngl ien hệ trung tâm trợ giúp");
            return null;
        }

        // Add user details to model
        model.addAttribute("user", userDetails);

        return userDetails;
    }

}
