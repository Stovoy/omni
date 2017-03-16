package omni.flow;

import omni.flow.scoring.Score;

import java.util.ArrayList;
import java.util.List;

public class Potential implements Comparable<Potential> {
    private Score score;
    private Action action;
    private List<Resource> utilizedResources;

    public Potential(Score score, Action action) {
        this.score = score;
        this.action = action;
        utilizedResources = new ArrayList<>();
    }

    public Potential(Score score, Action action, Resource utilizedResource) {
        this(score, action);
        utilizedResources.add(utilizedResource);
    }

    public void addUtilizedResource(Resource resource) {
        utilizedResources.add(resource);
    }

    public Score getScore() {
        return score;
    }

    public Action getAction() {
        return action;
    }

    public List<Resource> getUtilizedResources() {
        return utilizedResources;
    }

    @Override
    public int compareTo(Potential o) {
        return Integer.compare(score.get(), o.score.get());
    }
}
