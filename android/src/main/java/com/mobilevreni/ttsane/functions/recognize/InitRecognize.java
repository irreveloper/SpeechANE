package com.mobilevreni.ttsane.functions.recognize;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.mobilevreni.ttsane.utils.RecognizeUtils;

/**
 * Created by Jazzcript.
 */
public class InitRecognize implements FREFunction {
    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {

        RecognizeUtils.getInstance().initRecognize(freContext.getActivity());

        return null;
    }
}
