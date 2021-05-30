package com.innso.clientapi.api;


import com.innso.clientapi.exception.MyClientApiException;
import com.innso.clientapi.model.DossierClient;
import com.innso.clientapi.parametre.AddDossierParam;
import com.innso.clientapi.parametre.AddMessageParam;
import com.innso.clientapi.service.DossierClientService;
import com.innso.clientapi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;


@RestController
public class ClientApiController {

    @Autowired
    DossierClientService dossierClientService;

    @Autowired
    MessageService messageService;

    @PostMapping(value = "/message", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Callable<HttpEntity<Long>> ajouterMessage(@RequestBody final AddMessageParam addMessageParam) {
        final Long response = messageService.createMessage(addMessageParam.getAuteur(),
                addMessageParam.getContenu(), addMessageParam.getCanal());
        return () -> ResponseEntity.ok(response);
    }

    @PostMapping(value = "/dossier", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Callable<HttpEntity<Long>> ajouterDossier(@RequestBody final AddDossierParam addDossierParam) throws MyClientApiException {
        final Long response = dossierClientService.createDossierClient(addDossierParam.getNomClient(), addDossierParam.getIdMsg());
        return () -> ResponseEntity.ok(response);
    }

    @PutMapping(value = "/dossier/{dossierId}")
    public Callable<HttpEntity<Long>> attacherMessage(@PathVariable final Long dossierId, @RequestParam("msg") final Long messageId) throws MyClientApiException {
        final Long response = dossierClientService.attacherMessageToDossierClient(dossierId, messageId);
        return () -> ResponseEntity.ok(response);
    }

    @PutMapping(value = "/dossier/{dossierId}/message")
    public Callable<HttpEntity<Long>> attacherMessage(@PathVariable final Long dossierId, @RequestBody final AddMessageParam newMessage) throws MyClientApiException {
        final Long response = dossierClientService.attacherMessageToDossierClient(dossierId, newMessage);
        return () -> ResponseEntity.ok(response);
    }

    @GetMapping(value = "/dossier")
    public Callable<HttpEntity<List<DossierClient>>> getAllDossiersClient() {
        return () -> ResponseEntity.ok(dossierClientService.findAllDossierClient());
    }

    @PutMapping(value = "/dossier/ref/{id}")
    public Callable<HttpEntity<Boolean>> modifierRefDossier(@PathVariable Long id, @RequestParam String ref) {
        return () -> ResponseEntity.ok(dossierClientService.modifierRefDossierClient(id, ref));
    }

}
