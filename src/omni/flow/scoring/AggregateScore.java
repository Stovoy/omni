package omni.flow.scoring;

public class AggregateScore extends Score {
    private int aggregate = 0;

    public void add(Score other) {
        aggregate += other.get();
    }

    @Override
    protected int compute() {
        return aggregate;
    }
}
