package willow.getsimplerocketsship.lite.view;


import android.widget.Toast;
import android.os.Handler;
import android.content.Context;
import android.support.v7.widget.*;

public class WiToast
{
	private static Toast mToast;
	private static Handler mHandler = new Handler();
	private static Runnable r = new Runnable() {
		public void run() {
			mToast.cancel();
		} 
	};
	public static void showToast(Context mContext, String text, int duration) {
		mHandler.removeCallbacks(r);
		if (mToast != null)
			mToast.setText(text);
		else
			mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
		mHandler.postDelayed(r, duration);
		mToast.show();
	}
	public  static void showToast(Context mContext, int resId, int duration) {
		showToast(mContext, mContext.getResources().getString(resId), duration);
	}
}
