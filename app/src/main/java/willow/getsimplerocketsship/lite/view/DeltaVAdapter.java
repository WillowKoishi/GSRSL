package willow.getsimplerocketsship.lite.view;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import willow.getsimplerocketsship.lite.*;
import java.util.*;
import android.view.View.*;
import android.support.design.widget.*;
import willow.getsimplerocketsship.lite.view.DeltaVAdapter.*;

public class DeltaVAdapter extends RecyclerView.Adapter<DeltaVAdapter.Holder>
{
	public String[][] itemString1={{"Δv","比冲","全重","空重","重力加速度"},{"Δv","比冲","全重","油重","重力加速度"}};
	public ArrayList<InputObject> mIO=new ArrayList<InputObject>();
	public int[][] iconIDs={};

	private DeltaVAdapter.Holder holder;

	private int  n=1;

	public void setIsp(String isp)
	{
		InputObject io=new InputObject();
		int mi = 0;
		for(int i=0;i<mIO.size();i++){
		io=mIO.get(i);
		io.value=Double.valueOf(isp);
		mi=i;
		}
		mIO.set(mi,io);
		notifyDataSetChanged();
	}
	@Override
	public DeltaVAdapter.Holder onCreateViewHolder(ViewGroup p1, int p2R)
	{
		View item=LayoutInflater.from(p1.getContext()).inflate(R.layout.item_dvc, p1, false);
		Holder holder=new Holder(item);
		for (int i=1;i <= 5;i++)
		{
			InputObject io=new InputObject();
			//io.name=itemString1[];
			io.type=i;
			mIO.add(io);
		}
		int[][] mmi={{R.drawable.ic_invert_colors_black_48dp,
				R.drawable.isp_refer,
				R.drawable.ic_dvc_fuel_fulled_mass,
				R.drawable.ic_dvc_fuel_empty_mass,
				R.drawable.ic_language_black_48dp},
			{R.drawable.ic_invert_colors_black_48dp,
				R.drawable.isp_refer,
				R.drawable.ic_dvc_fuel_fulled_mass,
				R.drawable.ic_dvc_fuel_empty_mass,
				R.drawable.ic_language_black_48dp}};
		iconIDs = mmi;
		//holder.setIsRecyclable(false);
		return holder;
	}
	public void cleanData(){
		
	}
	@Override
	public void onBindViewHolder(final DeltaVAdapter.Holder p1,final int p2)
	{
		if(n<=5){
			p1.nn=p1.getPosition()+1;
			if(n==5){
				p1.et.setText("9.81"+p1.nn);
			}else{
			//p1.et.setText(p1.nn);
			}
			n++;
		}
		int p3=p2;
		InputObject io=mIO.get(p1.getPosition());
		switch(p1.nn){
			case 2:
				p1.et.setText(String.valueOf(io.value).equals("0.0")?"":String.valueOf((io.value)));
		break;
		case 5:
				p1.switcher.setVisibility(View.GONE);
			//p1.et.setText("9.81");
			break;
				}
	//	p1.til.setHint(itemString1[0][io.type-1]);
		p1.iv.setImageResource(iconIDs[0][io.type-1]);
		p1.et.setHint(itemString1[0][io.type-1]);
		if (p1.getPosition() == 0||io.type==5)
		{
			p1.switcher.setVisibility(View.GONE);
			if(io.type==5){
			//p1.et.setText("9.81");
			}
			p1.et.setEnabled(false);
		}
		else
		{
			if(io.type!=5){
			p1.et.setEnabled(true);
			p1.switcher.setVisibility(View.VISIBLE);
		}
		}
		
		p1.switcher.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p)
				{
				holder=p1;
				int mP=holder.getPosition();
				InputObject io2=mIO.get(mP);
				io2.value=0;
				mIO.remove(mP);
				mIO.add(0,io2);
					
				holder.et.setText("");
				
					if (holder.getPosition() == 0)
					{
						holder.switcher.setVisibility(View.GONE);
						holder.et.setEnabled(false);
					}
					else
					{
						holder.et.setEnabled(true);
						holder.switcher.setVisibility(View.VISIBLE);
					}
					notifyItemMoved(mP,0);
					notifyItemChanged(0);
					notifyItemChanged(1);
					//notifyDataSetChanged();
				}
			});
	}

	@Override
	public int getItemCount()
	{
		return 5;
	}

	public class Holder extends RecyclerView.ViewHolder
	{
		EditText et;
		Button switcher;
		ImageView iv;
		int nn=114514;
		//TextInputLayout til;
		public Holder(View item)
		{
			super(item);
			et = (EditText)item.findViewById(R.id.item_dvc_EditText);
			switcher = (Button)item.findViewById(R.id.item_dvc_calthis);
			iv = (ImageView) item.findViewById(R.id.dvc_inputType_icon);
		//	til=(TextInputLayout) item.findViewById(R.id.dvc_til);
		}
	}
	public class InputObject
	{
		public Integer type;
		public String name;
		public double value;
	}
}
