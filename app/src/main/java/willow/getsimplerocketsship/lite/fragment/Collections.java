package willow.getsimplerocketsship.lite.fragment;
import android.support.v4.app.Fragment;
import willow.getsimplerocketsship.lite.R;
import android.view.*;
import android.os.*;
import android.support.v7.widget.*;
import android.support.v4.app.*;
import android.support.v7.app.*;
import android.content.*;
import android.view.View.*;
import willow.getsimplerocketsship.lite.appcompat.*;

public class Collections extends Fragment
{
	Toolbar toolbar;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{View view=inflater.inflate(R.layout.fragment_colle,container,false);
	toolbar=(Toolbar)view.findViewById(R.id.colle_toolbar);
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
			toolbar.setTitle(R.string.item_colle);
		return view;
	}
}
