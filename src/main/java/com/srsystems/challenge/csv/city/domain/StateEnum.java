package com.srsystems.challenge.csv.city.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StateEnum {

    UF_AC("AC", 1L),
    UF_AL("AL", 2L),
    UF_AP("AP", 3L),
    UF_AM("AM", 4L),
    UF_BA("BA", 5L),
    UF_CE("CE", 6L),
    UF_DF("DF", 7L),
    UF_ES("ES", 8L),
    UF_GO("GO", 9L),
    UF_MA("MA", 10L),
    UF_MT("MT", 11L),
    UF_MS("MS", 12L),
    UF_MG("MG", 13L),
    UF_PA("PA", 14L),
    UF_PB("PB", 15L),
    UF_PR("PR", 16L),
    UF_PE("PE", 17L),
    UF_PI("PI", 18L),
    UF_RJ("RJ", 19L),
    UF_RN("RN", 20L),
    UF_RS("RS", 21L),
    UF_RO("RO", 22L),
    UF_RR("RR", 23L),
    UF_SC("SC", 24L),
    UF_SP("SP", 25L),
    UF_SE("SE", 26L),
    UF_TO("TO", 27L);

    @Getter
    private Long StateId;

    @Getter
    private String StateKey;

    private StateEnum(String key, long value) {
        this.StateKey = key;
        this.StateId = value;
    }
}
