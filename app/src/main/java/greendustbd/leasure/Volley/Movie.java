package greendustbd.leasure.Volley;

import java.util.ArrayList;

public class Movie {
	private String title,thumbnailUrl;
	private String date;
	private String timeing;
	private ArrayList<String> genre;

	public Movie() {
	}

	public Movie(String name, String thumbnailUrl, String date, String timeing,
			ArrayList<String> genre) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.date = date;
		this.timeing = timeing;
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}


	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return timeing;
	}

	public void setTimeing(String timeing) {
		this.timeing = timeing;
	}

	public ArrayList<String> getGenre() {
		return genre;
	}

	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}

}
