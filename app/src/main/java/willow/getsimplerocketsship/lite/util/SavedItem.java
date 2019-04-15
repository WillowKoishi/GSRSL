package willow.getsimplerocketsship.lite.util;

public class SavedItem
{
	public String mId;
	public boolean mType;
	public String mTime;
	public String mName;
	public Long mTimes;

	public void changeType(Boolean type)
	{
		mType=type;
	}
	public void initHistory(String id,boolean type,String time,Long times){
		mId=id;
		mType=type;
		mTime=time;
		mTimes=times;
	}
	public void initCollection(){
		
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
