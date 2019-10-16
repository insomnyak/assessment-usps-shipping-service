package com.trilogyed.clientservice.service;

import com.trilogyed.clientservice.domain.Shipment;
import com.trilogyed.clientservice.exception.TrackingNumberAlreadyExistsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceLayerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ServiceLayer sl;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() {
        Random rand = new Random();
        Shipment shipment = new Shipment();
        shipment.setName("test");
        Integer id = rand.nextInt(500000);
        shipment.setTrackingNumber(id);
        shipment = sl.save(shipment);

        Shipment shipment1 = sl.findByTrackingNumber(id);
        assertEquals(shipment, shipment1);

        try {
            sl.save(shipment);
        } catch (TrackingNumberAlreadyExistsException e) {}
    }

    @Test
    public void findByTrackingNumber() {
    }
}