package com.example.soundtest1;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PlaySound extends Activity {
	// originally from
	// http://marblemice.blogspot.com/2010/04/generate-and-play-tone-in-android.html
	// and modified by Steve Pomeroy <steve@staticfree.info>
	private final int duration = 1; // seconds
	private final int sampleRate = 8000;
	private final int numSamples = duration * sampleRate;
	private final double sample[] = new double[numSamples];
	private double freqOfTone = 880; // hz

	private final byte generatedSnd[] = new byte[2 * numSamples];

	Handler handler = new Handler();

	Button play;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_sound);

		play = (Button) findViewById(R.id.play);
		play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Thread thread = new Thread(new Runnable() {
					public void run() {
						setTone(659);
						genTone();
						handler.post(new Runnable() {

							public void run() {
								playSound();
							}
						});
					}
				});
				thread.start();
//				final Thread thread2 = new Thread(new Runnable() {
//					public void run() {
//						setTone(553);
//						genTone();
//						handler.post(new Runnable() {
//
//							public void run() {
//								playSound();
//							}
//						});
//					}
//				});
//				thread2.start();
			}
		});
	}
	
	private void setTone(int h) {
		freqOfTone = h;
	}

	public void genTone() {
		// fill out the array
		for (int i = 0; i < numSamples; ++i) {
			sample[i] = -(Math.sin(2 * Math.PI * i / (sampleRate / freqOfTone)));
		}

		// convert to 16 bit pcm sound array
		// assumes the sample buffer is normalised.
		int idx = 0;
		for (final double dVal : sample) {
			// scale to maximum amplitude
			final short val = (short) ((dVal * 32767));
			// in 16 bit wav PCM, first byte is the low order byte
			generatedSnd[idx++] = (byte) (val & 0x00ff);
			generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

		}
	}

	void playSound() {
		final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
				sampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
				AudioTrack.MODE_STATIC);
		audioTrack.write(generatedSnd, 0, generatedSnd.length);
		audioTrack.play();
	}
}