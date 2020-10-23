package ordsys;

import java.util.ArrayList;

public class FinalProject {

    public static MainMenu startMenu;
    public static ArrayList<Order> orderList;

    public static void main(String[] args) {
        orderList = new ArrayList<>();
        startMenu = new MainMenu();
    }

}
