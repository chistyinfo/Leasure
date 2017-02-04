package greendustbd.leasure.BookVolley;

public class Book {
	private String title,thumbnailUrl;
	private String genres;
	private String director;
	private String actor;

	public Book() {
	}

	public Book(String name, String thumbnailUrl, String genres, String director,
				String actor) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.genres = genres;
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

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

}
