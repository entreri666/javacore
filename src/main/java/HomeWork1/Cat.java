package HomeWork1;

public class Cat implements Competitor {

    private int maxJump;
    private int maxRun;

    public Cat(int maxJump, int maxRun) {
        this.maxJump = maxJump;
        this.maxRun = maxRun;
    }

    @Override
    public void jump(int height) {
        if (height > maxJump) {
            System.out.println("Кот: Высоко, не перепрыгну");
        } else {
            System.out.println("Кот: Перепрыгнул");

        }

    }

    @Override
    public void run(int length) {
        if (length > maxRun) {
            System.out.println("Кот: Не пробегу");
        } else {
            System.out.println("Кот: Пробежал");

        }
    }
}
