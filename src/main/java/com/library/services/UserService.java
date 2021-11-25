package com.library.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.library.entities.User;
import com.library.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
    PasswordEncoder passEncoder;
	
	public void save(User user) {
		user.setPassword(passEncoder.encode(user.getPassword()));
		userRepository.save(user);
		this.loadUserByUsername(user.getEmail());
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email);

		if (user != null) {
			List<GrantedAuthority> permissions = new ArrayList<>();
			GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + user.getRol().toString());
			permissions.add(p);
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("user", user);
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
					permissions);
		}
		return null;
	}
}
