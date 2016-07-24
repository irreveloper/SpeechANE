package com.mobilevreni.ttsane
{

import feathers.system.DeviceCapabilities;

import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
import flash.system.Capabilities;

public class SpeechExtension extends EventDispatcher
	{
		private static var _instance:SpeechExtension;
		
		private var extCtx:ExtensionContext = null;


	private const IS_SUPPORTED:Boolean=Capabilities.manufacturer.indexOf("Android")>-1;

		public function SpeechExtension()
		{
			if (!_instance)
			{
				//Same as Java Project
				extCtx = ExtensionContext.createExtensionContext("com.mobilevreni.ttsane.SpeechExtension", null);
				if (extCtx != null)
				{
					extCtx.addEventListener(StatusEvent.STATUS, onStatus);
				} 
				else
				{
					trace('[SpeechExtension] Error - Extension Context is null.');
				}
				_instance = this;
			}
			else
			{
				throw Error('This is a singleton, use getInstance(), do not call the constructor directly.');
			}
		}
		
		public static function getInstance() : SpeechExtension
		{
			return _instance ? _instance : new SpeechExtension();
		}
		
		

		public function isSupported(tag:String="Speech") : Boolean
		{
			if(!IS_SUPPORTED)
					trace(tag+" Not Supoorted");

			return IS_SUPPORTED;

		}


		
		public function initSpeech(locale:String="en",speechRate:Number=0.8):Object{

			if(!isSupported())
					return null;

			return extCtx.call("initSpeech",locale,speechRate);

		}

		//change speech language
		public function setSpeechLanguage(locale:String):Boolean{
			if(!isSupported())
				return null;

			return extCtx.call("setSpeechLanguage",locale);
		}

		//change speech speed
		public function setSpeechRate(speechRate:Number):Boolean{
			if(!isSupported())
				return null;

			return extCtx.call("setSpeechRate",speechRate);
		}

		
		public function speak(say:String) :void
		{
			if(!isSupported())
				return;

			 extCtx.call('speech',say);
		}

	//stop speech
		public function stopSpeak() :void
		{
			if(!isSupported())
				return;

			 extCtx.call('stopSpeech');
		}

		//init speech recognize
	    public function initRecognize() :void
		{

			if(!isSupported())
				return;

			 extCtx.call('initRecognize');
		}

		//start speech recognize
	    public function recognize(language:String="en-GB",maxResults:int=1,completeTimeMilis:Number=2000,minSpeechMilis:Number=2000) :void
		{

			if(!isSupported())
				return;

			 extCtx.call('recognize',language,maxResults,completeTimeMilis,minSpeechMilis);
		}

	//stop recognize
	    public function stopRecognize() :void
		{

			if(!isSupported())
				return;

			 extCtx.call('stopRecognize');
		}



		/**
		 * Listener for native events
		 * event.code is event type, event.level is data 
		 */
		private function onStatus( event : StatusEvent ) : void
		{

			switch (event.code){
				case  SpeechEvent.RECOGNIZE_READY:
					dispatchEvent(new SpeechEvent(SpeechEvent.RECOGNIZE_READY,event.level));
				break;
				case  SpeechEvent.RECOGNIZE_SPEECH_START:
					dispatchEvent(new SpeechEvent(SpeechEvent.RECOGNIZE_SPEECH_START));
				break;
				case  SpeechEvent.RECOGNIZE_SPEECH_END:
					dispatchEvent(new SpeechEvent(SpeechEvent.RECOGNIZE_SPEECH_END));
				break;
				case  SpeechEvent.RECOGNIZE_COMPLETE:
					dispatchEvent(new SpeechEvent(SpeechEvent.RECOGNIZE_COMPLETE,event.level));
				break;
				case  SpeechEvent.RECOGNIZE_COMPLETE_NO_VOICE:
					dispatchEvent(new SpeechEvent(SpeechEvent.RECOGNIZE_COMPLETE_NO_VOICE));
				break;
				case  SpeechEvent.RECOGNIZE_ERROR:
					dispatchEvent(new SpeechEvent(SpeechEvent.RECOGNIZE_ERROR,event.level));
				break;

				case  SpeechEvent.SPEAK_READY:
					dispatchEvent(new SpeechEvent(SpeechEvent.SPEAK_READY));
				break;
	        	case  SpeechEvent.SPEAK_ERROR:
					dispatchEvent(new SpeechEvent(SpeechEvent.SPEAK_ERROR,event.level));
				break;

				default:
					break;

			}


		}
	}
}