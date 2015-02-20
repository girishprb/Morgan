package net.hackgician.morgan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by Girish on 2/20/2015.
 */
public class MySMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle bundle = intent.getExtras();
        SmsMessage[] smsMessages = null;
        String sms_str ="";
        if (bundle != null)
        {
            // Get the SMS message
            Object[] pdus = (Object[]) bundle.get("pdus");
            smsMessages = new SmsMessage[pdus.length];
            for (int i=0; i<smsMessages.length; i++)
            {
                smsMessages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                //TODO: Check if the source is +17043502797
//                sms_str += "Sent From: " + smsMessages[i].getOriginatingAddress();
//                sms_str += "\r\nMessage: ";
                sms_str += smsMessages[i].getMessageBody().toString();
                sms_str+= "\r\n";
            }

            // Start Application's MainActivty activity
            Intent smsIntent=new Intent(context,MainActivity.class);
            smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            smsIntent.putExtra("sms_str", sms_str);
            context.startActivity(smsIntent);
        }
    }
}

//        Object[] pdu =(Object[])intent.getExtras().get("pdus");
//        SmsMessage shortMessage=SmsMessage.createFromPdu((byte[]) pdu[0]);
//        Log.d("SMSReceiver", "SMS message sender: " + shortMessage.getOriginatingAddress());
//        Log.d("SMSReceiver","SMS message text: "+ shortMessage.getDisplayMessageBody());
//    }
//}
