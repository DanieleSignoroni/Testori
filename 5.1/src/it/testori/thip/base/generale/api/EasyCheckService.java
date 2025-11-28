package it.testori.thip.base.generale.api;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONObject;

import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.rs.errors.ErrorUtils;

import it.testori.thip.easycheck.PezzaGreggiaField;
import it.testori.thip.easycheck.PezzaLavorata;

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

public class EasyCheckService {

	private static EasyCheckService instance;

	public static EasyCheckService getInstance() {
		if(instance == null) {
			instance = (EasyCheckService) Factory.createObject(EasyCheckService.class);
		}
		return instance;
	}

	public JSONObject riceviPezzaLavorata(JSONObject payload) {
		JSONObject response = new JSONObject();
		JSONObject result = new JSONObject();
		Status status = Status.OK;
		Collection<ErrorMessage> errors = new ArrayList<>();

		PezzaLavorata pezza = PezzaLavorata.fromJson(payload, errors);
		if (!errors.isEmpty()) {
			status = Status.BAD_REQUEST;
		} else {

		}

		result.put("errors", ErrorUtils.getInstance().toJSON(errors));
		response.put("response", result);
		response.put("status", status);
		return response;
	}

	public JSONObject ritornaDatiPezzaGreggia(JSONObject payload) {
		JSONObject response = new JSONObject();
		JSONObject result = new JSONObject();
		Status status = Status.OK;
		Collection<ErrorMessage> errors = new ArrayList<>();

		JSONObject reqSchema = payload.optJSONObject("request_schema");
		JSONArray fields = (reqSchema != null) ? reqSchema.optJSONArray("fields") : null;

		if (fields == null) {
			status = Status.BAD_REQUEST;
			errors.add(new ErrorMessage("BAS0000078","Array 'fields' non trovato nel nodo 'request_schema'"));
		}else {
			PezzaGreggiaField pezza = PezzaGreggiaField.fromPayload(payload, errors);
			if(errors.isEmpty()) {

			}else {
				status = Status.BAD_REQUEST;
			}
		}

		result.put("errors", ErrorUtils.getInstance().toJSON(errors));
		response.put("response", result);
		response.put("status", status);
		return response;
	}
}