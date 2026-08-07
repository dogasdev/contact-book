package model.entities;

import model.exceptions.InvalidDataException;
import util.Validation;

public class ContactEmail extends Contact {
    private String email;

    public ContactEmail(String nome, String email) {
        super(nome);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidDataException{
        if (email != null && Validation.isValidEmail(email)) {
            this.email = email;
        } else {
            throw new InvalidDataException("E-mail inválido!");
        }
    }

    public String getType() {
        return "Email";
    }

    @Override
    public void updateData(String newData) throws InvalidDataException {
        setEmail(newData);
    }

    @Override
    public String toString() {
        return String.format(" %d | %s | %s: %s", getID(), getName(), getType(), getEmail());
    }
}
