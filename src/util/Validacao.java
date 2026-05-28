package util;

import entities.Contato;
import entities.ContatoEmail;
import entities.ContatoTelefonico;
import java.util.List;

public class Validacao {
    public static boolean validarNome(String nome){
        if(nome != null && nome.length() >= 3){
            return true;
        }
        return false;
    }
    /*
    TODO: FAZER A FORMATAÇÃO DO NÚMERO DE TELEFONE
     */
    public static boolean validarTelefone(String telefone){
        telefone = telefone.replaceAll("[()\\s-]", "");
        return telefone.matches("\\d{10,11}");
    }

    public static boolean verificarTelefone(List<Contato> listaContatos, String telefone){
        for(Contato contato : listaContatos){
            if(contato instanceof ContatoTelefonico){ //Instanceof verifica se um objeto é de um determinado tipo/classe. "“O objeto guardado em contato é um ContatoTelefone?”
                ContatoTelefonico contatoTele = (ContatoTelefonico) contato;
                if(contatoTele.getTelefone().equals(telefone))
                    return true;
            }
        }
        return false;
    }

    public static boolean verificarEmail(List<Contato> listaContatos, String email){
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
}
