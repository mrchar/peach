package net.mrchar.peach.authorization.domain.authentication.service.impl;

import net.mrchar.peach.authorization.domain.authentication.model.AccountEntity;
import net.mrchar.peach.authorization.domain.authentication.model.UserDetailsImpl;
import net.mrchar.peach.authorization.domain.authentication.repository.AccountRepository;
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
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        AccountEntity accountEntity = this.accountRepository.findOneByName(accountName)
                .orElseThrow(() -> new UsernameNotFoundException("没有查找到账户名对应的账户"));
        return new UserDetailsImpl(accountEntity);
    }
}
