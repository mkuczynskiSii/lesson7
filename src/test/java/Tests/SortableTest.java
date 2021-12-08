package Tests;

import org.junit.jupiter.api.Test;

import java.util.Random;

class SortableTest
{
    public static void shuffleArray(int[] tab) {
        for (int i = tab.length - 1; i >= 1; i--) {
            Random random = new Random();
            int j = random.nextInt(i + 1);
            swapElements(tab, i, j);
        }
    }
    private static void swapElements(int[] tab, int i, int j) {
        int temp = tab[i];
        tab[i] = tab[j];
        tab[j] = temp;
    }

    @Test
    public void sortableTest() {
        int[] tab = { 1, 2, 3, 4, 5, 6 };
        shuffleArray(tab);
    }
}