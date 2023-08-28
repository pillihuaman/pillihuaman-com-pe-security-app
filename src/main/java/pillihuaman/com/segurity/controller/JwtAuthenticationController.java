
package pillihuaman.com.segurity.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import pillihuaman.com.segurity.Service.ControlService;
import pillihuaman.com.segurity.basebd.userGeneral.UserGeneralRepositoy;
import pillihuaman.com.segurity.crypto.PasswordUtils;
import pillihuaman.com.segurity.help.Constants;
import pillihuaman.com.segurity.help.MaestrosUtilidades;
import pillihuaman.com.segurity.lib.commons.MyJsonWebToken;
import pillihuaman.com.segurity.lib.request.ReqBase;
import pillihuaman.com.segurity.lib.request.ReqControl;
import pillihuaman.com.segurity.lib.request.ReqUser;
import pillihuaman.com.segurity.lib.response.RespBase;
import pillihuaman.com.segurity.lib.response.RespControl;
import pillihuaman.com.segurity.lib.response.RespUser;
import pillihuaman.com.segurity.requestResponse.AuthenticationResponse;
import pillihuaman.com.segurity.requestResponse.JwtRequest;
import pillihuaman.com.segurity.config.JwtTokenUtil;
import pillihuaman.com.segurity.config.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserGeneralRepositoy userGeneralRepositoy;
    @Autowired
    private ControlService controlService;

    @Operation(summary = "Create session", description = "Create session", tags = {""}, security = {
            @SecurityRequirement(name = Constants.BEARER_JWT)})
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
            @ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}),
            @ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))})})
    @PostMapping(path = {Constants.BASE_ENDPOINT + "/security/authenticate"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createAuthenticationToken(@RequestHeader String username, @RequestHeader String password, @RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(username, password,
                authenticationRequest.getMail());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getMail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        AuthenticationResponse auth = new AuthenticationResponse();
        RespBase<RespUser> respo = null;
        if (!token.isEmpty()) {
            respo = userGeneralRepositoy.getUserByMail(username);
            if (respo != null) {
                auth.setToken(token);
                ReqBase<ReqControl> request = new ReqBase<>();
                ReqControl d = new ReqControl();
                d.setId_user(respo.getPayload().getId_user());
                request.setData(d);
                auth.setAlias(respo.getPayload().getAlias());
                auth.setMail(respo.getPayload().getMail());
                auth.setUsername(respo.getPayload().getUsername());
                MyJsonWebToken con= new MyJsonWebToken();
                MyJsonWebToken.User use= new  MyJsonWebToken.User();
                use.setUser(respo.getPayload().getUsername());
                use.setMail(respo.getPayload().getMail());
                use.setIdUser(new ObjectId(respo.getPayload().getId_user()));
                con.setUser(use);
                auth.setUserInfo("");
                RespBase<RespControl> re = controlService.listControl(null, null);
                if (re != null) {
                    if (re.getPayload() != null) {
                        if (!re.getPayload().getLstControles().isEmpty()) {
                            auth.setControl(re.getPayload().getLstControles());
                        }
                    } else {
                        auth.setControl(null);
                    }
                }

            }
        }

        //auth.setId(1);
        return ResponseEntity.ok(auth);
    }
	

	/*@RequestMapping(value = "/authenticate", 
	 * 
	 * method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestHeader String username, @RequestHeader String password,  @RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(username, password,
				authenticationRequest.getMail());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getMail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		AuthenticationResponse auth = new AuthenticationResponse();
		auth.setToken(token);
		auth.setId(1);
		// return ResponseEntity.ok().headers(responseHeaders).body(user);
		return ResponseEntity.ok(auth);
	}*/

    @SuppressWarnings("null")
    private void authenticate(String username, String password, String mail) throws Exception {
        boolean valida = false;
        try {

            ReqUser user = new ReqUser();
            user.setUsername(username);
            user.setPassword(password);
            user.setMail(mail);

            //String salt = PasswordUtils.getSalt(500);
            //String mySecurePassword = PasswordUtils.generateSecurePassword(password, salt);

            //String codeString = bCryptPasswordEncoder.encode(password);

            RespBase<RespUser> userResponse = userGeneralRepositoy.getUserByMail(mail);

            if (userResponse != null && userResponse.getPayload() != null) {
                if (!MaestrosUtilidades.isEmpty(userResponse.getPayload().getUsername())) {
                    if (!MaestrosUtilidades.isEmpty(userResponse.getPayload().getMail())
                            || !MaestrosUtilidades.isEmpty(password)) {
                        if (!MaestrosUtilidades.isEmpty(userResponse.getPayload().getPassword())
                                || !MaestrosUtilidades.isEmpty(userResponse.getPayload().getSal_Password())) {

                            valida = PasswordUtils.verifyUserPassword(password, userResponse.getPayload().getApi_Password(),
                                    userResponse.getPayload().getSal_Password());
                            if (valida == false) {
                                throw new UsernameNotFoundException("Users not found with username: " + username);
                            }
                        } else {
                            throw new UsernameNotFoundException("Users not found with username: " + username);

                        }
                    } else {
                        throw new UsernameNotFoundException("Users not found with username: " + username);
                    }
                } else {
                    throw new UsernameNotFoundException("Users not found with username: " + username);
                }
            } else {
                throw new UsernameNotFoundException("Users not found with username: " + username);
            }
            //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mail, password));


        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}