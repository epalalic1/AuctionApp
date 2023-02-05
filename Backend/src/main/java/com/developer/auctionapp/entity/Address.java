package com.developer.auctionapp.entity;

import javax.persistence.*;

/**
 * <p>Address</p>
 *
 * Class that holds data about one Address and with annotation @Table we are creating that entity
 */

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private final long id;

    @Column(name = "country")
    private final String country;

    @Column(name = "city")
    private final String city;

    @Column(name = "postcode")
    private final String postcode;

    @Column(name = "streetName")
    private final String streetName;

    @Column(name = "streetNumber")
    private final long streetNumber;

    public Address(
            final long id,
            final String country,
            final String city,
            final String postcode,
            final String streetName,
            final long streetNumber
    ) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.postcode = postcode;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
    }

    public Address() {
        this.country = null;
        this.id = 0;
        this.city = null;
        this.postcode = null;
        this.streetName = null;
        this.streetNumber = 0;
    }

    public long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getStreetName() {
        return streetName;
    }

    public long getStreetNumber() {
        return streetNumber;
    }
}
