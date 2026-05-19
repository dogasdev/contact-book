package entities;

public class ContatoEmail extends Contato{
    private String email;

    public ContatoEmail(String nome, Integer ID, String email){
        super(nome, ID);
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public String getTipo(){
        return "Email";
    }

    public String toString(){
        return String.format("%s | %s: %s", getNome(), getTipo(), getEmail());
    }
}
