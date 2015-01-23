package com.cloudtist.ihd;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cloudtist.ihd.R;
import com.squareup.picasso.Picasso;

public class CustomJsonArrayAdapter extends BaseAdapter {
	Context mContext;
	// ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	ImageView i;
	TextView textView;
	ArrayList<Movie> movieList = new ArrayList<Movie>();
	int type;

	public CustomJsonArrayAdapter(Context context, ArrayList<Movie> movieList,
			int type, int type4) {
		this.mContext = context;
		this.movieList = movieList;
		this.type = type;
	}

	public int getCount() {
		return movieList.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	@SuppressLint("ViewHolder")
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View row = mInflater.inflate(R.layout.listview_json_item, parent, false);

		Movie movie = movieList.get(position);

		textView = (TextView) row.findViewById(R.id.text_title);
		textView.setText(movie.getTitle());

		i = (ImageView) row.findViewById(R.id.image_title);

		if (type == 0) {
			Picasso.with(mContext).load(movie.getImgUrl())
					.placeholder(R.drawable.bg_movie)
					.error(R.drawable.bg_movie)
					// .resize(150, 150)
					// .centerCrop()
					.into(i);
		}
		if (type == 1) {
			Picasso.with(mContext).load(movie.getImgUrl())
					.placeholder(R.drawable.bg_movie)
					.error(R.drawable.bg_movie)
					// .resize(100, 150).centerCrop()
					.into(i);
		}
		if (type == 2) {
			Picasso.with(mContext).load(movie.getImgUrl())
					.placeholder(R.drawable.bg_movie)
					.error(R.drawable.bg_movie)
					// .resize(100, 150)
					// .centerCrop()
					.into(i);
		}
		if (type == 3) {
			Picasso.with(mContext).load(movie.getImgUrl())
					.placeholder(R.drawable.bg_movie)
					.error(R.drawable.bg_movie)
					// .resize(150, 150)
					// .centerCrop()
					.into(i);
		}
		if (type == 4) {
			Picasso.with(mContext).load(movie.getImgUrl())
					.placeholder(R.drawable.bg_movie)
					.error(R.drawable.bg_movie).resize(150, 150).centerCrop()
					.into(i);

		}
		if (type == 5) {
			Picasso.with(mContext).load(movie.getImgUrl())
					.placeholder(R.drawable.bg_movie)
					.error(R.drawable.bg_movie)
					// .resize(150, 150)
					// .centerCrop()
					.into(i);

		}
		if (type == 6) {
			Picasso.with(mContext).load(movie.getImgUrl())
					.placeholder(R.drawable.bg_movie)
					.error(R.drawable.bg_movie)
					// .resize(150, 150)
					// .centerCrop()
					.into(i);

		}
		if (type == 7) {
			Picasso.with(mContext).load(movie.getImgUrl())
					.placeholder(R.drawable.bg_movie)
					.error(R.drawable.bg_movie)
					// .resize(150, 150)
					// .centerCrop()
					.into(i);

		}
		if (type == 8) {
			Picasso.with(mContext).load(movie.getImgUrl())
					.placeholder(R.drawable.bg_movie)
					.error(R.drawable.bg_movie)
					// .resize(150, 150)
					// .centerCrop()
					.into(i);

		}
		if (type == 9) {
			Picasso.with(mContext).load(movie.getImgUrl())
					.placeholder(R.drawable.bg_movie)
					.error(R.drawable.bg_movie)
					// .resize(150, 150)
					// .centerCrop()
					.into(i);

		}
		
		return row;
	}

}
