package com.example.admin.weekend2assigment;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Image View");
        dialog.setContentView(R.layout.dialog_layout);
        dialog.show();
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        };
        handler.postDelayed(runnable,3000);
    }

    public void Show(View view) {
        showDialog();
    }

    public void DefaultAlertDialog(View view) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Default Alert Dialog");
        alertDialogBuilder.setTitle("Alert Title");
        alertDialogBuilder.setIcon(R.drawable.image1);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void CustomAlertDialog(View view) {
        AlertDialog.Builder alertDialogBuilder  = new AlertDialog.Builder(this);
        View view1 = getLayoutInflater().inflate(R.layout.alert_customlayout,null);

        EditText ETPass = (EditText) view1.findViewById(R.id.ETPass);
        Button BtnPass = (Button) view1.findViewById(R.id.AlertBtn);
        BtnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Don't Click on This Button", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setView(view1);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void ListAlertDialog(View view) {
        final String[] Items;
        final boolean[] CheckItems;
        final ArrayList<Integer> SelectedItems = new ArrayList<>();

        Items = getResources().getStringArray(R.array.ArrrayString);
        CheckItems = new boolean[Items.length];

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Please Select your Items");
        alertDialogBuilder.setMultiChoiceItems(Items, CheckItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked)
                {
                    if(!SelectedItems.contains(which)) {
                        SelectedItems.add(which);
                    }
                }
                else if (SelectedItems.contains(which))
                {
                    SelectedItems.remove(which);
                }
            }
        });
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String x ="";
                for (int i = 0; i < SelectedItems.size(); i++) {
                    x+="\n" + Items[SelectedItems.get(i)];

                }
                AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
                ab.setTitle("Selected Items");
                ab.setMessage(x);
                ab.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog ad = ab.create();
                ad.show();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void SendNotification(View view) {
        Intent intent  = new Intent(MainActivity.this,SMSManager.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);
        Notification.Builder notificationBuilder = new Notification.Builder(MainActivity.this);
        notificationBuilder.setTicker("Ticker Test");
        notificationBuilder.setContentTitle("Notification Title");
        notificationBuilder.setContentText("Notification Text");
        notificationBuilder.setSmallIcon(R.drawable.image1);
        notificationBuilder.setContentIntent(pendingIntent).getNotification();

        Notification notification = notificationBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager =  (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,notification);

    }

    public void SendMessage(View view) {
        Intent i = new Intent(this,SMSManager.class);
        startActivity(i);
    }

    public void ShowList(View view) {
        List<Object> objectList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Object o = new Object();
            o.setID(i);
            o.setName("Object "+i);
            o.setDescription("Object Description");
            objectList.add(o);
        }

        listView =(ListView) findViewById(R.id.listView1);
        listView.setAdapter(new CustomAdabter(MainActivity.this,objectList));
    }
}

