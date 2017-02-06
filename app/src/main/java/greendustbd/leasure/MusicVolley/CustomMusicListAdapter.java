package greendustbd.leasure.MusicVolley;

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


public class CustomMusicListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Music> musicItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomMusicListAdapter(AppCompatActivity appCompatActivity, List<Music> musicItems) {
		this.activity = appCompatActivity;
		this.musicItems = musicItems;

	}

	@Override
	public int getCount() {
		return musicItems.size();
	}

	@Override
	public Object getItem(int location) {
		return musicItems.get(location);
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
			convertView = inflater.inflate(R.layout.list_music_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView singer = (TextView) convertView.findViewById(R.id.singer);

		// getting movie data for the row
		Music m = musicItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
		
		// title
		title.setText(m.getTitle());

		// Singer
		singer.setText("Singer: " + String.valueOf(m.getSinger()));
		


		return convertView;
	}

}