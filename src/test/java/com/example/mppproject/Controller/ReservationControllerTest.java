package com.example.mppproject.Controller;

import com.example.mppproject.Model.*;
import com.example.mppproject.Model.Enum.*;
import com.example.mppproject.Service.MyUserDetailService;
import com.example.mppproject.Service.ReservationService;
import com.example.mppproject.security.JwtRequestFilter;
import com.example.mppproject.util.JwtUtil;
import org.assertj.core.internal.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReservationController.class)
@AutoConfigureMockMvc(addFilters = false)
class ReservationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReservationService reservationService;
    @MockBean
    private MyUserDetailService myUserDetailService;
    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private JwtRequestFilter filter;
    private List<Reservation> reservationList;
    private Reservation reservation;


    @BeforeEach
    void setUp() {
        String refNumber="123ee";
        Account account = new Account(123465898, 672.2);
        Address address = new Address("Addis Ababa", "Addis Ababa", "Ethiopia", "1234", "5678", "19.2", "38.9");
        Set<Role> role = new HashSet<>();
        role.add(new Role(RoleType.GUEST));
        AppUser appUser = new AppUser("Dawit", "Demelash", "davespot10", role, "123456", address, account);
        Type type =  Type.HOME;
        Space space = Space.ENTIRE_PLACE;
        ApprovedStatus approvedStatus = ApprovedStatus.PENDING;
        HomeProperty homeProperty = new HomeProperty(2, 3, 3, "Excellent Condition");
        Property property = new Property("Luxury Apartment", type, space,
                "stay safe", address, 123.22, approvedStatus, true, 2, homeProperty, appUser);
        ReservationStatusEnum reservationStatusEnum = ReservationStatusEnum.PENDING;
      // reservation = new Reservation(123.3, "11/11/22", "12/12/22", appUser, property, "132", reservationStatusEnum);
        reservation = Reservation.builder()
                .appUser(appUser)
                .calculatedPrice(1000.00)
                .startDate("11/11/22")
                .endDate("12/12/22")
                .property(new Property("Luxury Apartment", Type.HOME, space,
                        "stay safe", address, 123.22, approvedStatus, true, 2, homeProperty, appUser))
                .reservationStatus(reservationStatusEnum)
                .refNumber("123")
                .id(1L)
                .build();
    }

    @Test
    void getReservations() throws Exception {
        Mockito.when(reservationService.getReservations())
                .thenReturn(reservationList);
        mockMvc.perform(get("/api/v1/reservation")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getResrvationByRef() throws Exception {
        Mockito.when(reservationService.getReservationByRef("123ee"))
                .thenReturn(reservation);
        mockMvc.perform(get("/api/v1/reservation/123ee")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("startDate")
                        .value(reservation.getStartDate()));
    }

    @Test
    void createReservation() {
    }

    @Test
    void cancelReservation() {
    }
}