package it.testori.thip.base.articolo;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Copyable;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.Proxy;

import it.thera.thip.base.articolo.Articolo;
//import it.thera.thip.base.articolo.ClasseC; 72099
import it.thera.thip.base.azienda.Azienda;

/**
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 17/07/2025
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72054    17/07/2025  DSSOF3   Prima stesura
 * 72099	28/08/2025	DSSOF3	 Cambiare classe servizio da bool proxy, cambiare master da classe c a proxy
 * 72144	28/08/2025	GLSOF3	 Cambiato il tipo, da Integer a BigDecimal, all'attributo iAreaPermeabilita
 * 72320	27/01/2026	DSSOF3	 Nuovo attributo
 */

public class YArticolo extends Articolo {

	protected Integer iDiametro;

	protected Integer iYLunghezza;

	protected BigDecimal iForoPiastra;

	protected BigDecimal iFiliTrama;

	protected BigDecimal iFiliOrdito;

	protected Integer iAreaPeso;

	//protected Integer iAreaPermeabilita; //72144
	protected BigDecimal iAreaPermeabilita; //72144

	protected boolean iLifetess = false;

	protected boolean iLeftover = false;

	protected String iCliente;

	protected BigDecimal iQtypref;

	protected String iIdCodLeftFioccoSecondario;

	protected boolean iPharma = false;

	protected boolean iCleanRoom = false;

	//72099 Inizio, remmare per cambiare con proxy
	//protected boolean iClasseServizio = false;
	protected Proxy iClasseServizio = new Proxy(it.testori.thip.base.articolo.YClasseServizio.class);
	//72099 Fine

	protected String iLeft1;

	protected BigDecimal iQtyGreggio;

	protected Proxy iConfezioneimboccatura = new Proxy(it.testori.thip.base.articolo.TipologiaConfezionamento.class);

	protected Proxy iGiunzionelongtubolare = new Proxy(it.testori.thip.base.articolo.GiunzioneLongTubolare.class);

	protected Proxy iConfezionefondello = new Proxy(it.testori.thip.base.articolo.TipologiaConfezionamento.class);

	protected Proxy iFibra = new Proxy(it.testori.thip.base.articolo.Fibre.class);

	protected Proxy iComposizione = new Proxy(it.testori.thip.base.articolo.Composizione.class);

	protected Proxy iIntreccio = new Proxy(it.testori.thip.base.articolo.Intreccio.class);

	protected Proxy iGruppolifetess = new Proxy(it.testori.thip.base.articolo.GruppiLifetess.class);

	protected Proxy iLeft2 = new Proxy(it.testori.thip.base.articolo.RaggrArticoloLeft2.class);

	protected Proxy iLeft2preferenziale = new Proxy(it.testori.thip.base.articolo.RaggrArticoloLeft2.class);

	protected Proxy iClassefittizia = new Proxy(it.testori.thip.base.articolo.ClasseFittizia.class);

	protected Proxy iLeft2fioccoprincipale = new Proxy(it.testori.thip.base.articolo.RaggrArticoloLeft2.class);

	protected Proxy iLeft2fioccosecondario = new Proxy(it.testori.thip.base.articolo.RaggrArticoloLeft2.class);

	protected Proxy iGreggio = new Proxy(it.thera.thip.base.articolo.Articolo.class);

	//72099 Inizio, remmare per cambiare Proxy da Classe C a classe pers
	//protected Proxy iMaster = new Proxy(it.thera.thip.base.articolo.ClasseC.class);
	protected Proxy iMaster = new Proxy(it.testori.thip.base.articolo.YCodiceMaster.class);
	//72099 Fine

	protected Proxy iTipoclean = new Proxy(it.testori.thip.base.articolo.TipoClean.class);

	protected Proxy iFeltrotessutopref = new Proxy(it.thera.thip.base.articolo.Articolo.class);

	public YArticolo() {
		super();
		setLifetess(false);
		setLeftover(false);
		setPharma(false);
		setCleanRoom(false);
		//setClasseServizio(false); 72099
		setIdAzienda(Azienda.getAziendaCorrente());
		setFloorStockContoLavoro(false); //72320
	}

	public void setDiametro(Integer diametro) {
		this.iDiametro = diametro;
		setDirty();
	}

	public Integer getDiametro() {
		return iDiametro;
	}

	public void setYLunghezza(Integer lunghezza) {
		this.iYLunghezza = lunghezza;
		setDirty();
	}

	public Integer getYLunghezza() {
		return iYLunghezza;
	}

	public void setForoPiastra(BigDecimal foroPiastra) {
		this.iForoPiastra = foroPiastra;
		setDirty();
	}

	public BigDecimal getForoPiastra() {
		return iForoPiastra;
	}

	public void setFiliTrama(BigDecimal filiTrama) {
		this.iFiliTrama = filiTrama;
		setDirty();
	}

	public BigDecimal getFiliTrama() {
		return iFiliTrama;
	}

	public void setFiliOrdito(BigDecimal filiOrdito) {
		this.iFiliOrdito = filiOrdito;
		setDirty();
	}

	public BigDecimal getFiliOrdito() {
		return iFiliOrdito;
	}

	public void setAreaPeso(Integer areaPeso) {
		this.iAreaPeso = areaPeso;
		setDirty();
	}

	public Integer getAreaPeso() {
		return iAreaPeso;
	}

	//<72144 inizio
	//	public void setAreaPermeabilita(Integer areaPermeabilita) {
	//		this.iAreaPermeabilita = areaPermeabilita;
	//		setDirty();
	//	}
	//
	//	public Integer getAreaPermeabilita() {
	//		return iAreaPermeabilita;
	//	}

	public void setAreaPermeabilita(BigDecimal areaPermeabilita) {
		this.iAreaPermeabilita = areaPermeabilita;
		setDirty();
	}

	public BigDecimal getAreaPermeabilita() {
		return iAreaPermeabilita;
	}
	//72144 fine>

	public void setLifetess(boolean lifetess) {
		this.iLifetess = lifetess;
		setDirty();
	}

	public boolean getLifetess() {
		return iLifetess;
	}

	public void setLeftover(boolean leftover) {
		this.iLeftover = leftover;
		setDirty();
	}

	public boolean getLeftover() {
		return iLeftover;
	}

	public void setCliente(String cliente) {
		this.iCliente = cliente;
		setDirty();
	}

	public String getCliente() {
		return iCliente;
	}

	public void setQtypref(BigDecimal qtypref) {
		this.iQtypref = qtypref;
		setDirty();
	}

	public BigDecimal getQtypref() {
		return iQtypref;
	}

	public void setIdCodLeftFioccoSecondario(String idCodLeftFioccoSecondario) {
		this.iIdCodLeftFioccoSecondario = idCodLeftFioccoSecondario;
		setDirty();
	}

	public String getIdCodLeftFioccoSecondario() {
		return iIdCodLeftFioccoSecondario;
	}

	public void setPharma(boolean pharma) {
		this.iPharma = pharma;
		setDirty();
	}

	public boolean getPharma() {
		return iPharma;
	}

	public void setCleanRoom(boolean cleanRoom) {
		this.iCleanRoom = cleanRoom;
		setDirty();
	}

	public boolean getCleanRoom() {
		return iCleanRoom;
	}

	//72099 Inizio, remmare per cambiare con proxy
	/*public void setClasseServizio(boolean classeServizio) {
		this.iClasseServizio = classeServizio;
		setDirty();
	}

	public boolean getClasseServizio() {
		return iClasseServizio;
	}*/
	public void setClasseServizio(YClasseServizio YClasseServizio) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (YClasseServizio != null) {
			idAzienda = KeyHelper.getTokenObjectKey(YClasseServizio.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iClasseServizio.setObject(YClasseServizio);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public YClasseServizio getClasseServizio() {
		return (YClasseServizio) iClasseServizio.getObject();
	}

	public void setClasseServizioKey(String key) {
		String oldObjectKey = getKey();
		iClasseServizio.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getClasseServizioKey() {
		return iClasseServizio.getKey();
	}

	public void setIdClasseServizio(String idClasseServizio) {
		String key = iClasseServizio.getKey();
		iClasseServizio.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idClasseServizio));
		setDirty();
	}

	public String getIdClasseServizio() {
		String key = iClasseServizio.getKey();
		String objidClasseServizio = KeyHelper.getTokenObjectKey(key, 2);
		return objidClasseServizio;
	}
	//72099 Fine

	public void setLeft1(String left1) {
		this.iLeft1 = left1;
		setDirty();
	}

	public String getLeft1() {
		return iLeft1;
	}

	public void setQtyGreggio(BigDecimal qtyGreggio) {
		this.iQtyGreggio = qtyGreggio;
		setDirty();
	}

	public BigDecimal getQtyGreggio() {
		return iQtyGreggio;
	}

	public void setConfezioneimboccatura(TipologiaConfezionamento confezioneimboccatura) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (confezioneimboccatura != null) {
			idAzienda = KeyHelper.getTokenObjectKey(confezioneimboccatura.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iConfezioneimboccatura.setObject(confezioneimboccatura);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public TipologiaConfezionamento getConfezioneimboccatura() {
		return (TipologiaConfezionamento) iConfezioneimboccatura.getObject();
	}

	public void setConfezioneimboccaturaKey(String key) {
		String oldObjectKey = getKey();
		iConfezioneimboccatura.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getConfezioneimboccaturaKey() {
		return iConfezioneimboccatura.getKey();
	}

	public void setIdCodiceConfImboccatura(String idCodiceConfImboccatura) {
		String key = iConfezioneimboccatura.getKey();
		iConfezioneimboccatura.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCodiceConfImboccatura));
		setDirty();
	}

	public String getIdCodiceConfImboccatura() {
		String key = iConfezioneimboccatura.getKey();
		String objIdCodiceConfImboccatura = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCodiceConfImboccatura;
	}

	public void setGiunzionelongtubolare(GiunzioneLongTubolare giunzionelongtubolare) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (giunzionelongtubolare != null) {
			idAzienda = KeyHelper.getTokenObjectKey(giunzionelongtubolare.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iGiunzionelongtubolare.setObject(giunzionelongtubolare);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public GiunzioneLongTubolare getGiunzionelongtubolare() {
		return (GiunzioneLongTubolare) iGiunzionelongtubolare.getObject();
	}

	public void setGiunzionelongtubolareKey(String key) {
		String oldObjectKey = getKey();
		iGiunzionelongtubolare.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getGiunzionelongtubolareKey() {
		return iGiunzionelongtubolare.getKey();
	}

	public void setIdCodiceGiunzLongTubolare(String idCodiceGiunzLongTubolare) {
		String key = iGiunzionelongtubolare.getKey();
		iGiunzionelongtubolare.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCodiceGiunzLongTubolare));
		setDirty();
	}

	public String getIdCodiceGiunzLongTubolare() {
		String key = iGiunzionelongtubolare.getKey();
		String objIdCodiceGiunzLongTubolare = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCodiceGiunzLongTubolare;
	}

	public void setConfezionefondello(TipologiaConfezionamento confezionefondello) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (confezionefondello != null) {
			idAzienda = KeyHelper.getTokenObjectKey(confezionefondello.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iConfezionefondello.setObject(confezionefondello);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public TipologiaConfezionamento getConfezionefondello() {
		return (TipologiaConfezionamento) iConfezionefondello.getObject();
	}

	public void setConfezionefondelloKey(String key) {
		String oldObjectKey = getKey();
		iConfezionefondello.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getConfezionefondelloKey() {
		return iConfezionefondello.getKey();
	}

	public void setIdCodiceConfFondello(String idCodiceConfFondello) {
		String key = iConfezionefondello.getKey();
		iConfezionefondello.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCodiceConfFondello));
		setDirty();
	}

	public String getIdCodiceConfFondello() {
		String key = iConfezionefondello.getKey();
		String objIdCodiceConfFondello = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCodiceConfFondello;
	}

	public void setFibra(Fibre fibra) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (fibra != null) {
			idAzienda = KeyHelper.getTokenObjectKey(fibra.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iFibra.setObject(fibra);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Fibre getFibra() {
		return (Fibre) iFibra.getObject();
	}

	public void setFibraKey(String key) {
		String oldObjectKey = getKey();
		iFibra.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getFibraKey() {
		return iFibra.getKey();
	}

	public void setIdCodiceFibra(String idCodiceFibra) {
		String key = iFibra.getKey();
		iFibra.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCodiceFibra));
		setDirty();
	}

	public String getIdCodiceFibra() {
		String key = iFibra.getKey();
		String objIdCodiceFibra = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCodiceFibra;
	}

	public void setComposizione(Composizione composizione) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (composizione != null) {
			idAzienda = KeyHelper.getTokenObjectKey(composizione.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iComposizione.setObject(composizione);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Composizione getComposizione() {
		return (Composizione) iComposizione.getObject();
	}

	public void setComposizioneKey(String key) {
		String oldObjectKey = getKey();
		iComposizione.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getComposizioneKey() {
		return iComposizione.getKey();
	}

	public void setIdCodiceComposizione(String idCodiceComposizione) {
		String key = iComposizione.getKey();
		iComposizione.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCodiceComposizione));
		setDirty();
	}

	public String getIdCodiceComposizione() {
		String key = iComposizione.getKey();
		String objIdCodiceComposizione = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCodiceComposizione;
	}

	public void setIntreccio(Intreccio intreccio) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (intreccio != null) {
			idAzienda = KeyHelper.getTokenObjectKey(intreccio.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iIntreccio.setObject(intreccio);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Intreccio getIntreccio() {
		return (Intreccio) iIntreccio.getObject();
	}

	public void setIntreccioKey(String key) {
		String oldObjectKey = getKey();
		iIntreccio.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getIntreccioKey() {
		return iIntreccio.getKey();
	}

	public void setIdCodiceIntreccio(String idCodiceIntreccio) {
		String key = iIntreccio.getKey();
		iIntreccio.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCodiceIntreccio));
		setDirty();
	}

	public String getIdCodiceIntreccio() {
		String key = iIntreccio.getKey();
		String objIdCodiceIntreccio = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCodiceIntreccio;
	}

	public void setGruppolifetess(GruppiLifetess gruppolifetess) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (gruppolifetess != null) {
			idAzienda = KeyHelper.getTokenObjectKey(gruppolifetess.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iGruppolifetess.setObject(gruppolifetess);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public GruppiLifetess getGruppolifetess() {
		return (GruppiLifetess) iGruppolifetess.getObject();
	}

	public void setGruppolifetessKey(String key) {
		String oldObjectKey = getKey();
		iGruppolifetess.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getGruppolifetessKey() {
		return iGruppolifetess.getKey();
	}

	public void setIdCodiceGruppoLifetess(String idCodiceGruppoLifetess) {
		String key = iGruppolifetess.getKey();
		iGruppolifetess.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCodiceGruppoLifetess));
		setDirty();
	}

	public String getIdCodiceGruppoLifetess() {
		String key = iGruppolifetess.getKey();
		String objIdCodiceGruppoLifetess = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCodiceGruppoLifetess;
	}

	public void setLeft2(RaggrArticoloLeft2 left2) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (left2 != null) {
			idAzienda = KeyHelper.getTokenObjectKey(left2.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iLeft2.setObject(left2);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public RaggrArticoloLeft2 getLeft2() {
		return (RaggrArticoloLeft2) iLeft2.getObject();
	}

	public void setLeft2Key(String key) {
		String oldObjectKey = getKey();
		iLeft2.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getLeft2Key() {
		return iLeft2.getKey();
	}

	public void setIdCodiceLeft2(String idCodiceLeft2) {
		String key = iLeft2.getKey();
		iLeft2.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCodiceLeft2));
		setDirty();
	}

	public String getIdCodiceLeft2() {
		String key = iLeft2.getKey();
		String objIdCodiceLeft2 = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCodiceLeft2;
	}

	public void setLeft2preferenziale(RaggrArticoloLeft2 left2preferenziale) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (left2preferenziale != null) {
			idAzienda = KeyHelper.getTokenObjectKey(left2preferenziale.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		String idCodLeft2Preferenziale = getIdCodLeft2Preferenziale();
		if (left2preferenziale != null) {
			idCodLeft2Preferenziale = KeyHelper.getTokenObjectKey(left2preferenziale.getKey(), 2);
		}
		setIdCodLeft2PreferenzialeInternal(idCodLeft2Preferenziale);
		this.iLeft2preferenziale.setObject(left2preferenziale);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public RaggrArticoloLeft2 getLeft2preferenziale() {
		return (RaggrArticoloLeft2) iLeft2preferenziale.getObject();
	}

	public void setLeft2preferenzialeKey(String key) {
		String oldObjectKey = getKey();
		iLeft2preferenziale.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		String idCodLeft2Preferenziale = KeyHelper.getTokenObjectKey(key, 2);
		setIdCodLeft2PreferenzialeInternal(idCodLeft2Preferenziale);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getLeft2preferenzialeKey() {
		return iLeft2preferenziale.getKey();
	}

	public void setClassefittizia(ClasseFittizia classefittizia) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (classefittizia != null) {
			idAzienda = KeyHelper.getTokenObjectKey(classefittizia.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iClassefittizia.setObject(classefittizia);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public ClasseFittizia getClassefittizia() {
		return (ClasseFittizia) iClassefittizia.getObject();
	}

	public void setClassefittiziaKey(String key) {
		String oldObjectKey = getKey();
		iClassefittizia.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getClassefittiziaKey() {
		return iClassefittizia.getKey();
	}

	public void setIdCodClasseFittizia(String idCodClasseFittizia) {
		String key = iClassefittizia.getKey();
		iClassefittizia.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCodClasseFittizia));
		setDirty();
	}

	public String getIdCodClasseFittizia() {
		String key = iClassefittizia.getKey();
		String objIdCodClasseFittizia = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCodClasseFittizia;
	}

	public void setLeft2fioccoprincipale(RaggrArticoloLeft2 left2fioccoprincipale) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (left2fioccoprincipale != null) {
			idAzienda = KeyHelper.getTokenObjectKey(left2fioccoprincipale.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		String idCodLeft2Preferenziale = getIdCodLeft2Preferenziale();
		if (left2fioccoprincipale != null) {
			idCodLeft2Preferenziale = KeyHelper.getTokenObjectKey(left2fioccoprincipale.getKey(), 2);
		}
		setIdCodLeft2PreferenzialeInternal(idCodLeft2Preferenziale);
		this.iLeft2fioccoprincipale.setObject(left2fioccoprincipale);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public RaggrArticoloLeft2 getLeft2fioccoprincipale() {
		return (RaggrArticoloLeft2) iLeft2fioccoprincipale.getObject();
	}

	public void setLeft2fioccoprincipaleKey(String key) {
		String oldObjectKey = getKey();
		iLeft2fioccoprincipale.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		String idCodLeft2Preferenziale = KeyHelper.getTokenObjectKey(key, 2);
		setIdCodLeft2PreferenzialeInternal(idCodLeft2Preferenziale);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getLeft2fioccoprincipaleKey() {
		return iLeft2fioccoprincipale.getKey();
	}

	public void setIdCodLeft2Preferenziale(String idCodLeft2Preferenziale) {
		setIdCodLeft2PreferenzialeInternal(idCodLeft2Preferenziale);
		setDirty();
	}

	public String getIdCodLeft2Preferenziale() {
		String key = iLeft2preferenziale.getKey();
		String objIdCodLeft2Preferenziale = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCodLeft2Preferenziale;
	}

	public void setLeft2fioccosecondario(RaggrArticoloLeft2 left2fioccosecondario) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (left2fioccosecondario != null) {
			idAzienda = KeyHelper.getTokenObjectKey(left2fioccosecondario.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iLeft2fioccosecondario.setObject(left2fioccosecondario);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public RaggrArticoloLeft2 getLeft2fioccosecondario() {
		return (RaggrArticoloLeft2) iLeft2fioccosecondario.getObject();
	}

	public void setLeft2fioccosecondarioKey(String key) {
		String oldObjectKey = getKey();
		iLeft2fioccosecondario.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getLeft2fioccosecondarioKey() {
		return iLeft2fioccosecondario.getKey();
	}

	public void setIdCodLeftFioccoPrincipale(String idCodLeftFioccoPrincipale) {
		String key = iLeft2fioccosecondario.getKey();
		iLeft2fioccosecondario.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCodLeftFioccoPrincipale));
		setDirty();
	}

	public String getIdCodLeftFioccoPrincipale() {
		String key = iLeft2fioccosecondario.getKey();
		String objIdCodLeftFioccoPrincipale = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCodLeftFioccoPrincipale;
	}

	public void setGreggio(Articolo greggio) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (greggio != null) {
			idAzienda = KeyHelper.getTokenObjectKey(greggio.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iGreggio.setObject(greggio);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Articolo getGreggio() {
		return (Articolo) iGreggio.getObject();
	}

	public void setGreggioKey(String key) {
		String oldObjectKey = getKey();
		iGreggio.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getGreggioKey() {
		return iGreggio.getKey();
	}

	public void setIdCodiceGreggio(String idCodiceGreggio) {
		String key = iGreggio.getKey();
		iGreggio.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCodiceGreggio));
		setDirty();
	}

	public String getIdCodiceGreggio() {
		String key = iGreggio.getKey();
		String objIdCodiceGreggio = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCodiceGreggio;
	}

	//72099 Inizio, remmare per cambiare Proxy da Classe C a classe pers
	/*public void setMaster(ClasseC master) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (master != null) {
			idAzienda = KeyHelper.getTokenObjectKey(master.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iMaster.setObject(master);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public ClasseC getMaster() {
		return (ClasseC) iMaster.getObject();
	}*/
	//72099 Fine

	public void setMaster(YCodiceMaster master) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (master != null) {
			idAzienda = KeyHelper.getTokenObjectKey(master.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iMaster.setObject(master);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public YCodiceMaster getMaster() {
		return (YCodiceMaster) iMaster.getObject();
	}

	public void setMasterKey(String key) {
		String oldObjectKey = getKey();
		iMaster.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getMasterKey() {
		return iMaster.getKey();
	}

	public void setIdCodiceMaster(String idCodiceMaster) {
		String key = iMaster.getKey();
		iMaster.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCodiceMaster));
		setDirty();
	}

	public String getIdCodiceMaster() {
		String key = iMaster.getKey();
		String objIdCodiceMaster = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCodiceMaster;
	}

	public void setTipoclean(TipoClean tipoclean) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (tipoclean != null) {
			idAzienda = KeyHelper.getTokenObjectKey(tipoclean.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iTipoclean.setObject(tipoclean);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public TipoClean getTipoclean() {
		return (TipoClean) iTipoclean.getObject();
	}

	public void setTipocleanKey(String key) {
		String oldObjectKey = getKey();
		iTipoclean.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getTipocleanKey() {
		return iTipoclean.getKey();
	}

	public void setIdCodiceTipoClean(String idCodiceTipoClean) {
		String key = iTipoclean.getKey();
		iTipoclean.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idCodiceTipoClean));
		setDirty();
	}

	public String getIdCodiceTipoClean() {
		String key = iTipoclean.getKey();
		String objIdCodiceTipoClean = KeyHelper.getTokenObjectKey(key, 2);
		return objIdCodiceTipoClean;
	}

	public void setFeltrotessutopref(Articolo feltrotessutopref) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (feltrotessutopref != null) {
			idAzienda = KeyHelper.getTokenObjectKey(feltrotessutopref.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iFeltrotessutopref.setObject(feltrotessutopref);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public Articolo getFeltrotessutopref() {
		return (Articolo) iFeltrotessutopref.getObject();
	}

	public void setFeltrotessutoprefKey(String key) {
		String oldObjectKey = getKey();
		iFeltrotessutopref.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
		}
	}

	public String getFeltrotessutoprefKey() {
		return iFeltrotessutopref.getKey();
	}

	public void setIdAzienda(String idAzienda) {
		super.setIdAzienda(idAzienda);
		setIdAziendaInternal(idAzienda);

	}

	public void setIdFeltroTessPreferenziale(String idFeltroTessPreferenziale) {
		String key = iFeltrotessutopref.getKey();
		iFeltrotessutopref.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idFeltroTessPreferenziale));
		setDirty();
	}

	public String getIdFeltroTessPreferenziale() {
		String key = iFeltrotessutopref.getKey();
		String objIdFeltroTessPreferenziale = KeyHelper.getTokenObjectKey(key, 2);
		return objIdFeltroTessPreferenziale;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		YArticoloBase yArticoloBase = (YArticoloBase) obj;
		iConfezioneimboccatura.setEqual(yArticoloBase.iConfezioneimboccatura);
		iGiunzionelongtubolare.setEqual(yArticoloBase.iGiunzionelongtubolare);
		iConfezionefondello.setEqual(yArticoloBase.iConfezionefondello);
		iFibra.setEqual(yArticoloBase.iFibra);
		iComposizione.setEqual(yArticoloBase.iComposizione);
		iIntreccio.setEqual(yArticoloBase.iIntreccio);
		iGruppolifetess.setEqual(yArticoloBase.iGruppolifetess);
		iLeft2.setEqual(yArticoloBase.iLeft2);
		iLeft2preferenziale.setEqual(yArticoloBase.iLeft2preferenziale);
		iClassefittizia.setEqual(yArticoloBase.iClassefittizia);
		iLeft2fioccoprincipale.setEqual(yArticoloBase.iLeft2fioccoprincipale);
		iLeft2fioccosecondario.setEqual(yArticoloBase.iLeft2fioccosecondario);
		iGreggio.setEqual(yArticoloBase.iGreggio);
		iMaster.setEqual(yArticoloBase.iMaster);
		iTipoclean.setEqual(yArticoloBase.iTipoclean);
		iFeltrotessutopref.setEqual(yArticoloBase.iFeltrotessutopref);
	}

	protected void setIdAziendaInternal(String idAzienda) {
		if (iConfezioneimboccatura != null) {
			String key1 = iConfezioneimboccatura.getKey();
			iConfezioneimboccatura.setKey(KeyHelper.replaceTokenObjectKey(key1, 1, idAzienda));
		}
		if (iGiunzionelongtubolare != null) {
			String key2 = iGiunzionelongtubolare.getKey();
			iGiunzionelongtubolare.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
		}
		if (iConfezionefondello != null) {
			String key3 = iConfezionefondello.getKey();
			iConfezionefondello.setKey(KeyHelper.replaceTokenObjectKey(key3, 1, idAzienda));
		}
		if (iFibra != null) {
			String key4 = iFibra.getKey();
			iFibra.setKey(KeyHelper.replaceTokenObjectKey(key4, 1, idAzienda));
		}
		if (iComposizione != null) {
			String key5 = iComposizione.getKey();
			iComposizione.setKey(KeyHelper.replaceTokenObjectKey(key5, 1, idAzienda));
		}
		if (iIntreccio != null) {
			String key6 = iIntreccio.getKey();
			iIntreccio.setKey(KeyHelper.replaceTokenObjectKey(key6, 1, idAzienda));
		}
		if (iGruppolifetess != null) {
			String key7 = iGruppolifetess.getKey();
			iGruppolifetess.setKey(KeyHelper.replaceTokenObjectKey(key7, 1, idAzienda));
		}
		if (iLeft2 != null) {
			String key8 = iLeft2.getKey();
			iLeft2.setKey(KeyHelper.replaceTokenObjectKey(key8, 1, idAzienda));
		}
		if (iLeft2preferenziale != null) {
			String key9 = iLeft2preferenziale.getKey();
			iLeft2preferenziale.setKey(KeyHelper.replaceTokenObjectKey(key9, 1, idAzienda));
		}
		if (iClassefittizia != null) {
			String key10 = iClassefittizia.getKey();
			iClassefittizia.setKey(KeyHelper.replaceTokenObjectKey(key10, 1, idAzienda));
		}
		if (iLeft2fioccoprincipale != null) {
			String key11 = iLeft2fioccoprincipale.getKey();
			iLeft2fioccoprincipale.setKey(KeyHelper.replaceTokenObjectKey(key11, 1, idAzienda));
		}
		if (iLeft2fioccosecondario != null) {
			String key12 = iLeft2fioccosecondario.getKey();
			iLeft2fioccosecondario.setKey(KeyHelper.replaceTokenObjectKey(key12, 1, idAzienda));
		}
		if (iGreggio != null) {
			String key13 = iGreggio.getKey();
			iGreggio.setKey(KeyHelper.replaceTokenObjectKey(key13, 1, idAzienda));
		}
		if (iMaster != null) {
			String key14 = iMaster.getKey();
			iMaster.setKey(KeyHelper.replaceTokenObjectKey(key14, 1, idAzienda));
		}
		if (iTipoclean != null) {
			String key15 = iTipoclean.getKey();
			iTipoclean.setKey(KeyHelper.replaceTokenObjectKey(key15, 1, idAzienda));
		}
		if (iFeltrotessutopref != null) {
			String key16 = iFeltrotessutopref.getKey();
			iFeltrotessutopref.setKey(KeyHelper.replaceTokenObjectKey(key16, 1, idAzienda));
		}
		//72099 Inizio
		if(iClasseServizio != null) {
			String key17 = iClasseServizio.getKey();
			iClasseServizio.setKey(KeyHelper.replaceTokenObjectKey(key17, 1, idAzienda));
		}
		//72099 Fine
	}

	protected void setIdCodLeft2PreferenzialeInternal(String idCodLeft2Preferenziale) {
		String key1 = iLeft2preferenziale.getKey();
		iLeft2preferenziale.setKey(KeyHelper.replaceTokenObjectKey(key1, 2, idCodLeft2Preferenziale));
		String key2 = iLeft2fioccoprincipale.getKey();
		iLeft2fioccoprincipale.setKey(KeyHelper.replaceTokenObjectKey(key2, 2, idCodLeft2Preferenziale));
	}

	//72320 <
	public boolean isFloorStockContoLavoro() {
		return ((YArticoloDatiProduz)getArticoloDatiProduz()).isFloorStockContoLavoro();
	}

	public void setFloorStockContoLavoro(boolean iFloorStockContoLavoro) {
		((YArticoloDatiProduz)getArticoloDatiProduz()).setFloorStockContoLavoro(iFloorStockContoLavoro);
	}
	//72320 >

	@Override
	public int save() throws SQLException {
		if(iInCopia) {
			setFloorStockContoLavoro(false); //72320
		}
		return super.save();
	}
}
