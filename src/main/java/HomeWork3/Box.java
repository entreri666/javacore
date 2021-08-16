package HomeWork3;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruitlist = new ArrayList<>();

    public Box() {
    }

    public ArrayList<T> getFruitlist() {
        return fruitlist;
    }

    public void setFruitlist(ArrayList<T> fruitlist) {
        this.fruitlist = fruitlist;
    }

    public void addFruit(T fruit) {
        fruitlist.add(fruit);
    }

    public float getWeight() {
        return fruitlist.size() * fruitlist.get(0).getWeight();
    }

    public boolean compare(Box<?> boxToCompare) {
        return Math.abs(getWeight() - boxToCompare.getWeight()) < 0.0001;
    }


    public void pourOver(Box<T> box) {
        box.getFruitlist().addAll(fruitlist);
        fruitlist.clear();
    }


    @Override
    public String toString() {
        return "Box{" +
                "fruitlist=" + fruitlist +
                '}';
    }
}
