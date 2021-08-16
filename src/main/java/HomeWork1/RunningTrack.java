package HomeWork1;

public class RunningTrack implements Obstacle {

    private int length;

    public RunningTrack(int length) {
        this.length = length;
    }

    public void overcome(Competitor competitor){
        competitor.run(length);
    };


}
