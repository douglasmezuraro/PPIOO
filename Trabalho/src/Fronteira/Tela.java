package Fronteira;

import Controladores.Controlador;
import Controladores.ControladorDeValidacao;
import Entidades.Cor;
import Entidades.Jogador;
import Entidades.Pais;
import Entidades.Territorio;
import Entidades.Validador;
import java.util.Scanner;

public class Tela {
    
    // Objetos utéis 
    
    public static Controlador controlador = new Controlador();
    public static ControladorDeValidacao controladorDeValidacao = new ControladorDeValidacao();
    public static Scanner scanner = new Scanner(System.in);
    
    // Métodos printáveis utéis
    
    public static void printLinha() {
        System.out.println("_________________________________________________________________________________________________________________________________________________________________________\n");
    }
    
    public static void printTelaInicial() {
        System.out.println("Bem vindo ao jogo WAR!");
    }
    
    public static void printDificuldades() {
        Validador v;
        String aux;
                
        System.out.println("Escolha uma dificuldade: " +
                           "\n1 - Fácil              " +
                           "\n2 - Médio              " +
                           "\n3 - Difícil            " );
        do {          
            aux = scanner.nextLine();
            v = controladorDeValidacao.validaDificuldade(aux);
            if(v.deuErro()) System.err.println(v.getMensagem());
        } while(v.deuErro());          
    }
    
    public static void printTerritorios(Jogador jogador) {
        System.out.println(
                "Territórios pertencentes ao jogador " + 
                jogador.getCor().nome + 
                ":"
        );
        
        for(Territorio t: controlador.getListaTerritorio(jogador.getCor())) {
            System.out.println("  * " + t.getPais().nome);
        }
    }

    public static void printTerritoriosInimigosQueFazemFronteira(Jogador jogador, Pais pais) {
        System.out.println(
                "Territórios inimigos que fazem fronteira com " + 
                pais.nome + 
                ":"
        );
        
        for(Territorio t: controlador.getListaTerritoriosInimigosQueFazemFronteira(jogador.getCor(), pais)) {
            System.out.println("  * " + t.getPais().nome);
        }
    }
    
    public static void printContingente(Jogador jogador) {
        System.out.println(
            "Exércitos terrestres disponíveis para o jogador " + 
            jogador.getCor().nome + 
            ": " + 
            jogador.getContingenteTerrestreDisponivel()                
        );
    }
    
    public static void printContingente(Pais pais) {
        System.out.println(
            "Exércitos terrestres disponíveis para o jogador no pais " + 
            pais.nome + 
            ": " + 
            controlador.getContingenteTerrestrePorPais(pais)
        );
    }
    
    public static void printJogar(Jogador jogador) {
        if(jogador.equals(controlador.jogador)) {
            printLinha();
            System.out.println("Vez do jogador");
            printLinha();
        }
        else {
            printLinha();
            System.out.println("Vez da máquina");
            printLinha();
        }
    }
    
    public static void printCoresDisponiveis() {
        for(Cor c: controlador.getListaCoresDisponiveis()) 
            System.out.println("  * " + c.nome);
    }
    
    public static void esperaTecla() {
        System.out.println("Digite alguma tecla para continuar...");
        scanner.nextLine();        
    }
    
    // Métodos utéis
      
    public static void escolheCorJogador() { 
        Validador v;
        String aux;
                
        System.out.println("Escolha uma cor para o jogador:");
        printCoresDisponiveis();
        
        do {          
            aux = scanner.nextLine();
            v = controladorDeValidacao.validaCor(aux);
            if(v.deuErro()) System.err.println(v.getMensagem());
        } while(v.deuErro());  
        
        controlador.setCorJogador(Cor.valueOf(aux.toUpperCase()));
    }
    
    public static void escolheCorMaquina() {
        Validador v;    
        String aux;
                
        System.out.println("Escolha uma cor para a máquina:");
        printCoresDisponiveis();
        
        do {          
            aux = scanner.nextLine();
            v = controladorDeValidacao.validaCor(aux);
            if(v.deuErro()) System.err.println(v.getMensagem());
        } while(v.deuErro());  
        
        controlador.setCorMaquina(Cor.valueOf(aux.toUpperCase()));
    } 
    
    public static Pais escolhePais() {
        Validador v;
        String aux;
                
        System.out.println("Escolha um país:");
        do {          
            aux = scanner.nextLine();
            v = controladorDeValidacao.validaPais(aux);
            if(v.deuErro()) System.err.println(v.getMensagem());
        } while(v.deuErro());  
        
        return Pais.byName(aux);
    }
    
    public static int getQuantidade() {
        Validador v;
        String aux;
                
        System.out.println("Digite a quantidade:");
        do {          
            aux = scanner.nextLine();
            v = controladorDeValidacao.validaQuantidade(aux);
            if(v.deuErro()) System.err.println(v.getMensagem());
        } while(v.deuErro());  
        
        return Integer.valueOf(aux);
    }    

    public static boolean desejarJogar() {                
        Validador v;
        String aux;
                        
        System.out.println("Deseja jogar(0: Não/1: Sim)? ");
        do {          
            aux = scanner.nextLine();
            v = controladorDeValidacao.validaContinuarJogando(aux);
            if(v.deuErro()) System.err.println(v.getMensagem());
        } while(v.deuErro());  
        
        return Integer.valueOf(aux) == 1;
    }
    
    // Definições e regras gerais
    
    public static void posicionaExercitoTerrestre(Jogador jogador) {   
        Validador v;
        Pais pais;
        int quantidade;

        do {
            pais = escolhePais();
            quantidade = getQuantidade();
            v = controlador.validaPosicionarExercitoTerrestre(jogador, pais, quantidade);   
            if(v.deuErro()) System.err.println(v.getMensagem());
        } while(v.deuErro());  
        
        controlador.posicionaExercitoTerrestre(jogador, pais, quantidade);                              
    }
    
    public static void prepararJogador() {
        printTerritorios(controlador.jogador);
        printLinha();      
        
        while(controlador.jogador.possuiContingenteTerrestreParaRemanejar()) {
            printContingente(controlador.jogador);
            
            printLinha();
            
            posicionaExercitoTerrestre(controlador.jogador);
        } 
    }
    
    public static void prepararMaquina() {
        controlador.posicionaExercitoTerrestreAleatoriamente();
    }
    
    public static void jogarManual() {
        printJogar(controlador.jogador);
        
        controlador.mapa.mostrarMapa();
        
        while(desejarJogar()) {
            Pais pais = escolhePais();
            int quantidade;

            printTerritoriosInimigosQueFazemFronteira(controlador.jogador, pais);
            printLinha();

            Pais paisInimigo = escolhePais();
            printLinha();

            printContingente(pais);       
            printContingente(paisInimigo);
            printLinha();

            System.out.print("Com quantos exercitos deseja atacar a/o " + paisInimigo.nome + "? ");
            quantidade = getQuantidade();

            controlador.atacarTerrestre(controlador.jogador, pais, paisInimigo, quantidade);
        }
    }
   
    public static void jogarAutomatico() {
        printJogar(controlador.maquina);
        
        do {
            controlador.atacarTerrestreAutomaticamente();
        } while(controlador.continuarAtacandoAutomaticamente());          
    }
    
    // 
    
    public static void inicializar() {
        printTelaInicial();
        printLinha();
        
        printDificuldades();        
        printLinha();
        
        escolheCorJogador();
        printLinha();
        
        escolheCorMaquina();
        printLinha();
        
        controlador.sorteiaTerritorios();
        controlador.SetContingenteTerrestreDisponivel();        
    }
        
    public static void preparar() {
        prepararJogador();
        prepararMaquina();
        printLinha();
    }
    
    public static void executar() {
        final int C_PRIMEIRA_RODADA = 0;
        int contadorDeRodadas = 0;        
        
        do {
            if(contadorDeRodadas != C_PRIMEIRA_RODADA) {
                // Se for a primeira rodada precisa poscionar o contingente
                controlador.SetContingenteTerrestreDisponivel();
            }
            
            preparar();
            
            controlador.mapa.mostrarMapa();            
            
            jogarManual();
            esperaTecla();
            
            jogarAutomatico();
            esperaTecla();
            
            controlador.mapa.mostrarMapa();
            esperaTecla();
            
            contadorDeRodadas++;
            
        } while(controlador.jogadorNaoGanhou(controlador.jogador) && controlador.jogadorNaoGanhou(controlador.maquina));  
        
        System.out.println("O jogo terminou e foram realizadas " + (contadorDeRodadas + 1)  + " rodadas!");
    }
    
    public static void main(String[] args) {    
        inicializar();
        executar();
    }
    
}
