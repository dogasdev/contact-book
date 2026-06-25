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
            for (Contato contato : listaContatos) {
                System.out.println(contato);
            }
        } else {
            System.out.println("Nenhum contato cadastrado!");
        }
    }

    public List<Contato> buscarContatosPorNome(List<Contato> listaContatos, String nome){
        if(!listaContatos.isEmpty()){
            List<Contato> contatosEncontrados = new ArrayList<>();
            for(Contato contato : listaContatos){
                if(contato.getNome() != null && contato.getNome().equalsIgnoreCase(nome)){
                    contatosEncontrados.add(contato);
                }
            }
            return contatosEncontrados;
        }
        return null;
    }


    public Contato buscarContatoEncontradoPorId(List<Contato> contatosEncontrados, int id){
        if(!contatosEncontrados.isEmpty()){
            for(Contato contato : contatosEncontrados){
                if(contato.getID() == id){
                    return contato;
                }
            }
        }
        return null;
    }

    public void modificarContato(Contato contato, int opcao, String novoNome, String novoDado){
        switch(opcao){
            case 1:
                if(novoNome != null && !novoNome.trim().isEmpty()){
                    contato.setNome(novoNome);
                }
                break;
            case 2:
                if(contato instanceof ContatoTelefonico){
                    ContatoTelefonico contatoTele = (ContatoTelefonico) contato;
                    contatoTele.setTelefone(novoDado);
                }else if(contato instanceof ContatoEmail){
                    ContatoEmail contatoEm = (ContatoEmail) contato;
                    contatoEm.setEmail(novoDado);
                }
                break;
            case 3:
                contato.setNome(novoNome);

                if(contato instanceof ContatoTelefonico){
                    ContatoTelefonico contatoTele = (ContatoTelefonico) contato;
                    contatoTele.setTelefone(novoDado);
                }else if(contato instanceof ContatoEmail){
                    ContatoEmail contatoEmail = (ContatoEmail) contato;
                    contatoEmail.setEmail(novoDado);
                }
                break;
            default:
                System.out.println("Opção inválida, tente novamente!");
                return;
        }
    }

    public boolean deletarContato(int id){
        return listaContatos.removeIf(contato -> contato.getID() == id);
    }
}

