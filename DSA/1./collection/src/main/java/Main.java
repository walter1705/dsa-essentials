import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Blue");
        colors.add("Blue");
        colors.add("Blue");
        colors.add("Blue");
        colors.add("Blue");
        colors.add("Blue");
        colors.add("Blue");
        colors.add("Blue");
        colors.add("Blue");
        colors.add("Blue");
        colors.add("Blue");
        colors.add("Blue");
        colors.add("Blue");
        colors.add("Blue");


        for (String color : colors) {
            if (color.equals("Green")) {
                colors.remove(color);
            }
        }
        System.out.println(colors.size());

    }

}
