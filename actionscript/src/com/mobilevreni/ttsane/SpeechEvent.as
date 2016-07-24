/**
 * Created by rashakol on 21.07.2016.
 */
package com.mobilevreni.ttsane {
import flash.events.Event;

public class SpeechEvent extends Event{

    public static const RECOGNIZE_READY:String="recognize_ready" //Sesi algılamak için hazır
    public static const RECOGNIZE_SPEECH_START:String="recognize_speech_start" //Mikrofona konuşma başladı
    public static const RECOGNIZE_SPEECH_END:String="recognize_speech_end" //Mikrofona konuşma bitti
    public static const RECOGNIZE_COMPLETE_NO_VOICE:String="recognize_complete_no_voice" //Ses algılama tamamlandı ancak ses yok
    public static const RECOGNIZE_COMPLETE:String="recognize_complete" //Ses algılama tamamlandı
    public static const RECOGNIZE_ERROR:String="recognize_error" //Ses algılamada hata oluştu


    public static const SPEAK_READY:String="speak_ready"; //Konuşma hazır
    public static const SPEAK_ERROR:String="speak_error"; //Konuşma oluşturulurken hata meydana geldi

    public var data:Object;

    public function SpeechEvent(type:String,data:Object=null) {
        super(type);

        this.data=data;
    }
}
}
