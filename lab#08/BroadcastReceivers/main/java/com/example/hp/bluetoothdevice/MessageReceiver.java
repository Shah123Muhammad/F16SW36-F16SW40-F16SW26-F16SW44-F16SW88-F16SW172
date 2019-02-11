package com.example.hp.bluetoothdevice;

import android.content.BroadcastReceiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.OnNmeaMessageListener;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MessageReceiver extends BroadcastReceiver {

    private static MessageListener myMessageListener;
    public static void bindListener(MessageListener listener){
        myMessageListener=listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();
        Object[] pdus = (Object[]) data.get("pdus");
        for(int i=0; i<pdus.length; i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

            String message = smsMessage.getMessageBody();
            myMessageListener.messageRecieved(message);
        }
    }
}