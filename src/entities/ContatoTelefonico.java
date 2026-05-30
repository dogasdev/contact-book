package entities;

public class ContatoTelefonico extends Contato{
    private String telefone;

    public ContatoTelefonico(String nome, Integer ID, String telefone){
        super(nome, ID);
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
