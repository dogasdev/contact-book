package entities;

public class ContatoEmail extends Contato{
    private String email;

    public ContatoEmail(String nome, Integer ID, String email){
        super(nome, ID);
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getTipo(){
        return "Email";
    }

    public String toString(){
        return String.format("%s | %s: %s", getNome(), getTipo(), getEmail());
    }
}
