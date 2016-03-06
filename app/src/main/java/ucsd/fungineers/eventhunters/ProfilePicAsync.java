package ucsd.fungineers.eventhunters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Timothy on 3/5/2016.
 */
public class ProfilePicAsync extends AsyncTask<String,String,String> {
    public static Bitmap bitmap;
    String url;

    public ProfilePicAsync(String url) {
        this.url = url;
    }
    @Override
    protected String doInBackground(String... params) {
        // Fetching data from URI and storing in bitmap
        bitmap = DownloadImageBitmap(url);
        return null;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        System.instance.saveNewUser();
    }

    public static Bitmap DownloadImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("IMAGE", "Error getting bitmap", e);
        }
        return bm;
    }
}
