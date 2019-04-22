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
import java.util.ArrayList;
import willow.getsimplerocketsship.lite.util.*;
import willow.getsimplerocketsship.lite.view.*;

public class Collections extends Fragment
{
	AppCompatMain thiS;
	Toolbar toolbar;
	ArrayList<SavedItemTools.Flags> flags=new ArrayList<SavedItemTools.Flags>();
	ArrayList<SavedItem> mColle=new ArrayList<SavedItem>();
	RecyclerView recycle;

	private ColleAdapter ca;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{View view=inflater.inflate(R.layout.fragment_colle,container,false);
	thiS=(AppCompatMain) getActivity();
	toolbar=(Toolbar)view.findViewById(R.id.colle_toolbar);
	toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
	flags=SavedItemTools.initFlags(getActivity());
	mColle=SavedItemTools.initColleData(getActivity());
		toolbar.setNavigationOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
				//AppCompatMain acm=(AppCompatMain)getActivity();
				//acm.hideMe();
				thiS.hideMe();
					//getActivity().getSupportFragmentManager().beginTransaction().hide(Collections.this);
				}
			});
			toolbar.setTitle(R.string.item_colle);
	recycle=(RecyclerView)view.findViewById(R.id.colle_recycle);
	initRecy();
		return view;
	}
	void initRecy(){
		ca=new ColleAdapter(getActivity(),mColle,flags);
		LinearLayoutManager manager=new LinearLayoutManager(getActivity());
		recycle.setLayoutManager(manager);
		recycle.setItemAnimator(new DefaultItemAnimator());
		recycle.setAdapter(ca);
		recycle.setHasFixedSize(true);
		//ca.setHasStableIds(true);
		ca.setOnColleClickListener(new ColleAdapter.OnColleClickListener(){
				@Override
				public void onClick(View view, int id, boolean type,int position)
				{final int po=position;
					switch(view.getId()){
						case R.id.colle_dele:
							AlertDialog.Builder ab=new AlertDialog.Builder(getActivity());
							ab.setTitle("真的要删除吗？");
							ab.setPositiveButton("我手滑了~", null);
							ab.setNegativeButton("确定", new AlertDialog.OnClickListener(){

									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										((AppCompatMain)getActivity()).reFreashRecy(mColle,Integer.valueOf(mColle.get(po).getId()));
										mColle.remove(po);
										((AppCompatMain)getActivity()).collections=mColle;
										SavedItemTools.saveCollections(mColle,getActivity());	
										//ca.delItem(po);
										ca.upData(mColle);
									}
								});
							ab.show();
							break;
						case R.id.colle_edit:
							break;
					}
				}
			});
			ca.upData(mColle);
	}
}
