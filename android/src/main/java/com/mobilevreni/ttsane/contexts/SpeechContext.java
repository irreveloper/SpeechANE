package com.mobilevreni.ttsane.contexts;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.mobilevreni.ttsane.functions.IsSupported;
import com.mobilevreni.ttsane.functions.recognize.InitRecognize;
import com.mobilevreni.ttsane.functions.recognize.Recognize;
import com.mobilevreni.ttsane.functions.recognize.StopRecognize;
import com.mobilevreni.ttsane.functions.speech.SetSpeechLanguage;
import com.mobilevreni.ttsane.functions.speech.SetSpeechRate;
import com.mobilevreni.ttsane.functions.speech.Speech;
import com.mobilevreni.ttsane.functions.speech.InitSpeech;
import com.mobilevreni.ttsane.functions.speech.StopSpeech;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jazzcript.
 */
public class SpeechContext extends FREContext {
    @Override
    public Map<String, FREFunction> getFunctions() {

        Map<String,FREFunction> functionMap=new HashMap<>();

        functionMap.put("isSupported",new IsSupported());
        functionMap.put("speech",new Speech());
        functionMap.put("initSpeech",new InitSpeech());
        functionMap.put("stopSpeech",new StopSpeech());
        functionMap.put("setSpeechLanguage",new SetSpeechLanguage());
        functionMap.put("setSpeechRate",new SetSpeechRate());
        functionMap.put("recognize",new Recognize());
        functionMap.put("stopRecognize",new StopRecognize());
        functionMap.put("initRecognize",new InitRecognize());

        return functionMap;
    }

    @Override
    public void dispose() {

    }


}
