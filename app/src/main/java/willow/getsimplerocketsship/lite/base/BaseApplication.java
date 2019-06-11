package willow.getsimplerocketsship.lite.base;
import android.app.Application;
import android.content.SharedPreferences;
import willow.getsimplerocketsship.lite.R;

public class BaseApplication extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		initTheme();
	}

	private void initTheme()
	{
		SharedPreferences sp=getSharedPreferences("",0);
		setTheme(sp.getInt("theme",R.style.AppTheme));
	}
	
}
