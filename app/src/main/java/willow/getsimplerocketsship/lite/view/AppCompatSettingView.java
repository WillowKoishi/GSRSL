package willow.getsimplerocketsship.lite.view;
import android.support.v7.app.AppCompatActivity;
import willow.getsimplerocketsship.lite.R;
import android.os.*;
import android.support.v7.widget.Toolbar;
import android.view.View.OnClickListener;
import android.view.View;
import android.preference.*;
import java.util.*;
import android.preference.PreferenceActivity.*;

public class AppCompatSettingView extends PreferenceFragment
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getPreferenceManager().setSharedPreferencesName("GSRSL_Settings");
		addPreferencesFromResource(R.xml.settings);

	}
}
