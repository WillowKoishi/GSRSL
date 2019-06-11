package willow.getsimplerocketsship.lite.appcompat;
import willow.getsimplerocketsship.lite.base.BaseAppCompatActivity;
import android.os.*;
import willow.getsimplerocketsship.lite.R;
import android.view.*;
import android.content.*;

public class ThemeSettings extends BaseAppCompatActivity
{
SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appcompat_theme_setting);
		sp=getSharedPreferences("Theme",0);
	}
	public void o1(View v){
		sp.edit().putInt("Theme",0).commit();
		//sp.edit().putBoolean("callReCreate",true).commit();
		recreate();
	}
	public void o2(View v){
		sp.edit().putInt("Theme",1).commit();
		//sp.edit().putBoolean("callReCreate",true).commit();
		recreate();
	}
}
