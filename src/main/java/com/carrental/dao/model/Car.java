package com.carrental.dao.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "car")
public class Car extends BaseEntity {

    @Column(name = "brand", length = 45)
    private String brand;

    @Column(name = "model", length = 45)
    private String model;

    @Column(name = "year_of_issue")
    private Integer yearOfIssue;

    @Column(name = "number_of_registration", length = 45)
    private String numberOfRegistration;

    @Column(name = "transmission", length = 45)
    private String transmission;

    @Column(name = "`price(day)$`", precision = 10, scale = 2)
    private BigDecimal priceDay;

    @OneToMany(mappedBy = "idCar")
    private List<Order> ordersCars;



    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearOfIssue=" + yearOfIssue +
                ", numberOfRegistration='" + numberOfRegistration + '\'' +
                ", transmission='" + transmission + '\'' +
                ", priceDay=" + priceDay +
                ", ordersCars=" + ordersCars +
                '}';
    }
}