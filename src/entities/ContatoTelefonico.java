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

    public void setTelefone(String telefone){
        if(telefone != null && Validacao.validarTelefone(telefone)){
            this.telefone = telefone;
        }
        System.out.println("Telefone inválido!");
    }

    public String getTipo(){
        return "Telefone";
    }

    @Override
    public String toString(){
        return String.format(" %d | %s | %s: %s", getID(), getNome(), getTipo(), getTelefone());
    }

}
