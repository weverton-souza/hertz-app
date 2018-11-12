package com.rent.hertz.model;

import java.util.List;
import java.util.Objects;

public class Rent {

    private long id;

    private String date;

    private Vehicle vehicle;

    private Customer customer;

    private List<Demage> demages;

    private List<TrafficTicket> trafficTickets;


    public long getId() {
        return id;
    }

    public Rent setId(long id) {
        this.id = id;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Rent setDate(String date) {
        this.date = date;
        return this;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Rent setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Rent setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public List<Demage> getDemages() {
        return demages;
    }

    public Rent setDemages(List<Demage> demages) {
        this.demages = demages;
        return this;
    }

    public List<TrafficTicket> getTrafficTickets() {
        return trafficTickets;
    }

    public Rent setTrafficTickets(List<TrafficTicket> trafficTickets) {
        this.trafficTickets = trafficTickets;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return id == rent.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", date=" + date +
                ", vehicle=" + vehicle +
                ", customer=" + customer +
                ", demages=" + demages +
                ", trafficTickets=" + trafficTickets +
                '}';
    }
}
