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
import android.support.v7.widget.helper.*;
import android.graphics.*;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.support.v7.widget.Toolbar;
import android.widget.RadioGroup.*;
import android.widget.*;
import android.support.design.widget.*;
import android.text.*;
import android.view.inputmethod.*;

public class Collections extends Fragment
{
	AppCompatMain thiS;
	Toolbar toolbar;
	ArrayList<SavedItemTools.Flags> flags=new ArrayList<SavedItemTools.Flags>();
	public ArrayList<SavedItem> mColle=new ArrayList<SavedItem>();
	RecyclerView recycle;
	ColleItemHelper helper;
	private ColleAdapter ca;
	private String flagName1;
	private AlertDialog a;

	private String flagna;

	private boolean type;

	private AlertDialog ad;

	public void addColle(ArrayList<SavedItem> colle)
	{
		//mColle.add(0,ci);
		mColle = colle;//SavedItemTools.initColleData(getActivity());
		ca.upData(mColle);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{View view=inflater.inflate(R.layout.fragment_colle, container, false);
		thiS = (AppCompatMain) getActivity();
		toolbar = (Toolbar)view.findViewById(R.id.colle_toolbar);
		toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
		flags = SavedItemTools.initFlags(getActivity());
		mColle = SavedItemTools.initColleData(getActivity());
		toolbar.setNavigationOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					thiS.hideMe();

				}
			});
		toolbar.setTitle(R.string.item_colle);
		recycle = (RecyclerView)view.findViewById(R.id.colle_recycle);
		initRecy();
		return view;
	}
	void initRecy()
	{
		ca = new ColleAdapter(getActivity(), mColle, flags);
		LinearLayoutManager manager=new LinearLayoutManager(getActivity());
		helper = new ColleItemHelper(ca);
		ItemTouchHelper iHelper=new ItemTouchHelper(helper);
		iHelper.attachToRecyclerView(recycle);
		recycle.setLayoutManager(manager);
		recycle.setItemAnimator(new DefaultItemAnimator());
		recycle.setAdapter(ca);
		recycle.setHasFixedSize(true);
		//recycle.set
		//ca.setHasStableIds(true);
		ca.setOnColleClickListener(new ColleAdapter.OnColleClickListener(){
				@Override
				public void onClick(View view, int id, boolean type, int position)
				{final int po=position;
					switch (view.getId())
					{
						case R.id.colle_icon:
							initFlags(position, true, null);
							break;
						case R.id.colle_start_game:
							((AppCompatMain)getActivity()).startSR(id, type);
							thiS.hideMe();
							break;
						case R.id.colle_dele:
							AlertDialog.Builder ab=new AlertDialog.Builder(getActivity());
							ab.setTitle("真的要删除吗？");
							ab.setPositiveButton("我手滑了~", null);
							ab.setNegativeButton("确定", new AlertDialog.OnClickListener(){

									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										((AppCompatMain)getActivity()).reFreashRecy(mColle, Integer.valueOf(mColle.get(po).getId()));
										mColle.remove(po);
										((AppCompatMain)getActivity()).collections = mColle;
										SavedItemTools.saveCollections(mColle, getActivity());	
										//ca.delItem(po);
										ca.upData(mColle);
									}
								});
							ab.show();
							break;
						case R.id.colle_edit:
							editColle(position, mColle.get(position).mIcon);
							break;
					}
				}
			});
		ca.upData(mColle);
	}
	String initFlags(final int posi, final boolean refrash, final ImageView iv)
	{
		flagName1 = mColle.get(posi).mIcon;
		AlertDialog.Builder ab=new AlertDialog.Builder(getActivity());
		ab.setTitle("选择图标");
		View view2=View.inflate(getActivity(), R.layout.dialog_select_colle_icon, null);
		RecyclerView iconRv=(RecyclerView)view2.findViewById(R.id.colle_dialog_recycle);
		GridLayoutManager girdLayoutManager=new GridLayoutManager(getActivity(), 3);
		//StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,OrientationHelper.VERTICAL);
		iconRv.setLayoutManager(girdLayoutManager);
		ColleIconRecyAdapter cira=new ColleIconRecyAdapter();
		cira.putFlags(flags);
		iconRv.setAdapter(cira);
		iconRv.setHasFixedSize(true);
		String iii=flagName1;
		cira.setOnIconClickListener(new ColleIconRecyAdapter.OnIconClickListener(){
				@Override
				public void onClick(Bitmap flag, String flagName)
				{
					flagName1 = flagName;
					//iii=flagName;
					if (refrash)
					{
						SavedItem si= mColle.get(posi);
						si.setIcon(flagName);
						mColle.set(posi, si);
						ca.upData(mColle);
					}
					else
					{
						iv.setImageBitmap(flag);
					}
					a.dismiss();
				}
			});
		iii = flagName1;
		ab.setView(view2);
		ab.setNegativeButton("取消", null);
		a = ab.create();
		a.show();

		//ab.show();
		return iii;
	}
	void editColle(final int position, final String flagn)
	{flagName1 = flagn;
		flagna = flagn;
		type = mColle.get(position).getType();
		AlertDialog.Builder ab=new AlertDialog.Builder(getActivity());
		View view3=View.inflate(getActivity(), R.layout.dialog_colle_edit, null);
		final ImageView iv=(ImageView)view3.findViewById(R.id.dialog_colle_edit_icon);
		for (int i=0;i < flags.size();i++)
		{
			SavedItemTools.Flags f=flags.get(i);
			if (f.flagName.equals(mColle.get(position).mIcon))
			{
				iv.setImageBitmap(f.flag);
			}
		}

		TextView tv=(TextView) view3.findViewById(R.id.dialog_colle_edit_icon_text);
		OnClickListener iconlistener=new OnClickListener(){
			@Override
			public void onClick(View p1)
			{
				flagna = initFlags(position, false, iv);
			}
		};
		RadioGroup rg=(RadioGroup) view3.findViewById(R.id.dialog_colle_edit_RadioGroup);
		rg.check(!type ?R.id.dialog_colle_edit_sandbox: R.id.dialog_colle_edit_ship);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(RadioGroup p1, int p2)
				{
					//RadioButton rb=p1.findViewById(
					switch (p2)
					{
						case R.id.dialog_colle_edit_sandbox:
							type = false;
							break;
						case R.id.dialog_colle_edit_ship:
							type = true;
							break;
					}
				}
			});
		TextInputLayout til=(TextInputLayout) view3.findViewById(R.id.dialog_til);
		TextInputLayout til2=(TextInputLayout) view3.findViewById(R.id.dialog_til2);
		til.setHint("名称");
		til2.setHint("ID");
		final EditText et1=(EditText) view3.findViewById(R.id.dialog_colle_edit_name);
		final EditText et2=(EditText) view3.findViewById(R.id.dialog_colle_edit_id);
		et1.setFocusable(true);
		et2.setFocusable(true);
		et1.setText(mColle.get(position).getName());
		et2.setText(mColle.get(position).getId());
		iv.setOnClickListener(iconlistener);
		tv.setOnClickListener(iconlistener);
		ab.setTitle("编辑");
		ab.setView(view3);
		Button b=(Button) view3.findViewById(R.id.dialog_colle_edit_save);
		b.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View view)
				{
					if (et1.getText().toString().indexOf("@c0lle") > -1
						|| et1.getText().toString().indexOf("@di") > -1
						|| et1.getText().toString().indexOf("@hist0ry") > -1)
					{
						WiToast.showToast(getActivity(), "命名不规范，软件两行泪<(｀^´)>", 2000);
					}
					else if (TextUtils.isEmpty(et1.getText().toString()))
					{
						WiToast.showToast(getActivity(), "你还没有输入名称！", 2000);

					}
					else if (TextUtils.isEmpty(et2.getText().toString()))
					{
						WiToast.showToast(getActivity(), "你还没有输入ID!", 2000);
					}
					else
					{
						SavedItem si= mColle.get(position);
						si.setIcon(flagName1);
						si.mName = et1.getText().toString();
						si.mId = et2.getText().toString();
						si.mType = type;
						mColle.set(position, si);
						ca.upData(mColle);
						ad.dismiss();
						InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.toggleSoftInput(1,2);
						
						//et1.setVisibility(View.GONE);
						//et2.setVisibility(View.GONE);
					}
				}
			});
		Button b2=(Button) view3.findViewById(R.id.dialog_colle_edit_cancle);
		b2.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					ad.dismiss();
					InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.toggleSoftInput(1,2);
				}
			});
		ad = ab.create();
		ad.show();
		mHandler.postDelayed(mr,100);
		}
	Runnable mr=new Runnable(){

		@Override
		public void run()
		{
			InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);

			
			mHandler.sendEmptyMessage(0);
		}
	};
	Handler mHandler=new Handler(){

		@Override
		public void handleMessage(Message msg)
		{
			// TODO: Implement this method
			super.handleMessage(msg);
		}
		
	
	};
}
