package greendustbd.leasure.BookVolley;

public class Book {
	private String title,thumbnailUrl;
	private String publisher;
	private String writer;

	public Book() {
	}

	public Book(String name, String thumbnailUrl, String genres, String publisher,
				String writer) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.publisher = publisher;
		this.writer = writer;
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


	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String director) {
		this.publisher = publisher;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String actor) {
		this.writer = writer;
	}

}
