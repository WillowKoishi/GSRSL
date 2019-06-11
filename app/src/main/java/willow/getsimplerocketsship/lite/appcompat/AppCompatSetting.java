package willow.getsimplerocketsship.lite.appcompat;
import android.support.v7.app.*;
import android.os.*;
import willow.getsimplerocketsship.lite.*;
import android.widget.FrameLayout;
import android.app.*;
import android.support.v7.widget.Toolbar;
import android.view.View.*;
import android.view.*;
import willow.getsimplerocketsship.lite.base.BaseAppCompatActivity;
import willow.getsimplerocketsship.lite.view.*;

public class AppCompatSetting extends BaseAppCompatActivity
{
	private FrameLayout fl;
	private Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		fl=(FrameLayout) findViewById(R.id.settingFrameLayout1);
		toolbar=(Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		AppCompatSettingView acp=new AppCompatSettingView();
		FragmentManager fm=getFragmentManager();
		FragmentTransaction ft=fm.beginTransaction();
		ft.add(R.id.settingFrameLayout1,acp);
		ft.commit();
		getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					finish();
				}
			});
	}

}
