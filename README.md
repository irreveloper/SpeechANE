Air Native Extension for Text-to-Speech &amp; Speech-to-Text (Android)
======================================

Usage
-----
Initiliaze
```actionscript
            SpeechExtension.getInstance().addEventListener(SpeechEvent.SPEAK_READY, function () {
                trace("speech ready");
            })

            SpeechExtension.getInstance().addEventListener(SpeechEvent.SPEAK_ERROR, function (event:SpeechEvent) {
                trace("speech error: " + event.data);
            })

            SpeechExtension.getInstance().addEventListener(SpeechEvent.RECOGNIZE_READY, function () {
                trace("recognize ready");
            })

            SpeechExtension.getInstance().addEventListener(SpeechEvent.RECOGNIZE_SPEECH_START, function () {
                trace("rec speech start");
            })

            SpeechExtension.getInstance().addEventListener(SpeechEvent.RECOGNIZE_SPEECH_END, function () {
                trace("rec speech end");
            })

            SpeechExtension.getInstance().addEventListener(SpeechEvent.RECOGNIZE_COMPLETE, function (event:SpeechEvent) {
                trace("recognize complete: " + event.data);
            })

            SpeechExtension.getInstance().addEventListener(SpeechEvent.RECOGNIZE_COMPLETE_NO_VOICE, function () {
                trace("no voice");
            })

            SpeechExtension.getInstance().addEventListener(SpeechEvent.RECOGNIZE_ERROR, function (event:SpeechEvent) {
                        trace("recognize error: "+event.data);
                    }
            )


            SpeechExtension.getInstance().initRecognize();

            SpeechExtension.getInstance().initSpeech();
```
Text-to-Speech

```actionscript
  SpeechExtension.getInstance().speak("Heloloy!");
```
Speech-to-Text

```actionscript

  SpeechExtension.getInstance().recognize();
                
```

Permissions
```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.RECORD_AUDIO" />
```

