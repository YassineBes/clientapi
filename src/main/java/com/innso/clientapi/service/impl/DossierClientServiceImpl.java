package com.innso.clientapi.service.impl;

import com.innso.clientapi.exception.MyClientApiErrorMessage;
import com.innso.clientapi.exception.MyClientApiException;
import com.innso.clientapi.model.DossierClient;
import com.innso.clientapi.model.Message;
import com.innso.clientapi.parametre.AddMessageParam;
import com.innso.clientapi.repository.DossierClientRepo;
import com.innso.clientapi.service.DossierClientService;
import com.innso.clientapi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DossierClientServiceImpl implements DossierClientService {

    @Autowired
    MessageService messageService;
    @Autowired
    DossierClientRepo dossierClientRepo;

    @Override
    public Long createDossierClient(String nomClient, Long idMessage) throws MyClientApiException {
        if (nomClient == null || nomClient.isEmpty() || idMessage == null) {
            throw new IllegalArgumentException(MyClientApiErrorMessage.DOSSIER_NOT_CONFORM);
        }
        var msg = messageService.findById(idMessage);
        return attacherMessageToDossierClient(msg, new DossierClient(nomClient, new Date())).getId();

    }

    @Override
    public Long attacherMessageToDossierClient(Long idDossier, Long idMessage) throws MyClientApiException {
        if (idDossier == null || idMessage == null) {
            throw new IllegalArgumentException(MyClientApiErrorMessage.PARAMETER_NOT_VALID);
        }
        var dossierClient = findById(idDossier);
        Message msg = this.messageService.findById(idMessage);
        return attacherMessageToDossierClient(msg, dossierClient).getId();

    }

    @Override
    public Long attacherMessageToDossierClient(Long idDossier, AddMessageParam newMsg) throws MyClientApiException {
        return attacherMessageToDossierClient(idDossier, this.messageService
                .createMessage(newMsg.getAuteur(), newMsg.getContenu(), newMsg.getCanal()));

    }

    private DossierClient attacherMessageToDossierClient(Message msg, DossierClient dossierClient) {
        if (dossierClient.getListeMsg() == null) {
            dossierClient.setListeMsg(new ArrayList<>());
        }
        dossierClient.getListeMsg().add(msg);
        return this.dossierClientRepo.save(dossierClient);
    }

    @Override
    public DossierClient findById(Long idDossier) throws MyClientApiException {
        return this.dossierClientRepo.findById(idDossier).orElseThrow(()
                -> new MyClientApiException(MyClientApiErrorMessage.DOSSIER_NOT_FOUND));
    }

    @Override
    public Boolean modifierRefDossierClient(Long idDossier, String refClient) {
        if (idDossier != null && refClient != null && !refClient.isEmpty()) {
            var dossierClient = dossierClientRepo.findById(idDossier);
            if (dossierClient.isPresent()) {
                dossierClient.get().setReference(refClient);
                dossierClientRepo.save(dossierClient.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<DossierClient> findAllDossierClient() {
        return (List<DossierClient>) dossierClientRepo.findAll();
    }
}
