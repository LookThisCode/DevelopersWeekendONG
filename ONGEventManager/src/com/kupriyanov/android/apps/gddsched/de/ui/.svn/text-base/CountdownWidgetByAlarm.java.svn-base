package com.kupriyanov.android.apps.gddsched.de.ui;

import com.google.android.apps.iosched.util.UIUtils;
import com.kupriyanov.android.apps.gddsched.R;
import com.kupriyanov.android.apps.gddsched.de.Setup;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

public class CountdownWidgetByAlarm extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		Log.d(getClass().getName(), "onUpdate");

		// To prevent any ANR timeouts, we perform the update in a service
		context.startService(new Intent(context, UpdateService.class));
		schedule(context);

	}

	@Override
	public void onDisabled(Context context) {
		Log.d(getClass().getName(), "onDisabled");
		cancel(context);
	}

	private void schedule(Context context) {
		Intent updateIntent = new Intent();
		updateIntent.setClass(context, UpdateService.class);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0, updateIntent, 0);
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(),
				AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
	}

	private void cancel(Context context) {
		Intent intent = new Intent();
		intent.setClass(context, UpdateService.class);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(pendingIntent);
	}

	public static class UpdateService extends Service {

		@Override
		public void onStart(Intent intent, int startId) {
			Log.d(getClass().getName(), "onStart");

			// Build the widget update for today
			RemoteViews updateViews = buildUpdate(this);

			// Push update for this widget to the home screen
			ComponentName thisWidget = new ComponentName(this, CountdownWidgetByAlarm.class);
			AppWidgetManager manager = AppWidgetManager.getInstance(this);
			manager.updateAppWidget(thisWidget, updateViews);

			stopSelf();

		}

		/**
		 * Build a widget update to show the current Wiktionary "Word of the day."
		 * Will block until the online API returns.
		 */
		public RemoteViews buildUpdate(Context context) {
			Log.d(getClass().getName(), "buildUpdate");

			/**
			 * Event that updates countdown timer. Posts itself again to
			 * {@link #mMessageHandler} to continue updating time.
			 */

			final long currentTimeMillis = System.currentTimeMillis();
			RemoteViews updateViews = null;

			if (currentTimeMillis < Setup.CONFERENCE_START_MILLIS) {
				updateViews = createNowPlayingBeforeView();
			} else if (currentTimeMillis > Setup.CONFERENCE_END_MILLIS) {
				updateViews = createNowPlayingAfterView();
			} else {
				updateViews = createNowPlayingDuringView();
			}

			// Create an Intent to launch ExampleActivity
			Intent intent = new Intent(context, HomeActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

			updateViews.setOnClickPendingIntent(R.id.now_playing_more, pendingIntent);

			return updateViews;
		}

		private RemoteViews createNowPlayingBeforeView() {
			// Before conference, show countdown.

			Log.d(getClass().getName(), "createNowPlayingBeforeView");

			RemoteViews updateViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.widget_countdown);

			int remainingSec = (int) Math.max(0, (Setup.CONFERENCE_START_MILLIS - System.currentTimeMillis()) / 1000);
			final boolean conferenceStarted = remainingSec == 0;

			final int secs = remainingSec % 86400;
			final int days = remainingSec / 86400;

			final String str = getResources().getQuantityString(R.plurals.now_playing_countdown, days, days,
					DateUtils.formatElapsedTime(secs));

			updateViews.setTextViewText(R.id.now_playing_title, str);

			// final View nowPlaying =
			// getLayoutInflater().inflate(R.layout.now_playing_before, null);
			// final TextView nowPlayingTitle = (TextView) nowPlaying.findViewById(
			// R.id.now_playing_title);
			//
			// mCountdownTextView = nowPlayingTitle;
			// mMessageHandler.post(mCountdownRunnable);
			// mNowPlayingLoadingView.setVisibility(View.GONE);
			// nowPlaying.setVisibility(View.VISIBLE);

			return updateViews;
		}

		private RemoteViews createNowPlayingAfterView() {

			Log.d(getClass().getName(), "createNowPlayingAfterView");

			RemoteViews updateViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.widget_countdown);

			updateViews.setTextViewText(R.id.now_playing_title, getString(R.string.now_playing_after_title));
			updateViews.setTextViewText(R.id.now_playing_subtitle, getString(R.string.now_playing_after_subtitle));

			return updateViews;
		}

		private RemoteViews createNowPlayingDuringView() {
			Log.d(getClass().getName(), "createNowPlayingDuringView");

			RemoteViews updateViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.widget_countdown);

			updateViews.setTextViewText(R.id.now_playing_title, getString(R.string.now_playing_title));
			updateViews.setTextViewText(R.id.now_playing_subtitle, getString(R.string.now_playing_subtitle));

			// Conference in progress, show now playing.
			// final View nowPlaying =
			// getLayoutInflater().inflate(R.layout.now_playing_during, null);
			// nowPlaying.setVisibility(View.GONE);
			// if (!forceRelocate && mState.mNowPlayingUri != null) {
			// mQueryHandler.startQuery(mState.mNowPlayingUri,
			// SessionsQuery.PROJECTION);
			// }
			return updateViews;
		}

		@Override
		public IBinder onBind(Intent intent) {
			// We don't need to bind to this service
			return null;
		}
	}
}
