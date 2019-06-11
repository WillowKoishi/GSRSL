package willow.getsimplerocketsship.lite.view;
import android.content.Context;
import android.widget.PopupWindow;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import willow.getsimplerocketsship.lite.R;
import android.view.View;
import android.widget.LinearLayout;
import android.support.v7.widget.CardView;
import android.view.View.*;
import android.view.*;
import android.view.ViewGroup.LayoutParams;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;
import android.widget.*;
import android.widget.AdapterView.*;
import android.support.design.widget.*;

public class CardPopupWindow extends PopupWindow
{LinearLayout parent;
	private View mMenuView;
	private TextView infoText;
	private CardView cardView;
	private NavigationView nv;
	private ListView popupList;
	public CardPopupWindow(Context context,NavigationView.OnNavigationItemSelectedListener nvl,String info){
		super(context);
		LayoutInflater inflater = (LayoutInflater) context  
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        mMenuView = inflater.inflate(R.layout.popup_card, null); 
		parent=(LinearLayout)mMenuView.findViewById(R.id.popup_parent);
		infoText=(TextView)mMenuView.findViewById(R.id.popupcardTextView1);
		popupList=(ListView)mMenuView.findViewById(R.id.popup_list);
		infoText.setText(info);
		String[] a=new String[]{"开始游戏","收藏","分享","删除","取消"};
		popupList.setAdapter(
		new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_list_item_1,a));
		nv=(NavigationView) mMenuView.findViewById(R.id.id_popup_card);
		nv.setNavigationItemSelectedListener(nvl);
	//	popupList.setOnItemClickListener(itemListener);
		//设置SelectPicPopupWindow的View  
        this.setContentView(mMenuView);  
        //设置SelectPicPopupWindow弹出窗体的宽  
        this.setWidth(LayoutParams.FILL_PARENT);  
        //设置SelectPicPopupWindow弹出窗体的高  
        this.setHeight(LayoutParams.MATCH_PARENT);  
        //设置SelectPicPopupWindow弹出窗体可点击  
        this.setFocusable(true);  
        //设置SelectPicPopupWindow弹出窗体动画效果  
       this.setAnimationStyle(R.style.AnimationPreview);  
        //实例化一个ColorDrawable颜色为半透明  
		ColorDrawable dw = new ColorDrawable(0x55000000);  
        //设置SelectPicPopupWindow弹出窗体的背景  
		this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框 
		parent.setOnTouchListener(new OnTouchListener(){

				public boolean onTouch(View v, MotionEvent event) {  

					int height = mMenuView.findViewById(R.id.popup_card).getTop();  
					int y=(int) event.getY();  
					if(event.getAction()==MotionEvent.ACTION_UP){  
						if(y<height){  
							dismiss();  
						}  
					}                 
					return true;  
				}  
			});
	}
}
