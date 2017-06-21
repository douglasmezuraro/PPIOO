package Entidades;

import java.util.Random;

public abstract class Exercito {

    public Exercito() {

    }
    
    public int combater() {
        Random r = new Random();
        return r.nextInt(6) + 1;
    }
}
