package HomeWork1;

public class Main {

    public static void main(String[] args) {

        Competitor[] competitors = {new Cat(7, 4), new Human(6, 8),
                new Robot(8, 9)};

        Obstacle[] obstacles = {new RunningTrack(9), new Wall(6)};

        for (Competitor competitor : competitors) {
            for (Obstacle obstacle : obstacles) {
                obstacle.overcome(competitor);
        }
    }
}
}
