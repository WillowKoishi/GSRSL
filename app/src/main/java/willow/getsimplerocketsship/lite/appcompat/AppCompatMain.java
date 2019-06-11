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
import android.net.Uri;
import java.io.InputStream;
import android.content.Context;
import java.io.IOException;
import android.widget.FrameLayout;
import willow.getsimplerocketsship.lite.fragment.*;
import willow.getsimplerocketsship.lite.view.RecyclerAdapter.onHistoryClick;
import android.view.View;
import java.sql.Date;
import java.text.SimpleDateFormat;
import willow.getsimplerocketsship.lite.base.BaseAppCompatActivity;
import android.widget.ImageButton;
import willow.getsimplerocketsship.lite.util.SavedItemTools;
import android.view.KeyEvent;
import android.support.v4.app.*;
import willow.getsimplerocketsship.lite.util.*;
import android.support.v7.widget.helper.*;

public class AppCompatMain extends BaseAppCompatActivity
{
	public DrawerLayout drawer;
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
	public ArrayList<SavedItem> history,collections;
	private RecyclerView.LayoutManager manager;
	private  RecyclerAdapter adapter;
	private CardPopupWindow cpw;
	private SharedPreferences sp;
	private FrameLayout frame;
	private ImageButton setTheme;
	private Collections colle;
	private DeltaVCalculater dvc;

	private SharedPreferences sp2;

	private HistoryItemHelper helper;

	public void reFreashRecy(ArrayList<SavedItem> collec, int id)
	{
		adapter.reFreashColle(collec, id);
	}

	public void hideMe()
	{
		fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
		fragmentTransaction.hide(colle);
		fragmentTransaction.hide(dvc);
		fragmentTransaction.commit();
		coordinatorLayout.setVisibility(View.VISIBLE);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appcompat_main);
		sp = getSharedPreferences("GSRSL_History", MODE_PRIVATE);
		//sp2=getSharedPreferences("willow.getsimplerocketsship.lite_preferences",MODE_PRIVATE);
		sp2 = getSharedPreferences("GSRSL_Settings", MODE_PRIVATE);
		drawer = (DrawerLayout)this.findViewById(R.id.drawer_layout);

		NaView = (NavigationView)this.findViewById(R.id.id_nv_menu);
		rv = (RecyclerView)this.findViewById(R.id.recyclerview);
		frame = (FrameLayout)this.findViewById(R.id.main_fragment);
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
						//addHistory(aedit.getText().toString(), false);
						//saveHistory();
						//startActivity(new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("simplerockets://03" + aedit.getText())));
						startSR(Integer.valueOf(aedit.getText().toString()), false);
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
						//addHistory(aedit.getText().toString(), true);
						//saveHistory();
						//startActivity(new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("simplerockets://00" + aedit.getText())));
						startSR(Long.valueOf(aedit.getText().toString()), true);
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
		setTheme = (ImageButton)this.findViewById(R.id.setTheme);
		setTheme.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					startActivity(new Intent(AppCompatMain.this, ThemeSettings.class));
				}
			});
		history = new ArrayList<SavedItem>();
		toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light);
		setSupportActionBar(toolbar);
		//id = new ArrayList<String>();
		//type = new ArrayList<Boolean>();
		//load load=new load();
		//load.execute();
		history = SavedItemTools.initHistoryData(AppCompatMain.this);
		collections = SavedItemTools.initColleData(AppCompatMain.this);
		initRecy();
		getSupportActionBar().setHomeButtonEnabled(true);
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
		//NaView.setc
		FragmentManager fm=getSupportFragmentManager();
		fragmentTransaction = fm.beginTransaction();
		colle = new Collections();
		dvc = new DeltaVCalculater();
		//AppCompatSetting acs=new AppCompatSetting();
		fragmentTransaction.add(R.id.main_fragment, colle);
		fragmentTransaction.add(R.id.main_fragment, dvc);
		//fragmentTransaction.add(R.id.main_fragment,((F)acs));
		fragmentTransaction.hide(colle);
		fragmentTransaction.hide(dvc);
		fragmentTransaction.commit();
		//dvc.
	}
	public void startSR(long id, boolean type)
	{
		boolean a=false;
		Date date=new Date(System.currentTimeMillis());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm");
		int position=0;
		for (int i=0;i < history.size();i++)
		{
			String aa=history.get(i).getId();
			String bb=String.valueOf(id);
			boolean cc=aa.indexOf(bb) >= 0 && bb.indexOf(aa) >= 0;
			if (cc)
			{	
				position = i;
				a = true;}
		}
		if (a)
		{
			SavedItem si=history.get(position);
			si.changeType(type);
			si.changeTime(sdf.format(date));
			history.add(0, si);
			history.remove(position + 1);
			adapter.moveItem(position, history);	
			saveHistory();
			//WiToast.showToast(AppCompatMain.this, "cf" + history.get(position).getId(), 2000);
			startActivity(new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("simplerockets://" + (type ?"00": "03") + id)));
		}
		else if (!a)
		{
			//WiToast.showToast(AppCompatMain.this, "j" + position + a + history.size(), 2000);
			addHistory(String.valueOf(id) , type, sdf.format(date));
			saveHistory();
			startActivity(new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("simplerockets://" + (type ?"00": "03") + id)));
		}
		if (sp2.getBoolean("start_sr_and_exit", false))
		{
			finish();
		}
	}
	private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(

			new NavigationView.OnNavigationItemSelectedListener()
			{

				@Override
				public boolean onNavigationItemSelected(MenuItem menuItem)
				{FragmentManager fm=getSupportFragmentManager();
					fragmentTransaction = fm.beginTransaction();
					fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
					menuItem.setChecked(true);

					switch (menuItem.getItemId())
					{
						case R.id.item_get:
							fragmentTransaction.hide(colle);
							fragmentTransaction.hide(dvc);
							coordinatorLayout.setVisibility(View.VISIBLE);
							break;
						case R.id.item_collection:
							//	hideMe();
							fragmentTransaction.hide(dvc);
							fragmentTransaction.show(colle);
							coordinatorLayout.setVisibility(View.GONE);
							break;
						case R.id.item_cal_dv:
							//hideMe();
							fragmentTransaction.hide(colle);
							fragmentTransaction.show(dvc);
							coordinatorLayout.setVisibility(View.GONE);
							break;
						case R.id.item_setting:
							startActivity(new Intent(AppCompatMain.this, AppCompatSetting.class));// AppCompatSetting.class));
							break;
					}
					fragmentTransaction.commit();
					drawer.closeDrawers();
					//if(menuItem.getItemId()!=R.id.item_setting){
					//navigationView.setChecked();
					//}
					return true;
				}
			});
    }

//	@Override  
//	public void onBackPressed()
//	{  
//		if (drawer.isDrawerVisible(Gravity.LEFT))
//		{
//			drawer.closeDrawer(Gravity.LEFT);
//		}
//		else if(!dvc.isHidden()||!colle.isHidden()){
//			hideMe();
//		}
//		else
//		{
//			long currentTime = System.currentTimeMillis();  
//			if ((currentTime - startTime) >= 2000)
//			{    
//				startTime = currentTime;  
//					//Snackbar sb = null;//=new Snackbar();	
//				Snackbar.make(frame,R.string.back_again, Snackbar.LENGTH_SHORT).show();
//			//	WiToast.showToast(this,R.string.back_again,2000);
//			}
//			else
//			{  
//				finish();  
//			} 
//		}
//	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{boolean reTurn=true;

		if (keyCode == KeyEvent.KEYCODE_BACK)
		{

			if (dvc.drawer.isDrawerOpen(Gravity.RIGHT) && !dvc.isHidden())
			{
				dvc.drawer.closeDrawer(Gravity.RIGHT);
			}
			else
			if (drawer.isDrawerVisible(Gravity.LEFT))
			{
				drawer.closeDrawer(Gravity.LEFT);
			}
			else if (!dvc.isHidden() || !colle.isHidden())
			{
				hideMe();
			}
			else
			{
				long currentTime = System.currentTimeMillis();  
				if ((currentTime - startTime) >= 2000)
				{    
					startTime = currentTime;  
					//Snackbar sb = null;//=new Snackbar();	
					Snackbar.make(frame, R.string.back_again, Snackbar.LENGTH_SHORT).show();
					//	WiToast.showToast(this,R.string.back_again,2000);
				}
				else
				{  
					finish();  
				} 
			}	
		}
		else if (keyCode == KeyEvent.KEYCODE_MENU)
		{

			if (drawer.isDrawerVisible(Gravity.LEFT))
			{
				drawer.closeDrawer(Gravity.LEFT);
			}
			else
			{
				drawer.openDrawer(Gravity.LEFT);
				if (!dvc.isHidden())
				{
					if (dvc.drawer.isDrawerVisible(Gravity.RIGHT))
					{
						dvc.drawer.closeDrawer(Gravity.RIGHT);
					}
				}
			}
		}
		else
		{
			reTurn = false;
		}
		return reTurn;
	} 
	public void initRecy()
	{
		manager = new LinearLayoutManager(this);
		//manager.setOrientation(OrientationHelper.VERTICAL);
		adapter = new RecyclerAdapter(this);//, id, type);
		rv.setHasFixedSize(true);
		rv.setItemAnimator(new DefaultItemAnimator());
		rv.setLayoutManager(manager);
		 helper = new HistoryItemHelper(adapter);
		ItemTouchHelper iHelper=new ItemTouchHelper(helper);
		iHelper.attachToRecyclerView(rv);
		//neoRecyAdapter nra=new neoRecyAdapter(this);
		//nra.setHasStableIds(true);
		//rv.setAdapter(nra);
		//nra.upData(id,type);
		//nra.notifyDataSetChanged();
		adapter.setHasStableIds(true);
		adapter.setOnitemClickLintener(new onHistoryClick(){

				@Override
				public void onClick(final String mid, final Boolean mtype, String time, final int position)
				{String info=!mtype ?"沙盒存档": "载具存档";
					//	if (type)
					//		{
					//			info = "载具存档";
					//		}
					//WiToast.showToast(AppCompatMain.this, id + type, 3000);
					//adapter.delItem(position);
					cpw = new CardPopupWindow(AppCompatMain.this, new NavigationView.OnNavigationItemSelectedListener(){

							@Override
							public boolean onNavigationItemSelected(MenuItem p1)
							{
								switch (p1.getItemId())
								{
									case R.id.item_card_start_game:
										//id.add(0, id.get(position));
										//id.remove(position + 1);
										//type.add(0, type.get(position));
										//type.remove(position + 1);
										SavedItem si=history.get(position);
										si.changeTime(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date(System.currentTimeMillis())));
										history.add(0, si);
										history.remove(position + 1);
										//saveHistory();
										adapter.moveItem(position, history);	
										rv.scrollToPosition(0);
										cpw.dismiss();
										startActivity(new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("simplerockets://" + (mtype ?"00": "03") + mid)));
										//WiToast.showToast(AppCompatMain.this,mtype?"00":"03",2000);
										if (sp2.getBoolean("start_sr_and_exit", false))
										{
											finish();
										}
										break;
									case R.id.item_card_collection:
										if (!history.get(position).isCollected)
										{
											SavedItem ci=new SavedItem();
											ci.initCollection(mid, "cyka blyat", mtype, new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date(System.currentTimeMillis())), "SGCF");
											collections.add(0, ci);
											SavedItemTools.saveCollections(collections, AppCompatMain.this);
											SavedItem si2=history.get(position);
											si2.isCollected = true;
											history.set(position, si2);
											colle.addColle(collections);
											adapter.moveItem(position, history);
											cpw.dismiss();
										}
										break;
									case R.id.item_card_share:
										break;
									case R.id.item_card_delete:
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
									case R.id.item_card_cancel:
										cpw.dismiss();
										break;
								}
								return false;
							}
						}

//							@Override
//							public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
//							{
//								switch ((int)p4)
//								{
//									case 0:
//										//id.add(0, id.get(position));
//										//id.remove(position + 1);
//										//type.add(0, type.get(position));
//										//type.remove(position + 1);
//										SavedItem si=history.get(position);
//										si.changeTime(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date(System.currentTimeMillis())));
//										history.add(0, si);
//										history.remove(position + 1);
//										//saveHistory();
//										adapter.moveItem(position, history);	
//										rv.scrollToPosition(0);
//										cpw.dismiss();
//										startActivity(new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("simplerockets://" + (mtype ?"00": "03") + mid)));
//										//WiToast.showToast(AppCompatMain.this,mtype?"00":"03",2000);
//										if(sp2.getBoolean("start_sr_and_exit",false)){
//											finish();
//										}
//										break;
//									case 1:
//										if(!history.get(position).isCollected){
//										SavedItem ci=new SavedItem();
//										ci.initCollection(mid,"cyka blyat",mtype,new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date(System.currentTimeMillis())),"SGCF");
//										collections.add(0,ci);
//										SavedItemTools.saveCollections(collections,AppCompatMain.this);
//										SavedItem si2=history.get(position);
//										si2.isCollected=true;
//										history.set(position,si2);
//										colle.addColle(collections);
//										adapter.moveItem(position,history);
//										cpw.dismiss();
//										}
//										break;
//									case 2:
//										break;
//									case 3:
//										AlertDialog.Builder ab=new AlertDialog.Builder(AppCompatMain.this);
//										ab.setTitle("要删除这条记录吗？");
//										//ab.setMessage("");
//										ab.setPositiveButton("我手滑了~", null);
//										ab.setNegativeButton("确定", new AlertDialog.OnClickListener(){
//
//												@Override
//												public void onClick(DialogInterface p1, int p2)
//												{
//													adapter.delItem(position);
//													cpw.dismiss();
//												}
//											});
//										ab.show();
//										break;
//									case 4:
//										cpw.dismiss();
//										break;
//								}
//							}
//						}
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
	boolean isEmpty(String s)
	{
		return TextUtils.isEmpty(s);
	}
	@Override
	protected void onStop()
	{
		collections = colle.mColle;
		saveHistory();
		SavedItemTools.saveCollections(collections, this);
		super.onStop();
	}
	@Override
	protected void onDestroy()
	{
		collections = colle.mColle;
		saveHistory();
		SavedItemTools.saveCollections(collections, this);
		System.exit(0);
		super.onDestroy();
	}
	void addHistory(String mId, Boolean mType, String date)
	{
		//id.add(0, mId);
		//type.add(0, mType);
		Boolean isColle=false;
		ArrayList<SavedItem> colle=SavedItemTools.initHistoryData(this);
		for (int i=0;i < colle.size();i++)
		{
			if (colle.get(i).getId().equals(mId))
			{
				isColle = true;
			}
		}
		SavedItem si=new SavedItem();
		si.initHistory(mId, mType, date, null, isColle);
		history.add(0, si);
		adapter.addItem(history);
		//rv.scrollToPosition(0);
	}
	void saveHistory()
	{
		SavedItemTools.saveHistory(history, this);
	}
}
//
