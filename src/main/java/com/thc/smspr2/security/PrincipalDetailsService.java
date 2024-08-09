package com.thc.smspr2.security;

import com.thc.smspr2.domain.Tbuser;
import com.thc.smspr2.exception.NoMatchingDataException;
import com.thc.smspr2.repository.TbuserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	private final TbuserRepository tbuserRepository;
	
	public PrincipalDetailsService(TbuserRepository tbuserRepository) {
		this.tbuserRepository = tbuserRepository;
	}
	
    /**
	 *  principalDetails 생성을 위한 함수.
	 *  username으로 tbuser 조회, principalDetails 생성
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Tbuser tbuser = tbuserRepository.findByUsername(username);
		if(tbuser == null) {
			throw new NoMatchingDataException("username : " + username);
		}
		return new PrincipalDetails(tbuser);
	}
	
}