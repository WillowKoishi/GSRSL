package willow.getsimplerocketsship.lite.fragment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.os.*;
import android.support.v7.widget.Toolbar;
import willow.getsimplerocketsship.lite.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import willow.getsimplerocketsship.lite.appcompat.AppCompatMain;
import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;

public class DeltaVCalculater extends Fragment
{
	Toolbar toolbar,referbar;
	DrawerLayout drawer;
	LinearLayout refer;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{View view=inflater.inflate(R.layout.fragment_dv,container,false);
		toolbar=(Toolbar)view.findViewById(R.id.dv_toolbar);
		toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
		toolbar.setNavigationOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					AppCompatMain acm=(AppCompatMain)getActivity();
					acm.hideMe();
					//getActivity().getSupportFragmentManager().beginTransaction().hide(Collections.this);
				}
			});
		toolbar.setTitle(R.string.item_DV);
		referbar=(Toolbar)view.findViewById(R.id.dv_toolbar_refer);
		referbar.setTitle(R.string.reference);
		drawer=(DrawerLayout)view.findViewById(R.id.dv_drawer_layout);
		return view;
	}
	
}
