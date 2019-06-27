package com.catolicasc.combatcontrol;
import java.io.Serializable;

public class Robo implements Serializable{

    private String nome;


    public Robo() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
