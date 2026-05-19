package entities;

abstract public class Contato {
    private String nome;
    private Integer ID;

    public Contato(String nome, Integer ID){
        this.nome = nome;
        this.ID = ID;
    }

    public String getNome(){
        return nome;
    }

    public Integer getID() {
        return ID;
    }
}
