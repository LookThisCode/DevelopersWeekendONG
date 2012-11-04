package org.asdra.gdglima;

import java.io.IOException;

import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;

/**
 * 
 * @author GDGLima
 * 
 *         Clase que sirve para administrar todo el contenido obtenido del JSON
 *         que almacena: id, titulo, resumen, url de la imagen y url del sonido.
 *         Que luego convierte la imagen a Bitmap y el sonido a un MediaPlayer
 * 
 */
public class YoAprendoModel {

	private String id;
	private String titulo;
	private String resumen;
	private Bitmap imagen;
	private String sonidoURL;
	private MediaPlayer sonido;

	/**
	 * M�todo constructor para iniciar los valores.
	 * 
	 * @param id
	 * @param titulo
	 * @param resumen
	 * @param sonidoURL
	 */
	public YoAprendoModel(String id, String titulo, String resumen,
			String sonidoURL) {
		this.id = id;
		this.titulo = titulo;
		this.resumen = resumen;
		this.sonidoURL = sonidoURL;
		this.imagen = null;
		sonido = new MediaPlayer();
		sonido.setAudioStreamType(AudioManager.STREAM_MUSIC);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public Bitmap getImagen() {
		return imagen;
	}

	public void setImagen(Bitmap imagen) {
		this.imagen = imagen;
	}

	public MediaPlayer getSonido() {
		return sonido;
	}

	public void setSonido(MediaPlayer sonido) {
		this.sonido = sonido;
	}

	/**
	 * Reproduce el sonido. Si un sonido existe lo resetea, vuelve a cargar el
	 * recurso (lo cual hace que se demore un poco) y lo reproduce. Esto
	 * optimiza mucho la memoria.
	 */
	public void playSound() {
		sonido.reset();

		try {
			sonido.setDataSource(sonidoURL);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sonido.setLooping(false);

		try {
			sonido.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sonido.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				mp.start();
			}
		});

	}

}
