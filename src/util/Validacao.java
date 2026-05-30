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

    public static boolean validarTelefone(String telefone){
        telefone = telefone.replaceAll("\\D", ""); //REMOÇÃO DE TUDO QUE NÃO SEJA DIGITO
        if(telefone.matches("\\d{10,11}")){
            return true;
        }
        return false;
    }

    public static String formatarTelefone(String telefone){
        telefone = telefone.replaceAll("\\D", "");

        String ddd = telefone.substring(0, 2);
        String sufix = telefone.substring(2);
        if(telefone.length() == 10){
            sufix = "9" + sufix;
        }

        telefone = String.format("(%s) %s-%s", //DDD, NONO DIGITO, PRIMEIRO SUFIXO, SEGUNDO SUFIXO
                ddd, sufix.substring(0, 5), sufix.substring(5));

        return telefone;
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
