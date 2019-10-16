package com.trilogyed.uspsshipmentservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.trilogyed.uspsshipmentservice.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
    Shipment findShipmentByTrackingNumber(Integer trackingNumber);
    List<Shipment> findAllShipmentByTrackingNumber(Integer trackingNumber);
}
