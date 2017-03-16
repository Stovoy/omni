package omni.flow.scoring;

public class DistanceScore extends Score {
    private int distance;
    private int maxDistance;

    public DistanceScore(int distance, int maxDistance) {
        if (maxDistance == 0) {
            throw new AssertionError();
        }
        this.distance = distance;
        this.maxDistance = maxDistance;
    }

    @Override
    protected int compute() {
        distance = Math.abs(distance);
        if (distance > maxDistance) {
            return MIN_SCORE;
        } else {
            return toInt((maxDistance - distance) / (float) maxDistance);
        }
    }
}
