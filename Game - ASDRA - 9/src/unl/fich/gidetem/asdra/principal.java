package unl.fich.gidetem.asdra;

import java.util.Timer;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class principal extends Activity {
    /** Called when the activity is first created. */
	Thread thread;
	int segundos = 8;
	private Handler handler = new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button boton = (Button)findViewById(R.id.boton);
        boton.setOnClickListener(entrar);
    }
  
    
    private OnClickListener entrar = new OnClickListener()
    {

		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(principal.this, elije.class);
			startActivity(intent);
			finish();
		    }
    };
}