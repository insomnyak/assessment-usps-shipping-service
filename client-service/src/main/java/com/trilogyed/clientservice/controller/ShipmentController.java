package com.trilogyed.clientservice.controller;

import com.trilogyed.clientservice.domain.Shipment;
import com.trilogyed.clientservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientfe")
public class ShipmentController {

    @Autowired
    ServiceLayer sl;

    @PostMapping("/addshipment")
    @ResponseStatus(HttpStatus.CREATED)
    public Shipment save(@RequestBody Shipment shipment) {
        return sl.save(shipment);
    }

    @GetMapping("/shipment/{trackingNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Shipment fetchShipment(@PathVariable Integer trackingNumber) {
        return sl.findByTrackingNumber(trackingNumber);
    }
}

