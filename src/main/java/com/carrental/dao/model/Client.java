package com.carrental.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "client")
public class Client extends BaseEntity {


    @Column(name = "surname", length = 45)
    @Size(min = 2, max = 30)
    private String surname;

    @Column(name = "name", length = 45)
    @Size(min = 2, max = 30)
    private String name;

    @Column(name = "patronymic", length = 45)
    @Size(min = 2, max = 30)
    private String patronymic;

    @Column(name = "number_of_passport", length = 45)
    private String numberOfPassport;

    @Column(name = "telephone", length = 45)
    @Pattern(regexp = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$",message = " +375297563344 или 80442897638")
    private String telephone;

    @Column(name = "address", length = 60)
    private String address;

    @Column(name = "driving_experience")
    private Integer drivingExperience;

    @Enumerated(EnumType.STRING)//эта запись обозначает ,что наши записи в Enum будут иметь тип String
    private Role role;

    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z](?!.*\\\\.\\\\.)[a-zA-Z.\\\\d]{5,19}$")
    //Длина: 6 - 20 символов
    // Нужно начинать с буквы a-zA-Z
    // Может содержать a-zA-Z0-9 и.
    // Не может быть двух последовательных точек
    private String username;

    @Column(name = "password")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Не менее 8 символов\n" +
                    "\n" +
                    "цифра должна встречаться хотя бы один раз\n" +
                    "\n" +
                    "строчная буква должна встречаться хотя бы один раз\n" +
                    "\n" +
                    "заглавная буква должна встречаться хотя бы один раз\n" +
                    "\n" +
                    "специальный символ должен встречаться хотя бы один раз\n" +

                    "Не содержит пробелов, табуляции и т. д.")
    private String password;

    @OneToMany(mappedBy = "idClient")
    private List<Order> ordersClient;//это в client


    @Override
    public String toString() {
        return "Client{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", numberOfPassport='" + numberOfPassport + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", drivingExperience=" + drivingExperience +
                ", role=" + role +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ordersClient=" + ordersClient +
                '}';
    }
}