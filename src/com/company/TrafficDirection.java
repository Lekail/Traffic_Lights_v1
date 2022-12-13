package com.company;

import java.util.List;

public class TrafficDirection {
    String identifier;
    List<TrafficLight> trafficLightsList;

    TrafficDirection(String newIdentifier, List newTrafficLightsList){
        identifier = newIdentifier;
        trafficLightsList = newTrafficLightsList;
    }
}
