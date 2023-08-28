package pillihuaman.com.segurity.Service;


import pillihuaman.com.segurity.lib.commons.MyJsonWebToken;
import pillihuaman.com.segurity.lib.request.ReqControl;
import pillihuaman.com.segurity.lib.response.RespBase;
import pillihuaman.com.segurity.lib.response.RespControl;

public interface ControlService {
	RespBase<RespControl> listControl(MyJsonWebToken token, ReqBase<ReqControl>  request);
	RespBase<RespControl> saveControl(MyJsonWebToken token, ReqBase<ReqControl>  request);
}




