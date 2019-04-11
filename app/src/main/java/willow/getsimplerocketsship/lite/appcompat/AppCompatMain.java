package willow.getsimplerocketsship.lite.appcompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.NavigationView;
import willow.getsimplerocketsship.lite.R;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.content.DialogInterface;
import willow.getsimplerocketsship.lite.view.RecyclerAdapter;
import willow.getsimplerocketsship.lite.view.WiToast;
import willow.getsimplerocketsship.lite.view.CardPopupWindow;
import willow.getsimplerocketsship.lite.util.SavedItem;
import  java.util.ArrayList;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.MenuItem;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Menu;
import android.support.v7.widget.AppCompatButton;
import android.view.View.OnClickListener;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.content.Intent;



public class AppCompatMain extends AppCompatActivity
{
	private DrawerLayout drawer;
	private NavigationView NaView;
	//private FloatingActionButton fab;
	private Toolbar toolbar;
	private CoordinatorLayout coordinatorLayout;
	private FragmentTransaction fragmentTransaction;
	private RecyclerView rv;
	private long startTime;
	private AppCompatEditText aedit;
	private AppCompatButton getsandbox,getship;
	private TextInputLayout til;
	private CollapsingToolbarLayout ctl;
	//private ArrayList<String> id;
	//private ArrayList<Boolean> type;
	private ArrayList<SavedItem> history;
	private RecyclerView.LayoutManager manager;
	private  RecyclerAdapter adapter;
	private CardPopupWindow cpw;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appcompat_main);
		sp = getSharedPreferences("GSRSL_History", MODE_PRIVATE);
		drawer = (DrawerLayout)this.findViewById(R.id.drawer_layout);
		NaView = (NavigationView)this.findViewById(R.id.id_nv_menu);
		rv = (RecyclerView)this.findViewById(R.id.recyclerview);
		//fab=(FloatingActionButton)this.findViewById(R.id.fab_delete);
		coordinatorLayout = (CoordinatorLayout)this.findViewById(R.id.appcompatmainCoordinatorLayout1);
		ctl = (CollapsingToolbarLayout)this.findViewById(R.id.ctl1);
		til = (TextInputLayout)this.findViewById(R.id.fragment_til);
		aedit = (AppCompatEditText)this.findViewById(R.id.main_getship_edit);
		toolbar = (Toolbar)this.findViewById(R.id.toolbar);
		getsandbox = (AppCompatButton)ctl.findViewById(R.id.getsandbox);
		getship = (AppCompatButton)ctl.findViewById(R.id.getship);
		//getsandbox.setFocusable(true);
		//getsandbox.setFocusableInTouchMode(true);
		getsandbox.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					//WiToast.showToast(AppCompatMain.this,"sandbox",3000);
					if (!isEmpty(aedit.getText().toString()))
					{
						addHistory(aedit.getText().toString(), false);
						saveHistory();
					}
				}
			});
		//getship.setFocusable(true);
		//getship.setFocusableInTouchMode(true);
		getship.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					if (!isEmpty(aedit.getText().toString()))
					{
						addHistory(aedit.getText().toString(), true);
						saveHistory();
					}
				}
			});
		toolbar.setTitle(R.string.app_name);
		til.setHint(getString(R.string.hint_inputid));
		ctl.setTitle(getString(R.string.app_name));
		ctl.setExpandedTitleColor(0xffffff);
		ctl.setFocusableInTouchMode(true);
		ctl.setFocusable(true);
		ctl.setClickable(true);
		history=new ArrayList<SavedItem>();
		toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light);
		setSupportActionBar(toolbar);
		//id = new ArrayList<String>();
		//type = new ArrayList<Boolean>();
		initData();
		getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		//setSelectedItemId(NaView.getMenu().getItem(position).getItemId());
		android.support.v7.app.ActionBarDrawerToggle drawerToggle = new android.support.v7.app.ActionBarDrawerToggle(AppCompatMain.this, drawer, toolbar, R.string.whatnew , R.string.about) {
			@Override
			public void onDrawerOpened(View drawerView)
			{
				super.onDrawerOpened(drawer);
			}

			@Override
			public void onDrawerClosed(View drawerView)
			{
				super.onDrawerClosed(drawerView);
			}
		};
		drawerToggle.syncState();
        //绑定监听器
        drawer.setDrawerListener(drawerToggle);
		setupDrawerContent(NaView);
		FragmentManager fm=getSupportFragmentManager();
		fragmentTransaction = fm.beginTransaction();
		//fragmentTransaction.add(R.id.main_fragment,acgs);
		//fragmentTransaction.commit();
	}
	private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(

			new NavigationView.OnNavigationItemSelectedListener()
			{

				@Override
				public boolean onNavigationItemSelected(MenuItem menuItem)
				{
					menuItem.setChecked(true);
					switch (menuItem.getItemId())
					{
						case R.id.item_setting:
							startActivity(new Intent(AppCompatMain.this,AppCompatSetting.class));
							break;
					}
					drawer.closeDrawers();
					return true;
				}
			});
    }
	@Override  
	public void onBackPressed()
	{  
		if (drawer.isDrawerVisible(Gravity.LEFT))
		{
			drawer.closeDrawer(Gravity.LEFT);
		}
		else
		{
			long currentTime = System.currentTimeMillis();  
			if ((currentTime - startTime) >= 2000)
			{    
				startTime = currentTime;  
				//	Snackbar sb=new Snackbar();
				Snackbar.make(coordinatorLayout, R.string.back_again, Snackbar.LENGTH_SHORT).show();
				//WiToast.showToast(this,R.string.back_again,2000);
			}
			else
			{  
				finish();  
			} 
		}
	} 
	public void initRecy()
	{
		manager = new LinearLayoutManager(this);
		//manager.setOrientation(OrientationHelper.VERTICAL);
		adapter = new RecyclerAdapter(this);//, id, type);
		rv.setHasFixedSize(true);
		rv.setItemAnimator(new DefaultItemAnimator());
		rv.setLayoutManager(manager);
		//neoRecyAdapter nra=new neoRecyAdapter(this);
		//nra.setHasStableIds(true);
		//rv.setAdapter(nra);
		//nra.upData(id,type);
		//nra.notifyDataSetChanged();
		adapter.setHasStableIds(true);
		adapter.setOnitemClickLintener(new RecyclerAdapter.onHistoryClick(){

				@Override
				public void onClick(String mid, Boolean mtype, String time, final int position)
				{String info=!mtype ?"沙盒存档": "载具存档";
					//	if (type)
					//		{
					//			info = "载具存档";
					//		}
					//WiToast.showToast(AppCompatMain.this, id + type, 3000);
					//adapter.delItem(position);
					cpw = new CardPopupWindow(AppCompatMain.this, new AdapterView.OnItemClickListener(){

							@Override
							public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
							{
								switch ((int)p4)
								{
									case 0:
										//id.add(0, id.get(position));
										//id.remove(position + 1);
										//type.add(0, type.get(position));
										//type.remove(position + 1);
										history.add(0,history.get(position));
										history.remove(position+1);
										//saveHistory();
										adapter.moveItem(position,history);	
										rv.scrollToPosition(0);
										cpw.dismiss();
										break;
									case 1:
										break;
									case 2:
										break;
									case 3:
										AlertDialog.Builder ab=new AlertDialog.Builder(AppCompatMain.this);
										ab.setTitle("要删除这条记录吗？");
										//ab.setMessage("");
										ab.setPositiveButton("我手滑了~", null);
										ab.setNegativeButton("确定", new AlertDialog.OnClickListener(){

												@Override
												public void onClick(DialogInterface p1, int p2)
												{
													adapter.delItem(position);
													cpw.dismiss();
												}
											});
										ab.show();
										break;
									case 4:
										cpw.dismiss();
										break;
								}
							}
						}
						, info + "  ID=" + mid);
					cpw.showAtLocation(AppCompatMain.this.findViewById(R.id.drawer_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
				}
			});
		rv.setAdapter(adapter);
		adapter.updata(history);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{getMenuInflater().inflate(R.menu.menu1, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{switch (item.getItemId())
		{
			case R.id.toolbar_about:
				break;
			case R.id.toolbar_clear_history:
				AlertDialog.Builder ab=new AlertDialog.Builder(AppCompatMain.this);
				ab.setTitle("要删除所有历史记录吗？");
				//ab.setMessage("");
				ab.setPositiveButton("我手滑了~", null);
				ab.setNegativeButton("确定", new AlertDialog.OnClickListener(){

						@Override
						public void onClick(DialogInterface p1, int p2)
						{
							adapter.clearAll();
							//id.clear();
							//type.clear();
							history.clear();
							saveHistory();
						}});
				ab.show();
				break;
			case R.id.toolbar_exit:
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void initData()
	{
		String[] histor=new String[]{};
		if (!isEmpty(sp.getString("history", null)))
		{
			histor = sp.getString("history", null).split("@hist0ry");//@hist0ry ID @di type @di time
		}
		if (histor != null)
		{
			for (int i=0;i < histor.length;i++)
			{
				String[] hisItem=histor[i].split("@di");
				SavedItem si=new SavedItem();
				si.initHistory(hisItem[0],Integer.valueOf(hisItem[1]) == 1 ?true:false,"2019",null);
				history.add(si);
				//id.add(hisItem[0]);
				//type.add(Integer.valueOf(hisItem[1]) == 1 ?true: false);
			}
		}
		else
		{
			//id = null;
			//type = null;
			history=null;
		}
		//	for (int i=1;i < 40;i++)
		//	{		id.add(Integer.toString(1000000 + i));
		//		double b=(Math.random());
		//		if (b > 0.5)
		//		{
		//			type.add(true);
		//		}
		//		else
		//	{
		//			type.add(false);
		//		}	
		//	}
		initRecy();
	}
	void addHistory(String mId, Boolean mType)
	{
		//id.add(0, mId);
		//type.add(0, mType);
		SavedItem si=new SavedItem();
		si.initHistory(mId,mType,"2019",null);
		history.add(0,si);
		adapter.addItem(history);
		//rv.scrollToPosition(0);
	}
	void saveHistory()
	{
		String mH = null;
		for//(int i=id.size()-1;i>=0;i--)
		(int i=0;i < history.size();i++)
		{SavedItem si=new SavedItem();
		si=history.get(i);
			mH = (isEmpty(mH) ?"": mH) + si.getId() + "@di" + (si.getType() ?1: 0) + "@di" + si.getTime() + (!(i == history.size()) ?"@hist0ry": "");
		}
		sp.edit().putString("history", mH).commit();
	}
	boolean isEmpty(String s)
	{
		return TextUtils.isEmpty(s);
	}
	@Override
	protected void onPause()
	{
		saveHistory();
		super.onPause();
	}

	@Override
	protected void onStop()
	{
		saveHistory();
		super.onStop();
	}

	@Override
	protected void onDestroy()
	{
		saveHistory();
		super.onDestroy();
	}

}
//
