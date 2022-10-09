package pillihuaman.com.help;


import pillihuaman.com.model.response.RespUser;
import pillihuaman.com.user.domain.User;



public class ConvertClass {

	

public static  RespUser	UserTblToUserDTO(User request){
	RespUser resp= new RespUser();
	resp.setMail(request.getMail());
	resp.setUsername(request.getUser_name());
	resp.setAlias(request.getAlias());
	resp.setApi_Password(request.getApi_password());
	resp.setSal_Password(request.getSal_password());
	resp.setPassword(request.getPassword());
	return resp;
	
}


}
