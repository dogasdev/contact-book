import model.entities.Contact;
import model.entities.ContactEmail;
import model.entities.ContactTelephone;
import model.exceptions.InvalidDataException;
import model.service.AgendaService;
import util.Validation;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static AgendaService agenda = new AgendaService();

    public static void main(String[] args) {
        int option;
        Scanner userInput = new Scanner(System.in);

        do {
            displayMenu();
            option = readOption(userInput);
            processOption(option, userInput);
        } while (option != 7);
    }

    public static void displayMenu() {
        System.out.println("=== AGENDA ===");
        System.out.println("[1] Criar novo contato\n[2] Listar contatos \n[3] Relatório de Contatos\n[4] Procurar contato\n[5] Atualizar contato\n[6] Deletar contato\n[7] Sair");
        System.out.print("Insira uma opção: \n");
    }

    public static int readOption(Scanner userInput) {
        try {
            return Integer.parseInt(userInput.nextLine());
        } catch (Exception e) {
            return 0;
        }
    }

    public static void processOption(int opcao, Scanner userInput) {
        switch (opcao) {
            case 1 -> displayRegistration(userInput);
            case 2 -> displayContacts();
            case 3 -> displayReport();
            case 4 -> findContact(userInput);
            case 5 -> showModifyContact(userInput);
            case 6 -> displayDeleteContact(userInput);
        }
    }

    public static void displayRegistration(Scanner userInput) {
        System.out.println("[ INSIRA AS INFORMAÇÕES DO CONTATO ]");
        System.out.print("Nome: ");
        String name = userInput.nextLine().toUpperCase().trim();
        while (!Validation.validateName(name)) {
            System.out.println("Nome inválido! Tente novamente: ");
            name = userInput.nextLine().toUpperCase().trim();
        }
        Contact contact;

        System.out.println("Tipo de Contato -- [1] Telefone [2] E-mail");
        try{
            int type = Integer.parseInt(userInput.nextLine());
            if (type == 1) {
                System.out.print("Telefone: ");
                String telephone = userInput.nextLine();
                while (!Validation.validatePhone(telephone)) {
                    System.out.println("Número inválido! Tente novamente: ");
                    telephone = userInput.nextLine();
                }
                while (Validation.phoneExists(agenda.getContactList(), telephone)) {
                    System.out.println("Número de telefone já cadastrado! Tente novamente: ");
                    telephone = userInput.nextLine();
                }
                contact = new ContactTelephone(name, telephone);

                agenda.registerContact(contact);
                System.out.print("Contato cadastrado com sucesso!\n\n");
            } else {
                System.out.print("E-mail: ");
                String email = userInput.nextLine();
                while (!Validation.isValidEmail(email)) {
                    System.out.println("E-mail inválido! Tente novamente: ");
                    email = userInput.nextLine();
                }
                while (Validation.emailExists(agenda.getContactList(), email)) {
                    System.out.println("E-mail já cadastrado! Tente novamente:");
                    email = userInput.nextLine();
                }
                contact = new ContactEmail(name, email);
                agenda.registerContact(contact);
                System.out.println("Contato de e-mail cadastrado com sucesso!\n\n");
            }
        }catch (NumberFormatException e){
            System.out.println("Erro de Input: " + e.getMessage());
        }
    }

    public static void displayContacts() {
        agenda.listContacts();
    }

    public static void displayReport() {
        agenda.reportContacts();
    }

    public static void findContact(Scanner userInput) {
        System.out.print("Insira o nome do contato que deseja procurar: ");
        String name = userInput.nextLine().toUpperCase();

        List<Contact> contactsFound = agenda.findContactsByName(agenda.getContactList(), name);
        if (contactsFound != null && !contactsFound.isEmpty()) {
            System.out.println("[ CONTATOS ENCONTRADOS ]");
            for (Contact contact : contactsFound) {
                System.out.println(contact);
            }
        } else {
            System.out.println("Nenhum contato encontrado.");
        }
    }

    public static void showModifyContact(Scanner userInput){

        System.out.println("[ MENU DE ALTERAÇÃO ]");
        System.out.print("Insira o nome do contato que deseja alterar: \n");
        String name = userInput.nextLine().trim();

        List<Contact> contactsFound = agenda.findContactsByName(agenda.getContactList(), name);

        if (contactsFound.isEmpty()) {
            System.out.println("Nenhum contato cadastrado na agenda!");
            return;
        }

        Contact contact;

        if (contactsFound.size() == 1) {
            contact = contactsFound.getFirst();
        } else {
            for (Contact contactFound : contactsFound) {
                System.out.println(contactFound);
            }
            System.out.println("[!] Foram encontrados mais de um contato com o mesmo nome. Insira o ID correspondente para escolher qual modificar: ");
            int id = Integer.parseInt(userInput.nextLine());

            contact = agenda.findContactFoundById(contactsFound, id);

            if (contact == null) {
                System.out.println("Contato não encontrado!");
                return;
            } else {
                System.out.println("Contato escolhido: " + contact);
            }
        }

        System.out.println("CONTATO: " + contact);
        System.out.println("[1] Nome \n[2] Dado do contato \n[3] Ambos os dados");
        int option = Integer.parseInt(userInput.nextLine());

        String newName = null;
        String newData = null;

        switch (option) {
            case 1:
                System.out.println("Novo nome: ");
                newName = userInput.nextLine();
                break;
            case 2:
                System.out.println("Novo dado (Telefone ou E-mail): ");
                newData = userInput.nextLine();
                break;
            case 3:
                System.out.println("Novo nome: ");
                newName = userInput.nextLine();

                System.out.println("Novo dado (Telefone ou E-mail): ");
                newData = userInput.nextLine();
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }
        try{
            agenda.updateContact(contact, option, newName, newData);
        }catch(InvalidDataException e){
            System.out.println(e.getMessage());
        }

    }

    public static void displayDeleteContact(Scanner userInput) {
        System.out.println("[ MENU DE DELEÇÃO ]");
        System.out.println("Insira o nome do contato que deseja deletar: ");
        String name = userInput.nextLine();

        List<Contact> contactsFound = agenda.findContactsByName(agenda.getContactList(), name);

        if (contactsFound.isEmpty()) {
            System.out.println("[!] Contato não encontrado");
            return;
        }

        Contact contact;

        if (contactsFound.size() == 1) {
            contact = contactsFound.getFirst();
        } else {
            for (Contact contactFound : contactsFound) {
                System.out.println(contactFound);
            }
            System.out.println("[!] Foram encontrados mais de um contato com esse nome. Insira o ID correspondente para selecionar o contato: ");
            int id = Integer.parseInt(userInput.nextLine());

            contact = agenda.findContactFoundById(contactsFound, id);

            if (contact == null) {
                System.out.println("[!] Contato não encontrado");
                return;
            }
        }

        System.out.println("Contato selecionado: " + contact);
        System.out.println("Deseja deletar este contato? (s/sim)");
        String answer = userInput.nextLine();

        if (answer.equalsIgnoreCase("sim") || answer.equalsIgnoreCase("s")) {
            boolean contactDeleted = agenda.deleteContact(contact.getID());

            if (contactDeleted) {
                System.out.println("Contato deletado com sucesso!");
            } else {
                System.out.println("Não foi possível deletar o contato.");
            }
        } else {
            System.out.println("Operação cancelada!");
        }
    }
}

