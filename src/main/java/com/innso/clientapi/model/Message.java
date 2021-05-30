package com.innso.clientapi.model;


import com.innso.clientapi.ref.CanalEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "MESSAGE")

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dateMessage;
    private String nomAuteur;
    private String contenuMsg;
    private CanalEnum canal;

    public Message(Date dateMessage, String nomAuteur, String contenuMsg, CanalEnum canal) {
        this.dateMessage = dateMessage;
        this.nomAuteur = nomAuteur;
        this.contenuMsg = contenuMsg;
        this.canal = canal;
    }
}
