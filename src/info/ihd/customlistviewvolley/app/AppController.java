package info.ihd.customlistviewvolley.app;
 
import info.androidhive.customlistviewvolley.util.LruBitmapCache;
import android.app.Application;
import android.text.TextUtils;
 
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.cloudtist.ihd.MainActivity;
import com.parse.Parse;
import com.parse.PushService;
 
public class AppController extends Application {
 
    public static final String TAG = AppController.class.getSimpleName();
 
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
 
    private static AppController mInstance;
 
    @SuppressWarnings("deprecation")
	@Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        
   
        
        Parse.initialize(this, "vTtzP9Pf24unJa2XF8akrFll6b6lquBTbedwKtSY", "1jojMAx9Hhy30eQJdgdb95XHi6i9QkSweNlqSfOt");

		// Specify an Activity to handle all pushes by default.
		PushService.setDefaultPushCallback(this, MainActivity.class);
    }
 
    public static synchronized AppController getInstance() {
        return mInstance;
    }
 
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }
 
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }
 
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }
 
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
 
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}