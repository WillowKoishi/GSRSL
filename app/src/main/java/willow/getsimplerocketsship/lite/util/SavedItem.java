package willow.getsimplerocketsship.lite.util;

public class SavedItem
{
	public String mId;
	public boolean mType,isCollected;
	public String mTime;
	public String mName;
	public Long mTimes;
	public String mIcon;
	public void changeType(Boolean type)
	{
		mType=type;
	}
	public void changeTime(String time){
		mTime=time;
	}
	public void initHistory(String id,boolean type,String time,Long times,Boolean isColle){
		mId=id;
		mType=type;
		mTime=time;
		mTimes=times;
		isCollected=isColle;
	}
	public void initCollection(String id,String name,boolean type,String time,String icon){
		mId=id;
		mType=type;
		mTime=time;
		mIcon=icon;
		mName=name;
	}
	public String getId(){
		return mId;
	}
	public boolean getType(){
		return mType;
	}
	public String getTime(){
		return mTime;
	}
	public String getName(){
		return mName;
	}
	
}
