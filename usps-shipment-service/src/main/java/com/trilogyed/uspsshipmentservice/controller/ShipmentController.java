package com.trilogyed.uspsshipmentservice.controller;

import com.trilogyed.uspsshipmentservice.model.Shipment;
import com.trilogyed.uspsshipmentservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    @Autowired
    ServiceLayer sl;

    @PostMapping("/addshipment")
    @ResponseStatus(HttpStatus.CREATED)
    public Shipment save(@RequestBody @Valid Shipment shipment) {
        return sl.save(shipment);
    }

    @GetMapping("/{trackingNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Shipment fetchShipment(@PathVariable Integer trackingNumber) {
        return sl.findByTrackingNumber(trackingNumber);
    }
}
