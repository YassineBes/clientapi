package com.innso.clientapi.service;

import com.innso.clientapi.exception.MyClientApiException;
import com.innso.clientapi.model.DossierClient;
import com.innso.clientapi.parametre.AddMessageParam;

import java.util.List;

public interface DossierClientService {

    Long createDossierClient(String nomClient, Long idMessage) throws MyClientApiException;

    Long attacherMessageToDossierClient(Long idDossier, Long idMessage) throws MyClientApiException;

    Long attacherMessageToDossierClient(Long idDossier,  AddMessageParam newMessage) throws MyClientApiException;

    Boolean modifierRefDossierClient(Long idDossier, String refClient);

    List<DossierClient> findAllDossierClient();

    DossierClient findById(Long idDossier) throws MyClientApiException;
}
