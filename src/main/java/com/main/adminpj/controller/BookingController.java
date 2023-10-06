package com.main.adminpj.controller;

import com.main.adminpj.dao.SeatDAO;
import com.main.adminpj.entity.ThanhToan;
import com.main.adminpj.model.*;
import com.main.adminpj.service.BookingListRowMapper;
import com.main.adminpj.service.BookingService;
import com.main.adminpj.service.PaymentService;
import com.main.adminpj.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SeatDAO seatDAO;
//admin
    @GetMapping("/bookings")
    public String viewBookings(Model model) {
        String query = "SELECT * FROM bookinglist";
        List<bookinglist> bookings = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(bookinglist.class));
        model.addAttribute("bookings", bookings);
        return "admin/bookings";
    }

    @GetMapping("/bookings/add")
    public String addBookingForm(Model model) {
        model.addAttribute("booking", new bookinglist());
        return "admin/addBookingForm";
    }

    @PostMapping("/bookings/add")
    public String addBooking(@ModelAttribute("booking") bookinglist booking, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/addBookingForm";
        }

        String sql = "INSERT INTO bookinglist (so_dien_thoai, email, ho_ten, id_card, flight_number, loai_ghe, ma_ve, price, ImgLink) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, booking.getSo_dien_thoai(), booking.getEmail(), booking.getHo_ten(), booking.getId_card(), booking.getFlight_number(), booking.getLoai_ghe(), booking.getMa_ve(), booking.getPrice(), booking.getImgLink());

        return "redirect:/bookings";
    }

    @GetMapping("/bookings/edit/{ma_ve}")
    public String editBookingForm(@PathVariable("ma_ve") String maVe, Model model) {
        String sql = "SELECT * FROM bookinglist WHERE ma_ve = ?";
        bookinglist booking = jdbcTemplate.queryForObject(sql, new Object[]{maVe}, new BeanPropertyRowMapper<>(bookinglist.class));
        model.addAttribute("booking", booking);
        return "admin/editBookingForm";
    }

    @PostMapping("/bookings/edit/{ma_ve}")
    public String updateBooking(@PathVariable("ma_ve") String maVe, @ModelAttribute("booking") bookinglist booking,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            booking.setMa_ve(maVe);
            return "admin/editBookingForm";
        }

        String sql = "UPDATE bookinglist SET so_dien_thoai=?, email=?, ho_ten=?, id_card=?, flight_number=?, loai_ghe=?, price=?, ImgLink=? WHERE ma_ve=?";
        jdbcTemplate.update(sql, booking.getSo_dien_thoai(), booking.getEmail(), booking.getHo_ten(), booking.getId_card(), booking.getFlight_number(), booking.getLoai_ghe(), booking.getPrice(), booking.getImgLink(), maVe);

        return "redirect:/bookings";
    }


    @GetMapping("/bookings/delete/{id}")
    public String deleteBooking(@PathVariable("id") int id) {
        String sql = "DELETE FROM bookinglist WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "redirect:/bookings";
    }

    @GetMapping("/bookings/search")
    public String searchBooking(@RequestParam("so_dien_thoai") String so_dien_thoai, Model model) {
        String sql = "SELECT * FROM bookinglist WHERE so_dien_thoai = ?";
        List<bookinglist> bookinglist = jdbcTemplate.query(sql, new Object[]{so_dien_thoai}, new BookingListRowMapper());

        if (bookinglist.isEmpty()) {
            // User not found, show error message
            model.addAttribute("errorMessage", "User not found.");
        } else {
            // User found, show the user details
            model.addAttribute("bookings", bookinglist);
        }

        return "admin/bookings";
    }

    //người dùng

    @GetMapping("/bookings/payment")
    public String payment(HttpSession session, Model model) {
        bookinglist booking = (bookinglist) session.getAttribute("booking");
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        String username = user.getUsername();
        if (booking == null) {
            // Nếu không có thông tin đặt vé, chuyển hướng đến trang lỗi hoặc trang khác
            return "redirect:/error";
        } else {
            // Nếu có thông tin đặt vé, chuyển hướng đến trang thanh toán
            BigDecimal balance = userService.getBalance(username);
            model.addAttribute("balance", balance);

            List<payment_system> paymentMethods = paymentService.getAllPayments();
            model.addAttribute("paymentMethods", paymentMethods);

            model.addAttribute("booking", booking);
            return "paying";
        }
    }


    @PostMapping("/bookings/payment/success")
    public String paymentSuccess(HttpSession session, @Param("ma_ve") String maVe) {
        bookinglist booking = (bookinglist) session.getAttribute("booking");

        // Kiểm tra xem có thông tin booking trong session hay không
        if (booking == null) {
            // Nếu không có thông tin booking, chuyển hướng người dùng đến trang lỗi hoặc trang khác tùy thuộc vào yêu cầu
            return "redirect:/userdashboard";
        }

        // Ghi dữ liệu booking vào cơ sở dữ liệu (hoặc thực hiện các thao tác khác liên quan đến ghi dữ liệu)
        String sql = "INSERT INTO bookinglist (so_dien_thoai, email, ho_ten, id_card, flight_number, loai_ghe, ma_ve, price, ImgLink, so_ghe) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, booking.getSo_dien_thoai(), booking.getEmail(), booking.getHo_ten(), booking.getId_card(),
                booking.getFlight_number(), booking.getLoai_ghe(), booking.getMa_ve(), booking.getPrice(), booking.getImgLink(), booking.getSo_ghe());

        // Cập nhật trạng thái đặt chỗ cho ghế
        seatDAO.reserveSeat(booking.getSo_ghe());

        // Cập nhật số lượng ghế trong bảng flight
        String updateFlightSql = "UPDATE flights SET number_seat = number_seat - 1 WHERE flight_number = ?";
        jdbcTemplate.update(updateFlightSql, booking.getFlight_number());

        // Ghi dữ liệu vào bảng thanh_toan
        ThanhToan thanhToan = new ThanhToan();

        try {
            // Thực hiện cập nhật trạng thái thanh toán


            // Gọi phương thức để thêm thanh toán vào cơ sở dữ liệu
            thanhToan.setPaymentId(booking.getMa_ve());
            thanhToan.setPaymentStatus("Success");
            thanhToan.setPaymentAmount(new BigDecimal(booking.getPrice()));
            thanhToan.setPaymentCurrency("USD");
            thanhToan.setPaymentDate(new Date());
            paymentService.addPayment(thanhToan);

            // Trừ số tiền từ balance của người dùng
            BigDecimal paymentAmount = new BigDecimal(booking.getPrice());
            userService.updateBalanceByUsername(booking.getEmail(), paymentAmount.negate());

            // Xóa thông tin booking khỏi session sau khi ghi dữ liệu thành công
            session.removeAttribute("booking");

            // Chuyển hướng người dùng đến trang thành công
            return "redirect:/bookings/success";
        } catch (Exception e) {
            // Xử lý lỗi và thông báo cho người dùng
            e.printStackTrace();
            // Ghi log lỗi vào hệ thống log
            // ...
            thanhToan.setPaymentId(booking.getMa_ve());
            thanhToan.setPaymentStatus("Error");
            thanhToan.setPaymentAmount(new BigDecimal(booking.getPrice()));
            thanhToan.setPaymentCurrency("USD");
            thanhToan.setPaymentDate(new Date());
            paymentService.addPayment(thanhToan);
            // Cập nhật trạng thái thanh toán vào giá trị tương ứng khi gặp lỗi

            // Gọi phương thức để thêm thanh toán vào cơ sở dữ liệu với trạng thái lỗi
            paymentService.addPayment(thanhToan);

            // Chuyển hướng người dùng đến trang lỗi hoặc trang khác tùy thuộc vào yêu cầu
            return "redirect:/error";
        }
    }

    @GetMapping("/bookings/book/{flightNumber}")
    public String showBookingForm(@PathVariable("flightNumber") String flightNumber, Model model, HttpSession session) {
        model.addAttribute("flightNumber", flightNumber);
        model.addAttribute("booking", new bookinglist());
        User user = getUserFromSession(session, model);
        FlightNSeat fs = (FlightNSeat) session.getAttribute("transferredData");
        model.addAttribute("fs", fs);
        return "booking-form";
    }


    @PostMapping("/bookings/book/{flightNumber}")
    public String book(@PathVariable("flightNumber") String flightNumber, HttpSession session, @RequestParam("so_dien_thoai") String soDienThoai,
                       @RequestParam("email") String email, @RequestParam("ho_ten") String hoTen, @RequestParam("id_card") String idCard,
                       @RequestParam("seatNumber") String seatNumber, Model model) {

        FlightNSeat fs = (FlightNSeat) session.getAttribute("transferredData");
        String flightN = fs.getFlightNumber();
        String flightS = fs.getSeat_number();
        String classType = seatDAO.getClasstypeBySeatNumber(seatNumber);
        // Lấy thông tin người dùng từ session
        User user = getUserFromSession(session, model);

        // Kiểm tra xem có thông tin người dùng từ session hay không
        if (user == null) {
            // Nếu không có thông tin người dùng, chuyển hướng người dùng đến trang đăng nhập hoặc trang khác tùy thuộc vào yêu cầu của bạn
            return "redirect:/login";
        }

        // Tạo đối tượng booking từ thông tin người dùng và thông tin đặt vé
        bookinglist booking = new bookinglist();
        booking.setSo_dien_thoai(soDienThoai);
        booking.setEmail(email);
        booking.setHo_ten(hoTen);
        booking.setId_card(idCard);
        booking.setFlight_number(flightN);
        booking.setLoai_ghe(classType); // Giả sử mặc định là "economy", bạn có thể thay đổi tùy theo yêu cầu
        booking.setMa_ve(generateMaVe()); // Hàm generateMaVe() để tạo mã vé (tuỳ chỉnh)
        booking.setPrice(seatDAO.calculateFare(classType)); // Giá vé, bạn có thể thay đổi tùy theo yêu cầu
        booking.setImgLink(""); // Link hình ảnh, bạn có thể thay đổi tùy theo yêu cầu
        booking.setSo_ghe(flightS);

        // Lưu thông tin booking vào session để sử dụng sau khi thanh toán
        session.setAttribute("booking", booking);

        return "redirect:/bookings/payment";
    }

    @GetMapping("/booking/history")
    public String getBookingHistory(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String username = user.getUsername();
        List<BookingHistory> bookingHistoryList = bookingService.getBookingHistory(username);
        model.addAttribute("bookingHistoryList", bookingHistoryList);
        return "booking-history";
    }


    // Hàm generateMaVe() để tạo mã vé (tuỳ chỉnh)
    private String generateMaVe() {
        String bookingDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String maVe = "VN" + bookingDate;
        return maVe; // Thay thế bằng mã logic của bạn
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
