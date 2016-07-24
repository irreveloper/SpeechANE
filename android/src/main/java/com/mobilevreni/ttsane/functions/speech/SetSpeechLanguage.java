package com.mobilevreni.ttsane.functions.speech;

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
public class SetSpeechLanguage implements FREFunction {
    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {

        try {
            return FREObject.newObject(SpeechUtils.getInstance().setLocale(SpeechUtils.getInstance().stringToLocale(freObjects[0].getAsString())));
        } catch (FREWrongThreadException e) {
            e.printStackTrace();
        } catch (FRETypeMismatchException e) {
            e.printStackTrace();
        } catch (FREInvalidObjectException e) {
            e.printStackTrace();
        }


        return null;
    }
}
