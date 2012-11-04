package org.asdra.gdglima;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 
 * @author GDGLima Actividad que genera la pantalla inicial, en vez de usar un
 *         animation se ha decidido utilizar un Timer para poder ejecutar la
 *         transici—n
 */
public class SplashActivity extends BaseActivity {

	private static final long DELAY_ACTIVITY = 2000L;
	private Timer executeTimer = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_layout);

		String version = "Version: "
				+ getResources().getString(R.string.version_name);
		((TextView) findViewById(R.id.version)).setText(version);

		_loadTimer();
	}

	/**
	 * Carga el timer para poder realizar la transici—n.
	 */
	private void _loadTimer() {
		executeTimer = new Timer();
		executeTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				Intent intent = new Intent(SplashActivity.this,
						AsdraActivity.class);
				startActivity(intent);
				finish();
			}
		}, DELAY_ACTIVITY);
	}
}
