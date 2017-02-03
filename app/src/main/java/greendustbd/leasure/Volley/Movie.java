package greendustbd.leasure.Volley;

import java.util.ArrayList;

public class Movie {
	private String title,thumbnailUrl;
	private String date;
	private String director;
	private ArrayList<String> actor;

	public Movie() {
	}

	public Movie(String name, String thumbnailUrl, String date, String director,
			ArrayList<String> actor) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.date = date;
		this.director = director;
		this.actor = actor;
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

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public ArrayList<String> getActor() {
		return actor;
	}

	public void setActor(ArrayList<String> actor) {
		this.actor = actor;
	}

}
