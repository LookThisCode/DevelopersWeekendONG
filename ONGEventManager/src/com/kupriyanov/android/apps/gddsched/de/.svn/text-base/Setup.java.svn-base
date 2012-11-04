package com.kupriyanov.android.apps.gddsched.de;

import java.util.Locale;
import java.util.TimeZone;

import com.google.android.apps.iosched.util.ParserUtils;

public class Setup {


	/**
	 * Clone then google docs document from here
	 * @link https://spreadsheets.google.com/ccc?key=0Akgh73WhU1qHdHJmSUlSb0JIckowX1ZiQkhsYmdkdkE&hl=en&authkey=CIaKgpcD
	 *
	 * or from templates gallery
	 * 
	 * @link https://docs.google.com/templates?q=gddsched&sort=hottest&view=author
	 */

	public static final String WORKSHEETS_URL = "http://spreadsheets.google.com/feeds/worksheets/0Akgh73WhU1qHdHJmSUlSb0JIckowX1ZiQkhsYmdkdkE/public/basic";
	public static final String EXTRA_STATUS_RECEIVER = "com.kupriyanov.android.gddsched.extra.STATUS_RECEIVER";
	public static final TimeZone CONFERENCE_TIME_ZONE = TimeZone.getTimeZone("Europe/Berlin");

	public static final String TIME_ZONE = "+0100"; // RFC 822 - time zone

	public static final String DAY1_START = "2010-11-09T07:00:00.000+01:00";
	public static final String DAY1_END = "2010-11-09T20:00:00.000+01:00";

	public static final long CONFERENCE_START_MILLIS = ParserUtils.parseTime(DAY1_START);
	public static final long CONFERENCE_END_MILLIS = ParserUtils.parseTime(DAY1_END);

	public static final Locale LOCALE = Locale.US;
	// public static final Locale LOCALE = Locale.GERMANY;
	public static final String EVENT_YEAR = "2010";
	// public static final String DATETIME_FORMAT = "EEEE MMM d yyyy h:mma Z";
	public static final String DATETIME_FORMAT = "dd.MM.yyyy HH:mm Z";
	public static final String CONFERNCE_URL = "http://www.google.de/intl/en/events/developerday/2010/";
	public static final String CONTENT_AUTHORITY = "com.kupriyanov.android.apps.gddsched.de";
	public static final String DATABASE_NAME = "schedule_de.db";

	public static final boolean USE_WIFI_CHECK = false;
	public static final int VER_SESSION_HASHTAG = 21;

}