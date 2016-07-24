package com.mobilevreni.ttsane.functions.speech;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.mobilevreni.ttsane.utils.SpeechUtils;

/**
 * Created by Jazzcript.
 */
public class StopSpeech implements FREFunction {
    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {

        Log.i("Speech: ","stopped");

        SpeechUtils.getInstance().stop();

        return null;
    }
}
