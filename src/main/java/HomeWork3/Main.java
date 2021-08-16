package HomeWork3;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Object[] array = new Object[]{"1", "2", "3"};

        changeArrElements(array, 0, 1);

    }

    public static void changeArrElements(Object[] array, int a, int b) {
        System.out.println(Arrays.toString(array));
        Object tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
        System.out.println(Arrays.toString(array));
    }
}
