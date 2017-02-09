package edu.fsu.cs.mobile.hw3pt2;

import android.app.*;
import android.app.ListFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        SmsMessage[] msgs = null;

        String str = "";

        if (bundle != null)
        {
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            for (int i = 0; i < msgs.length; i++)
            {
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                str += msgs[i].getMessageBody();
                str += "\n";
            }
            Log.d(TAG, str);
            String link = Uri.parse(str).toString();

            if (link.contains("\n"))
            {
                link = link.substring(0, link.length()-1);
            }
            if (!link.contains("http://"))
            {
                link = "http://" + link;
            }

            Intent newI = new Intent(context,MainActivity.class);
            newI.putExtra("link",link);
            newI.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(newI);

            //MainActivity ma = (MainActivity) context;
            //ma.setNew(link);
        }

    }
}
