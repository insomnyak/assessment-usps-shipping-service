package com.trilogyed.clientservice.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Shipment {
    private Integer shipmentId;

    @NotBlank(message = "Please provide a name.")
    private String name;

    @NotNull(message = "Please provide the trackingNumber.")
    @Min(value = 1, message = "Invalid trackingNumber. Must be an integer greater than or equal to 1.")
    private Integer trackingNumber;

    public Integer getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Integer shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(Integer trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shipment)) return false;
        Shipment shipment = (Shipment) o;
        return Objects.equals(getShipmentId(), shipment.getShipmentId()) &&
                Objects.equals(getName(), shipment.getName()) &&
                Objects.equals(getTrackingNumber(), shipment.getTrackingNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShipmentId(), getName(), getTrackingNumber());
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "shipmentId=" + shipmentId +
                ", name='" + name + '\'' +
                ", trackingNumber=" + trackingNumber +
                '}';
    }
}
