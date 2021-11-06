import java.util.Random;

public class Helper {


    public static String[] appendToTab(String[] tab, String element) {
        String[] newTab = new String[tab.length + 1];
        for (int i = 0; i < tab.length; i++) {
            newTab[i] = tab[i];
        }
        newTab[newTab.length - 1] = element;

        return newTab;
    }

    public static boolean isDouble(String d) {
        try {
            Double.parseDouble(d.trim());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
