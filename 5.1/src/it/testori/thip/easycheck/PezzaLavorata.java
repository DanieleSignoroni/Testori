package it.testori.thip.easycheck;

import java.math.BigDecimal;
import java.util.Collection;

import org.json.JSONObject;

import com.thera.thermfw.common.ErrorMessage;

public class PezzaLavorata {

	private String headerID;
	private BigDecimal netQuantityMeters;
	private BigDecimal minWidth;
	private BigDecimal maxWidth;
	private BigDecimal pieceWeight;
	private String finalQuality;
	private String widthQuality;
	private String productionOrder;
	private String operatorCode;
	private BigDecimal allowance;
	private String pieceNote;
	private String rawPieceCode;
	private String locationCode;

	public String getHeaderID() {
		return headerID;
	}

	public void setHeaderID(String headerID) {
		this.headerID = headerID;
	}

	public BigDecimal getNetQuantityMeters() {
		return netQuantityMeters;
	}

	public void setNetQuantityMeters(BigDecimal netQuantityMeters) {
		this.netQuantityMeters = netQuantityMeters;
	}

	public BigDecimal getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(BigDecimal minWidth) {
		this.minWidth = minWidth;
	}

	public BigDecimal getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(BigDecimal maxWidth) {
		this.maxWidth = maxWidth;
	}

	public BigDecimal getPieceWeight() {
		return pieceWeight;
	}

	public void setPieceWeight(BigDecimal pieceWeight) {
		this.pieceWeight = pieceWeight;
	}

	public String getFinalQuality() {
		return finalQuality;
	}

	public void setFinalQuality(String finalQuality) {
		this.finalQuality = finalQuality;
	}

	public String getWidthQuality() {
		return widthQuality;
	}

	public void setWidthQuality(String widthQuality) {
		this.widthQuality = widthQuality;
	}

	public String getProductionOrder() {
		return productionOrder;
	}

	public void setProductionOrder(String productionOrder) {
		this.productionOrder = productionOrder;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public BigDecimal getAllowance() {
		return allowance;
	}

	public void setAllowance(BigDecimal allowance) {
		this.allowance = allowance;
	}

	public String getPieceNote() {
		return pieceNote;
	}

	public void setPieceNote(String pieceNote) {
		this.pieceNote = pieceNote;
	}

	public String getRawPieceCode() {
		return rawPieceCode;
	}

	public void setRawPieceCode(String rawPieceCode) {
		this.rawPieceCode = rawPieceCode;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public static PezzaLavorata fromJson(JSONObject json,
			Collection<ErrorMessage> errors) {

		PezzaLavorata result = new PezzaLavorata();

		if (json == null) {
			errors.add(new ErrorMessage("BAS0000078", "JSON nullo per PezzaLavorata"));
			return result;
		}

		result.setHeaderID(getRequiredString(json, "headerID", "BAS0000078", errors));
		result.setFinalQuality(getRequiredString(json, "finalQuality", "BAS0000078", errors));
		result.setProductionOrder(getRequiredString(json, "productionOrder", "BAS0000078", errors));
		result.setOperatorCode(getRequiredString(json, "operatorCode", "BAS0000078", errors));
		result.setRawPieceCode(getRequiredString(json, "rawPieceCode", "BAS0000078", errors));
		result.setLocationCode(getRequiredString(json, "locationCode", "BAS0000078", errors));

		result.setWidthQuality(json.optString("widthQuality", null));
		result.setPieceNote(json.optString("pieceNote", null));

		result.setNetQuantityMeters(getBigDecimal(json, "netQuantityMeters", true, "BAS0000078", errors));
		result.setMinWidth(getBigDecimal(json, "minWidth", true, "BAS0000078", errors));
		result.setMaxWidth(getBigDecimal(json, "maxWidth", true, "BAS0000078", errors));
		result.setPieceWeight(getBigDecimal(json, "pieceWeight", true, "BAS0000078", errors));

		result.setAllowance(getBigDecimal(json, "allowance", false, "BAS0000078", errors));

		return result;
	}

	private static String getRequiredString(JSONObject obj,
			String field,
			String errorCode,
			Collection<ErrorMessage> errors) {
		String value = obj.optString(field, null);
		if (value == null || value.isEmpty()) {
			errors.add(new ErrorMessage(
					errorCode,
					"Campo stringa obbligatorio '" + field + "' mancante o vuoto"
					));
		}
		return value;
	}

	private static BigDecimal getBigDecimal(JSONObject obj,
			String field,
			boolean required,
			String errorCode,
			Collection<ErrorMessage> errors) {
		if (!obj.has(field) || obj.isNull(field)) {
			if (required) {
				errors.add(new ErrorMessage(
						errorCode,
						"Campo numerico obbligatorio '" + field + "' mancante o nullo"
						));
			}
			return null;
		}

		Object raw = obj.opt(field);
		if (raw == null || raw == JSONObject.NULL) {
			if (required) {
				errors.add(new ErrorMessage(
						errorCode,
						"Campo numerico obbligatorio '" + field + "' nullo"
						));
			}
			return null;
		}

		try {
			// Può essere Number o String, li tratto entrambi
			return new BigDecimal(raw.toString());
		} catch (NumberFormatException ex) {
			errors.add(new ErrorMessage(
					errorCode,
					"Campo numerico '" + field + "' non è un BigDecimal valido: " + raw
					));
			return null;
		}
	}
}
