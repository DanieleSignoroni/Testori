package it.testori.thip.produzione.rifornLinea;

import java.math.BigDecimal;

import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Copyable;

import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.produzione.rifornLinea.PianoRifornimento;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 31/10/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    31/10/2025  DSSOF3   Prima stesura
 */

public class YPianoRifornimento extends PianoRifornimento {

	protected String iVarchar01;

	protected String iVarchar02;

	protected java.sql.Date iDate01;

	protected java.sql.Date iDate02;

	protected BigDecimal iDecimal01;

	protected BigDecimal iDecimal02;

	protected Integer iInteger01;

	protected Integer iInteger02;

	protected boolean iFlag01 = false;

	protected boolean iFlag02 = false;

	protected char iEnum1 = 'I';

	protected char iEnum2 = 'I';

	public YPianoRifornimento() {
		setFlag01(false);
		setFlag02(false);
		setEnum1('I');
		setEnum2('I');
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setVarchar01(String varchar01) {
		this.iVarchar01 = varchar01;
		setDirty();
	}

	public String getVarchar01() {
		return iVarchar01;
	}

	public void setVarchar02(String varchar02) {
		this.iVarchar02 = varchar02;
		setDirty();
	}

	public String getVarchar02() {
		return iVarchar02;
	}

	public void setDate01(java.sql.Date date01) {
		this.iDate01 = date01;
		setDirty();
	}

	public java.sql.Date getDate01() {
		return iDate01;
	}

	public void setDate02(java.sql.Date date02) {
		this.iDate02 = date02;
		setDirty();
	}

	public java.sql.Date getDate02() {
		return iDate02;
	}

	public void setDecimal01(BigDecimal decimal01) {
		this.iDecimal01 = decimal01;
		setDirty();
	}

	public BigDecimal getDecimal01() {
		return iDecimal01;
	}

	public void setDecimal02(BigDecimal decimal02) {
		this.iDecimal02 = decimal02;
		setDirty();
	}

	public BigDecimal getDecimal02() {
		return iDecimal02;
	}

	public void setInteger01(Integer integer01) {
		this.iInteger01 = integer01;
		setDirty();
	}

	public Integer getInteger01() {
		return iInteger01;
	}

	public void setInteger02(Integer integer02) {
		this.iInteger02 = integer02;
		setDirty();
	}

	public Integer getInteger02() {
		return iInteger02;
	}

	public void setFlag01(boolean flag01) {
		this.iFlag01 = flag01;
		setDirty();
	}

	public boolean getFlag01() {
		return iFlag01;
	}

	public void setFlag02(boolean flag02) {
		this.iFlag02 = flag02;
		setDirty();
	}

	public boolean getFlag02() {
		return iFlag02;
	}

	public void setEnum1(char enum1) {
		this.iEnum1 = enum1;
		setDirty();
	}

	public char getEnum1() {
		return iEnum1;
	}

	public void setEnum2(char enum2) {
		this.iEnum2 = enum2;
		setDirty();
	}

	public char getEnum2() {
		return iEnum2;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
	}

}