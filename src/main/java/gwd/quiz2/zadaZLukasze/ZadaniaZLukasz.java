package gwd.quiz2.zadaZLukasze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZadaniaZLukasz {


    public static int sumOf15devider(int limit) {
        int sum = 0;

        for (int i = 0; i <= limit; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }

        return sum;
    }

    public static int maxValue(int[] tab) {

        Arrays.sort(tab);
        return tab[tab.length-1];
    }

    public static void main(String[] args) {

        System.out.println(sumOf15devider(100));

        int [] tab = {5,10,5,6,8,1,2,3,12,2,3};
        System.out.println(maxValue(tab));


    }
}
