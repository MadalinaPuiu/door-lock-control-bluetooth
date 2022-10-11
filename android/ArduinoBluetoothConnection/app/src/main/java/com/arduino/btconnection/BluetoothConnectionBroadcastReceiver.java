package com.arduino.btconnection;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BluetoothConnectionBroadcastReceiver extends BroadcastReceiver {
    private BroadcastCallback broadcastCallback;

    BluetoothConnectionBroadcastReceiver(BroadcastCallback callback) {
        this.broadcastCallback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();

        switch (action) {
            case BluetoothDevice.ACTION_ACL_CONNECTED:
                Log.e("Receiver message:", "Device connected!");
                break;
            case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                Log.e("Receiver message:", "Device disconnected!");
                if (broadcastCallback != null) broadcastCallback.onBluetoothChanges();
                break;
        }
    }
}
