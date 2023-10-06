//package com.main.adminpj.controller;
//
//import com.main.adminpj.entity.flights;
//import com.main.adminpj.service.FlightService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.*;
//
//@Controller
//public class FlightController {
//
//    private final FlightService flightService;
//
//    @Autowired
//    public FlightController(FlightService flightService) {
//        this.flightService = flightService;
//    }
//
//    @GetMapping("/flight")
//    public String getFlights(Model model) {
//        List<flights> flights = flightService.getAllFlights();
//        model.addAttribute("flights", flights);
//
//        // Filter unique and sorted arrival times
//        Set<LocalTime> uniqueArrivalTimes = new TreeSet<>();
//        for (flights flight : flights) {
//            uniqueArrivalTimes.add(flight.getArrivalTime());
//        }
//
//        // Convert Set to List and sort
//        List<LocalTime> sortedArrivalTimes = new ArrayList<>(uniqueArrivalTimes);
//        Collections.sort(sortedArrivalTimes);
//
//        // Add the unique and sorted arrival times to the model
//        model.addAttribute("sortedArrivalTimes", sortedArrivalTimes);
//
//        return "flight-list";
//    }
//
//    @GetMapping("/flight/search")
//    public String searchFlights(
//            @RequestParam(value = "flightName", required = false) String flightName,
//            @RequestParam(value = "flightNumber", required = false) String flightNumber,
//            @RequestParam(value = "departureTime", required = false) LocalTime departureTime,
//            @RequestParam(value = "arrivalTime", required = false) LocalTime arrivalTime,
//            @RequestParam(value = "departureDate", required = false) LocalDate departureDate,
//            @RequestParam(value = "arrivalDate", required = false) LocalDate arrivalDate,
//            @RequestParam(value = "originCity", required = false) String originCity,
//            @RequestParam(value = "arrivalCity", required = false) String arrivalCity,
//            Model model) {
//
//        // Trim the values if they are not null
//        flightName = flightName != null ? flightName.trim() : null;
//        flightNumber = flightNumber != null ? flightNumber.trim() : null;
//        originCity = originCity != null ? originCity.trim() : null;
//        arrivalCity = arrivalCity != null ? arrivalCity.trim() : null;
//
//        List<flights> searchResults = flightService.searchFlights(
//                flightName,
//                flightNumber,
//                departureTime,
//                arrivalTime,
//                departureDate,
//                arrivalDate,
//                originCity,
//                arrivalCity
//        );
//
//
//        if (searchResults.isEmpty()) {
//            // Handle empty search results
//            model.addAttribute("emptyResults", true);
//        } else {
//            model.addAttribute("searchResults", searchResults);
//
//            // Filter unique and sorted flight names
//            Set<String> uniqueFlightNames = new TreeSet<>();
//            for (flights flight : searchResults) {
//                uniqueFlightNames.add(flight.getFlightName());
//            }
//
//            // Convert Set to List and sort
//            List<String> sortedFlightNames = new ArrayList<>(uniqueFlightNames);
//            Collections.sort(sortedFlightNames);
//
//            // Add the unique and sorted flight names to the model
//            model.addAttribute("sortedFlightNames", sortedFlightNames);
//
//            // Filter unique and sorted flight numbers
//            Set<String> uniqueFlightNumbers = new TreeSet<>();
//            for (flights flight : searchResults) {
//                uniqueFlightNumbers.add(flight.getFlightNumber());
//            }
//
//            // Convert Set to List and sort
//            List<String> sortedFlightNumbers = new ArrayList<>(uniqueFlightNumbers);
//            Collections.sort(sortedFlightNumbers);
//
//            // Add the unique and sorted flight numbers to the model
//            model.addAttribute("sortedFlightNumbers", sortedFlightNumbers);
//
//            // Filter unique and sorted departure times
//            Set<LocalTime> uniqueDepartureTimes = new TreeSet<>();
//            for (flights flight : searchResults) {
//                uniqueDepartureTimes.add(flight.getDepartureTime());
//            }
//
//            // Convert Set to List and sort
//            List<LocalTime> sortedDepartureTimes = new ArrayList<>(uniqueDepartureTimes);
//            Collections.sort(sortedDepartureTimes);
//
//            // Add the unique and sorted departure times to the model
//            model.addAttribute("sortedDepartureTimes", sortedDepartureTimes);
//
//            // Filter unique and sorted arrival times
//            Set<LocalTime> uniqueArrivalTimes = new TreeSet<>();
//            for (flights flight : searchResults) {
//                uniqueArrivalTimes.add(flight.getArrivalTime());
//            }
//
//            // Convert Set to List and sort
//            List<LocalTime> sortedArrivalTimes = new ArrayList<>(uniqueArrivalTimes);
//            Collections.sort(sortedArrivalTimes);
//
//            // Add the unique and sorted arrival times to the model
//            model.addAttribute("sortedArrivalTimes", sortedArrivalTimes);
//        }
//
//        return "flight-list";
//    }
//}
// update lần 2
package com.main.adminpj.controller;

import com.main.adminpj.entity.flights;
import com.main.adminpj.model.User;
import com.main.adminpj.model.flight;
import com.main.adminpj.service.AdminFlightRepository;
import com.main.adminpj.service.FlightRowMapper;
import com.main.adminpj.service.FlightService;
import com.main.adminpj.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Controller
public class FlightController {

    private final FlightService flightService;

    @Autowired
    private AdminFlightRepository adminFlightRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }
//amdin
    @GetMapping("/flights")
    public String getUsers(HttpSession session, Model model) {
        User user = getUserFromSession(session, model);
        if(user == null) {
            return "redirect:/login?";
        }
        List<flight> flight = flightService.getAllFlight();
        model.addAttribute("flights",flight);

        return "admin/flights";
    }
    @GetMapping("/flights/new")
    public String showAddFlightForm(Model model) {
        model.addAttribute("flight", new flights());
        return "admin/addFlightForm";
    }

    @PostMapping("/flights/add")
    public String addFlight(@ModelAttribute("flight") flights flight) {
        adminFlightRepository.save(flight);
        return "redirect:/flights";
    }

    // Hiển thị form chỉnh sửa chuyến bay
    @GetMapping("/flights/edit/{id}")
    public String showEditFlightForm(@PathVariable("id") int id, Model model) {
        // Truy vấn dữ liệu chuyến bay dựa trên id
        flights flight = adminFlightRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid flight Id:" + id));

        // Đưa dữ liệu chuyến bay vào mô hình để hiển thị trong form
        model.addAttribute("flight", flight);

        return "admin/editFlightForm";
    }

    // Xử lý yêu cầu cập nhật chuyến bay
    @PostMapping("/flights/edit/{id}")
    public String editFlight(@PathVariable("id") int id, @ModelAttribute("flight") flights updatedFlight) {
        // Truy vấn chuyến bay cần chỉnh sửa dựa trên id
        flights flight = adminFlightRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid flight Id:" + id));

        // Cập nhật các trường dữ liệu từ form chỉnh sửa
        flight.setFlightName(updatedFlight.getFlightName());
        flight.setFlightNumber(updatedFlight.getFlightNumber());
        flight.setDepartureDate(updatedFlight.getDepartureDate());
        flight.setArrivalDate(updatedFlight.getArrivalDate());
        flight.setDepartureTime(updatedFlight.getDepartureTime());
        flight.setArrivalTime(updatedFlight.getArrivalTime());
        flight.setNumberSeat(updatedFlight.getNumberSeat());
        flight.setOriginCity(updatedFlight.getOriginCity());
        flight.setDestinationCity(updatedFlight.getDestinationCity());

        // Lưu chuyến bay đã chỉnh sửa
        adminFlightRepository.save(flight);

        return "redirect:/flights";
    }

    // Xử lý yêu cầu xóa chuyến bay
    @GetMapping("/flights/delete/{id}")
    public String deleteFlight(@PathVariable("id") int id) {
        adminFlightRepository.deleteById(id);
        return "redirect:/flights";
    }

    @GetMapping("/flights/search")
    public String searchBooking(@RequestParam("flight_number") String flight_number, Model model) {
        String sql = "SELECT * FROM flights WHERE flight_number = ?";
        List<flight> Flight = jdbcTemplate.query(sql, new Object[]{flight_number}, new FlightRowMapper());

        if (Flight.isEmpty()) {
            // User not found, show error message
            model.addAttribute("errorMessage", "User not found.");
        } else {
            // User found, show the user details
            model.addAttribute("flights", Flight);
        }

        return "admin/flights";
    }

    //người dùng
    @GetMapping("/flight")
    public String getFlights(Model model) {
        List<flights> flights = flightService.getAllFlights();
        model.addAttribute("flights", flights);

        // Filter unique and sorted arrival times
        Set<LocalTime> uniqueArrivalTimes = new TreeSet<>();
        for (flights flight : flights) {
            uniqueArrivalTimes.add(flight.getArrivalTime());
        }

        // Convert Set to List and sort
        List<LocalTime> sortedArrivalTimes = new ArrayList<>(uniqueArrivalTimes);
        Collections.sort(sortedArrivalTimes);

        // Add the unique and sorted arrival times to the model
        model.addAttribute("sortedArrivalTimes", sortedArrivalTimes);

        return "flight-list";
    }

    @GetMapping("/flight/search")
    public String searchFlights(
            @RequestParam(value = "flightName", required = false) String flightName,
            @RequestParam(value = "flightNumber", required = false) String flightNumber,
            @RequestParam(value = "departureTime", required = false) LocalTime departureTime,
            @RequestParam(value = "arrivalTime", required = false) LocalTime arrivalTime,
            @RequestParam(value = "departureDate", required = false) LocalDate departureDate,
            @RequestParam(value = "arrivalDate", required = false) LocalDate arrivalDate,
            @RequestParam(value = "originCity", required = false) String originCity,
            @RequestParam(value = "arrivalCity", required = false) String arrivalCity,
            Model model) {

        // Trim the values if they are not null
        flightName = flightName != null ? flightName.trim() : null;
        flightNumber = flightNumber != null ? flightNumber.trim() : null;
        originCity = originCity != null ? originCity.trim() : null;
        arrivalCity = arrivalCity != null ? arrivalCity.trim() : null;

        List<flights> searchResults = flightService.searchFlights(
                flightName,
                flightNumber,
                departureTime,
                arrivalTime,
                departureDate,
                arrivalDate,
                originCity,
                arrivalCity
        );

        if (searchResults.isEmpty()) {
            // Handle empty search results
            model.addAttribute("emptyResults", true);
        } else {
            model.addAttribute("searchResults", searchResults);

            // Filter unique and sorted flight names
            Set<String> uniqueFlightNames = new TreeSet<>();
            for (flights flight : searchResults) {
                uniqueFlightNames.add(flight.getFlightName());
            }

            // Convert Set to List and sort
            List<String> sortedFlightNames = new ArrayList<>(uniqueFlightNames);
            Collections.sort(sortedFlightNames);

            // Add the unique and sorted flight names to the model
            model.addAttribute("sortedFlightNames", sortedFlightNames);

            // Filter unique and sorted flight numbers
            Set<String> uniqueFlightNumbers = new TreeSet<>();
            for (flights flight : searchResults) {
                uniqueFlightNumbers.add(flight.getFlightNumber());
            }

            // Convert Set to List and sort
            List<String> sortedFlightNumbers = new ArrayList<>(uniqueFlightNumbers);
            Collections.sort(sortedFlightNumbers);

            // Add the unique and sorted flight numbers to the model
            model.addAttribute("sortedFlightNumbers", sortedFlightNumbers);

            // Filter unique and sorted departure times
            Set<LocalTime> uniqueDepartureTimes = new TreeSet<>();
            for (flights flight : searchResults) {
                uniqueDepartureTimes.add(flight.getDepartureTime());
            }

            // Convert Set to List and sort
            List<LocalTime> sortedDepartureTimes = new ArrayList<>(uniqueDepartureTimes);
            Collections.sort(sortedDepartureTimes);

            // Add the unique and sorted departure times to the model
            model.addAttribute("sortedDepartureTimes", sortedDepartureTimes);

            // Filter unique and sorted arrival times
            Set<LocalTime> uniqueArrivalTimes = new TreeSet<>();
            for (flights flight : searchResults) {
                uniqueArrivalTimes.add(flight.getArrivalTime());
            }

            // Convert Set to List and sort
            List<LocalTime> sortedArrivalTimes = new ArrayList<>(uniqueArrivalTimes);
            Collections.sort(sortedArrivalTimes);

            // Add the unique and sorted arrival times to the model
            model.addAttribute("sortedArrivalTimes", sortedArrivalTimes);

            // Filter unique and sorted departure dates
            Set<LocalDate> uniqueDepartureDates = new TreeSet<>();
            for (flights flight : searchResults) {
                uniqueDepartureDates.add(flight.getDepartureDate());
            }

            // Convert Set to List and sort
            List<LocalDate> sortedDepartureDates = new ArrayList<>(uniqueDepartureDates);
            Collections.sort(sortedDepartureDates);

            // Add the unique and sorted departure dates to the model
            model.addAttribute("sortedDepartureDates", sortedDepartureDates);

            // Filter unique and sorted arrival dates
            Set<LocalDate> uniqueArrivalDates = new TreeSet<>();
            for (flights flight : searchResults) {
                uniqueArrivalDates.add(flight.getArrivalDate());
            }

            // Convert Set to List and sort
            List<LocalDate> sortedArrivalDates = new ArrayList<>(uniqueArrivalDates);
            Collections.sort(sortedArrivalDates);

            // Add the unique and sorted arrival dates to the model
            model.addAttribute("sortedArrivalDates", sortedArrivalDates);
        }

        return "flight-list";
    }

    @PostMapping("/reserveFlight")
    public String reserveFlight(@ModelAttribute("flight") flights flight, HttpSession session,Model model) {
        // Lưu đối tượng Flight vào HttpSession
        session.setAttribute("selectedFlight", flight);
        User user = getUserFromSession(session, model);
        if(user == null ){
            return "redirect:/login?";

        }
        session.setAttribute("user", user);
        // Chuyển hướng người dùng đến trang ghế
        return "redirect:/seats/" + flight.getFlightNumber();

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

