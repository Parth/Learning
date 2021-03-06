package com.parth.travis.vanderstad.mehrotra.myhouse;

import java.util.ArrayList;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MyHouseActivity extends Activity {

	// originally from
	// http://marblemice.blogspot.com/2010/04/generate-and-play-tone-in-android.html
	// and modified by Steve Pomeroy <steve@staticfree.info>
	private final int duration = 10; // seconds
	private final int sampleRate = 4000;
	private final double sample1[] = new double[duration * sampleRate];
	private final double sample2[] = new double[duration * sampleRate];
	private double freqOfTone1 = 880; // hz
	private double freqOfTone2 = 698; // hz
	String logString = null;
	int i = 0;

	private final byte generatedSnd[] = new byte[2 * (duration * sampleRate)];

	Handler handler = new Handler();
	TextView log;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);

		log = (TextView) findViewById(R.id.log);

		final Thread thread = new Thread(new Runnable() {
			public void run() {
				genTone();
				handler.post(new Runnable() {

					public void run() {
						playSound();
					}
				});
			}
		});
		thread.start();
	}

	public void genTone() {
		// fill out the array
		for (int i = 0; i < sample1.length; ++i) {
			double time = (double) i / (double) sampleRate;
			
			//sample1[i] = Math.sin(freqOfTone1 * 2 * Math.PI * i / (sampleRate));
			sample2[i] = Math.sin(freqOfTone2 * 2 * Math.PI * i/ (sampleRate));
		}

		// convert to 16 bit pcm sound array
		// assumes the sample buffer is normalised.
		int idx = 0;
		double sampleSum[] = addArrays(sample1, sample2);
		for (final double dVal : sampleSum) {
			// scale to maximum amplitude
			final short val = (short) ((dVal * 32767));
			// in 16 bit wav PCM, first byte is the low order byte
			generatedSnd[idx++] = (byte) (val & 0x00ff);
			generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

		}
	}
	
	private double[] addArrays(double[] a, double[] b) {
		double[] sum = new double[a.length];
		
		for (int i = 0; i < sum.length; i++) {
			sum[i] = a[i] + b[i];
		}
		
		return sum;
		
	}

	public void playSound() {
		final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
				sampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
				AudioTrack.MODE_STATIC);
		audioTrack.write(generatedSnd, 0, generatedSnd.length);
		audioTrack.play();
	}

}
