package model;

public class Book {
	private int bookId;
	private String title;
	private String publisherName;
	
	public Book(int bookId, String title, String publisherName){
		this.bookId = bookId;
		this.title = title;
		this.publisherName = publisherName;
	}
	
	public int getBookId() {
		return bookId;
	}
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPublisherName() {
		return publisherName;
	}
	
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", publisherName=" + publisherName + "]";
	}
}