package com.sattboot.gallery.adapter;

import com.sattboot.gallery.widget.CoverFlow;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private int[] mImageIds;
	private ImageView[] mImages;


	public ImageAdapter(Context context, int[] imageIds) {
		this.mContext = context;
		this.mImageIds = imageIds;
		mImages = new ImageView[imageIds.length];
	}

	
	public int getCount() {
		return mImages.length;
	}

	
	public Object getItem(int position) {
		return mImages[position];
	}

	
	public long getItemId(int position) {
		return position;
	}

	

	@SuppressWarnings("deprecation")
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView i = new ImageView(mContext);
		i.setImageResource(mImageIds[position]);
		i.setLayoutParams(new CoverFlow.LayoutParams(260, 260));
		i.setScaleType(ImageView.ScaleType.FIT_XY);
		

		//Make sure we set anti-aliasing otherwise we get jaggies
		BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
		drawable.setAntiAlias(true);
		return i;
	}



	@SuppressWarnings("deprecation")
	public boolean createReflectedImages() {
	
		final int reflectionGap = 4;
		int index = 0;
		for (int imageId : mImageIds) {
		
			Bitmap originalImage = BitmapFactory.decodeResource(
					mContext.getResources(), imageId);

			int width = originalImage.getWidth();
			int height = originalImage.getHeight();

			Matrix matrix = new Matrix();
			matrix.preScale(1, -1);
	
			Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
					height / 2, width, height / 2, matrix, false);

			
			Bitmap bitmapWithReflection = Bitmap.createBitmap(width, height
					+ height / 2, Config.ARGB_8888);
		
			Canvas canvas = new Canvas(bitmapWithReflection);
		
			canvas.drawBitmap(originalImage, 0, 0, null);

			Paint defaultpaint = new Paint();
			canvas.drawRect(0, height, width, height + reflectionGap,
					defaultpaint);
		
			canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

			Paint paint = new Paint();
			paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
			LinearGradient shader = new LinearGradient(0, height, 0,
					bitmapWithReflection.getHeight() + reflectionGap,
					0x70FFFFFF, 0x00FFFFFF, TileMode.CLAMP);
		
			paint.setShader(shader);
			canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
					+ reflectionGap, paint);

	
			ImageView imageView = new ImageView(mContext);
			BitmapDrawable bd = new BitmapDrawable(bitmapWithReflection);
		
			bd.setAntiAlias(true);
			imageView.setImageDrawable(bd);
		
			imageView.setLayoutParams(new CoverFlow.LayoutParams(120, 180));
			imageView.setScaleType(ScaleType.FIT_CENTER);
			mImages[index++] = imageView;
		}
		return true;
	}
	public float getScale(boolean focused, int offset) {
		/* Formula: 1 / (2 ^ offset) */
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}

}
