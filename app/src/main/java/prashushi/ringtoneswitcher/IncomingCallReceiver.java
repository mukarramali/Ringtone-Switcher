package prashushi.ringtoneswitcher;

/**
 * Created by Dell User on 12/4/2016.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class IncomingCallReceiver extends BroadcastReceiver
{
    Context mContext;
    @Override
    public void onReceive(Context mContext, Intent intent)
    {
        //Toast.makeText(mContext, "Receiver working!", Toast.LENGTH_SHORT).show();
        try
        {

     //       toast(mContext, "Receiver1");
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING))
            {
               // Toast.makeText(mContext, "Phone Is Ringing", Toast.LENGTH_SHORT).show();
                // Your Code
                changeRingtone(mContext);
            }

            if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
            {
             //   Toast.makeText(mContext, "Call Recieved", Toast.LENGTH_SHORT).show();
                // Your Code
            }

            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE))
            {

                System.out.println("Phone Is Idle");
     //           Toast.makeText(mContext, "Phone Is Idle", Toast.LENGTH_SHORT).show();
                // Your Code


            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            //your custom message
        }

    }
    private void toast(Context ctx, String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
    }

    public void changeRingtone(Context context){
        int[] rings={R.raw.one, R.raw.two, R.raw.three, R.raw.four};
        System.out.println("xxx 1");
        SharedPreferences sPref=context.getSharedPreferences("ringtone", MODE_PRIVATE);
        int present=sPref.getInt("ringtone", 1);
        System.out.println("xxx 2");
        String fPAth = "android.resource://" + context.getPackageName() + "/"+rings[present];
        System.out.println("xxx  fPath:"+fPAth);
        Uri uri = Uri.parse(fPAth);
        RingtoneManager.setActualDefaultRingtoneUri(context,
                RingtoneManager.TYPE_RINGTONE, uri);
        present=(present+1)%4;
        SharedPreferences.Editor editor=sPref.edit();
        editor.putInt("ringtone", present);
        editor.commit();
        
    }

}