package willow.getsimplerocketsship.lite.view;
import android.widget.*;
import android.view.*;
import android.database.*;
import java.util.*;
import android.content.*;
import willow.getsimplerocketsship.lite.R;
import willow.getsimplerocketsship.lite.util.*;

public class ReferAdapter extends BaseExpandableListAdapter
{
	ArrayList<String> mEngineName=new ArrayList();
	List<Integer> amount=new ArrayList<Integer>();
	ArrayList<ArrayList<Engine>> mIspData=new ArrayList<ArrayList<Engine>>();
	Context mContext;
	public ReferAdapter(Context context,ArrayList<ArrayList<Engine>> ispData,ArrayList engine){
		mContext=context;
		mEngineName=engine;
		mIspData=ispData;
		reFreash();
	}
	public void reFreash(){
		amount.clear();
		for(int i=0;i<mIspData.size();i++){
			amount.add(mIspData.get(i).size());
		}
	}
	@Override
	public int getGroupCount()
	{
		return mEngineName.size();
	}

	@Override
	public int getChildrenCount(int p1)
	{
		return mIspData.get(p1).size();
	}

	@Override
	public Object getGroup(int p1)
	{
		// TODO: Implement this method
		return p1;
	}

	@Override
	public Object getChild(int p1, int p2)
	{
		// TODO: Implement this method
		return mIspData.get(p1).get(p2);
	}

	@Override
	public long getGroupId(int p1)
	{
		// TODO: Implement this method
		return p1;
	}

	@Override
	public long getChildId(int p1, int p2)
	{
		// TODO: Implement this method
		return p2;
	}

	@Override
	public boolean hasStableIds()
	{
		// TODO: Implement this method
		return true;
	}

	@Override
	public View getGroupView(int p1, boolean p2, View GroupName, ViewGroup p4)
	{
		GroupName=View.inflate(mContext,R.layout.item_engine_group,null);
		TextView ount=(TextView)GroupName.findViewById(R.id.item_engine_cot);
		TextView GroupText=(TextView)GroupName.findViewById(R.id.isp_group_name);
		GroupText.setText(mEngineName.get(p1));	
		//ount.setText(amount.get(p1));
		return GroupName;
	}

	@Override
	public View getChildView(int p1, int p2, boolean p3, View p4, ViewGroup p5)
	{
		View engine=View.inflate(mContext,R.layout.item_engine,null);
		TextView engineName=(TextView)engine.findViewById(R.id.item_engine_name);
		engineName.setText(mIspData.get(p1).get(p2).EngineName);
		TextView Count=(TextView)engine.findViewById(R.id.item_engine_isp);
		Count.setText(mIspData.get(p1).get(p2).Isp);
		return engine;
	}

	@Override
	public boolean isChildSelectable(int p1, int p2)
	{
		// TODO: Implement this method
		return true;
	}
	

}
