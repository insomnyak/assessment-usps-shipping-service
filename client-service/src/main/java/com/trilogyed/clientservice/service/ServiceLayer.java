package com.trilogyed.clientservice.service;

import com.trilogyed.clientservice.domain.Shipment;
import com.trilogyed.clientservice.exception.TrackingNumberAlreadyExistsException;
import com.trilogyed.clientservice.exception.TupleNotFoundException;
import com.trilogyed.clientservice.util.feign.ShipmentServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {

    ShipmentServiceClient client;

    @Autowired
    public ServiceLayer(ShipmentServiceClient client) {
        this.client = client;
    }

    public Shipment save(Shipment shipment) {
        try {
            return client.save(shipment);
        } catch (Exception e) {
            if (e.getMessage().contains("422")) {
                throw new TrackingNumberAlreadyExistsException("A shipment with tracking number " +
                        shipment.getTrackingNumber() + " already exists.");
            } else {
                throw e;
            }
        }
    }

    public Shipment findByTrackingNumber(Integer trackingNumber) {
        Shipment shipment = client.fetchShipment(trackingNumber);
        if (shipment == null) {
            throw new TupleNotFoundException("Shipment not found for trackingNumber " + trackingNumber);
        }
        return shipment;
    }
}
