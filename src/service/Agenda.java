package service;
import entities.Contato;
import entities.ContatoEmail;
import entities.ContatoTelefonico;

import java.util.List;
import java.util.ArrayList;

public class Agenda {
    private final List<Contato> listaContatos = new ArrayList<>();

    public List<Contato> getListaContatos() {
        return listaContatos;
    }

    public void relatorioContatos() {
        int totalTelefone = 0;
        int totalEmail = 0;

        for (Contato contato : listaContatos) {
            if (contato instanceof ContatoTelefonico) {
                totalTelefone++;
            }
            if (contato instanceof ContatoEmail) {
                totalEmail++;
            }
        }
        System.out.println("=== RELATÓRIO DE CONTATOS ===");
        System.out.println("Total de Contatos: " + listaContatos.size());
        System.out.println("Total de contatos com telefone: " + totalTelefone);
        System.out.println("Total de contatos com email: " + totalEmail);
    }


    public void cadastrarContato(Contato contato) {
        listaContatos.add(contato);
    }

    public void listarContatos() {
        if (!listaContatos.isEmpty()) {
            for (Contato cont : listaContatos) {
                System.out.println((cont.getID()) + " | " + cont);
            }
        } else {
            System.out.println("Nenhum contato cadastrado!");
        }
    }
}
