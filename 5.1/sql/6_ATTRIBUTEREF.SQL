 
# Riferimento: YVincoloAssocDocOrdVenRig
    # ATTRIBUTE_REF
    INSERT INTO <SCH>ATTRIBUTE_REF (ATTRIBUTE_REF, DESCRIPTION, TYPE_ID, TYPE_SIZE, TYPE_SCALE, DB_SIZE, DB_SCALE, FW_DATA_OWNER_ID, USER_ID, TIMESTAMP, ONLY_ICON_ON_WEBGRIDS) VALUES ('YVincoloAssocDocOrdVenRig', 'Vincolo associazione doc - OVR', 8, 0, 0, 0, 0, 'THIP_PERS', <USER>, <CURTS>, 'N');
    # ENUM
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YVincoloAssocDocOrdVenRig', '0', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YVincoloAssocDocOrdVenRig', '1', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YVincoloAssocDocOrdVenRig', '2', 0, null, null, null);
    INSERT INTO <SCH>ENUM (ATTRIBUTE_REF, ENUM_VALUE, SEQUENCE, ICON_NAME, BACK_COLOR, FORE_COLOR) VALUES ('YVincoloAssocDocOrdVenRig', '3', 0, null, null, null);
    # ENUM_NLS
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YVincoloAssocDocOrdVenRig', '0', 'it', 'Non significativo');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YVincoloAssocDocOrdVenRig', '1', 'it', 'Allegato richiesto e obbligatorio per l’evasione');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YVincoloAssocDocOrdVenRig', '2', 'it', 'Allegato richiesto e obbligatorio per la spedizion');
    INSERT INTO <SCH>ENUM_NLS (ATTRIBUTE_REF, ENUM_VALUE, LANGUAGE, ENUM_DESC) VALUES ('YVincoloAssocDocOrdVenRig', '3', 'it', 'Allegato non applicabile');
    # ATTRIBUTE_REF_EXTENSION_DATA
    INSERT INTO <SCH>EXT_ATTRIBUTE_REF (ATTRIBUTE_REF, COLUMN_TYPE, REMARKS) VALUES ('YVincoloAssocDocOrdVenRig', '0', null);
