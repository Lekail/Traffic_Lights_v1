package com.company;

public class TrafficLight {

    String identifier;
    LightState currentLightState;
    LightType lightType;

    TrafficLight(String newIdentifier, LightType newLightType){
        identifier = newIdentifier;
        lightType = newLightType;
        currentLightState = LightState.Initialize;
    }
}
