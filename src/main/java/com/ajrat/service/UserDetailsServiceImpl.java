package com.ajrat.service;

import com.ajrat.domain.Reader;
import com.ajrat.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ReaderRepository readerRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Reader reader = readerRepository.findByName(name).orElseThrow(() ->
                new UsernameNotFoundException("Reader doesn't exists"));
        return Reader.getUserDetailsFromReader(reader);
    }
}