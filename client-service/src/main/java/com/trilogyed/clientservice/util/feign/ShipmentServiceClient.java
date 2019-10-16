package com.trilogyed.clientservice.util.feign;

import com.trilogyed.clientservice.domain.Shipment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "USPS-SHIPMENT-SERVICE")
@RequestMapping("/shipment")
public interface ShipmentServiceClient {
    @PostMapping("/addshipment")
    public Shipment save(@RequestBody @Valid Shipment shipment);

    @GetMapping("/{trackingNumber}")
    public Shipment fetchShipment(@PathVariable Integer trackingNumber);
}
