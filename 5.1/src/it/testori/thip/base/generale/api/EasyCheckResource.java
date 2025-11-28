package it.testori.thip.base.generale.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import org.json.JSONObject;

import com.thera.thermfw.rs.BaseResource;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 28/11/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    28/11/2025  DSSOF3   Prima stesura
 */

@Path("/easyCheck")
public class EasyCheckResource extends BaseResource {

	private static EasyCheckService service = EasyCheckService.getInstance();

	@POST
	@Path("/pezzaGreggia")
	public Response ritornaDatiPezzaGreggia(String payload) {
		JSONObject response = service.ritornaDatiPezzaGreggia(new JSONObject(payload));
		return buildResponse((StatusType) response.get("status"),response.get("response"));
	}

	@POST
	@Path("/pezzaLavorata")
	public Response riceviPezzaLavorata(String payload) {
		JSONObject response = service.riceviPezzaLavorata(new JSONObject(payload));
		return buildResponse((StatusType) response.get("status"),response.get("response"));
	}
}