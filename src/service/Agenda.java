package service;
import entities.Contato;
import entities.ContatoEmail;
import entities.ContatoTelefonico;

import java.util.List;
import java.util.ArrayList;

public class Agenda {
    private final List<Contato> listaContatos = new ArrayList<>();

    public List<Contato> getListaContatos(){
        return listaContatos;
    }

    public void relatorioContatos(){
        int totalTelefone = 0;
        int totalEmail = 0;

        for(Contato contato : listaContatos){
            if(contato instanceof ContatoTelefonico){
                totalTelefone++;
            }
            if(contato instanceof ContatoEmail){
                totalEmail++;
            }
        }
        System.out.println("=== RELATÓRIO DE CONTATOS ===");
        System.out.println("Total de Contatos: " + listaContatos.size());
        System.out.println("Total de contatos com telefone: " + totalTelefone);
        System.out.println("Total de contatos com email: " + totalEmail);
    }


    public void cadastrarContato(Contato contato){
        listaContatos.add(contato);
    }

    public void listarContatos(){
        if(!listaContatos.isEmpty()){
            for(int i = 0; i < listaContatos.size(); i++){
                Contato contato = listaContatos.get(i);
                System.out.println((contato.getID()) + " | " + contato);
            }
        }else{
            System.out.println("Nenhum contato cadastrado!");
        }
    }

    public boolean verificarTelefone(List<Contato> listaContatos, String telefone){
        for(Contato contato : listaContatos){
            if(contato instanceof ContatoTelefonico){ //Instanceof verifica se um objeto é de um determinado tipo/classe. "“O objeto guardado em contato é um ContatoTelefone?”
                ContatoTelefonico contatoTele = (ContatoTelefonico) contato;
                if(contatoTele.getTelefone().equals(telefone))
                    return true;
            }
        }
        return false;
    }

    public boolean verificarEmail(List<Contato> listaContatos, String email){
        for(Contato contato : listaContatos){
           if(contato instanceof ContatoEmail contatoEm){
               //ContatoEmail contatoEm = (ContatoEmail) contato;
               if(contatoEm.getEmail().equals(email)){
                   return true;
               }
           }
        }
        return false;
    }

    public boolean validarNome(String nome){
        if(nome != null && nome.trim().length() >= 4){
            return true;
        }
        return false;
    }
}
