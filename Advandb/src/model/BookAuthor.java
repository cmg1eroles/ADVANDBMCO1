package model;

public class BookAuthor {
	private int bookId;
	private String lastName;
	private String firstName;
	
	public BookAuthor(int bookId, String lastName, String firstName){
		this.bookId = bookId;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public int getBookId() {
		return bookId;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}
	
	@Override
	public String toString() {
		return "Book Author [bookId=" + bookId + ", last name=" + lastName + ", first name=" + firstName + "]";
	}
}
