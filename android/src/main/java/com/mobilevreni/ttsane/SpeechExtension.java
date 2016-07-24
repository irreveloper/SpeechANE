package com.mobilevreni.ttsane;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;
import com.mobilevreni.ttsane.contexts.SpeechContext;

/**
 * Created by Jazzcript.
 */
public class SpeechExtension implements FREExtension {
    public static FREContext context;
    @Override
    public void initialize() {

    }

    @Override
    public FREContext createContext(String s) {

        return context=new SpeechContext();
    }

    @Override
    public void dispose() {
        context = null;
    }
}
