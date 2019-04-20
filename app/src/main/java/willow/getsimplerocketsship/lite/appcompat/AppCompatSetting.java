package willow.getsimplerocketsship.lite.appcompat;
import android.support.v7.app.AppCompatActivity;
import willow.getsimplerocketsship.lite.R;
import android.os.*;
import android.support.v7.widget.Toolbar;
import android.view.View.OnClickListener;
import android.view.View;
import android.preference.*;

public class AppCompatSetting extends PreferenceFragment
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}

	
}
