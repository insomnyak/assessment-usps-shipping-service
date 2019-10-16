package com.trilogyed.uspsshipmentservice.service;

import com.trilogyed.uspsshipmentservice.dao.ShipmentRepository;
import com.trilogyed.uspsshipmentservice.exception.ShipmentAlreadyExistsException;
import com.trilogyed.uspsshipmentservice.exception.TupleNotFoundException;
import com.trilogyed.uspsshipmentservice.model.Shipment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class ServiceLayerTest {

    @Mock
    ShipmentRepository repo;

    @InjectMocks
    ServiceLayer sl = new ServiceLayer(repo);

    Shipment shipment1a;
    Shipment shipment1b;

    Shipment shipment2a;
    Shipment shipment2b;

    @Before
    public void setUp() throws Exception {
        constructSampleData();

        doReturn(shipment1b).when(repo).save(shipment1a);
        doReturn(shipment1b).when(repo).findShipmentByTrackingNumber(12345);
        doReturn(Optional.of(shipment1b)).when(repo).findById(1);

        doReturn(null).when(repo).findShipmentByTrackingNumber(54321);
        doReturn(new ArrayList<>()).when(repo).findAllShipmentByTrackingNumber(12345);

        List<Shipment> shipments2 = new ArrayList<>();
        shipments2.add(shipment2b);

        doReturn(shipments2).when(repo).findAllShipmentByTrackingNumber(123456);
    }

    @After
    public void tearDown() throws Exception {
        destroySampleData();
    }

    public void constructSampleData() {
        shipment1a = new Shipment();
        shipment1b = new Shipment();
        shipment2a = new Shipment();
        shipment2b = new Shipment();

        shipment1a.setName("shipment 1");
        shipment1a.setTrackingNumber(12345);

        shipment1b.setName("shipment 1");
        shipment1b.setTrackingNumber(12345);
        shipment1b.setShipmentId(1);

        shipment2a.setName("shipment 1");
        shipment2a.setTrackingNumber(123456);

        shipment2b.setName("shipment 1");
        shipment2b.setTrackingNumber(123456);
        shipment2b.setShipmentId(2);
    }

    public void destroySampleData() {
        shipment1a = null;
        shipment1b = null;

        shipment2a = null;
        shipment2b = null;
    }

    @Test
    public void saveGet() {
        // save, get by Id, and find by tracking number
        Shipment shipment = sl.save(shipment1a);
        Shipment shipment1 = sl.findByTrackingNumber(12345);
        assertEquals(shipment, shipment1);
    }

    @Test(expected = TupleNotFoundException.class)
    public void findTrackingNumberThatDoesNotExist() {
        Shipment shipment = sl.findByTrackingNumber(54321);
        assertNull(shipment);
    }

    @Test(expected = ShipmentAlreadyExistsException.class)
    public void testWhenSameTrackingNumberIsAdded() {
        sl.save(shipment2a);
    }
}