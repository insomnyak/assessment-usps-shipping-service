package com.trilogyed.uspsshipmentservice.dao;

import com.trilogyed.uspsshipmentservice.exception.ShipmentAlreadyExistsException;
import com.trilogyed.uspsshipmentservice.model.Shipment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipmentRepositoryTest {

    @Autowired
    ShipmentRepository shipmentRepo;

    @Before
    public void setUp() throws Exception {
        shipmentRepo.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findShipmentByTrackingNumber() {
        // test add & get
        Shipment shipment = new Shipment();
        shipment.setName("Rene's Shipment #1");
        shipment.setTrackingNumber(1234123);
        shipmentRepo.save(shipment);

        Shipment shipment1 = shipmentRepo.findById(shipment.getShipmentId()).orElse(null);
    }

    @Test
    public void testDatabaseConstraints() {
        // test save shipment with null name
        try {
            Shipment shipment = new Shipment();
            shipment.setTrackingNumber(1234123);
            shipmentRepo.save(shipment);
        } catch (Throwable ignore) {}

        // test save shipment with name that exceeds max character limit of 50
        try {
            Shipment shipment = new Shipment();
            shipment.setName("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            shipment.setTrackingNumber(1234123);
            shipmentRepo.save(shipment);
        } catch (Throwable ignore) {}

        // test save shipment with null trackingNumber
        try {
            Shipment shipment = new Shipment();
            shipment.setName("Rene's Shipment #1");
            shipmentRepo.save(shipment);
        } catch (Throwable ignore) {}

        List<Shipment> shipments = shipmentRepo.findAll();
        assertEquals(0, shipments.size());
    }


}