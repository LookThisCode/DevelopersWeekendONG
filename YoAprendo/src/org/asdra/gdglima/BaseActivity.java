package org.asdra.gdglima;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;

/**
 * 
 * @author GDGLima Clase base que sirve como padre para todas las otras
 *         actividades. No se utiliza como activity que trabaje con Layout.
 */
public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
	}

}
