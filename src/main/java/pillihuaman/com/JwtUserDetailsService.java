package pillihuaman.com;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import pillihuaman.com.model.response.RespBase;
import pillihuaman.com.model.response.RespUser;
import pillihuaman.com.service.UserService;

@Component
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RespBase<RespUser> respo =  userService.getUserByMail(username);

		if (respo !=null) {
			if (respo.getPayload().getMail().equals(username)) {
				return new User(respo.getPayload().getMail(), respo.getPayload().getPassword(),
						new ArrayList<>());
			} else {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);

		}
	}
}