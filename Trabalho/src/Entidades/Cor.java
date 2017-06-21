package Entidades;

public enum Cor {
    AMARELO(0, "Amarelo",  "AM"),
    AZUL(1, "Azul", "AZ"),
    LARANJA(2, "Laranja", "LA"),
    ROSA(3, "Rosa", "RS"),
    ROXO(4, "Roxo", "RX"),
    VERDE(5, "Verde", "VE");
    
    public int index;
    public String nome;
    public String inicial;
    
    Cor(int index, String nome, String inicial) {
        this.index = index;
        this.nome = nome;
        this.inicial = inicial;
    }
        
    public Cor fromOrdinal(int index) {
        for(Cor cor: Cor.values()) {
            if(cor.index == index)
                return cor;        
        }
        return null;
    }
    
    public int toOrdinal(Cor cor) {
        for(Cor c: Cor.values()) {
            if(c.equals(cor))
                return c.index;
                
        }
        return -1;
    }    
};