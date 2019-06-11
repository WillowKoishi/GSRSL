package willow.getsimplerocketsship.lite.util;
import android.text.TextUtils;
import android.content.SharedPreferences;
import android.content.*;
import java.util.*;
import java.io.*;
import android.graphics.drawable.*;
import android.graphics.*;

public class SavedItemTools
{
	public static ArrayList<SavedItem> initHistoryData(Context context)
	{

//加载历史
		ArrayList<SavedItem> colle =new ArrayList<SavedItem>();
		SharedPreferences sp2=context.getSharedPreferences("GSRSL_Collections", 0);
		String[] histo=new String[]{};
		if (!TextUtils.isEmpty(sp2.getString("collections", null)))
		{
			histo = sp2.getString("collections", null).split("@c0lle");//@hist0ry ID @di type @di time
		}
		if (histo != null)
		{
			for (int i=0;i < histo.length;i++)
			{
				String[] hisItem=histo[i].split("@di");
				SavedItem si=new SavedItem();
				si.initCollection(hisItem[0], hisItem[3], Integer.valueOf(hisItem[1]) == 1 ?true: false, hisItem[2], null);
				colle.add(si);
				//id.add(hisItem[0]);
				//type.add(Integer.valueOf(hisItem[1]) == 1 ?true: false);
			}
		}




		ArrayList<SavedItem> history=new ArrayList<SavedItem>();
		SharedPreferences sp=context.getSharedPreferences("GSRSL_History", 0);
		//ArrayList<SavedItem> colle=SavedItemTools.initHistoryData(context);
		String[] histor=new String[]{};
		if (!TextUtils.isEmpty(sp.getString("history", null)))
		{
			histor = sp.getString("history", null).split("@hist0ry");//@hist0ry ID @di type @di time
		}
		if (histor != null)
		{
			for (int i=0;i < histor.length;i++)
			{
				String[] hisItem=histor[i].split("@di");
				SavedItem si=new SavedItem();
				Boolean isColle=false;
				if (colle != null)
				{
					for (int ii=0;ii < colle.size();ii++)
					{
						if (colle.get(ii).getId().equals(hisItem[0]))
						{
							isColle = true;
						}
					}
				}
				si.initHistory(hisItem[0], Integer.valueOf(hisItem[1]) == 1 ?true: false, hisItem[2], null, isColle);
				history.add(si);
				//id.add(hisItem[0]);
				//type.add(Integer.valueOf(hisItem[1]) == 1 ?true: false);
			}
		}
		else
		{
			history = null;
		}
		return history;
	}
	//保存历史
	public static void saveHistory(ArrayList<SavedItem> history, Context context)
	{SharedPreferences sp=context.getSharedPreferences("GSRSL_History", 0);
		String mH = null;
		for//(int i=id.size()-1;i>=0;i--)
		(int i=0;i < history.size();i++)
		{SavedItem si=new SavedItem();
			si = history.get(i);
			mH = (TextUtils.isEmpty(mH) ?"": mH) + si.getId() + "@di" + (si.getType() ?1: 0) + "@di" + si.getTime() + (!(i == history.size()) ?"@hist0ry": "");
		}
		sp.edit().putString("history", mH).commit();
	}
//加载收藏
	public static ArrayList<SavedItem> initColleData(Context context)
	{
		ArrayList<SavedItem> collections =new ArrayList<SavedItem>();
		SharedPreferences sp=context.getSharedPreferences("GSRSL_Collections", 0);
		String[] histor=new String[]{};
		if (!TextUtils.isEmpty(sp.getString("collections", null)))
		{
			histor = sp.getString("collections", null).split("@c0lle");//@hist0ry ID @di type @di time
		}
		if (histor != null)
		{
			for (int i=0;i < histor.length;i++)
			{
				String[] hisItem=histor[i].split("@di");
				SavedItem si=new SavedItem();
				si.initCollection(hisItem[0],
								 hisItem[3], 
								 Integer.valueOf(hisItem[1]) == 1 ?true: false, 
								 hisItem[2],
								hisItem.length<5?"default": hisItem[4]);
				collections.add(si);
				//id.add(hisItem[0]);
				//type.add(Integer.valueOf(hisItem[1]) == 1 ?true: false);
			}
		}
		else
		{
			collections = null;
		}
		return collections;
	}
	//保存收藏
	public static void saveCollections(ArrayList<SavedItem> collections, Context context)
	{SharedPreferences sp=context.getSharedPreferences("GSRSL_Collections", 0);
		String mH = null;
		for//(int i=id.size()-1;i>=0;i--)
		(int i=0;i < collections.size();i++)
		{SavedItem si=new SavedItem();
			si = collections.get(i);
			mH = (TextUtils.isEmpty(mH) ?"": mH) + 
			       si.getId() +//0 id
			"@di" + (si.getType() ?1: 0) + //1类型
			"@di"+ si.getTime() +//2时间
			"@di" + si.getName() +//3名称
			"@di"+si.mIcon +//4图标
			(!(i == collections.size()) ?"@c0lle": "");
		}
		sp.edit().putString("collections", mH).commit();
	}
	public static String readAssetsTxt(Context context)
	{
        try
		{
            //Return an AssetManager instance for your application's package
            InputStream is =context.getAssets().open("UpdataLoGLite");
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.
			return text;
        }
		catch (IOException e)
		{
            // Should never happen!
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return null;
    }
	public static ArrayList<Flags> initFlags(Context context)//加载旗帜
	{ArrayList<Flags> FlagSet=new ArrayList<Flags>();
		try
		{
			String[] dir=context.getAssets().list("flags");
			for (int i=0;i < dir.length;i++)
			{
				InputStream is3=context.getAssets().open("flags/" + dir[i]);
				Flags flag=new Flags();
				flag.flag = BitmapFactory.decodeStream(is3);
				flag.flagName = dir[i].substring(0, dir[i].length() - 4);
				FlagSet.add(flag);
				is3.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return FlagSet;
	}
	public static class Flags
	{
		public Bitmap flag;
		public String flagName;
	}
}
