package willow.getsimplerocketsship.lite.base;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.content.SharedPreferences;
import willow.getsimplerocketsship.lite.R;

public class BaseAppCompatActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		initTheme();
	}
	

	private void initTheme()
	{
		SharedPreferences sp=getSharedPreferences("Theme",MODE_PRIVATE);
		//int i=sp.getInt("Theme",0);
		switch(sp.getInt("Theme",0)){
			case 0:
			setTheme(R.style.theme_sky);
				break;
			case 1:
			setTheme(R.style.theme_grass);
				break;
		}
	}
	
}

