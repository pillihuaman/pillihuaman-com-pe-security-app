package pillihuaman.com.segurity.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pillihuaman.com.segurity.Service.ControlService;
import pillihuaman.com.segurity.help.Constants;
import pillihuaman.com.segurity.lib.commons.MyJsonWebToken;
import pillihuaman.com.segurity.lib.request.ReqBase;
import pillihuaman.com.segurity.lib.request.ReqControl;
import pillihuaman.com.segurity.lib.response.RespBase;
import pillihuaman.com.segurity.lib.response.RespControl;


@RestController
@Tag(name = "Control", description = "")


public class ControlsController {

	@Autowired
	private ControlService controlService;
	@Autowired
	private HttpServletRequest httpServletRequest;
	@Operation(summary = "Save control by system and user", description = "Save control by system and user", tags = { "" }, security = {
			@SecurityRequirement(name = Constants.BEARER_JWT) })
	@ApiResponses(value = {
			@ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
			@ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}),
			@ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}) })
	@PostMapping(path = { Constants.BASE_ENDPOINT + "/control/saveControl" }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<RespControl>> saveControl(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqControl> request , HttpServletRequest jwts){
		MyJsonWebToken jwtss = (MyJsonWebToken) httpServletRequest.getAttribute("jwt");
		MyJsonWebToken jwt = (MyJsonWebToken) jwts.getAttribute("jwt");
		RespBase<RespControl> response = controlService.saveControl( jwt,null);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Get list control by system and user", description = "Get list control by system and user", tags = { "" }, security = {
			@SecurityRequirement(name = Constants.BEARER_JWT) })




	
	@ApiResponses(value = {
			@ApiResponse(responseCode = Constants.SERVER_200, description = Constants.OPERACION_EXITOSA),
			@ApiResponse(responseCode = Constants.SERVER_400, description = Constants.ERROR_VALIDACION, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}),
			@ApiResponse(responseCode = Constants.SERVER_500, description = Constants.ERROR_INTERNO, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RespBase.class))}) })
	@GetMapping(path = { Constants.BASE_ENDPOINT + "/control/listControl" }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RespBase<RespControl>> listControl(
			@PathVariable String access,
			@Valid @RequestBody ReqBase<ReqControl> request ,HttpServletRequest jwts){
		MyJsonWebToken jwt = (MyJsonWebToken) jwts.getAttribute("jwt");
		RespBase<RespControl> response = controlService.listControl( jwt,null);
		return ResponseEntity.ok(response);
	}





	    
}