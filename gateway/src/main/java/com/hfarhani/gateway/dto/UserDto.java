package com.hfarhani.gateway.dto;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class UserDto implements UserDetails {
    private String username;
    private String password;
    private Date passwordExpirationDate;

    public UserDto() {
        username = "test";
        password = "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6";
        Calendar c = Calendar.getInstance(); // starts with today's date and time
        c.add(Calendar.DAY_OF_YEAR, 2);  // advances day by 2
        passwordExpirationDate = c.getTime();
    }

    public static UserDto findByUsername(String username) {
        UserDto userDto = new UserDto();
        if (username != null && username.equals(userDto.getUsername()))
            return userDto;
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {

        return passwordExpirationDate.after(Calendar.getInstance().getTime());
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

}
