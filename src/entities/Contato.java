package entities;

abstract public class Contato{
    private static int contadorID = 1;
    private String nome;
    private Integer ID;

    public Contato(String nome){
        this.nome = nome;
        this.ID = contadorID++;
    }

    public String getNome(){
        return nome;
    }

    public Integer getID() {
        return ID;
    }
}
