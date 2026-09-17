package ar.edu.unrc.game2048.RandomNumGeneration;
import java.util.ArrayList;
import java.util.List;

public class DeterministicRandom implements IRandom{
    private final List<Integer> values;
    private int nextIndex;

    public DeterministicRandom(List<Integer> values){
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Random values must not be empty");
        }
        this.values = new ArrayList<>(values);
        this.nextIndex = 0;
    }

    public int nextRandom(){
        int value = values.get(nextIndex);
        nextIndex = (nextIndex + 1) % values.size();
        return value;
    }
}
