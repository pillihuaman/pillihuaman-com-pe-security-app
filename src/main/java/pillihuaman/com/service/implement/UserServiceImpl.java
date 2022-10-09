package pillihuaman.com.service.implement;


import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pillihuaman.com.crypto.PasswordUtils;
import pillihuaman.com.help.AuditEntity;
import pillihuaman.com.help.ConvertClass;
import pillihuaman.com.model.request.ReqUser;
import pillihuaman.com.model.response.RespBase;
import pillihuaman.com.model.response.RespUser;
import pillihuaman.com.service.UserService;
import pillihuaman.com.user.domain.User;
import pillihuaman.com.user.domain.dao.UserRepository;


@Component
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public RespBase<RespUser> getUserByMail(String mail) {

		RespBase<RespUser> respo = new RespBase<RespUser>();

		try {
			List<User> lis = userRepository.findUserByMail(mail);
			RespUser obj = new RespUser();
			for (User user : lis) {
				obj.setAlias(user.getAlias());
				obj.setApi_Password(user.getApi_password());
				obj.setId_system(user.getId_system());
				obj.setMail(user.getMail());
				obj.setPassword(user.getPassword());
				obj.setSal_Password(user.getSal_password());
				obj.setUsername(user.getUser_name());
			}
			respo.setPayload(obj);

		} catch (Exception e) {
			respo.getStatus().setSuccess(Boolean.FALSE);
			respo.getStatus().getError().getMessages().add(e.getMessage());
		}

		return respo;
	}

	@Override
	public RespBase<RespUser> getUserByUserName(String username) {
		RespBase<RespUser> respo = new RespBase<RespUser>();

		try {

			List<User> listaUsuario = userRepository.findUserName(username);
			if (listaUsuario != null && listaUsuario.size() > 0) {
				respo.setPayload(ConvertClass.UserTblToUserDTO(listaUsuario.get(0)));

			}

		} catch (Exception e) {
			respo.getStatus().setSuccess(Boolean.FALSE);
			respo.getStatus().getError().getMessages().add(e.getMessage());
		}
		return respo;
	}

	@Override
	public RespBase<RespUser> registerUser(ReqUser request) {
		try {
			User filtro = new User();
			String salt = PasswordUtils.getSalt(30);
			String apiPasword = PasswordUtils.generateSecurePassword(request.getPassword(), salt);
			BCryptPasswordEncoder en = new BCryptPasswordEncoder();

			String Password = en.encode(request.getPassword());
			filtro.set_id(new ObjectId());
			filtro.setAlias("");
			filtro.setApi_password(apiPasword);
			filtro.setId_system(1);
			List<User> lst = userRepository.findLastUser();
			if (lst != null) {
				if (lst.size() > 0) {
					filtro.setId_user(lst.get(0).getId_user() + 1);
				} else {
					filtro.setId_user(1);
				}
			}

			filtro.setMail(request.getMail());
			filtro.setMobil_phone(request.getMobilPhone());
			filtro.setPassword(Password);
			filtro.setSal_password(salt);
			filtro.setUser_name(request.getUsername());
			filtro.setNumType_document(request.getNumTypeDocument());
			filtro.setType_document(request.getTypeDocument());
			AuditEntity auditEntity = new AuditEntity();
			auditEntity.setCodUsuModif("o1Zarmir");
			auditEntity.setCodUsuRegis("o1Zarmir");
			auditEntity.setFecModif(new Date());
			auditEntity.setFecRegis(new Date());
			userRepository.save(filtro);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public int getLastIdUser() {
		int id = 0;
		try {
			MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
			MongoDatabase database = mongoClient.getDatabase("gamachicas");
			MongoCollection<Document> collection = database.getCollection("user");
			Document sort = new Document().append("_id", -1);
			Document lis = collection.find().sort(sort).first();
			if (Objects.nonNull(lis)) {
				id = (int) lis.get("idUser");
			}
			mongoClient.close();
		} catch (MongoException e) {
			id = 0;
		}
		return id;
	}

	@Override
	public RespBase<RespUser> lastUser(ReqUser request) {
		// TODO Auto-generated method stub
		return null;
	}

}
