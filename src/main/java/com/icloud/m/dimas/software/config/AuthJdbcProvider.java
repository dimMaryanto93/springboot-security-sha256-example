package com.icloud.m.dimas.software.config;

import com.icloud.m.dimas.software.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AuthJdbcProvider implements AuthenticationProvider {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials().toString();
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("select username, encript_password, hash_id from user_detail where username = ?", new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new User(
                            rs.getString("username"),
                            rs.getString("encript_password"),
                            rs.getString("hash_id")
                    );
                }
            }, username);

            boolean isValid = passwordEncoder.isPasswordValid(user.getPassword(), password, user.getSalt());
            if (isValid) return new UsernamePasswordAuthenticationToken(username, user.getPassword());
            else throw new BadCredentialsException("Username and password salah!");
        } catch (EmptyResultDataAccessException erdae) {
            throw new UsernameNotFoundException("Username tidak ditemukan di sistem!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}