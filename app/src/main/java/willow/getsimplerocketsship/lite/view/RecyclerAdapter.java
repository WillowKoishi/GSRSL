package willow.getsimplerocketsship.lite.view;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
//import willow.getsimplerocketsship.lite.util.History;
import android.view.*;
import android.content.*;
import java.util.*;
import willow.getsimplerocketsship.lite.R;
import android.widget.*;
//import android.graphics.drawable.
import willow.getsimplerocketsship.lite.view.RecyclerAdapter.*;
import android.view.View.*;
import android.support.v7.widget.*;
import willow.getsimplerocketsship.lite.util.SavedItem;
import willow.getsimplerocketsship.lite.util.*;
import android.support.v7.app.*;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.eiewHolder> implements HistoryItemHelper.OnIMListener
{

	@Override
	public void onItemMove(int fromPosition, int toPosition)
	{
	}
	
	private Context mContext;
	private ArrayList<SavedItem> mHistory;
	private RecyclerAdapter.onHistoryClick ohc;
	public RecyclerAdapter(Context context, ArrayList<SavedItem> history)
	{
		mContext = context;
		mHistory = history;
	}
	@Override 
	public void onItemDismiss(final int position)
	{
		//mHistory.remove(position);
		mHistory.remove(position);
		//notifyDataSetChanged();
		notifyItemRemoved(position);	
	}
	public void reFreashColle(ArrayList<SavedItem> collec, int deColleID)
	{
		int posi = 0;
		for (int i=0;i < mHistory.size();i++)
		{
			SavedItem si=mHistory.get(i);
			if (Integer.valueOf(si.getId()) == deColleID)
			{
				si.isCollected = false;
				mHistory.set(i, si);
			}
		}
		notifyDataSetChanged();
	}

	public void clearAll()
	{
		int range=mHistory.size();
		mHistory.clear();
		//mID.clear();
		//	mType.clear();
		notifyItemRangeRemoved(0, range);
	}
//	public void addItem(ArrayList<String> id, ArrayList<Boolean> type){
//		mID=id;
//		mType=type;
//		notifyItemInserted(0);
//	}
	public void addItem(ArrayList<SavedItem> al)
	{
		mHistory = al;
		notifyItemInserted(0);
	}
	public void moveItem(int position, ArrayList<SavedItem> history)
	{
		//mID.add(mID.get(position));
		//mID.remove(position+1);
		//	mType.add(mType.get(position));
		//	mType.remove(position+1);
		//mID=id;
		//mType=type;
		mHistory = history;
		//notifyItemMoved(position,0);
		notifyDataSetChanged();
		//notifyItemRangeChanged(0,history.size());
	}
	public void delItem(int p0)
	{
		//mID.remove(p0);
		//mType.remove(p0);
		mHistory.remove(p0);
		notifyItemRemoved(p0);

	}
	public void updata(ArrayList<SavedItem> history)
	{
		//mHistory=history;
		//mID = id;
		//mType = type;
		mHistory = history;
		notifyItemRangeInserted(0, mHistory.size());
		//notifyDataSetChanged();
		//notifyItemInserted(1);
	}
	public RecyclerAdapter(Context context)
	{
		mContext = context;
	}
	@Override
	public eiewHolder onCreateViewHolder(ViewGroup p1, int p2)
	{
		View item = LayoutInflater.from(p1.getContext()).inflate(R.layout.item_history, p1, false);
		//item=p1.inflate(mContext,R.layout.item_history,p1);
		eiewHolder viewHolder = new eiewHolder(item);
		viewHolder.setIsRecyclable(false);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(final eiewHolder p1, int p2)
	{SavedItem si= new SavedItem();
		si = mHistory.get(p2);
		p1.type.setImageResource(si.getType() ?R.drawable.ic_ship: R.drawable.ic_lang);
		p1.name.setText(si.getType() ?R.string.ship: R.string.sandbox);
		p1.id.setText("ID=" + si.getId());
		p1.time.setText(si.getTime());
		p1.colle.setVisibility(si.isCollected ?View.VISIBLE: View.GONE);
		final SavedItem si2=si;
		p1.card.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p11)
				{
					//ohc.onClick(mID.get(p1.getPosition()),mType.get(p1.getPosition()),0,p1.getPosition());
					ohc.onClick(si2.getId(), si2.getType(), si2.getTime(), p1.getPosition());
				}
			});
	}

	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return mHistory == null ?0: mHistory.size();
		//return mHistory.size(;//mID.size();
	}

    class eiewHolder extends RecyclerView.ViewHolder
	{

        ImageView type,colle;
		TextView name,id,time;
		CardView card;
		//ImageButton delete;

        public eiewHolder(View itemView)
		{
            super(itemView);
			card = (CardView)itemView.findViewById(R.id.item_card);
            type = (ImageView)itemView.findViewById(R.id.itemhistoryImageView1);
			name = (TextView)itemView.findViewById(R.id.itemhistory_type);
			id = (TextView)itemView.findViewById(R.id.itemhistory_id);
			time = (TextView)itemView.findViewById(R.id.itemhistory_time);
			colle = (ImageView)itemView.findViewById(R.id.itemhistoryImageView2);
			//delete=(ImageButton)itemView.findViewById(R.id.itemhistory_delete);	
        }
    }


	public void setOnitemClickLintener(onHistoryClick onitemClick)
	{ 
		this.ohc = onitemClick;
	}
	public interface onHistoryClick
	{
		void onClick(String id, Boolean type, String time, int position);

	}



}
