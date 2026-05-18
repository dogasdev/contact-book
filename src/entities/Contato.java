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
    public void setNome(String nome){
        this.nome = nome;
    }

    public Integer getID() {
        return ID;
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }
}
