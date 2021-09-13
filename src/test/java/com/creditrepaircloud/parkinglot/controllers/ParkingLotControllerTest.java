package com.creditrepaircloud.parkinglot.controllers;

import com.creditrepaircloud.parkinglot.ParkinglotApplication;

import com.creditrepaircloud.parkinglot.domain.Car;
import com.creditrepaircloud.parkinglot.domain.Slot;
import com.creditrepaircloud.parkinglot.domain.Token;
import com.creditrepaircloud.parkinglot.services.ParkingLot;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ParkinglotApplication.class)
@WebAppConfiguration
//@SpringBootTest
public class ParkingLotControllerTest {

    @Mock
    ParkingLot parkingLot;

    @InjectMocks
    ParkingLotController parkingLotController;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under
        // test.
        MockitoAnnotations.initMocks(this.parkingLotController);

        this.mockMvc = MockMvcBuilders.standaloneSetup(parkingLotController).build();

    }


    @Test
    public void testTheGetCarService() throws Exception
    {
        Token token = new Token("XYZ123123",new Slot(123),new Car("Blue","123"));

        when(parkingLotController.getCar("123")).thenReturn(token);

        mockMvc.perform(get("/getCar/{carNumber}", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tokenNumber", is("XYZ123123")))
                .andExpect(jsonPath("$['slotDetails'].slotNumber", is(123))
                );

        verify(parkingLot, times(1)).getCar("123");
        verifyNoMoreInteractions(parkingLot);

    }
    @Test
    public void testTheinitiateLotFunctionality() throws Exception
    {
        Slot slot = new Slot(1);
        ArrayList<Slot> Success = new ArrayList<Slot>();
        Success.add(slot);

        when(parkingLotController.initiateLot("10")).thenReturn(Success);

        mockMvc.perform(post("/initiateLot")
                .param("NumberOfLot","10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].slotNumber").value("1"))
                .andExpect(jsonPath("$[0].slotFree").value("true"));
        verify(parkingLot, times(1)).initiateLot(10);
        verifyNoMoreInteractions(parkingLot);

    }
    @Test
    public void testTheParkTheCarService() throws Exception
    {
        Token token = new Token("XYZ123123",new Slot(123),new Car("Blue","123"));

        when(parkingLotController.parkCar("Blue","123")).thenReturn(token);
        mockMvc.perform(post("/ParkTheCar")
                        .param("Color","Blue")
                        .param("Number","123")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['carDetails'].carColor").value("Blue"))
                .andExpect(jsonPath("$['carDetails'].carColor").value("Blue"))
                .andExpect(jsonPath("$.tokenNumber").value("XYZ123123"));
        verify(parkingLot, times(1)).parkTheCar("Blue","123");
        verifyNoMoreInteractions(parkingLot);

    }
    @Test
    public void testUnParkTheCarService() throws Exception
    {
        String responseString = "Car entry removed";

        when(parkingLotController.unParkCar("XYZ123123")).thenReturn(responseString);

        mockMvc.perform(delete("/unParkTheCar/{tokenNumber}", "XYZ123123"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", is("Car entry removed")));

        verify(parkingLot, times(1)).unParkTheCar("XYZ123123");
        verifyNoMoreInteractions(parkingLot);

    }
    }