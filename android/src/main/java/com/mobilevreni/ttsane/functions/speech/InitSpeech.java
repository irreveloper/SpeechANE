package com.mobilevreni.ttsane.functions.speech;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.mobilevreni.ttsane.utils.SpeechUtils;

import java.util.Locale;

/**
 * Created by Jazzcript.
 */
public class InitSpeech implements FREFunction {


    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {

        try {
            Locale locale= Locale.ENGLISH;
            float speechRate= .8f;

            if(freObjects[0]!=null && freObjects[0].getAsString().length()>0)
                locale=SpeechUtils.getInstance().stringToLocale(freObjects[0].getAsString());

            if(freObjects[1]!=null)
                speechRate= (float) freObjects[1].getAsDouble();

            if(SpeechUtils.getInstance().isInitialized)
                return FREObject.newObject("Already initialized");

            SpeechUtils.getInstance().initTextToSpeech(freContext.getActivity(),locale,speechRate);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
