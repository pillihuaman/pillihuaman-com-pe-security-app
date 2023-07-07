package pillihuaman.com.requestResponse;


import pillihuaman.com.base.request.ReqControl;

import java.util.List;

public class AuthenticationResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String alias;
	private String mail;
	private String username;
	private String token;
	private String userInfo;
	private List<ReqControl> control;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	public List<ReqControl> getControl() {
		return control;
	}

	public void setControl(List<ReqControl> control) {
		this.control = control;
	}
}
