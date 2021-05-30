package com.innso.clientapi.service;


import com.innso.clientapi.exception.MyClientApiException;
import com.innso.clientapi.model.Message;
import com.innso.clientapi.ref.CanalEnum;

public interface MessageService {
    Long createMessage(String auteur, String contenu, CanalEnum canal);

    Message findById(Long idMessage) throws MyClientApiException;
}
