package willow.getsimplerocketsship.lite.fragment;
import android.os.*;
import android.support.v4.app.*;
import android.support.v4.widget.*;
import android.support.v7.widget.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import java.io.*;
import java.util.*;
import willow.getsimplerocketsship.lite.*;
import willow.getsimplerocketsship.lite.appcompat.*;
import willow.getsimplerocketsship.lite.util.*;
import willow.getsimplerocketsship.lite.view.*;

import android.support.v7.widget.Toolbar;
import org.xmlpull.v1.*;
import android.animation.*;
import android.widget.ExpandableListView.*;
import android.support.v7.internal.view.menu.*;

public class DeltaVCalculater extends Fragment
{
	Toolbar toolbar,referbar;
	public DrawerLayout drawer;
	LinearLayout refer;
	ExpandableListView ispReference;

	private ReferAdapter referAdapter;

	private View view;

	private ArrayList<ArrayList<Engine>> engines;

	private RecyclerView calRecyer;

	private DeltaVAdapter dva;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{view = inflater.inflate(R.layout.fragment_dv, container, false);
		toolbar = (Toolbar)view.findViewById(R.id.dv_toolbar);
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
		toolbar.setTitle(getString(R.string.item_DV) + "++");
		referbar = (Toolbar)view.findViewById(R.id.dv_toolbar_refer);
		referbar.setTitle(R.string.reference);
		referbar.inflateMenu(R.menu.menu_add);
		//referbar.setNavigationIcon(R.drawable.isp_refer);
		drawer = (DrawerLayout)view.findViewById(R.id.dv_drawer_layout);
		initIspData();
		toolbar.inflateMenu(R.menu.isp_refer_menu);
		toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){

				@Override
				public boolean onMenuItemClick(MenuItem p1)
				{
					switch (p1.getItemId())
					{
						case R.id.refer_menu_button:
							drawer.openDrawer(Gravity.RIGHT);
					}
					return false;
				}
			});
		drawer.setDrawerListener(new DrawerLayout.DrawerListener(){

				@Override
				public void onDrawerSlide(View p1, float p2)
				{
					// TODO: Implement this method
					//WiToast.showToast(getActivity(),p2+"",1000);
				}

				@Override
				public void onDrawerOpened(View p1)
				{
					((AppCompatMain)getActivity()).drawer.setDrawerLockMode(1);
				}

				@Override
				public void onDrawerClosed(View p1)
				{
					((AppCompatMain)getActivity()).drawer.setDrawerLockMode(0);
				}

				@Override
				public void onDrawerStateChanged(int p1)
				{
					// TODO: Implement this method
				}
			});
		calRecyer = (RecyclerView)view.findViewById(R.id.dvc_recycle);
		dva=new DeltaVAdapter();
		LinearLayoutManager manager=new LinearLayoutManager(getActivity());
		calRecyer.setLayoutManager(manager);
		calRecyer.setAdapter(dva);
		return view;
	}
	public void initIspData()
	{
		String ispdata ="";
		try
		{
			InputStream is =getActivity().getAssets().open("IspData/" + getString(R.string.ispData) + ".xml");

			int size = is.available();
			// Read the entire asset into a local byte buffer.
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			// Convert the buffer into a string.
			ispdata = new String(buffer, "utf-8");
		}
		catch (IOException e)
		{}
		ArrayList<String> ispGroup=new ArrayList();
		engines = new ArrayList<ArrayList<Engine>>();

		try
		{
			XmlPullParserFactory xppf=XmlPullParserFactory.newInstance();


			XmlPullParser xpp=xppf.newPullParser();
			xpp.setInput(new StringReader(ispdata));
			String groupName = null;
			String isp=null;
			int eventType = xpp.getEventType();
			ArrayList<Engine> mGroup=new ArrayList();
			while (eventType != xpp.END_DOCUMENT)
			{
				String NoteName=xpp.getName();
				switch (eventType)
				{
					case xpp.START_TAG:
						if ("Group".equals(NoteName))
						{
							ispGroup.add(xpp.getAttributeValue("", "name"));
						}
						if ("Engine".equals(NoteName))
						{

						}
						break;
					case xpp.END_TAG:
						if ("Group".equals(NoteName))
						{
							engines.add(mGroup);
							//	ispGroup.add(xpp.getAttributeValue("", "name"));
							mGroup = new ArrayList();
						}
						else if ("Engine".equals(NoteName))
						{
							Engine mE=new Engine();
							mE.EngineName = xpp.getAttributeValue("", "name");
							mE.Isp = xpp.getAttributeValue("", "isp");
							mGroup.add(mE);
						}
						break;
				}

				eventType = xpp.next();

			}
		}
		catch (XmlPullParserException e)
		{}
		catch (IOException e)
		{}

		referAdapter = new ReferAdapter(getActivity(), engines, ispGroup);
		ispReference = (ExpandableListView)view.findViewById(R.id.isp_reference_list);
		ispReference.setSmoothScrollbarEnabled(true);
		ispReference.setStateListAnimator(new StateListAnimator());
		ispReference.setAdapter(referAdapter);
		ispReference.setOnChildClickListener(new OnChildClickListener(){

				@Override
				public boolean onChildClick(ExpandableListView p1, View p2, int p3, int p4, long p5)
				{
					String isp= engines.get(p3).get(p4).Isp;
					if(dva.mIO.get(0).type!=2){
					//WiToast.showToast(getActivity(), isp, 3000);
					dva.setIsp(isp);
					} else{
					WiToast.showToast(getActivity(),"你不是要计算比冲嘛",3000);
					}
					drawer.closeDrawer(Gravity.RIGHT);
					return true;
				}
			});
	}

}
