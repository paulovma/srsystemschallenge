package com.srsystems.challenge.csv.city.domain;

import lombok.Getter;

public enum CsvHeaderEnum {

    IBGE_ID("ibge_id"),
    NAME("name"),
    UF("uf"),
    CAPITAL("capital"),
    LON("lon"),
    LAT("lat"),
    NO_ACCENTS("no_accents"),
    ALTERNATIVE_NAMES("alternative_names"),
    MICROREGION("microregion"),
    MESOREGION("mesoregion");


    @Getter
    private String header;

    private CsvHeaderEnum(String value) {
        this.header = value;
    }
}
