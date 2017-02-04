package greendustbd.leasure.DramaVolley;

public class Drama {
	private String title,thumbnailUrl;
	private String director;
	private String stars;

	public Drama() {
	}

	public Drama(String name, String thumbnailUrl, String director,
				 String stars) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.director = director;
		this.stars = stars;

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


	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}



}
