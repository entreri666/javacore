package HomeWork1;

public class Human implements Competitor {

    private int maxJump;
    private int maxRun;

    public Human(int maxJump, int maxRun) {
        this.maxJump = maxJump;
        this.maxRun = maxRun;
    }

    @Override
    public void jump(int height) {
        if (height > maxJump) {
            System.out.println("Человек: Высоко, не перепрыгну");
        } else {
            System.out.println("Человек: Перепрыгнул");

        }

    }

    @Override
    public void run(int length) {
        if (length > maxRun) {
            System.out.println("Человек: Не пробегу");
        } else {
            System.out.println("Человек: Пробежал");

        }
    }
}
