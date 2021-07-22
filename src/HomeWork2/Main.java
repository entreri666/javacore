package HomeWork2;

public class Main {
    public static void main(String[] args) {
        String[][] successArray = {
                {"5", "6", "3", "1"},
                {"4", "7", "5", "2"},
                {"2", "3", "8", "4"},
                {"8", "1", "4", "3"}
        };
        try {
            System.out.println(sumArray(successArray));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }


        String[][] failDataArray = {
                {"5", "6", "3", "1"},
                {"4", "7", "5", "2"},
                {"2", "3", "8b", "4"},
                {"8", "1", "4", "3"}
        };

        try {
            System.out.println(sumArray(failDataArray));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }

        String[][] failSizeArray = {
                {"5", "6", "3", "1", "1"},
                {"4", "7", "5", "2", "2"},
                {"2", "3", "8", "4", "2"},
                {"8", "1", "4", "3", "2"},
        };

        try {
            System.out.println(sumArray(failSizeArray));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }


    }


    public static int sumArray(String[][] givenArray) throws MyArraySizeException, MyArrayDataException {
        if (givenArray.length != 4) {
            throw new MyArraySizeException("длинна массива не равна 4");
        }

        for (String[] array : givenArray) {
            if (array.length != 4) {
                throw new MyArraySizeException("высота массива не равна 4");
            }
        }

        int sum = 0;
        for (int i = 0; i < givenArray.length; i++) {
            for (int j = 0; j < givenArray[i].length; j++) {
                try {
                    sum += Integer.parseInt(givenArray[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("не верный тип данных в ячейке " + i + "-" + j);
                }
            }
        }

        return sum;
    }
}