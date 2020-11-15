package com.ajrat.security;

import com.ajrat.domain.Reader;
import com.ajrat.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ReaderRepository readerRepository;

    @Autowired
    public UserDetailsServiceImpl(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Reader reader = readerRepository.findByName(name).orElseThrow(() ->
                new UsernameNotFoundException("Reader doesn't exists"));
        return SecurityUser.fromReader(reader);
    }
}