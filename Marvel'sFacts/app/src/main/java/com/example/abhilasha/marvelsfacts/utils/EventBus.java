package com.example.abhilasha.marvelsfacts.utils;

import com.squareup.otto.Bus;

/**
 * Created by Abhilasha on 11/8/2017.
 */

public class EventBus extends Bus {
    private static  final  EventBus bus=new EventBus();
    public static Bus getInstance(){return bus;}
    private  EventBus() {}
}
