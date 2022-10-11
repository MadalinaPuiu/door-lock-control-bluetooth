package com.arduino.btconnection;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BluetoothBroadcastReceiver extends BroadcastReceiver {

    private BroadcastCallback broadcastCallback;

    BluetoothBroadcastReceiver(BroadcastCallback callback) {
        this.broadcastCallback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();

        if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
            final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
            switch (state) {
                case BluetoothAdapter.STATE_OFF:
                    Log.e("Receiver message:","Bluetooth is off");
                    if (broadcastCallback != null) broadcastCallback.onBluetoothChanges();
                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    Log.e("Receiver message:","Bluetooth is turning off");
                    break;
                case BluetoothAdapter.STATE_ON:
                    Log.e("Receiver message:","Bluetooth is on");
                    break;
                case BluetoothAdapter.STATE_TURNING_ON:
                    Toast.makeText(context, "Bluetooth is turning on", Toast.LENGTH_SHORT);
                    Log.e("Receiver message:","Bluetooth is turning on");
                    break;
            }

        }
    }
}
