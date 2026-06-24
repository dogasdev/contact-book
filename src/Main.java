import java.util.Scanner;
import entities.Contato;
import entities.ContatoEmail;
import entities.ContatoTelefonico;
import service.Agenda;
import util.Validacao;
import java.util.List;

public class Main {
    private static Agenda agenda = new Agenda();

    public static void main(String[] args) {
        int opcao;
        Scanner userInput = new Scanner(System.in);

        do{
            exibirMenu();
            opcao = lerOpcao(userInput);
            processarOpcao(opcao, userInput);
        }while(opcao != 7);
    }

    public static void exibirMenu(){
        System.out.println("=== AGENDA ===");
        System.out.println("[1] Criar novo contato\n[2] Listar contatos \n[3] Relatório de Contatos\n[4] Procurar contato\n[5] Atualizar contato\n[6] Deletar contato\n[7] Sair");
        System.out.print("Insira uma opção: \n");
    }

    public static int lerOpcao(Scanner userInput){
        try{
            return Integer.parseInt(userInput.nextLine());
        }catch (Exception e){
            return 0;
        }
    }

    public static void processarOpcao(int opcao, Scanner userInput){
        switch(opcao){
            case 1:
                exibirCadastro(userInput);
                break;
            case 2:
                exibirContatos();
                break;
            case 3:
                exibirRelatorio();
                break;
            case 4:
                buscarContato(userInput);
                break;
            case 5:
                exibirModificarContato(userInput);
                break;
        }
    }

    public static void exibirCadastro(Scanner userInput){
        System.out.println("[ INSIRA AS INFORMAÇÕES DO CONTATO ]");
        System.out.print("Nome: ");
        String nome = userInput.nextLine().toUpperCase().trim();
        while(!Validacao.validarNome(nome)){
            System.out.println("Nome inválido! Tente novamente: ");
            nome = userInput.nextLine().toUpperCase().trim();
        }
        Contato contato;

        System.out.println("Tipo de Contato -- [1] Telefone [2] E-mail");
        int tipo = Integer.parseInt(userInput.nextLine());
        if(tipo == 1){
            System.out.print("Telefone: ");
            String telefone = userInput.nextLine();
            while(!Validacao.validarTelefone(telefone)){
                System.out.println("Número inválido! Tente novamente: ");
                telefone = userInput.nextLine();
            }
            while(Validacao.telefoneExists(agenda.getListaContatos(), telefone)){
                System.out.println("Número de telefone já cadastrado! Tente novamente: ");
                telefone = userInput.nextLine();
            }
            contato = new ContatoTelefonico(nome, telefone);

            agenda.cadastrarContato(contato);
            System.out.print("Contato cadastrado com sucesso!\n\n");
        }else{
            System.out.print("E-mail: ");
            String email = userInput.nextLine();
            while(!Validacao.isValidEmail(email)){
                System.out.println("E-mail inválido! Tente novamente: ");
                email = userInput.nextLine();
            }
            while(Validacao.emailExists(agenda.getListaContatos(), email)){
                System.out.println("E-mail já cadastrado! Tente novamente:");
                email = userInput.nextLine();
            }
            contato = new ContatoEmail(nome, email);
            agenda.cadastrarContato(contato);
            System.out.println("Contato de e-mail cadastrado com sucesso!\n\n");
        }
    }

    public static void exibirContatos(){
        agenda.listarContatos();
    }

    public static void exibirRelatorio(){
        agenda.relatorioContatos();
    }

    public static void buscarContato(Scanner userInput){
        System.out.print("Insira o nome do contato que deseja procurar: ");
        String nome = userInput.nextLine().toUpperCase();

        List<Contato> contatosEncontrados = agenda.buscarContatosPorNome(agenda.getListaContatos(), nome);
        if(contatosEncontrados != null && !contatosEncontrados.isEmpty()){
            System.out.println("[ CONTATOS ENCONTRADOS ]");
            for(Contato contato : contatosEncontrados){
                System.out.println(contato);
            }
        }else{
            System.out.println("Nenhum contato encontrado.");
        }
    }

    public static void exibirModificarContato(Scanner userInput){

        System.out.println("[ MENU DE ALTERAÇÃO ]");
        System.out.print("Insira o nome do contato que deseja alterar: \n");
        String nome = userInput.nextLine().toUpperCase().trim();

        List<Contato> contatosEncontrados = agenda.buscarContatosPorNome(agenda.getListaContatos(), nome);

        if(contatosEncontrados.isEmpty()){
            System.out.println("Nenhum contato cadastrado na agenda!");
            return;
        }

        Contato contato;

        if(contatosEncontrados.size() == 1){
            contato = contatosEncontrados.getFirst();
        }else{
            System.out.print("Foram encontrados mais de um contato com o mesmo nome. Insira o ID correspondente para escolher qual modificar: ");

            for(Contato contatoEnc : contatosEncontrados){
                System.out.println(contatoEnc);
            }

            int id = Integer.parseInt(userInput.nextLine());
            contato = agenda.buscarContatoEncontradoPorId(contatosEncontrados, id);

            if(contato == null){
                System.out.println("Contato não encontrado!");
                return;
            }else{
                System.out.println("Contato escolhido: " + contato);
            }
        }

        System.out.println("CONTATO: " + contato);
        System.out.println("[1] Nome \n[2] Dado do contato \n[3] Ambos");
        int opcao = Integer.parseInt(userInput.nextLine());

        String novoNome = null;
        String novoDado = null;

        switch (opcao){
            case 1:
                System.out.println("Novo nome: ");
                novoNome = userInput.nextLine();
                break;
            case 2:
                System.out.println("Novo dado (Telefone ou E-mail): ");
                novoDado = userInput.nextLine();
                break;
            case 3:
                System.out.println("Novo nome: ");
                novoNome = userInput.nextLine();

                System.out.println("Novo dado (Telefone ou E-mail): ");
                novoDado = userInput.nextLine();
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }
        agenda.modificarContato(contato, opcao, novoNome, novoDado);
    }
}

