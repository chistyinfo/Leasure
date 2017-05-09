package greendustbd.leasure.AppsVolley;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import greendustbd.leasure.AppController;
import greendustbd.leasure.R;


public class CustomAppsListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Apps> appsItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomAppsListAdapter(AppCompatActivity appCompatActivity, List<Apps> appsItems) {
		this.activity = appCompatActivity;
		this.appsItems = appsItems;

	}

	@Override
	public int getCount() {
		return appsItems.size();
	}

	@Override
	public Object getItem(int location) {
		return appsItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_apps_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView singer = (TextView) convertView.findViewById(R.id.publisher);

		// getting movie data for the row
		Apps m = appsItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
		
		// title
		title.setText(m.getTitle());

		// Apps Publisher
		singer.setText("Publisher: " + String.valueOf(m.getPublisher()));
		


		return convertView;
	}

}