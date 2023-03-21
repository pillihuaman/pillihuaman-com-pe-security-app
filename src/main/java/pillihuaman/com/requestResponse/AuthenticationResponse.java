package pillihuaman.com.requestResponse;


import org.bson.types.ObjectId;
import pillihuaman.com.base.request.ReqControl;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespUser;
import pillihuaman.com.basebd.control.domain.Control;

import java.util.List;

public class AuthenticationResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObjectId id_user;
	private String token;
	private int idSystem;
	private List<ReqControl> control;

	public ObjectId getId_user() {
		return id_user;
	}

	public void setId_user(ObjectId id_user) {
		this.id_user = id_user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getIdSystem() {
		return idSystem;
	}

	public void setIdSystem(int idSystem) {
		this.idSystem = idSystem;
	}

	public List<ReqControl> getControl() {
		return control;
	}

	public void setControl(List<ReqControl> control) {
		this.control = control;
	}
}
