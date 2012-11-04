package org.asdra.gdglima;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author GDGLima Actividad principal que carga los datos y permite reproducir
 *         los sonidos.
 * 
 */
public class AsdraActivity extends BaseActivity implements OnClickListener {

	private static final long CHECK_DATA = 200L;
	private ImageView imgFigura = null;
	private TextView tvTitle = null;

	private static final String URL_DATA = "http://yoaprendo.hansyschmitt.com/data.json";
	private List<YoAprendoModel> listaYoAprendo = null;
	private ProgressDialog prgDialog = null;
	private int currentIndex = -1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asdra_layout);
		_init();
	}

	/**
	 * Método que inicia todos los valores para poder generar el cuadro de
	 * diálogo de carga, inicializar la lista, cargar los elementos del layout,
	 * determinar si la lista esta preparada y lanzar la tarea de descarga del
	 * archivo JSON
	 */
	private void _init() {
		prgDialog = new ProgressDialog(AsdraActivity.this);
		prgDialog.setCancelable(false);
		prgDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		prgDialog.setTitle(getResources().getString(R.string.app_name));
		prgDialog.setMessage(getResources().getString(R.string.loading));
		prgDialog.setIcon(getResources().getDrawable(R.drawable.ic_launcher));
		prgDialog.show();

		listaYoAprendo = new ArrayList<YoAprendoModel>();

		imgFigura = (ImageView) findViewById(R.id.imgFigura);
		tvTitle = (TextView) findViewById(R.id.tvTitle);

		handler.sendEmptyMessageDelayed(0, CHECK_DATA);
		new DownloadFileTask().execute();
	}

	/**
	 * 
	 * @author GDGLima Clase asíncrona que sirve para cargar el Raw Data del
	 *         JSON que tiene la lista de imagenes, url y nombres referenciales.
	 */
	private class DownloadFileTask extends AsyncTask<Void, Void, JSONArray> {

		@Override
		protected JSONArray doInBackground(Void... params) {
			String data = Utils.httpRequest(URL_DATA);
			JSONArray json = null;
			try {
				json = new JSONArray(data);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return json;
		}

		@Override
		protected void onPostExecute(JSONArray json) {
			super.onPostExecute(json);
			if (json != null) {
				json = Utils.shuffleJsonArray(json);
				int i, count = json.length();
				for (i = 0; i < count; i++) {
					try {
						String id = json.getJSONObject(i).getString("id")
								.trim();
						String titulo_imagen = json.getJSONObject(i)
								.getString("titulo_imagen").trim();
						String resumen_imagen = json.getJSONObject(i)
								.getString("resumen_imagen").trim();
						String imagen_url = json.getJSONObject(i)
								.getString("imagen_url").trim();
						String sonido_url = json.getJSONObject(i)
								.getString("sonido_url").trim();

						YoAprendoModel obj = new YoAprendoModel(id,
								titulo_imagen, resumen_imagen, sonido_url);
						listaYoAprendo.add(obj);

						new DownloadImageTask(i).execute(imagen_url);

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}

		}

	}

	/**
	 * 
	 * @author GDGLima Clase asíncrona que sirve para descargar la imagen
	 *         almacenada en la Web.
	 */
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

		private int index;

		public DownloadImageTask(int index) {
			this.index = index;
		}

		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			Bitmap bmp = null;
			try {
				InputStream in = new URL(url).openStream();
				bmp = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bmp;
		}

		protected void onPostExecute(Bitmap result) {
			listaYoAprendo.get(index).setImagen(result);
		}
	}

	/**
	 * Este handler se encarga de evaluar que los parametros del listado (Imagen
	 * y Sonido) esten listos y recién ahí permite interactuar con la
	 * aplicación.
	 */
	Handler handler = new Handler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			boolean sw = false;
			if (listaYoAprendo != null && !listaYoAprendo.isEmpty()) {
				for (int i = 0; i < listaYoAprendo.size(); i++) {
					Bitmap bmp = listaYoAprendo.get(i).getImagen();
					if (bmp != null) {
						sw = true;
					}
				}
			}
			if (!sw) {
				handler.sendEmptyMessageDelayed(0, CHECK_DATA);
			} else {

				currentIndex = 0;

				setData();

				imgFigura.setOnClickListener(AsdraActivity.this);

				((ImageButton) findViewById(R.id.btnPrev))
						.setOnClickListener(AsdraActivity.this);

				((ImageButton) findViewById(R.id.btnPlay))
						.setOnClickListener(AsdraActivity.this);

				((ImageButton) findViewById(R.id.btnNext))
						.setOnClickListener(AsdraActivity.this);

				prgDialog.hide();
				prgDialog.dismiss();

			}
			return false;
		}

	});

	/**
	 * Inicializa la data y para sonidos si es que hay alguno reproduciendose
	 * para no generar cruces de sonidos. Asigna el título, la imagen y la
	 * descripción al ImageView.
	 */
	private void setData() {
		pararSonidos();
		tvTitle.setText(listaYoAprendo.get(currentIndex).getTitulo());
		imgFigura.setImageBitmap(listaYoAprendo.get(currentIndex).getImagen());
		imgFigura.setContentDescription(listaYoAprendo.get(currentIndex)
				.getResumen());
	}

	@Override
	public void onClick(View v) {
		if (currentIndex > -1) {
			if (v.getId() == R.id.imgFigura) {
				listaYoAprendo.get(currentIndex).playSound();
			} else if (v.getId() == R.id.btnPrev) {
				currentIndex--;
				if (currentIndex == -1) {
					currentIndex = listaYoAprendo.size() - 1;
				}
				setData();
			} else if (v.getId() == R.id.btnPlay) {
				listaYoAprendo.get(currentIndex).playSound();
			} else if (v.getId() == R.id.btnNext) {
				currentIndex++;
				if (currentIndex == listaYoAprendo.size()) {
					currentIndex = 0;
				}
				setData();
			}
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		pararSonidos();
	}

	/**
	 * Detiene todos los sonidos si alguno se encuentra reproduciendo.
	 */
	private void pararSonidos() {
		if (listaYoAprendo != null && !listaYoAprendo.isEmpty()) {
			for (YoAprendoModel obj : listaYoAprendo) {
				if (obj.getSonido() != null && obj.getSonido().isPlaying()) {
					obj.getSonido().stop();
					obj.getSonido().prepareAsync();
				}
			}
		}
	}
}
