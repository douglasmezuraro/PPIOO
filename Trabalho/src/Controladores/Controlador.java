package Controladores;

import Entidades.Continente;
import Entidades.Coordenada;
import Entidades.Jogador;
import Entidades.Cor;
import Entidades.Mapa;
import Entidades.Pais;
import Entidades.Territorio;
import Entidades.Validador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import static Fronteira.Tela.printLinha;
import java.util.Arrays;

public class Controlador {
  
    /* Declarações de constantes */
    
    public static String C_MSG_DIGITE_NOVAMENTE = "Digite novamente!";
    public static int C_PRIMEIRO_DA_LISTA = 0;
    
    /* Declarações de objetos */
    
    public Jogador jogador;
    public Jogador maquina; 
    public Mapa mapa;
   
    /* Construtor do controlador */
    
    public Controlador() {
        jogador = new Jogador();
        maquina = new Jogador();
        mapa = new Mapa();
    }
    
    /* Sets úteis do controlador */
    
    public void setCorJogador(Cor cor) {
       this.jogador.setCor(cor);
    }
    
    public void setCorMaquina(Cor cor) {
        this.maquina.setCor(cor);
    }
    
    public void SetContingenteTerrestreDisponivel() {
        this.jogador.setContingenteTerrestreDisponivel(
            this.jogador.getContingenteTerrestreDisponivel() + this.getContingenteTerrestre(this.jogador.getCor())
        );
        
        this.maquina.setContingenteTerrestreDisponivel(
            this.maquina.getContingenteTerrestreDisponivel() + this.getContingenteTerrestre(this.maquina.getCor())
        );
    }   
    
    /* Gets úteis do controlador */
    
    public Pais getPais(String stringPais) {
        try {
            return Pais.byName(stringPais);
        } catch(IllegalArgumentException e) {
            System.err.println("País não encontrado. " + this.C_MSG_DIGITE_NOVAMENTE);
            return null;
        }           
    }
    
    public Coordenada getCoordenadaPais(Pais pais) {   
    // Esse método tem como objetivo retornar a coordenada do país que
    // é passado como parâmetro no matriz.
        
        for(int i = 0; i < mapa.NUMERO_COLUNA; i++){
            for(int j = 0; j < mapa.NUMERO_LINHA; j++){
                if(!ehMar(this.mapa.matriz[j][i])){
                    if(this.mapa.matriz[j][i].getPais().equals(pais)){
                        return new Coordenada(i, j);
                    }
                }
            }
        }
        return null;
    }
    
    public int getContingenteTerrestre(Cor cor) {
        // Esse método tem como objetivo retornar a quantidade de contingente 
        // terrestre que o jogador poderá ter após cada turno. De acordo 
        // com a regra do jogo a quantidade de exércitos que um jogador deve
        // receber é: a parte inteira da divisão da quantidade de territórios 
        // que o jogaodor tem por dois.
        
        int qtdTerritorios = 0;
        
        for(int i = 0; i < mapa.NUMERO_COLUNA; i++) {
            for(int j = 0; j < mapa.NUMERO_LINHA; j++) {
                if(!ehMar(this.mapa.matriz[j][i])){
                    if(this.mapa.matriz[j][i].getCor().equals(cor))
                        qtdTerritorios++;
                }
            }
        }
        return qtdTerritorios / 2;
    }    
    
    public int getContingenteAereo(Cor cor) {
        // Esse método tem como objetivo retornar a quantidade de contingente 
        // aereo que o jogador poderá ter após cada turno. De acordo 
        // com a regra do jogo a quantidade de exércitos que um jogador deve
        // receber é: a parte inteira da divisão da quantidade de territórios 
        // que o jogaodor tem por três.
        
        int qtdTerritorios = 0;
        
        for(int i = 0; i < mapa.NUMERO_COLUNA; i++) {
            for(int j = 0; j < mapa.NUMERO_LINHA; j++) {
                if(this.mapa.matriz[j][i] != null){
                    if(this.mapa.matriz[j][i].getCor().equals(cor))
                        qtdTerritorios++;
                }
            }
        }
        return qtdTerritorios / 3;
    } 
    
    public Territorio getTerritorio(Pais pais) {
        for(int i = 0; i < this.mapa.NUMERO_COLUNA; i++) {
            for(int j = 0; j < this.mapa.NUMERO_LINHA; j++) {
                if(!ehMar(this.mapa.matriz[j][i])) {
                    if(this.mapa.matriz[j][i].getPais().equals(pais)) 
                        return this.mapa.matriz[j][i]; 
                }
            }
        }
        return null;
    }
    
    public int getQuantidadeDeDadosDeAtaque(Pais pais) {
        int tamanho = 0, qtd = 0;
        for(int i = 0; i < this.mapa.NUMERO_COLUNA; i++) {
            for(int j = 0; j < this.mapa.NUMERO_LINHA; j++) {
                if(!ehMar(this.mapa.matriz[j][i])) {
                    if(this.mapa.matriz[j][i].getPais().equals(pais)) {
                        tamanho = this.mapa.matriz[j][i].getTerrestres().size();

                        if(tamanho >= 4)
                            qtd = 3;
                        else if (tamanho >= 3)
                            qtd = 2;
                        else if (tamanho >= 2)
                            qtd = 1;
                        
                        return qtd;
                    }
                }
            }
        }
        return 0;
    }
    
    public int getQuantidadeDeDadosDeDefesa(Pais pais) {
        int tamanho = 0, qtd = 0;
        for(int i = 0; i < this.mapa.NUMERO_COLUNA; i++) {
            for(int j = 0; j < this.mapa.NUMERO_LINHA; j++) {
                if(!ehMar(this.mapa.matriz[j][i])) {
                    if(this.mapa.matriz[j][i].getPais().equals(pais)) {
                        tamanho = this.mapa.matriz[j][i].getTerrestres().size();

                        if(tamanho >= 4)
                            qtd = 3;
                        else if (tamanho == 3)
                            qtd = 3;
                        else if (tamanho == 2)
                            qtd = 2;
                        else 
                            qtd = 1;
                        
                        return qtd;
                    }
                }
            }
        }
        return 0;
    }
        
    public int getContingenteTerrestrePorPais(Pais pais) {
        Territorio territorio = this.getTerritorio(pais);
        return territorio.getTerrestres().size();
    }    
    
    // Retorna um paíss aleatório para máquina
    private Pais getPaisAleatorio() {
        List<Pais> lista = new ArrayList<>();
        
        for(int i = 0; i < this.mapa.NUMERO_COLUNA; i++) {
            for(int j = 0; j < this.mapa.NUMERO_LINHA; j++) {
                if(!ehMar(this.mapa.matriz[j][i])) {
                    if(this.mapa.matriz[j][i].getCor().equals(this.maquina.getCor())){
                        lista.add(this.mapa.matriz[j][i].getPais());                      
                    }
                }
            }
        }
        
        Collections.shuffle(lista);
        return lista.get(C_PRIMEIRO_DA_LISTA);
    }
    
    // Retorna um páis inimigo aleatório para máquina atacar que faz fronteira com o pais escolhido anteriomente
    private Pais getPaisInimigoAleatorio(Pais paisInimigo) {
        List<Pais> lista = new ArrayList<>();
        
        for(int i = 0; i < this.mapa.NUMERO_COLUNA; i++) {
            for(int j = 0; j < this.mapa.NUMERO_LINHA; j++) {
                if(!ehMar(this.mapa.matriz[j][i])) {
                    if(!this.mapa.matriz[j][i].getCor().equals(this.maquina.getCor())) {
                        if(fazFronteira(this.mapa.matriz[j][i].getPais(), paisInimigo)) {
                            lista.add(this.mapa.matriz[j][i].getPais());                      
                        }
                    }
                }
            }
        }
        
        if(lista.size() > 0) {
            Collections.shuffle(lista);
            return lista.get(C_PRIMEIRO_DA_LISTA);
        }
        else return null;
    }  
    
    // Retorna uma lista de territórios do jogador que chamá-la
    public List<Territorio> getListaTerritorio(Cor cor) {
        List<Territorio> lista = new ArrayList<>();
        
        for(int i = 0; i < this.mapa.NUMERO_COLUNA; i++){
            for(int j = 0; j < this.mapa.NUMERO_LINHA; j++){
                if(!ehMar(this.mapa.matriz[j][i])){
                    if(this.mapa.matriz[j][i].getCor().equals(cor)){                       
                        lista.add(this.mapa.matriz[j][i]);
                    }
                }
            }
        }
        
        return lista;
    }
    
    // Retorna uma lista de territórios inimigos para o jogador que chamá-la
    public List<Territorio> getListaTerritoriosInimigosQueFazemFronteira(Cor cor, Pais pais) {
        List<Territorio> lista = new ArrayList<>();
        
        for(int i = 0; i < this.mapa.NUMERO_COLUNA; i++) {
            for(int j = 0; j < this.mapa.NUMERO_LINHA; j++) {
                if(!this.ehMar(this.mapa.matriz[j][i])) {
                    if(this.fazFronteira(pais, this.mapa.matriz[j][i].getPais())) {
                        if(this.ehInimigo(cor, this.mapa.matriz[j][i].getCor())) {
                            lista.add(this.mapa.matriz[j][i]);                      
                        }
                    }                     
                }
            }   
        }
        
        return lista;
    }            
    
    // Retorna uma lista de cores que ainda não foram escolhidas
    public List<Cor> getListaCoresDisponiveis() {
        List<Cor> lista = new ArrayList<>();

        lista.addAll(Arrays.asList(Cor.values()));
       
        if(this.jogador.getCor() != null)
            lista.remove(this.jogador.getCor());
       
        if(this.maquina.getCor() != null)
            lista.remove(this.maquina.getCor());
        
        return lista;
    }
    
    /* Verificações utéis */
    
    //
    public boolean continuarAtacandoAutomaticamente() {
        Random r = new Random();
        return r.nextBoolean();
    }
    
    // Verifica se o Território passado como parâmetro é mar
    private boolean ehMar(Territorio territorio) {
        return territorio == null;
    }        
    
    // Verifica se os dois países passados como parâmetros fazem fronteira
    public boolean fazFronteira(Pais a, Pais b) {
    // Esse método verifica se o país "a" e o país "b" que são passados
    // como parâmetros são vizinhos de acordo com o matriz da especificação
    // do jogo.
       
       Coordenada ca, cb;
       boolean fronteiraEixoX, fronteiraEixoY; 
       
       ca = this.getCoordenadaPais(a);
       cb = this.getCoordenadaPais(b);
       
       fronteiraEixoX = ((ca.x + 1 == cb.x) || (ca.x == cb.x + 1)) && (ca.y == cb.y);
       fronteiraEixoY = (ca.x == cb.x) && ((ca.y + 1 == cb.y) || (ca.y == cb.y + 1));
       
       if((ca.x == 0 && cb.x== 7) || (cb.x == 0 && ca.x== 7)){ //Verfica se os paises q estão nas extremidades 
           if(ca.y == cb.y){ //da matriz podem se atacar
              fronteiraEixoX = true;
           }
       }
       
        /*if(fronteiraEixoX || fronteiraEixoY){
            System.out.printf("Deu certoooo");
        }*/ //Algoritmo para jogadorNaoGanhou
       return fronteiraEixoX || fronteiraEixoY;
    }
    
    // Verifica se tem pelo menos dois territorios para que possa atacar
    public boolean possuiContingenteMinimoParaAtacar(Pais pais) {
        Territorio territorio = this.getTerritorio(pais);        
        return territorio.getTerrestres().size() > 1;
    }       
    
    // Verifica se restou ao menos um terrestre no território para que possa atacar
    public boolean possuiContingenteRestanteNoTerritorio(Pais pais, int quantidade) {
        Territorio territorio = this.getTerritorio(pais);     
        return (territorio.getTerrestres().size() - quantidade) >= 1;
        
    }
    
    // Verifica se o país é inimigo
    private boolean ehInimigo(Cor corDoPaisAmigo, Cor corDoPaisInimigo) {
        return corDoPaisAmigo != corDoPaisInimigo;
    } 
    
    // Verifica se o jogador ou a maquina atingiram os requisitos necessarios para ganhar
    public boolean jogadorNaoGanhou(Jogador jogador) {
        int tr = 0, countA = 0, countB = 0, countC = 0, countD = 0, countE = 0, countF = 0;
        
        for(int i = 0; i < this.mapa.NUMERO_COLUNA; i++) {
            for(int j = 0; j < this.mapa.NUMERO_LINHA; j++) {
                if(!ehMar(this.mapa.matriz[j][i])) {
                    if(this.mapa.matriz[j][i].getContinente().equals(Continente.AFRICA)){
                        if(this.mapa.matriz[j][i].getCor().equals(jogador.getCor())){
                            countA++;
                        }
                    }
                    
                    else if(this.mapa.matriz[j][i].getContinente().equals(Continente.OCEANIA)){
                        if(this.mapa.matriz[j][i].getCor().equals(jogador.getCor())){
                            countB++;
                        }
                    }
                    else if(this.mapa.matriz[j][i].getContinente().equals(Continente.EUROPA)){
                        if(this.mapa.matriz[j][i].getCor().equals(jogador.getCor())){
                            countC++;
                        }
                    }
                    
                    else if(this.mapa.matriz[j][i].getContinente().equals(Continente.AMERICA_DO_NORTE)){
                        if(this.mapa.matriz[j][i].getCor().equals(jogador.getCor())){
                            countD++;
                        }
                    }
                    
                    else if(this.mapa.matriz[j][i].getContinente().equals(Continente.AMERICA_DO_SUL)){
                        if(this.mapa.matriz[j][i].getCor().equals(jogador.getCor())){
                            countE++;
                        }
                    }
                    
                    else if(this.mapa.matriz[j][i].getContinente().equals(Continente.ASIA)){
                        if(this.mapa.matriz[j][i].getCor().equals(jogador.getCor())){
                            countF++;
                        }
                    }
                }
            }
        }
        if(countA == this.mapa.COUNT_AFRICA){
            System.out.println("O "+ jogador.getCor() +" conquistou o continente AFRICANO!");
            tr++;
        }
        if(countB == this.mapa.COUNT_OCEANIA){
            System.out.println("O "+ jogador.getCor() +" conquistou o continente OCEANICO!");
            tr++;
        }
        if(countC == this.mapa.COUNT_EUROPA){
            System.out.println("O "+ jogador.getCor() +" conquistou o continente EUROPEU!");
            tr++;
        }
        if(countD == this.mapa.COUNT_AMERICA_DO_NORTE){
            System.out.println("O "+ jogador.getCor() +" conquistou o continente NORTE AMERICADO!");
            tr++;
        }
        if(countE == this.mapa.COUNT_AMERICA_DO_SUL){
            System.out.println("O "+ jogador.getCor() +" conquistou o continente SUL AMERICANO!");
            tr++;
        }
        if(countF == this.mapa.COUNT_ASIA){
            System.out.println("O "+ jogador.getCor() +" conquistou o continente ASIATICO!");
            tr++;
        }
        if(tr >= 2){
            System.out.println("O "+ jogador.getCor() +" conquistou dois ou mais continentes e venceu o jogo!!!");
            return false;
        }
        return true;
    }    
    
    /* Procedimentos diversos */
    
    // Procedimento que distribui os territórios iniciais do jogo
    public void sorteiaTerritorios() {
        // Método que sorteia os territórios de acordo com as cores escolhidas
        // pelo usuário, para isso é criado uma lista de cores com as duas cores
        // que estão nos objetos "jogador" e "máquina" e então é feito um passeio
        // em cada posição da matriz onde é usado o método "shuffle" para 
        // embaralhar a lista para então atribuir o primeiro elemento cor da lista
        // na variável "cor" então setar a variável na posição da matriz.
        final int C_NUMERO_MAXIMO_DE_PAIS = 17;
        int countCorJogador = 0, countCorMaquina = 0;
        List<Cor> listaDeCores = new ArrayList<>();
        
        listaDeCores.add(this.jogador.getCor());
        listaDeCores.add(this.maquina.getCor());
        
        for(int i = 0; i < this.mapa.NUMERO_COLUNA; i++) {
            for(int j = 0; j < this.mapa.NUMERO_LINHA; j++) {
                if(countCorJogador == C_NUMERO_MAXIMO_DE_PAIS)
                    listaDeCores.remove(this.jogador.getCor());
                else if(countCorMaquina == C_NUMERO_MAXIMO_DE_PAIS)
                    listaDeCores.remove(this.maquina.getCor());
                
                Collections.shuffle(listaDeCores);                
                Cor cor = listaDeCores.get(C_PRIMEIRO_DA_LISTA);                
                
                if((cor.equals(this.jogador.getCor())) && (!ehMar(this.mapa.matriz[j][i]))) 
                    countCorJogador++;
                else if((cor.equals(this.maquina.getCor())) && (!ehMar(this.mapa.matriz[j][i])))
                    countCorMaquina++;                                              
                                            
                if(!ehMar(this.mapa.matriz[j][i]))
                    this.mapa.matriz[j][i].setCor(cor);                 
            }
        }
    }         
    
    // Procedimento que posiciona uma certa quantidade de contingente terrestre em um certo território
    public void posicionaExercitoTerrestre(Jogador jogador, Pais pais, int quantidade) {
        Coordenada c = getCoordenadaPais(pais);
        // Adiciona a quantidade de exercitos terrestres que o usuário 
        // no território do país que é passado por parâmetro.
        this.mapa.matriz[c.y][c.x].adicionaTerrestre(quantidade);
        // Diminui o contingente disponível
        jogador.setContingenteTerrestreDisponivel(
                jogador.getContingenteTerrestreDisponivel() - quantidade
        );      
    }
    
    // Procedimento que posiciona o contigente terrestre da máquina aleatóriamente
    public void posicionaExercitoTerrestreAleatoriamente() {
        printLinha();
        while(this.maquina.possuiContingenteTerrestreParaRemanejar()) {
           Pais pais = this.getPaisAleatorio();
           Random r = new Random();
           int quantidade = r.nextInt(this.getContingenteTerrestre(this.maquina.getCor())) + 1;
           Coordenada c = getCoordenadaPais(pais);
     
            if(this.mapa.matriz[c.y][c.x].getCor().equals(this.maquina.getCor())) {

                if(quantidade <= this.maquina.getContingenteTerrestreDisponivel()) {

                    this.mapa.matriz[c.y][c.x].adicionaTerrestre(quantidade);

                    this.maquina.setContingenteTerrestreDisponivel(
                            this.maquina.getContingenteTerrestreDisponivel() - quantidade
                    ); 
                    
                    System.out.println("A maquina distribui " + quantidade + " de seu exercito para " + pais.nome);             
                }
            }
        }     
    }
    
    // Remove uma certa quantidade de terrestre de um certo país
    public void removeTerrestre(Pais pais, int quantidade) {
        for(int i = 0; i < this.mapa.NUMERO_COLUNA; i++) {
            for(int j = 0; j < this.mapa.NUMERO_LINHA; j++) {
                if(!ehMar(this.mapa.matriz[j][i])) {
                    if(this.mapa.matriz[j][i].getPais().equals(pais))
                        this.mapa.matriz[j][i].removeTerrestre(quantidade);
                }
            }
        }
    }   
    
    // Obtém as informações necessárias automaticamente para efetuar um ataque terrestre
    public void atacarTerrestreAutomaticamente() {
        Pais paisAtacante, paisInimigo;
        int quantidade = 0;
        
        do {     
            // Obtém o país que irá atacar aleatóriamente
            paisAtacante = this.getPaisAleatorio();
            // Obtém o país que irá ser atacado aleatóriamentr
            paisInimigo = this.getPaisInimigoAleatorio(paisAtacante);
            // Pega o maior número possível de contingente para atacar
            quantidade = this.getQuantidadeDeDadosDeAtaque(paisAtacante);  
        } while((paisInimigo == null) || ((quantidade <= 0) || (quantidade > 3)));
        
       System.out.println("A máquina atacou seu o seu território " + paisInimigo.nome + " com o território " + paisAtacante.nome + "!");       
       
       this.atacarTerrestre(this.maquina, paisAtacante, paisInimigo, quantidade);        
    }
    
    // Método de atacar terrestre
    public void atacarTerrestre(Jogador jogador, Pais paisAtacante, Pais paisInimigo, int quantidade) {
        // TODO: Criar a classe dados para que possa ser reaproveitado no método atacar aéreo
        
        List<Integer> dadosJogador = new ArrayList<>();
        List<Integer> dadosAdversario = new ArrayList<>();
       
        int qtdDadosAtaque = this.getQuantidadeDeDadosDeAtaque(paisAtacante);
        int qtdDadosDefesa = this.getQuantidadeDeDadosDeDefesa(paisInimigo);
        
        Validador validador = validarAtaque(jogador, paisAtacante, paisInimigo, quantidade);
        
        if(validador.deuCerto()) {            
            for(int i = 0; i < qtdDadosAtaque; i++) 
                dadosJogador.add(this.getTerritorio(paisAtacante).getTerrestres().get(i).combater());

            for(int i = 0; i < qtdDadosDefesa; i++) 
                dadosAdversario.add(this.getTerritorio(paisInimigo).getTerrestres().get(i).combater());

            while(!dadosJogador.isEmpty() && !dadosAdversario.isEmpty()){
                
                // Ordena a lista do maior pro menor
                Collections.sort(dadosJogador, Collections.reverseOrder());
                Collections.sort(dadosAdversario, Collections.reverseOrder());
                
                if(dadosJogador.get(C_PRIMEIRO_DA_LISTA) > dadosAdversario.get(C_PRIMEIRO_DA_LISTA)) {
                    this.getTerritorio(paisInimigo).removeTerrestre(1);                  
                    System.out.println("Atacante ganhou! Dado atacante: " + dadosJogador.get(C_PRIMEIRO_DA_LISTA) + ", dado defensor: " + dadosAdversario.get(C_PRIMEIRO_DA_LISTA));
                    dadosAdversario.remove(C_PRIMEIRO_DA_LISTA);
                    
                    // Verifica se o inimigo que foi atacado possui contingente
                    // Se sim significa que você ganhou o território dele, senão
                    // significa que ele conseguiu defendê-lo de você.
                    if(this.getTerritorio(paisInimigo).getTerrestres().isEmpty()) {   
                        // Altera a cor do território para a cor do jogador 
                        this.getTerritorio(paisInimigo).setCor(jogador.getCor()); 
                        // Posiciona o contingente no território conquistado
                        this.getTerritorio(paisInimigo).adicionaTerrestre(quantidade);

                        System.out.println("O atacante ganhou o território!");
                    }           
                }
                else {
                    this.getTerritorio(paisAtacante).removeTerrestre(1);                    
                    System.out.println("Atacante perdeu! Dado atacante: " + dadosJogador.get(C_PRIMEIRO_DA_LISTA) + ", dado defensor: " + dadosAdversario.get(C_PRIMEIRO_DA_LISTA));
                    dadosJogador.remove(C_PRIMEIRO_DA_LISTA);    
                    quantidade--; // se perder tira do exercito atacante
                }
            }            
            
            // Se a quantidade for 0 quer dizer que todos os exercitos foram derrotados
            if(quantidade == 0) System.out.println("O território foi defendido!");
            
            printLinha();
        }
        else System.err.println(validador.getMensagem());
    }            
    
    /* Validações utéis */
    /* TODO: Passar todos os métodos validadores do deste controlador para o controladorDeValidacao */
    
    private Validador validarAtaque(Jogador jogador, Pais paisAtacante, Pais paisInimigo, int quantidade) {
        final int C_NUMERO_MAXIMO_DE_ATACANTES = 3;
        String mensagemDeErro = "";
        
        Validador validador = new Validador(false, mensagemDeErro);
        
        if(jogador.getCor().equals(this.getTerritorio(paisAtacante).getCor())) {
            if(jogador.getCor() != this.getTerritorio(paisInimigo).getCor()) {
                if(this.fazFronteira(paisAtacante, paisInimigo)) {
                    if(this.possuiContingenteMinimoParaAtacar(paisAtacante)) {
                        if(quantidade <= C_NUMERO_MAXIMO_DE_ATACANTES) {
                            if(this.possuiContingenteRestanteNoTerritorio(paisAtacante, quantidade)) {
                                validador.setResultado(true);
                            } else mensagemDeErro = "É necessário que fique ao menos um exército terrestre no país atacante!";
                        } else mensagemDeErro = "O numéro máximo de exércitos que podem atacar é " + C_NUMERO_MAXIMO_DE_ATACANTES + "!";
                    } else mensagemDeErro = "É necessário ter ao menos dois exércitos terrestres para poder atacar!";
                } else mensagemDeErro = "Os países precisam ser vizinhos para usar o ataque terrestre!";                
            } else mensagemDeErro = "Não se pode atacar o próprio território!"; 
        } else mensagemDeErro = "Você só pode atacar com um país que lhe pertence!";

        validador.setMensagem(mensagemDeErro);
        return validador;
    }
        
    public Validador validaPosicionarExercitoTerrestre(Jogador jogador, Pais pais, int quantidade) {
        Coordenada c = getCoordenadaPais(pais);
        // Verifica se a cor do território é a mesma cor do jogador.
        if(this.mapa.matriz[c.y][c.x].getCor().equals(jogador.getCor())) {
            // Verifica se o jogador tem o contingente disponível.
            if(quantidade <= jogador.getContingenteTerrestreDisponivel()) {
                return new Validador(true, "");
            }
            else return new Validador(false, "A quantidade que o jogador digitou é maior que a disponível. Digite novamente.");
        }
        else return new Validador(false, "O país escolhido não lhe pertence! Digite novamente.");     
    }

}
