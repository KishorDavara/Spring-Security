package com.express.config;

import com.express.model.Customer;
import com.express.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EasyBankDetailService implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password;
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Customer> customers = customerRepository.findByEmail(username);
        if (customers.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for the user: " + username);
        } else {
            userName = customers.get(0).getEmail();
            password = customers.get(0).getPwd();
            authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
        }
        return new User(userName, password, authorities);
    }
}
