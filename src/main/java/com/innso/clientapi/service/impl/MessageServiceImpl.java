package com.innso.clientapi.service.impl;

import com.innso.clientapi.exception.MyClientApiErrorMessage;
import com.innso.clientapi.exception.MyClientApiException;
import com.innso.clientapi.model.Message;
import com.innso.clientapi.ref.CanalEnum;
import com.innso.clientapi.repository.MessageRepo;
import com.innso.clientapi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepo messageRepo;

    @Override
    public Long createMessage(String auteur, String contenu, CanalEnum canal) {
        if (auteur == null || auteur.isEmpty() || contenu == null || contenu.isEmpty() || canal == null) {
            throw new IllegalArgumentException(MyClientApiErrorMessage.MESSAGE_NOT_CONFORM);
        }
        return messageRepo.save(new Message(new Date(), auteur, contenu, canal)).getId();
    }

    @Override
    public Message findById(Long idMessage) throws MyClientApiException {
        return messageRepo.findById(idMessage).orElseThrow(() ->
                new MyClientApiException(MyClientApiErrorMessage.MESSAGE_NOT_FOUND));
    }

}
