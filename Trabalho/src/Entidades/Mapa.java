package Entidades;

public class Mapa {
       
    final public int NUMERO_LINHA = 5;
    final public int NUMERO_COLUNA = 8;
    final public Territorio[][] matriz = new Territorio[NUMERO_LINHA][NUMERO_COLUNA];   

    final public int COUNT_AMERICA_DO_NORTE = 7;
    final public int COUNT_AMERICA_DO_SUL = 4;
    final public int COUNT_EUROPA = 5;
    final public int COUNT_AFRICA = 6;
    final public int COUNT_ASIA = 7;
    final public int COUNT_OCEANIA = 4;

    public Mapa() {
        this.createTerritorios();
        this.setTerritorios();
        this.setContinentes();
        this.adicionaExercitosIniciais();
    }    
    
    private void createTerritorios() {
        // Método que percorre toda a matriz e instância um objeto "Territorio" 
        // em cada posição da matriz.
        
        for(int i = 0; i < this.NUMERO_COLUNA; i++){
            for(int j = 0; j < this.NUMERO_LINHA; j++){
                this.matriz[j][i] = new Territorio();
            }
        }            
    }
    
    private void setTerritorios() {
        // Primeira linha
        this.matriz[0][0].setPais(Pais.ALASCA);
        this.matriz[0][1].setPais(Pais.VANCOUVER);
        this.matriz[0][2].setPais(Pais.GROELANDIA);
        this.matriz[0][3].setPais(Pais.INGLATERRA);
        this.matriz[0][4].setPais(Pais.ITALIA);
        this.matriz[0][5].setPais(Pais.SUECIA);
        this.matriz[0][6] = null;
        this.matriz[0][7].setPais(Pais.VLADIVOSTOK);
        // Segunda linha
        this.matriz[1][0] = null;
        this.matriz[1][1].setPais(Pais.CALIFORNIA);
        this.matriz[1][2].setPais(Pais.OTAWA);
        this.matriz[1][3] = null;
        this.matriz[1][4].setPais(Pais.ALEMANHA);
        this.matriz[1][5].setPais(Pais.MOSCOU);
        this.matriz[1][6].setPais(Pais.OMSK);
        this.matriz[1][7].setPais(Pais.SIBERIA);
        // Terceira linha
        this.matriz[2][0] = null;
        this.matriz[2][1].setPais(Pais.MEXICO);
        this.matriz[2][2].setPais(Pais.NOVA_YORK);
        this.matriz[2][3].setPais(Pais.NIGERIA);
        this.matriz[2][4].setPais(Pais.EGITO);
        this.matriz[2][5].setPais(Pais.ORIENTE_MEDIO);
        this.matriz[2][6].setPais(Pais.INDIA);
        this.matriz[2][7].setPais(Pais.CHINA);
        // Quarta linha
        this.matriz[3][0].setPais(Pais.CHILE);
        this.matriz[3][1].setPais(Pais.COLOMBIA);
        this.matriz[3][2] = null;
        this.matriz[3][3].setPais(Pais.CONGO);
        this.matriz[3][4].setPais(Pais.SUDAO);
        this.matriz[3][5].setPais(Pais.SUMATRA);
        this.matriz[3][6].setPais(Pais.BORNEU);
        this.matriz[3][7].setPais(Pais.JAPAO);
        // Quinta linha
        this.matriz[4][0].setPais(Pais.ARGENTINA);
        this.matriz[4][1].setPais(Pais.BRASIL);
        this.matriz[4][2] = null;
        this.matriz[4][3].setPais(Pais.AFRICA_DO_SUL);
        this.matriz[4][4].setPais(Pais.MADAGASCAR);
        this.matriz[4][5] = null;
        this.matriz[4][6].setPais(Pais.AUSTRALIA);
        this.matriz[4][7].setPais(Pais.NOVA_GUINE);          
    }    
   
    private void setContinentes() {
        // Método que vincula os continentes em cada território de acordo
        // com seu país.
        
        for(int i = 0; i < this.NUMERO_COLUNA; i++) {
            for(int j = 0; j < this.NUMERO_LINHA; j++) {
                if(this.matriz[j][i] != null)
                    this.matriz[j][i].setContinente(this.matriz[j][i].getPais().continente);                            
            }
        }   
    }
    
    private void adicionaExercitosIniciais() {
        // Método que tem como objetivo ocupar todo território do jogo        
        // inicialmente com um exercito aereo e um exercito terrestre.
        
        for(int i = 0; i < this.NUMERO_COLUNA; i++) {
            for(int j = 0; j < this.NUMERO_LINHA; j++) {
                if(this.matriz[j][i] != null) {
                    this.matriz[j][i].adicionaTerrestre(1);
                    this.matriz[j][i].adicionaAereo(1);
                }
            }
        }
    }
    
    public void mostrarMapa(){
        System.out.print("|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
        for(int i = 0; i < this.NUMERO_LINHA; i++) {
            for(int j = 0; j < this.NUMERO_COLUNA; j++) {
                if(this.matriz[i][j] != null) 
                    System.out.print("| " + getNomeFormatado(this.matriz[i][j].getPais()) + " - " + this.matriz[i][j].getCor().inicial + " "); 
                else
                    System.out.print("|                    ");
            }
            System.out.println("|");
            System.out.print("|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
        }        
    }       
    
    private String getNomeFormatado(Pais p) {
        final int C_LENGTH_DO_MAIOR_PAIS = 13;
        String nome = p.nome;
        
        while(nome.length() < C_LENGTH_DO_MAIOR_PAIS)
            nome = nome + " ";
        
        return nome;        
    }
    
     
}
