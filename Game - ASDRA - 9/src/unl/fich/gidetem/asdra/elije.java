package unl.fich.gidetem.asdra;

import java.util.Timer;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class elije extends Activity {
	
	ImageButton imagencaballo;
	ImageButton imagenperro;
	ImageButton imagengato;
	int cantidad = 0;
	Thread thread;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elige);
		
        imagencaballo = (ImageButton)findViewById(R.id.caballo);
        imagencaballo.setOnClickListener(presionaCaballo);
        
        imagenperro = (ImageButton)findViewById(R.id.perro);
        imagenperro.setOnClickListener(presionaPerro);
        
        imagengato = (ImageButton)findViewById(R.id.gato);
        imagengato.setOnClickListener(presionaGato);
        
        Fade.runAlphaAnimation(this, imagencaballo.getId());
        Fade.runAlphaAnimation(this, imagenperro.getId());
        Fade.runAlphaAnimation(this, imagengato.getId());


        
        imagencaballo.setImageResource(R.drawable.gift);
        imagenperro.setImageResource(R.drawable.gift);
        imagengato.setImageResource(R.drawable.gift);
        
        Toast.makeText(getApplicationContext(), "¿DONDE ESTA EL PERRO?", Toast.LENGTH_SHORT).show();
        
        if (cantidad==3) {
        	 Toast.makeText(getApplicationContext(), "MUY BIEN, ¡LO LOGRASTE!", Toast.LENGTH_SHORT).show();
        }
        
    }
    
    private OnClickListener presionaCaballo = new OnClickListener()
    {

		public void onClick(View v) {
			imagencaballo.setImageResource(R.drawable.caballo);
			Toast.makeText(getApplicationContext(), "¿DONDE ESTA EL GATO?", Toast.LENGTH_SHORT).show();
			if (cantidad==3) {
				Toast.makeText(getApplicationContext(), "MUY BIEN, ¡LO LOGRASTE!", Toast.LENGTH_SHORT).show();
		        } else {
		        	cantidad++;
		    }
		}
    };
    
    private OnClickListener presionaPerro = new OnClickListener()
    {

		public void onClick(View v) {
			imagenperro.setImageResource(R.drawable.perro);
			Toast.makeText(getApplicationContext(), "¿DONDE ESTA EL CABALLO?", Toast.LENGTH_SHORT).show();
			if (cantidad==3) {
				Toast.makeText(getApplicationContext(), "MUY BIEN, ¡LO LOGRASTE!", Toast.LENGTH_SHORT).show();
		        } else {
		        	cantidad++;
		    }
		}
    };
    
    private OnClickListener presionaGato = new OnClickListener()
    {

		public void onClick(View v) {
			imagengato.setImageResource(R.drawable.gato);
			Toast.makeText(getApplicationContext(), "¿DONDE ESTA EL PERRO?", Toast.LENGTH_SHORT).show();
			if (cantidad==3) {
				Toast.makeText(getApplicationContext(), "MUY BIEN, ¡LO LOGRASTE!", Toast.LENGTH_SHORT).show();
		        } else {
		        	cantidad++;
		    }
		}
    };
    
}