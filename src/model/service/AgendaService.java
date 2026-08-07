package model.service;

import model.entities.Contact;
import model.entities.ContactEmail;
import model.entities.ContactTelephone;
import model.exceptions.InvalidDataException;
import util.Validation;

import java.util.ArrayList;
import java.util.List;

public class AgendaService {
    private final List<Contact> contactList = new ArrayList<>();

    public List<Contact> getContactList() {
        return contactList;
    }

    public void reportContacts() {
        int totalTelephone = 0;
        int totalEmail = 0;

        for (Contact contact : contactList) {
            if (contact instanceof ContactTelephone) {
                totalTelephone++;
            }
            if (contact instanceof ContactEmail) {
                totalEmail++;
            }
        }
        System.out.println("=== RELATÓRIO DE CONTATOS ===");
        System.out.println("Total de Contatos: " + contactList.size());
        System.out.println("Total de contatos com telefone: " + totalTelephone);
        System.out.println("Total de contatos com email: " + totalEmail);
    }

    public void registerContact(Contact contact) {
        contactList.add(contact);
    }

    public void listContacts() {
        if (!contactList.isEmpty()) {
            for (Contact contact : contactList) {
                System.out.println(contact);
            }
        } else {
            System.out.println("Nenhum contato cadastrado!");
        }
    }

    public List<Contact> findContactsByName(List<Contact> contactList, String nome) {
        if (!contactList.isEmpty()) {
            List<Contact> contactsFound = new ArrayList<>();
            for (Contact contact : contactList) {
                if (contact.getName() != null && contact.getName().equalsIgnoreCase(nome)) {
                    contactsFound.add(contact);
                }
            }
            return contactsFound;
        }
        return List.of();
    }


    public Contact findContactFoundById(List<Contact> contatosEncontrados, int id) {
        if (!contatosEncontrados.isEmpty()) {
            for (Contact contact : contatosEncontrados) {
                if (contact.getID() == id) {
                    return contact;
                }
            }
        }
        return null;
    }


    public void updateContact(Contact contact, int opcao, String newName, String newData) throws InvalidDataException{
        switch (opcao) {
            case 1 ->{
                if(!Validation.validateName(newName)){
                    throw new InvalidDataException("Operação cancelada! Nome inválido!");
                }
                contact.setName(newName);
            }
            case 2 -> contact.updateData(newData);

            case 3 -> {
                try{
                    if(!Validation.validateName(newName)){
                        throw new InvalidDataException("Operação cancelada! Nome inválido!");
                    }
                    contact.updateData(newData);
                    contact.setName(newData);
                }catch(InvalidDataException e){
                    throw new InvalidDataException("Operação cancelada: dado de contato inválido!");
                }
            }
            default -> System.out.println("Opção inválida, tente novamente!");
        }
    }

    public boolean deleteContact(int id) {
        return contactList.removeIf(contact -> contact.getID() == id);
    }
}

