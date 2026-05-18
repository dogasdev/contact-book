package service;
import entities.Contato;

import java.util.ArrayList;

public class Agenda {
    private final ArrayList<Contato> listaContatos = new ArrayList<>();

    public void cadastrarContato(Contato contato){
        listaContatos.add(contato);
    }

    public void listarContatos(){
        if(!listaContatos.isEmpty()){
            for(int i = 0; i < listaContatos.size(); i++){
                Contato contato = listaContatos.get(i);
                System.out.println(i + " | " + contato);
            }
        }else{
            System.out.println("Nenhum contato cadastrado!");
        }
    }
}
