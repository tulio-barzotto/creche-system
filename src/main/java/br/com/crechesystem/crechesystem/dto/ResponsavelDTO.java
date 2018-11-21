package br.com.crechesystem.crechesystem.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class ResponsavelDTO implements Serializable {

    private String name;

    private String dddPhoneNumber;

    private String phoneNumber;

    private String address;

    private String city;

    private String uf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDddPhoneNumber() {
        return dddPhoneNumber;
    }

    public void setDddPhoneNumber(String dddPhoneNumber) {
        this.dddPhoneNumber = dddPhoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "ResponsavelDTO{" +
                "name='" + name + '\'' +
                ", dddPhoneNumber='" + dddPhoneNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
