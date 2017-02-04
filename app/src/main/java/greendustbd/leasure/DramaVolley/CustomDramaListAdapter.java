package greendustbd.leasure.DramaVolley;

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


public class CustomDramaListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Drama> dramaItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomDramaListAdapter(AppCompatActivity appCompatActivity, List<Drama> dramaItems) {
		this.activity = appCompatActivity;
		this.dramaItems = dramaItems;

	}

	@Override
	public int getCount() {
		return dramaItems.size();
	}

	@Override
	public Object getItem(int location) {
		return dramaItems.get(location);
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
			convertView = inflater.inflate(R.layout.list_drama_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView director = (TextView) convertView.findViewById(R.id.ddirector);
		TextView stars = (TextView) convertView.findViewById(R.id.stars);

		// getting movie data for the row
		Drama m = dramaItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
		
		// Title/Name
		title.setText(m.getTitle());
		
		// Dorector
		director.setText("Director: " + String.valueOf(m.getDirector()));
		// Stars
		stars.setText("Stars: " + String.valueOf(m.getStars()));



		return convertView;
	}

}