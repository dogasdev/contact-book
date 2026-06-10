package entities;
import util.Validacao;

public class ContatoTelefonico extends Contato{
    private String telefone;

    public ContatoTelefonico(String nome, String telefone){
        super(nome);
        this.telefone = telefone;
    }

    public String getTelefone(){
        return Validacao.formatarTelefone(this.telefone);
    }

    public String getTipo(){
        return "Telefone";
    }

    public String toString(){
        return String.format("%s | %s: %s", getNome(), getTipo(), getTelefone());
    }

}
