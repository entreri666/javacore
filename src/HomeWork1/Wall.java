package HomeWork1;

public class Wall implements Obstacle {

    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public void overcome(Competitor competitor){
        competitor.jump(height);
    };
}
