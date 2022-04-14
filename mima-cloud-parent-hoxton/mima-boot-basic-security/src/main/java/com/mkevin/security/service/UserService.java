package com.mkevin.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Map<String,Object> userPo = jdbcTemplate.queryForMap("select * from auth_user where username='" + username + "'");
            if (userPo == null) {
                throw new UsernameNotFoundException("用户名不存在");
            }

            Long id = (Long)userPo.get("id");
            String password = (String)userPo.get("password");

            //用户权限
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from auth_user_role where user_id=" + id);
            if (!CollectionUtils.isEmpty(list)) {
                for (Map<String,Object> po : list) {
                    String roleCode = (String)po.get("role_code");
                    authorities.add(new SimpleGrantedAuthority(roleCode));
                }
            }
            return new User(username, password, authorities);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
