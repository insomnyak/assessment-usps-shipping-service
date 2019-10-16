package com.trilogyed.uspsshipmentservice.service;

import com.trilogyed.uspsshipmentservice.dao.ShipmentRepository;
import com.trilogyed.uspsshipmentservice.exception.ShipmentAlreadyExistsException;
import com.trilogyed.uspsshipmentservice.exception.TupleNotFoundException;
import com.trilogyed.uspsshipmentservice.model.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {

    ShipmentRepository shipmentRepo;

    @Autowired
    public ServiceLayer(ShipmentRepository shipmentRepo) {
        this.shipmentRepo = shipmentRepo;
    }

    public Shipment save(Shipment shipment) {
        List<Shipment> shipments = findAllShipmentsByTrackingNumber(shipment.getTrackingNumber());

        if (shipments.size() > 0) {
            throw new ShipmentAlreadyExistsException("Cannot save. A shipment with tracking number " +
                    shipment.getTrackingNumber() + " already exists.");
        }

        return shipmentRepo.save(shipment);
    }

    public Shipment findByTrackingNumber(Integer trackingNumber) {
        Shipment shipment = shipmentRepo.findShipmentByTrackingNumber(trackingNumber);
        if (shipment == null) {
            throw new TupleNotFoundException(String.format("Shipment not found for trackingNumber %d", trackingNumber));
        }
        return shipment;
    }

    public List<Shipment> findAllShipmentsByTrackingNumber(Integer trackingNumber) {
        return shipmentRepo.findAllShipmentByTrackingNumber(trackingNumber);
    }
}
