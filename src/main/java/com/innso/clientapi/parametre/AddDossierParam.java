package com.innso.clientapi.parametre;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDossierParam {
    private String nomClient;
    private Long idMsg;
    private Long idDossier;
}
