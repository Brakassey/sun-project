package com.brakassey.sunproject.utils;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class SoundManager implements Audio {

	@Override
	public AudioDevice newAudioDevice(int samplingRate, boolean isMono) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AudioRecorder newAudioRecorder(int samplingRate, boolean isMono) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sound newSound(FileHandle fileHandle) {
		// TODO Auto-generated method stub
		return Gdx.audio.newSound(fileHandle);
	}

	@Override
	public Music newMusic(FileHandle file) {
		// TODO Auto-generated method stub
		return Gdx.audio.newMusic(file);
	}

}
