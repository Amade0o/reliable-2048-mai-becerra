package ar.edu.unrc.game2048.RandomNumGeneration;

public class MathRandom implements IRandom{
    public MathRandom(){

    }

    public int nextRandom(){
        int value = (int) (Math.random() * 100);
        return value;
    }
}
