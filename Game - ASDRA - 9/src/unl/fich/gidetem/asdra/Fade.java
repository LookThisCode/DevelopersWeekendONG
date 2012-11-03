package unl.fich.gidetem.asdra;


import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Fade {

	/**
	 * handles all subclasses of View : TextView, Button, ImageView etc..
	 * given the component's id in their layout file
	 * */
	public static void runAlphaAnimation(Activity act, int viewId) {

	    // load animation XML resource under res/anim
	    Animation animation  = AnimationUtils.loadAnimation(act, R.anim.alpha);
	    if(animation == null){
		return; // here, we don't care
	    }
	    // reset initialization state
	    animation.reset();
	    // find View by its id attribute in the XML
	    View v = act.findViewById(viewId);
	    // cancel any pending animation and start this one
	    if (v != null){
	      v.clearAnimation();
	      v.startAnimation(animation);
	    }
	}
}

