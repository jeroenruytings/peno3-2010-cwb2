package cw.kuleuven.be.peno1011.cwb2;

import java.net.URL;
import java.sql.Date;

public class Document {

	private URL link;
	private Appreciation appreciation;
	private String Description;
	private String type;
	private int size;
	private Date uploadDate;
	private Date dateLastChanged;
	private User author;
	private String title;
	
	
	public Document(URL link, Appreciation appreciation, String description,
			String type, int size, Date uploadDate, Date dateLastChanged,
			User author, String title) {
		
		setLink(link);
		setAppreciation(appreciation);
		setDescription(description);
		setType(type);
		setSize(size);
		setUploadDate(uploadDate);
		setDateLastChanged(dateLastChanged);
		setAuthor(author);
		setTitle(title);
		
		
	}


	public URL getLink() {
		return link;
	}


	public void setLink(URL link) {
		this.link = link;
	}


	public Appreciation getAppreciation() {
		return appreciation;
	}


	public void setAppreciation(Appreciation appreciation) {
		this.appreciation = appreciation;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public Date getUploadDate() {
		return uploadDate;
	}


	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}


	public Date getDateLastChanged() {
		return dateLastChanged;
	}


	public void setDateLastChanged(Date dateLastChanged) {
		this.dateLastChanged = dateLastChanged;
	}


	public User getAuthor() {
		return author;
	}


	public void setAuthor(User author) {
		this.author = author;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
	
}
