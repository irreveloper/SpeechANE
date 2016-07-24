package com.mobilevreni.ttsane.functions.speech;

import android.content.Context;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;
import com.mobilevreni.ttsane.utils.SpeechUtils;

/**
 * Created by Jazzcript.
 */
public class Speech implements FREFunction {




    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {


        try {

            if(!SpeechUtils.getInstance().isInitialized)
                return FREObject.newObject("Speech util was not initialized");

           // Log.i("SPEAK: ",freObjects[0].getAsString());

            SpeechUtils.getInstance().speak(freObjects[0].getAsString());

        } catch (Exception e)
        {
            e.printStackTrace();
        }



        return null;
    }
}
