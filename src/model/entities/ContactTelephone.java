package model.entities;

import model.exceptions.InvalidDataException;
import util.Validation;

public class ContactTelephone extends Contact {
    private String telephone;

    public ContactTelephone(String nome, String telephone) {
        super(nome);
        this.telephone = telephone;
    }

    public String getTelephone() {
        return Validation.formatTelephone(this.telephone);
    }

    public void setTelephone(String telephone) throws InvalidDataException{
        if (telephone != null && Validation.validatePhone(telephone)) {
            this.telephone = telephone;
        }
        throw new InvalidDataException("Telefone inválido!");
    }

    public String getType() {
        return "Telefone";
    }

    @Override
    public void updateData(String newData) throws InvalidDataException {
        setTelephone(newData);
    }

    @Override
    public String toString() {
        return String.format(" %d | %s | %s: %s", getID(), getName(), getType(), getTelephone());
    }

}
