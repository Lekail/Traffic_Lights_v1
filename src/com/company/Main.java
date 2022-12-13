package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TrafficLightsController trafficLightsController = new TrafficLightsController();
        new Thread(() -> {
            trafficLightsController.startCycling();
        }).start();

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n Press enter to see current state: \n");
            scanner.nextLine();
            trafficLightsController.currentState();
        }
        while(true);
    }
}
