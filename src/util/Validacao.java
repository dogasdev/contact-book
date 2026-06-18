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

    public static boolean telefoneExists(List<Contato> listaContatos, String telefone){
        for(Contato contato : listaContatos){
            if(contato instanceof ContatoTelefonico){ //Instanceof verifica se um objeto é de um determinado tipo/classe. "“O objeto guardado em contato é um ContatoTelefone?”
                ContatoTelefonico contatoTele = (ContatoTelefonico) contato; //transforma o contato abstrato num contato telefonico
                if(contatoTele.getTelefone().equals(telefone))
                    return true;
            }
        }
        return false;
    }

    public static boolean emailExists(List<Contato> listaContatos, String email){
        for(Contato contato : listaContatos){
            if(contato instanceof ContatoEmail){
                ContatoEmail contatoEm = (ContatoEmail) contato;
                if(contatoEm.getEmail().equals(email)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValidEmail(String email){
        final String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if(email.length() > 6) {
            if (email.matches(emailPattern)) { //é possível simplificar apenas colocando return email.matches(emailPattern)
                return true; //matches verifica se a string segue um padrão
            }
        }
        return false;

        /*
            PADRÃO ESPERADO DO EMAIL: ^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@ + (?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$

            PRIMEIRA PARTE: ^[a-zA-Z0-9_+&*-]+
            ^ = Indica o início da String
            [a-zA-Z] = Permite letras minúsculas e maiúsculas.
            [0-9] = Permite números de 0 a 9.
            [_+&*-] = Permite os caracteres especiais _, +, &, * e -.
            + = O conjunto anterior deve aparecer uma ou mais vezes -> Isto é, eu não posso ter apenas um caractere ou vazio.

            RESULTADO: DEFINE OS CARACTERES ESPECIAIS PERMITIDOS ANTES DO @.

            SEGUNDA PARTE: (?:\\.[a-zA-Z0-9_+&*-]+)*
            \ = Caractere de escape da Regex -> Símbolo usado que altera o significado do caractere que vem logo em seguida. Servindo p/ inserir
            caracteres especiais
            \\. = Representa um ponto literal (.). ->  ponto sozinho possui significado especial na Regex. Por isso isso utiliza-se "\" para remover esse significado.
            Como "\" também possui significado especial em Strings Java, é necessário escrever "\\" para que o regex receba "\"

            [a-zA-Z0-9_+&*-]+ = Define que após o ponto, os mesmos caracteres permitidos anteriormente podem ser utilizados.
            * = O grupo pode aparecer zero ou mais vezes.

            TERCEIRA PARTE: @
            @ = * = O grupo pode aparecer zero ou mais vezes.
            -> A Regex é lida da esquerda para a direita. Após validar o nome do usuário, ela espera obrigatoriamente encontrar um @.

            RESULTADO: SEPARA O NOME DO USUÁRIO DO DOMÍNIO

            QUARTA PARTE (DOMÍNIO): (?:[a-zA-Z0-9-]+\\.)+
            [a-zA-Z0-9-] = Permite letras, números e hífen.
            + = O conjunto deve aparecer uma ou mais vezes.
            \\. = Exige a presença de um ponto após cada parte do domínio.

            RESULTADO: Define as partes do domínio que antecedem a extensão.

            QUINTA PARTE: [a-zA-Z]{2,7}$
            [a-zA-Z] = Permite apenas letras.
            {2,7} = A extensão deve possuir entre 2 e 7 caracteres.
            $ = Indica o fim da String.

            RESULTADO: Garante que o e-mail termine com uma extensão válida.
             */

    }
}
