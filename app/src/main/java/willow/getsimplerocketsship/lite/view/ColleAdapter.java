package willow.getsimplerocketsship.lite.view;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.*;
import android.view.*;
import willow.getsimplerocketsship.lite.R;
import android.widget.*;
import java.util.ArrayList;
import willow.getsimplerocketsship.lite.util.SavedItem;
import android.view.View.*;
import android.content.*;
import willow.getsimplerocketsship.lite.util.SavedItemTools.Flags;
import android.content.res.*;
import willow.getsimplerocketsship.lite.util.ColleItemHelper.OnIMListener;
import android.support.v7.widget.helper.ItemTouchHelper;

public class ColleAdapter extends Adapter<ColleAdapter.Holder> implements OnIMListener
{
	OnColleClickListener oCCL;
	ArrayList<Flags> mFlags=new ArrayList<Flags>();
	Context mContext;
	private ArrayList<SavedItem> collections=new ArrayList<SavedItem>();

	public ColleAdapter(Context context,ArrayList<SavedItem> colles,ArrayList<Flags> flags){
		mFlags=flags;
		//collections=colles;
	}
	public void upData(ArrayList<SavedItem> colles){
		collections=colles;
		//notifyItemRangeInserted(0,colles.size());
		notifyDataSetChanged();
	}
	public void delItem(int position)
	{
		collections.remove(position);
		notifyDataSetChanged();
	}
	
	@Override
	public ColleAdapter.Holder onCreateViewHolder(ViewGroup p1, int p2)
	{
		View item = LayoutInflater.from(p1.getContext()).inflate(R.layout.item_colle, p1, false);
		Holder viewHolder=new Holder(item);
		viewHolder.setIsRecyclable(false);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(final ColleAdapter.Holder item, int p2)
	{
		OnClickListener listener=new OnClickListener(){
			@Override
			public void onClick(View p1)
			{
				SavedItem si=collections.get(item.getPosition());
				oCCL.onClick(p1,Integer.valueOf(si.getId()),si.getType(),item.getPosition());
			}
		};
		item.icon.setOnClickListener(listener);
		item.start.setOnClickListener(listener);
		item.edit.setOnClickListener(listener);
		item.share.setOnClickListener(listener);
		item.dele.setOnClickListener(listener);
		SavedItem si=collections.get(p2);
	//	Boolean antiCrash=true;
		for(int i=0;i<mFlags.size();i++){
			Flags f=mFlags.get(i);
			if(f.flagName.equals(si.mIcon)){
				item.icon.setImageBitmap(f.flag);
			//	antiCrash=false;
			}
		}
		//if(antiCrash){
		//	item.icon.setImageResource((R.drawable.ic_rocket));
		//}
		item.title.setText(si.getName());
		item.info.setText(si.getType()?R.string.ship:R.string.sandbox);
		item.info.setText(item.info.getText().toString()+ "    ID="+si.getId());
		item.time.setText(si.getTime());
	}

	@Override
	public int getItemCount()
	{
		return collections==null?0:collections.size();
	}
	
	class Holder extends RecyclerView.ViewHolder{
		private ImageView icon;
		private ImageButton start,edit,share,dele;
		private TextView title,info,time;
		public Holder(View item){
			super(item);
			icon=(ImageView)item.findViewById(R.id.colle_icon);
			start=(ImageButton)item.findViewById(R.id.colle_start_game);
			edit=(ImageButton)item.findViewById(R.id.colle_edit);
			share=(ImageButton)item.findViewById(R.id.colle_share);
			dele=(ImageButton)item.findViewById(R.id.colle_dele);
			title=(TextView)item.findViewById(R.id.colle_title);
			info=(TextView)item.findViewById(R.id.colle_info);
			time=(TextView)item.findViewById(R.id.colle_time);		
		}
	}
	public void setOnColleClickListener(OnColleClickListener occl){
		this.oCCL=occl;
	}
	public interface OnColleClickListener{
		void onClick(View view,int id,boolean type,int position);
	}
	@Override 
	public void onItemMove(int fromPosition, int toPosition)
	{ java.util.Collections.swap(collections, fromPosition, toPosition); 
	notifyItemMoved(fromPosition, toPosition); }
	@Override 
	public void onItemDismiss(int position)
	{ collections.remove(position);
	notifyDataSetChanged(); }
}
