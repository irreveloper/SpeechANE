package com.mobilevreni.ttsane.utils;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import com.mobilevreni.ttsane.SpeechExtension;
import com.mobilevreni.ttsane.functions.speech.Speech;

import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Created by Jazzcript.
 */
public class SpeechUtils {

    public static final String SPEAK_ERROR="speak_error";
    public static final String SPEAK_READY = "speak_ready";

    private static SpeechUtils ourInstance = new SpeechUtils();

    public static SpeechUtils getInstance() {
        return ourInstance;
    }

    public Boolean isInitialized=false;

    private TextToSpeech textToSpeech=null;
    private SpeechUtils() {
    }

    public void initTextToSpeech(Context context, final Locale locale,final float speechRate){

        if(textToSpeech!=null)
        {
            if(isInitialized)
            SpeechExtension.context.dispatchStatusEventAsync(SPEAK_ERROR,"Already initialized");

            return;
        }

        textToSpeech=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR){
                    textToSpeech.setLanguage(locale);
                    textToSpeech.setSpeechRate(speechRate);
                    isInitialized=true;
                    SpeechExtension.context.dispatchStatusEventAsync(SPEAK_READY,"");

                }
                else {
                    SpeechExtension.context.dispatchStatusEventAsync(SPEAK_ERROR,"initial error");
                }
            }


        });

    }

    public void stop(){
        if(textToSpeech!=null)
            textToSpeech.stop();
    }

    public Boolean setLocale(Locale locale){
        if (textToSpeech==null || !isInitialized)
            return false;

        textToSpeech.setLanguage(locale);

        return true;
    }

  public Boolean setSpeechRate(float speechRate){
        if (textToSpeech==null || !isInitialized)
            return false;

        textToSpeech.setSpeechRate(speechRate);

        return true;
    }


    public void speak(String message){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);
        }
        else{
            textToSpeech.speak(message,TextToSpeech.QUEUE_FLUSH,null);
        }
    }

    public Locale stringToLocale(String s) {
        StringTokenizer tempStringTokenizer = new StringTokenizer(s,",");
        String l="";
        String c="";
        if(tempStringTokenizer.hasMoreTokens())
            l= (String) tempStringTokenizer.nextElement();
        if(tempStringTokenizer.hasMoreTokens())
           c = (String) tempStringTokenizer.nextElement();
        return new Locale(l,c);
    }
}
