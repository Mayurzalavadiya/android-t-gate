package com.tgate.gate.Interface;

import android.content.IntentFilter;

public interface IntentReceiver {

    public static final String BROADCAST_RECEIVER = "broadcast_receiver";


    public static IntentFilter getBroadcast() {
        return new IntentFilter(BROADCAST_RECEIVER );
    }

}
