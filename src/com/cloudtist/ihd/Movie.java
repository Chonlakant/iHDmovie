package com.cloudtist.ihd;

import com.cloudtist.ihd.R;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
	private String id;
	private String title;
	private String detail;
	private String imgUrl;
	private int imgLoc;
	private String vdoUrl;
	private String room_ext1;
	private String openstatic;

	public Movie(){
		
	}
	
	public Movie(Parcel in){
		this.vdoUrl = in.readString();
	}
	
	public Movie(String id,String title,String detail,String vdoUrl,int imgLoc ,String room_ext1,String openstatic) {
		this.id = id;
		this.title = title;
		this.imgLoc = imgLoc;
		this.vdoUrl = vdoUrl;
		this.imgUrl = "";
		this.room_ext1 = room_ext1;
		this.openstatic = openstatic;
	}
	
	public String getOpenstatic() {
		return openstatic;
	}

	public void setOpenstatic(String openstatic) {
		this.openstatic = openstatic;
	}

	public String getRoom_ext1() {
		return room_ext1;
	}

	public void setRoom_ext1(String room_ext1) {
		this.room_ext1 = room_ext1;
	}

	public Movie(String id,String title,String detail,String vdoUrl,String imgUrl ) {
		this.id = id;
		this.title = title;
		this.imgUrl = imgUrl; 
		this.vdoUrl = vdoUrl; 
		this.imgLoc = R.drawable.ic_launcher;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getVdoUrl() {
		return vdoUrl;
	}

	public void setVdoUrl(String vdoUrl) {
		this.vdoUrl = vdoUrl;
	}
	
	public int  getImgLoc() {
		return imgLoc;
	}

	public void setImgLocg(int imgLoc) {
		this.imgLoc = imgLoc;
	}
	

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return this.hashCode();
	}



	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(vdoUrl);
		
	}
	public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>(){

		@Override
		public Movie createFromParcel(Parcel in) {
			return new Movie(in);
		}

		@Override
		public Movie[] newArray(int size) {
			return new Movie[size];
		}
		
	};
	
}
