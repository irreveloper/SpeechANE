package com.mobilevreni.ttsane.functions.recognize;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.mobilevreni.ttsane.SpeechExtension;
import com.mobilevreni.ttsane.utils.RecognizeUtils;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Jazzcript.
 */
public class Recognize implements FREFunction {



    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {


        try {
            String language=freObjects[0].getAsString();
            int maxResults=freObjects[1].getAsInt();
            long completeTimeMilis= (long) freObjects[2].getAsDouble();
            long minSpeechMilis= (long) freObjects[3].getAsDouble();

            Log.i("RECORD: ","here");

            RecognizeUtils.getInstance().recognize(language,maxResults,completeTimeMilis,minSpeechMilis);


        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }


        return null;
    }



}
