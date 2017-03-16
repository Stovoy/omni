package omni.flow.scoring;

public abstract class Score {
    public static Score min() {
        return new Score() {
            @Override
            protected int compute() {
                return MIN_SCORE;
            }
        };
    }

    public static Score max() {
        return new Score() {
            @Override
            protected int compute() {
                return MAX_SCORE;
            }
        };
    }

    private Integer score = null;

    protected final static int MIN_SCORE = 0;
    protected final static int MAX_SCORE = 1000;

    protected int toInt(float score) {
        return (int) (score * 1000);
    }

    public int get() {
        if (score == null) {
            score = compute();
        }
        return score;
    }

    protected abstract int compute();
}
