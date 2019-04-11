package willow.getsimplerocketsship.lite.appcompat;
import android.support.v7.app.AppCompatActivity;
import willow.getsimplerocketsship.lite.R;
import android.os.*;
import android.support.v7.widget.Toolbar;
import android.view.View.OnClickListener;
import android.view.View;

public class AppCompatSetting extends AppCompatActivity
{

	private Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setTheme(R.style.AppTheme2);
		setContentView(R.layout.appcompat_setting);
		toolbar=(Toolbar)this.findViewById(R.id.toolbar);
		toolbar.setTitle(R.string.setting);
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true); 
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		//toolbar.setBackgroundColor(0xffFF658A);
		toolbar.setNavigationOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					finish();
				}
			});
	}
}
