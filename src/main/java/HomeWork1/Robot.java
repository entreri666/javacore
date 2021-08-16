package HomeWork1;

public class Robot implements Competitor {

    private int maxJump;
    private int maxRun;

    public Robot(int maxJump, int maxRun) {
        this.maxJump = maxJump;
        this.maxRun = maxRun;
    }

    @Override
    public void jump(int height) {
        if (height > maxJump) {
            System.out.println("Робот: Высоко, не перепрыгну");
        } else {
            System.out.println("Робот: Перепрыгнул");

        }

    }

    @Override
    public void run(int length) {
        if (length > maxRun) {
            System.out.println("Робот: Не пробегу");
        } else {
            System.out.println("Робот: Пробежал");

        }
    }
}
