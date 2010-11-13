package cw.kuleuven.be.peno1011.cwb2.model;

public class Book extends Product{
	
	private String isbn;
	private boolean available;
	private String author;
	private String title;
	private String subject;
	/**
	 * @param name
	 * @param price
	 */
	public Book(String name, int price, String isbn, boolean available, String author, String title, String subject) {
		super(name, price);
		setIsbn(isbn);
		setAvailable(available);
		setAuthor(author);
		setTitle(title);
		setSubject(subject);
	}
	
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
	

}
