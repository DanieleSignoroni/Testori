package it.testori.thip.easycheck;

import java.util.Collection;

import org.json.JSONObject;

import com.thera.thermfw.common.ErrorMessage;

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

public class PezzaGreggiaField {

	private String badgeCode;
	private Integer pieceNumber;
	private Integer subPieceNumber;
	private String cutCode;
	private String frameCode;
	private String itemCode;
	private String productionOrder;
	private String rawPieceFlag;
	private String clientCode;

	public String getBadgeCode() {
		return badgeCode;
	}

	public void setBadgeCode(String badgeCode) {
		this.badgeCode = badgeCode;
	}

	public Integer getPieceNumber() {
		return pieceNumber;
	}

	public void setPieceNumber(Integer pieceNumber) {
		this.pieceNumber = pieceNumber;
	}

	public Integer getSubPieceNumber() {
		return subPieceNumber;
	}

	public void setSubPieceNumber(Integer subPieceNumber) {
		this.subPieceNumber = subPieceNumber;
	}

	public String getCutCode() {
		return cutCode;
	}

	public void setCutCode(String cutCode) {
		this.cutCode = cutCode;
	}

	public String getFrameCode() {
		return frameCode;
	}

	public void setFrameCode(String frameCode) {
		this.frameCode = frameCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getProductionOrder() {
		return productionOrder;
	}

	public void setProductionOrder(String productionOrder) {
		this.productionOrder = productionOrder;
	}

	public String getRawPieceFlag() {
		return rawPieceFlag;
	}

	public void setRawPieceFlag(String rawPieceFlag) {
		this.rawPieceFlag = rawPieceFlag;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public static PezzaGreggiaField fromPayload(JSONObject payload,
			Collection<ErrorMessage> errors) {

		PezzaGreggiaField result = new PezzaGreggiaField();

		if (payload == null) {
			errors.add(new ErrorMessage("BAS0000078", "Payload nullo"));
			return result;
		}

		JSONObject req = payload.optJSONObject("request_schema");
		if (req == null) {
			errors.add(new ErrorMessage("BAS0000078",
					"Oggetto 'request_schema' non trovato nel payload"));
			return result;
		}

		String badgeCode = req.optString("badgeCode", null);
		if (badgeCode == null || badgeCode.isEmpty()) {
			errors.add(new ErrorMessage("BAS0000078",
					"Campo 'badgeCode' mancante o vuoto in request_schema"));
		} else {
			result.setBadgeCode(badgeCode);
		}

		Integer pieceNumber = getOptionalInteger(req, "pieceNumber", true, "BAS0000078", errors);
		result.setPieceNumber(pieceNumber);

		Integer subPieceNumber = getOptionalInteger(req, "subPieceNumber", true, "BAS0000078", errors);
		result.setSubPieceNumber(subPieceNumber);

		String cutCode = req.optString("cutCode", null);
		if (cutCode == null || cutCode.isEmpty()) {
			errors.add(new ErrorMessage("BAS0000078",
					"Campo 'cutCode' mancante o vuoto in request_schema"));
		} else {
			result.setCutCode(cutCode);
		}

		String frameCode = req.optString("frameCode", null);
		result.setFrameCode(frameCode);

		String itemCode = req.optString("itemCode", null);
		if (itemCode == null || itemCode.isEmpty()) {
			errors.add(new ErrorMessage("BAS0000078",
					"Campo 'itemCode' mancante o vuoto in request_schema"));
		} else {
			result.setItemCode(itemCode);
		}

		String productionOrder = req.optString("productionOrder", null);
		if (productionOrder == null || productionOrder.isEmpty()) {
			errors.add(new ErrorMessage("BAS0000078",
					"Campo 'productionOrder' mancante o vuoto in request_schema"));
		} else {
			result.setProductionOrder(productionOrder);
		}

		String rawPieceFlag = req.optString("rawPieceFlag", null);
		if (rawPieceFlag == null || rawPieceFlag.isEmpty()) {
			errors.add(new ErrorMessage("BAS0000078",
					"Campo 'rawPieceFlag' mancante o vuoto in request_schema"));
		} else {
			result.setRawPieceFlag(rawPieceFlag);
		}

		String clientCode = req.optString("clientCode", null);
		result.setClientCode(clientCode);

		return result;
	}

	private static Integer getOptionalInteger(JSONObject obj,
			String fieldName,
			boolean required,
			String errorCode,
			Collection<ErrorMessage> errors) {
		if (!obj.has(fieldName) || obj.isNull(fieldName)) {
			if (required) {
				errors.add(new ErrorMessage(errorCode,
						"Campo '" + fieldName + "' mancante o nullo in request_schema"));
			}
			return null;
		}

		try {
			return obj.getInt(fieldName);
		} catch (Exception e) {
			errors.add(new ErrorMessage(errorCode,
					"Campo '" + fieldName + "' non è un intero valido in request_schema"));
			return null;
		}
	}
}