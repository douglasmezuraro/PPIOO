package Entidades;

public enum Pais {      
    ALASCA(0, "Alasca", Continente.AMERICA_DO_NORTE),
    VANCOUVER(1, "Vancouver", Continente.AMERICA_DO_NORTE),
    GROELANDIA(2, "Groelandia", Continente.AMERICA_DO_NORTE),
    INGLATERRA(3, "Inglaterra", Continente.EUROPA),
    ITALIA(4, "Italia", Continente.EUROPA),
    SUECIA(5, "Suecia", Continente.EUROPA),
    VLADIVOSTOK(6, "Vladivostok", Continente.ASIA),
    CALIFORNIA(7, "California", Continente.AMERICA_DO_NORTE),
    OTAWA(8, "Otawa", Continente.AMERICA_DO_NORTE),
    ALEMANHA(9, "Alemanha", Continente.EUROPA),
    MOSCOU(10, "Moscou", Continente.EUROPA),
    OMSK(11, "Omsk", Continente.ASIA),
    SIBERIA(12, "Siberia", Continente.ASIA),
    MEXICO(13, "Mexico", Continente.AMERICA_DO_NORTE),
    NOVA_YORK(14, "Nova_York", Continente.AMERICA_DO_NORTE),
    NIGERIA(15, "Nigeria", Continente.AFRICA),
    EGITO(16, "Egito", Continente.AFRICA),
    ORIENTE_MEDIO(17, "Oriente_Medio", Continente.ASIA),
    INDIA(18, "India", Continente.ASIA),
    CHINA(19, "China", Continente.ASIA),
    CHILE(20, "Chile", Continente.AMERICA_DO_SUL),
    COLOMBIA(21, "Colombia", Continente.AMERICA_DO_SUL),
    CONGO(22, "Congo", Continente.AFRICA),
    SUDAO(23, "Sudao", Continente.AFRICA),
    SUMATRA(24, "Sumatra", Continente.OCEANIA),
    BORNEU(25, "Borneu", Continente.OCEANIA),
    JAPAO(26, "Japao", Continente.ASIA),
    ARGENTINA(27, "Argentina", Continente.AMERICA_DO_SUL),
    BRASIL(28, "Brasil", Continente.AMERICA_DO_SUL),
    AFRICA_DO_SUL(29, "Africa_Do_Sul", Continente.AFRICA),
    MADAGASCAR(30, "Madagascar", Continente.AFRICA),
    AUSTRALIA(31, "Australia", Continente.OCEANIA),
    NOVA_GUINE(32, "Nova_Guine", Continente.OCEANIA);
    
    public int index;
    public String nome;
    public Continente continente;
    
    Pais(int index, String nome, Continente continente) {
        this.index = index;
        this.nome = nome;
        this.continente = continente;
    }

    static public Pais fromOrdinal(int index) {
        for(Pais pais: Pais.values()) {
            if(pais.index == index)
                return pais;        
        }
        return null;
    }
    
    static public int toOrdinal(Pais pais) {
        for(Pais p: Pais.values()) {
            if(p.equals(pais))
                return p.index;
                
        }
        return -1;
    }     
    
    static public Pais byName(String name) throws IllegalArgumentException {
        for(Pais p: Pais.values()) {
            if(p.nome.equalsIgnoreCase(name)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Páis não encontrado!");
    }
    
}
