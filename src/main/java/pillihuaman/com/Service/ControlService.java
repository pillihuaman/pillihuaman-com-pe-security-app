package pillihuaman.com.Service;

import pillihuaman.com.base.request.ReqBase;
import pillihuaman.com.base.request.ReqControl;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespControl;
import pillihuaman.com.basebd.control.domain.Control;
import pillihuaman.com.crypto.MyJsonWebToken;

public interface ControlService {
	RespBase<RespControl> listControl(MyJsonWebToken token, ReqBase<ReqControl>  request);
	RespBase<RespControl> saveControl(MyJsonWebToken token, ReqBase<ReqControl>  request);
}




