package pillihuaman.com.requestResponse;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class JwtRequest implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;

	private String username;
	private String password;
	private String mail;

	public JwtRequest() {

	}

	public JwtRequest(String username, String password,String mail) {
		this.setUsername(username);
		this.setPassword(password);
		this.setMail(mail);
	}

}