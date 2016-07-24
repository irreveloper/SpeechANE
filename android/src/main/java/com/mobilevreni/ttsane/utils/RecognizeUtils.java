package com.mobilevreni.ttsane.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import com.mobilevreni.ttsane.SpeechExtension;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Jazzcript.
 */
public class RecognizeUtils {
    public static final String RECOGNIZE_READY ="recognize_ready";
    public static final String RECOGNIZE_SPEECH_START ="recognize_speech_start";
    public static final String RECOGNIZE_SPEECH_END ="recognize_speech_end";
    public static final String RECOGNIZE_COMPLETE="recognize_complete";
    public static final String RECOGNIZE_COMPLETE_NO_VOICE="recognize_complete_no_voice";
    public static final String RECOGNIZE_ERROR = "recognize_error";


    private static RecognizeUtils ourInstance = new RecognizeUtils();

    public static RecognizeUtils getInstance() {
        return ourInstance;
    }


    private SpeechRecognizer recognizer=null;

    private RecognizeUtils() {

    }

    public void initRecognize(Context context){

        if(recognizer!=null)
        {
            SpeechExtension.context.dispatchStatusEventAsync(RECOGNIZE_ERROR,String.valueOf("Already initialized"));
            return;
        }


         recognizer = SpeechRecognizer
                .createSpeechRecognizer(context.getApplicationContext());

        RecognitionListener listener = new RecognitionListener() {
            @Override
            public void onResults(Bundle results) {
                ArrayList<String> voiceResults = results
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (voiceResults == null) {
                    Log.i("Record", "No voice results");
                    SpeechExtension.context.dispatchStatusEventAsync(RECOGNIZE_COMPLETE_NO_VOICE,"");

                } else {

                    String jsonResult=new JSONArray(voiceResults).toString();

                    Log.i("Record", "Printing matches: "+jsonResult);

                    SpeechExtension.context.dispatchStatusEventAsync(RECOGNIZE_COMPLETE,jsonResult);

                }
            }

            @Override
            public void onReadyForSpeech(Bundle params) {
                Log.i("Record", "Ready for speech");
                SpeechExtension.context.dispatchStatusEventAsync(RECOGNIZE_READY,"");

            }

            @Override
            public void onError(int error) {
                Log.i("Record",
                        "Error listening for speech: " + error);
                SpeechExtension.context.dispatchStatusEventAsync(RECOGNIZE_ERROR,String.valueOf(error));
            }

            @Override
            public void onBeginningOfSpeech() {
                Log.i("Record", "Speech starting");

                SpeechExtension.context.dispatchStatusEventAsync(RECOGNIZE_SPEECH_START,"");

            }

            @Override
            public void onBufferReceived(byte[] buffer) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onEndOfSpeech() {
                // TODO Auto-generated method stub

                SpeechExtension.context.dispatchStatusEventAsync(RECOGNIZE_SPEECH_END,"");

            }

            @Override
            public void onEvent(int eventType, Bundle params) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onRmsChanged(float rmsdB) {
                // TODO Auto-generated method stub

            }
        };
        recognizer.setRecognitionListener(listener);
    }

    public void recognize(String language,int maxResults,long completeTimeMilis,long minSpeechMilis){

        if(recognizer==null){
            SpeechExtension.context.dispatchStatusEventAsync(RECOGNIZE_ERROR,String.valueOf("Recognizer was not initialized"));
            return;
        }

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, completeTimeMilis);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, completeTimeMilis);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, minSpeechMilis);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, maxResults);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                "com.mobilevreni.ttsane");


        recognizer.startListening(intent);
    }

    public void stop(){
        if(recognizer!=null)
            recognizer.cancel();
    }

}
