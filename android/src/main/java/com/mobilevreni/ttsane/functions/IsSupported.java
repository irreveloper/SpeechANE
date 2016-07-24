package com.mobilevreni.ttsane.functions;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;

/**
 * Created by Jazzcript.
 */
public class IsSupported implements FREFunction {
    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {

        try
        {
            return FREObject.newObject(true);
        }
        catch (FREWrongThreadException exception)
        {
            Log.d("TTS: ", exception.getLocalizedMessage());
            return null;
        }
    }
}
