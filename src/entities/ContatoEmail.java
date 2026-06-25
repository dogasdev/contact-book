package entities;

import util.Validacao;

public class ContatoEmail extends Contato{
    private String email;

    public ContatoEmail(String nome, String email){
        super(nome);
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        if(email != null && Validacao.isValidEmail(email)){
            this.email = email;
        }else{
            System.out.println("Telefone inválido!");
        }
    }

    public String getTipo(){
        return "Email";
    }

    @Override
    public String toString(){
        return String.format(" %d | %s | %s: %s", getID(), getNome(), getTipo(), getEmail());
    }
}
