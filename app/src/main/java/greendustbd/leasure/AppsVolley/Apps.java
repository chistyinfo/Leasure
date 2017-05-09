package greendustbd.leasure.AppsVolley;

public class Apps {
	private String title,thumbnailUrl;
	private String publisher;
	private String details;


	public Apps() {
	}

	public Apps(String name, String thumbnailUrl, String publisher) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.publisher = publisher;

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

	public void setPublisher(String singer) {
		this.publisher = singer;
	}


}
