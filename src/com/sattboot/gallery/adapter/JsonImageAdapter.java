package com.sattboot.gallery.adapter;

import java.util.ArrayList;

import info.ihd.customlistviewvolley.app.AppController;

import com.android.volley.toolbox.ImageLoader;
import com.cloudtist.ihd.Movie;
import com.cloudtist.ihd.R;
import com.sattboot.gallery.widget.CoverFlow;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class JsonImageAdapter extends BaseAdapter {
	private Context mContext;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	ArrayList<Movie> movieList = new ArrayList<Movie>();

	public JsonImageAdapter(Context context, ArrayList<Movie> movieList) {
		this.mContext = context;
		this.movieList = movieList;
	}

	public int getCount() {
		return movieList.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("deprecation")
	public View getView(int position, View convertView, ViewGroup parent) {

		Movie movie = movieList.get(position);

		ImageView i = (ImageView) new ImageView(mContext);

		// i.setImageUrl(movie.getImgUrl(), imageLoader);
		i.setLayoutParams(new CoverFlow.LayoutParams(200, 360));
		i.setScaleType(ImageView.ScaleType.CENTER);

		Picasso.with(mContext).load(movie.getImgUrl())
				.placeholder(R.drawable.bg_movie).error(R.drawable.bg_movie)
				.resize(250, 300).into(i);

		// Make sure we set anti-aliasing otherwise we get jaggies

		return i;
	}

	public boolean createReflectedImages() {

		// final int reflectionGap = 4;
		// int index = 0;
		// for (int imageId : mImageIds) {
		//
		// Bitmap originalImage = BitmapFactory.decodeResource(
		// mContext.getResources(), imageId);
		//
		// int width = originalImage.getWidth();
		// int height = originalImage.getHeight();
		//
		// Matrix matrix = new Matrix();
		// matrix.preScale(1, -1);
		//
		// Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
		// height / 2, width, height / 2, matrix, false);
		//
		//
		// Bitmap bitmapWithReflection = Bitmap.createBitmap(width, height
		// + height / 2, Config.ARGB_8888);
		//
		// Canvas canvas = new Canvas(bitmapWithReflection);
		//
		// canvas.drawBitmap(originalImage, 0, 0, null);
		//
		// Paint defaultpaint = new Paint();
		// canvas.drawRect(0, height, width, height + reflectionGap,
		// defaultpaint);
		//
		// canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);
		//
		// Paint paint = new Paint();
		// paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// LinearGradient shader = new LinearGradient(0, height, 0,
		// bitmapWithReflection.getHeight() + reflectionGap,
		// 0x70FFFFFF, 0x00FFFFFF, TileMode.CLAMP);
		//
		// paint.setShader(shader);
		// canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
		// + reflectionGap, paint);
		//
		//
		// ImageView imageView = new ImageView(mContext);
		// BitmapDrawable bd = new BitmapDrawable(bitmapWithReflection);
		//
		// bd.setAntiAlias(true);

		// imageView.setImageDrawable(bd);

		// imageView.setLayoutParams(new CoverFlow.LayoutParams(120, 180));
		// imageView.setScaleType(ScaleType.FIT_CENTER);
		// mImages[index++] = imageView;
		// }
		return true;
	}

	public float getScale(boolean focused, int offset) {
		/* Formula: 1 / (2 ^ offset) */
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}

}
