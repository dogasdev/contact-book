package model.entities;

import model.exceptions.InvalidDataException;

abstract public class Contact {
    private static int counterId = 1;
    private String name;
    private Integer ID;

    public Contact(String name) {
        this.name = name;
        this.ID = counterId++;
    }

    public String getName() {
        return name;
    }

    public Integer getID() {
        return ID;
    }

    public void setName(String newName) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = newName;
        }
    }

    public abstract void updateData(String newData) throws InvalidDataException;
}
