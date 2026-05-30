import java.util.Scanner;
import entities.Contato;
import entities.ContatoEmail;
import entities.ContatoTelefonico;
import service.Agenda;
import util.Validacao;

public class Main {
    private static int contagemID = 1;
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
            while(Validacao.verificarTelefone(agenda.getListaContatos(), telefone)){
                System.out.println("Número de telefone já cadastrado! Tente novamente: ");
                telefone = userInput.nextLine();
            }
            contato = new ContatoTelefonico(nome, contagemID++ , telefone);

            agenda.cadastrarContato(contato);
            System.out.print("Contato cadastrado com sucesso!\n\n");
        }else{
            System.out.print("E-mail: ");
            String email = userInput.nextLine();
            while(Validacao.verificarEmail(agenda.getListaContatos(), email)){
                System.out.println("Email já cadastrado! Tente novamente: ");
                email = userInput.nextLine();
            }
            contato = new ContatoEmail(nome, contagemID++, email);

            agenda.cadastrarContato(contato);
            System.out.println("Contato cadastrado com sucesso!\n\n");
        }
    }

    public static void exibirContatos(){
        agenda.listarContatos();
    }

    public static void exibirRelatorio(){
        agenda.relatorioContatos();
    }
}

