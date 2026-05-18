package entities;

public class ContatoTelefonico extends Contato{
    private String telefone;

    public ContatoTelefonico(String nome, Integer ID, String telefone){
        super(nome, ID);
    }

    public String getTelefone(){
        return telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getTipo(){
        return "Telefone";
    }

    public String toString(){
        return String.format("%s | %s: %s", getNome(), getTipo(), getTelefone());
    }

}
