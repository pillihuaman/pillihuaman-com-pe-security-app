package pillihuaman.com.segurity.Service.Implement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pillihuaman.com.segurity.Service.ControlService;
import pillihuaman.com.segurity.lib.commons.MyJsonWebToken;
import pillihuaman.com.segurity.lib.response.RespBase;
import pillihuaman.com.segurity.lib.response.RespControl;

@Component
public class ControlServiceImpl implements ControlService {
    
    protected final Log log = LogFactory.getLog(getClass());

    @Override
    public RespBase<RespControl> listControl(MyJsonWebToken token, ReqBase<ReqControl> request) {
        return null;
    }

    @Override
    public RespBase<RespControl> saveControl(MyJsonWebToken token, ReqBase<ReqControl> request) {
        return null;
    }
/*
    @Override
    public RespBase<RespControl> listControl(MyJsonWebToken token, ReqBase<ReqControl> request) {
        RespBase<RespControl> re = new RespBase<>();
        re.setPayload(ConvertClass.listControlToRespControl(ControlDAO.listControl(request.getData())));
        return re;
    }

    @Override
    public RespBase<RespControl> saveControl(MyJsonWebToken token, ReqBase<ReqControl> request) {
        RespBase<RespControl> re = new RespBase<>();
        List<RespControl> e = ControlDAO.saveControl(request.getData(),token);
        if (e != null ) {
            re.setPayload(e.get(0));
            RespBase.Status s = new RespBase.Status();
            s.setSuccess(true);
            RespBase.Status.Error er = new RespBase.Status.Error();
            er.setCode("200");
            List<String> ls = new ArrayList<>();
            ls.add("Success");
            er.setMessages(ls);
            s.setError(er);
            re.setStatus(s);
        } else {
            re.setPayload(null);
            RespBase.Status s = new RespBase.Status();
            s.setSuccess(false);
            RespBase.Status.Error er = new RespBase.Status.Error();
            er.setCode("402");
            List<String> ls = new ArrayList<>();
            ls.add("The code exist or dont save");
            er.setMessages(ls);
            s.setError(er);
            re.setStatus(s);

        }


        return re;
    }

 */
}
