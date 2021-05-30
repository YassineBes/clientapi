package com.innso.clientapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "DOSSIER_CLIENT")
public class DossierClient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomClient;
    private Date dateOuverture;
    private String reference;
    @OneToMany(targetEntity = Message.class)
    private List<Message> listeMsg;

    public DossierClient(String nomClient, Date dateOuverture) {
        this.nomClient = nomClient;
        this.dateOuverture = dateOuverture;
    }

}
