package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TrafficLightsController {

    public int durationOfState = 10;
    public int deadzone = 5;
    public List<TrafficDirection> trafficDirectionList;

    TrafficLightsController(){
        trafficDirectionList = new ArrayList<>();
        trafficDirectionList.add(new TrafficDirection("NS", new ArrayList<TrafficLight>(){{
            add(new TrafficLight("North", LightType.Vehicle));
            add(new TrafficLight("South", LightType.Vehicle));
            add(new TrafficLight("WestPedestrian", LightType.Pedestrian));
            add(new TrafficLight("EastPedestrian", LightType.Pedestrian));
        }}));

        trafficDirectionList.add(new TrafficDirection("EW", new ArrayList<TrafficLight>(){{
            add(new TrafficLight("East", LightType.Vehicle));
            add(new TrafficLight("West", LightType.Vehicle));
            add(new TrafficLight("NorthPedestrian", LightType.Pedestrian));
            add(new TrafficLight("SouthPedestrian", LightType.Pedestrian));
        }}));
    }

    TrafficLight startCycling(){
        do {
            trafficDirectionList.forEach(trafficDirection -> {
                System.out.println("*log message* Lights are changing green for " + trafficDirection.identifier + "\n");
                changeLights(trafficDirection);
                delay(durationOfState);
            });
        }
        while(true);
    }

    private void changeLights(TrafficDirection currentTrafficDirection) {
        trafficDirectionList.forEach(trafficDirection -> {
            System.out.println("Updating direction " + trafficDirection.identifier);
            trafficDirection.trafficLightsList.forEach(trafficLight -> {
                switch (trafficLight.lightType) {
                    case Pedestrian:
                        trafficLight.currentLightState = LightState.Red;
                        break;
                    case Vehicle:
                        trafficLight.currentLightState = LightState.Yellow;
                        break;
                    default:
                        trafficLight.currentLightState = LightState.Initialize;
                        break;
                }
            });
        });

        System.out.println("*log message* All are yellow");
        delay(deadzone);

        trafficDirectionList.forEach(trafficDirection -> {
            if (trafficDirection.identifier.equals(currentTrafficDirection.identifier)){
                trafficDirection.trafficLightsList.forEach(trafficLight -> trafficLight.currentLightState = LightState.Green);
            }
            else {
                trafficDirection.trafficLightsList.forEach(trafficLight -> trafficLight.currentLightState = LightState.Red);
            }
            System.out.println("*log message* " + trafficDirection.identifier + " is " + trafficDirection.trafficLightsList.get(0).currentLightState);
        });
    }

    private void delay(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void currentState() {
        trafficDirectionList.forEach(trafficDirection -> {
            trafficDirection.trafficLightsList.forEach(trafficLight -> {
                System.out.println(trafficLight.identifier + " is " + trafficLight.currentLightState);
            });
        });
    }

}
