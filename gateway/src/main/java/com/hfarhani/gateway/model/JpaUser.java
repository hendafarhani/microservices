package com.hfarhani.gateway.model;

import com.hfarhani.gateway.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JpaUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Date passwordExpirationDate;

    public JpaUser(String username, String password, Date passwordExpirationDate) {
        this.username = username;
        this.password = password;
        this.passwordExpirationDate = passwordExpirationDate;
    }

    public UserDto convertToDto() {
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setPasswordExpirationDate(passwordExpirationDate);
        return userDto;
    }
}
