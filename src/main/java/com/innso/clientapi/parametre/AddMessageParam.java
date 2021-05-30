package com.innso.clientapi.parametre;

import com.innso.clientapi.ref.CanalEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMessageParam {
    private String contenu;
    private String auteur;
    private CanalEnum canal;
}
