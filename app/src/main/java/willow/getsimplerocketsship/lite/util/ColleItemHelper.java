package willow.getsimplerocketsship.lite.util;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.support.v7.widget.*;
import android.support.v7.widget.helper.ItemTouchHelper;

public class ColleItemHelper extends Callback
{
	public OnIMListener adapter;
	public ColleItemHelper(OnIMListener ma)
	{
		adapter = ma;
	}
	@Override
	public int getMovementFlags(RecyclerView p1, RecyclerView.ViewHolder p2)
	{
		RecyclerView.LayoutManager 
			layoutManager = p1.getLayoutManager();
		if (layoutManager instanceof GridLayoutManager) 
		{ int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | 
				ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
			int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
			return makeMovementFlags(dragFlags, swipeFlags);
		} 
		else if (layoutManager instanceof LinearLayoutManager)
		{
			LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
			if (linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL)
			{
				int dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
				int swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN; 
				return makeMovementFlags(dragFlags, swipeFlags); }
			else
			{
				int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
				int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
				return makeMovementFlags(dragFlags, swipeFlags);
			}
		} return makeMovementFlags(0, 0);
	}

	@Override
	public boolean onMove(RecyclerView p1, RecyclerView.ViewHolder p2, RecyclerView.ViewHolder p3)
	{
		adapter.onItemMove(p2.getAdapterPosition(), p3.getAdapterPosition());//移动 
		return true;
		}

	@Override
	public void onSwiped(RecyclerView.ViewHolder p1, int p2)
	{
		adapter.onItemDismiss(p1.getAdapterPosition());
	}

	@Override
	public boolean isLongPressDragEnabled()
	{
		return true;
	}

	@Override
	public boolean isItemViewSwipeEnabled()
	{
		return false;
	}
	public interface OnIMListener { 
	void onItemMove(int fromPosition, int toPosition);
	void onItemDismiss(int position); 
	}
}
