package org.asdra.gdglima;

import java.io.IOException;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

/**
 * 
 * @author GDGLima Clase de utilidades: El mŽtodo para obtener request de la web
 *         (por ejemplo, para obtener el contenido del Json en String) y el de
 *         log personalizado para determinar si la App esta en DEBUG o no
 */
public class Utils {

	private static final boolean DEBUG = false;

	/**
	 * Carga el contenido de una Web y lo devuelve en formato de String
	 * 
	 * @param url
	 * @return
	 */
	public static String httpRequest(String url) {
		String response = null;
		HttpClient http_client = new DefaultHttpClient();
		HttpGet http_get = new HttpGet(url);
		try {
			HttpResponse http_response = http_client.execute(http_get);
			if (http_response.getStatusLine().getStatusCode() == 200) {
				response = EntityUtils.toString(http_response.getEntity())
						.trim();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * Realiza el Log en consola.
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void log(String tag, String msg) {
		if (DEBUG) {
			Log.d(tag, msg);
		}
	}

	/**
	 * Agradecimientos a:
	 * http://stackoverflow.com/questions/5531130/an-efficient
	 * -way-to-shuffle-a-json-array-in-java
	 * 
	 * @param array
	 * @return
	 * @throws JSONException
	 */
	public static JSONArray shuffleJsonArray(JSONArray array) {
		// Implementing FisherÐYates shuffle
		Random rnd = new Random();
		for (int i = array.length() - 1; i >= 0; i--) {
			int j = rnd.nextInt(i + 1);
			// Simple swap
			Object object = null;
			try {
				object = array.get(j);
				array.put(j, array.get(i));
				array.put(i, object);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}

		}
		return array;
	}

}
