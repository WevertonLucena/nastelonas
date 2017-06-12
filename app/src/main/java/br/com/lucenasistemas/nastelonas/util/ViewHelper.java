package br.com.lucenasistemas.nastelonas.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.Base64;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Weverton on 26/11/2015.
 */
public class ViewHelper {

    public static void fade(View v1, View v2,int duration){
       if(v2.getVisibility() == View.VISIBLE){
           v1.setVisibility(View.VISIBLE);
           v1.setAlpha(0.0f);
           v1.animate().alpha(1.0f).setDuration(duration);
           v2.setVisibility(View.GONE);
       }
    }

    public static void fadeIn(View view, int duration){
        if (view.getVisibility() == View.GONE)
            view.setVisibility(View.VISIBLE);
        view.setAlpha(0.0f);
        view.animate().alpha(1.0f).setDuration(duration);
    }

    public static void fadeOut(View view, int duration){
        if (view.getVisibility() == View.GONE)
            view.setVisibility(View.VISIBLE);
        view.setAlpha(1.0f);
        view.animate().alpha(0.0f).setDuration(duration);
        //view.setVisibility(View.GONE);
    }
}
