package pillihuaman.com.Service.Implement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pillihuaman.com.Service.ControlService;
import pillihuaman.com.base.commons.MyJsonWebToken;
import pillihuaman.com.base.request.ReqBase;
import pillihuaman.com.base.request.ReqControl;
import pillihuaman.com.base.response.RespBase;
import pillihuaman.com.base.response.RespControl;
import pillihuaman.com.basebd.control.domain.dao.ControlDAO;
import pillihuaman.com.basebd.help.ConvertClass;

import java.util.ArrayList;
import java.util.List;

@Component
public class ControlServiceImpl implements ControlService {

    @Autowired
    private ControlDAO ControlDAO;

    protected final Log log = LogFactory.getLog(getClass());

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
}
