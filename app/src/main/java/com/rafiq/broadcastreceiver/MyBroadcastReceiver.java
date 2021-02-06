package com.rafiq.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by RAFIQ on 3/25/2018.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String myAction = intent.getAction();
        switch (myAction){
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                Toast.makeText(context,"Airplane Mode Changed",Toast.LENGTH_LONG).show();

                Intent i = new Intent("myAction");
                i.putExtra("message","This is from Broadcast");
                context.sendBroadcast(i);

                break;
        }

    }
}
