package com.unibratec.misael_junior.projetofilmes;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by misael-junior on 11/05/16.
 */
public class FilmeApp extends Application{

    EventBus eventBus;

    @Override
    public void onCreate() {
        super.onCreate();

        eventBus = new EventBus();
        //sdfdfdfdfdfdfd
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
