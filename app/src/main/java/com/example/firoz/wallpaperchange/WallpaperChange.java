package com.example.firoz.wallpaperchange;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Firoz on 21-12-2016.
 */

public class WallpaperChange extends Service implements Runnable {
    //store the reference of the wallpaper in the array
    private int WallpaperId[]={R.drawable.wallpaper1,R.drawable.wallpaper2};

    //to store user selected time
    private int time=0;

    //declare a flag to check which image to display next
    private int FLAG=0;

    private Thread t;

    //declare two Bitmap objects to store the wallpaper images

    private Bitmap bitmap1,bitmap2;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Toast.makeText(this,"Time has been set",Toast.LENGTH_SHORT).show();

                Bundle bundle=intent.getExtras();

                time=bundle.getInt("time");


        //initialize the bitmap objects with the wallpaper images
        bitmap1= BitmapFactory.decodeResource(getResources(),WallpaperId[0]);
        bitmap2=BitmapFactory.decodeResource(getResources(),WallpaperId[1]);

        //start a thread to keep the service running
        t=new Thread(WallpaperChange.this);
        t.start();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"Service destroyed",Toast.LENGTH_SHORT).show();
        System.exit(0);
    }

    @Override
    public void run() {
        try
        {
            while(true)
            {
                if(FLAG==0) {
                    /*//WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                    // myWallpaperManager.setBitmap(bitmap1);
                    We can use setResource directly without using bitmap object
                    //myWallpaperManager.setResource(R.drawable.wallpaper1);
                   */
                    this.setWallpaper(bitmap1);
                    FLAG++;
                }
                else
                {
                    this.setWallpaper(bitmap2);
                    FLAG--;
                }

                Thread.sleep(1000*time);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
