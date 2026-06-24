package entities;

public class ContatoEmail extends Contato{
    private String email;

    public ContatoEmail(String nome, String email){
        super(nome);
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String novoDado){
        this.email = novoDado;
    }

    public String getTipo(){
        return "Email";
    }

    @Override
    public String toString(){
        return String.format(" %d | %s | %s: %s", getID(), getNome(), getTipo(), getEmail());
    }
}
