package willow.getsimplerocketsship.lite.view;
import android.support.v7.widget.*;
import android.view.*;
import willow.getsimplerocketsship.lite.util.*;
import willow.getsimplerocketsship.lite.R;
import java.util.*;
import android.widget.*;
import android.graphics.*;
import willow.getsimplerocketsship.lite.view.ColleIconRecyAdapter.*;
import android.view.View.*;

public class ColleIconRecyAdapter extends RecyclerView.Adapter<ColleIconRecyAdapter.Holder>
{
	public ArrayList<SavedItemTools.Flags> flags=new ArrayList<SavedItemTools.Flags>();

	public ColleIconRecyAdapter.OnIconClickListener oCCL;
	@Override
	public ColleIconRecyAdapter.Holder onCreateViewHolder(ViewGroup p1, int p2)
	{
		View item = LayoutInflater.from(p1.getContext()).inflate(R.layout.item, p1, false);
		Holder viewHolder=new Holder(item);
		viewHolder.setIsRecyclable(false);
		return viewHolder;
	}
	public void putFlags(ArrayList<SavedItemTools.Flags> flags){
		this.flags=flags;
		notifyDataSetChanged();
	}
	
	@Override
	public void onBindViewHolder(ColleIconRecyAdapter.Holder holder, final int p2)
	{
		OnClickListener oicl=new OnClickListener(){

			@Override
			public void onClick(View view)
			{
				oCCL.onClick(flags.get(p2).flag,flags.get(p2).flagName);
			}
		};
		
		
		holder.icon.setImageBitmap(flags.get(p2).flag);
		holder.tv.setText(flags.get(p2).flagName);
		holder.tv.setOnClickListener(oicl);
		holder.icon.setOnClickListener(oicl);
		holder.lb.setOnClickListener(oicl);
	}

	@Override
	public int getItemCount()
	{
		return flags.isEmpty()?0:flags.size();
	}
	
	public class Holder extends RecyclerView.ViewHolder{
		private ImageView icon;
		private TextView tv;
		private LinearLayout lb;
		public Holder(View item){
			super(item);
			icon=(ImageView)item.findViewById(R.id.item_colles_icon);
			tv=(TextView)item.findViewById(R.id.item_icon_name);
			lb=(LinearLayout)item.findViewById(R.id.itemLinearLayout1);
		}
	}
	public void setOnIconClickListener(OnIconClickListener occl){
		this.oCCL=occl;
	}
	public interface OnIconClickListener{
		void onClick(Bitmap flag,String flagName);
	}
}
