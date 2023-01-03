package com.github.mrchar.peach.authorization.domain.authentication.service.impl;

import com.github.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import com.github.mrchar.peach.authorization.domain.authentication.model.UserDetailsImpl;
import com.github.mrchar.peach.authorization.domain.authentication.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity accountEntity = this.accountRepository.findOneByName(username);
        return new UserDetailsImpl(accountEntity);
    }
}
