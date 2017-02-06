package greendustbd.leasure.MusicVolley;

public class Music {
	private String title,thumbnailUrl;
	private String singer;
	private String details;


	public Music() {
	}

	public Music(String name, String thumbnailUrl, String singer) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.singer = singer;

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

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}


}
