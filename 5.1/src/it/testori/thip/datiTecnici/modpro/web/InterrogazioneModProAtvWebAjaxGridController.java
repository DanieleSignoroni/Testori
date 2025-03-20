package it.testori.thip.datiTecnici.modpro.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thera.thermfw.web.WebAjaxGridControllerDependent;
import com.thera.thermfw.web.ajax.XMLElement;

public class InterrogazioneModProAtvWebAjaxGridController extends WebAjaxGridControllerDependent {

	@Override
	public XMLElement processRequest(XMLElement xmlReq, HttpServletRequest req, HttpServletResponse resp) {
		XMLElement event = xmlReq.getFirstChild(EVENT_ELEMENT);
		String eventId = event.getAttribute(EVENTID_ATTRIBUTE);
		if (eventId.equals(DELETE_ROWS)) {
			return processDeleteRows(xmlReq, req, resp);
		}
		return super.processRequest(xmlReq, req, resp);
	}
}