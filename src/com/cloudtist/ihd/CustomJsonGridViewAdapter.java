package com.cloudtist.ihd;

import info.ihd.customlistviewvolley.app.AppController;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.cloudtist.ihd.R;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomJsonGridViewAdapter extends BaseAdapter {
	
	Context mContext;
	ArrayList<Movie> movieList = new ArrayList<Movie>();
//	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	View row;

	public CustomJsonGridViewAdapter(Context context, ArrayList<Movie> movieList) {
		this.mContext = context;
		this.movieList = movieList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return movieList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		row = mInflater.inflate(R.layout.item_gridview, parent, false);

		Movie movie = movieList.get(position);

		TextView text = (TextView) row.findViewById(R.id.grid_text);
		text.setText(movie.getTitle());
//		NetworkImageView imageView = (NetworkImageView) row.findViewById(R.id.grid_image);
//		imageView.setImageUrl(movie.getImgUrl(), imageLoader);

		 ImageView i = (ImageView) row.findViewById(R.id.grid_image);
		 Picasso.with(mContext)
		 .load(movie.getImgUrl())
		 .placeholder(R.drawable.bg_movie)
		 .error(R.drawable.bg_movie)
//		 .resize(250, 300)
		.into(i);
		return row;
	}

}
