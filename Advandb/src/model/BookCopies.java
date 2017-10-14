package model;

public class BookCopies {
	private int bookId;
	private int branchId;
	private int noOfCopies;
	
	public BookCopies(int bookId, int branchId, int noOfCopies){
		this.bookId = bookId;
		this.branchId = branchId;
		this.noOfCopies = noOfCopies;
	}
	
	public int getBookId(){
		return bookId;
	}
	
	public int getBranchId(){
		return branchId;
	}
	
	public int getNoOfCopies(){
		return noOfCopies;
	}
	
	@Override
	public String toString() {
		return "Book Copies [bookId=" + bookId + ", branchId=" + branchId + ", noOfCopies=" + noOfCopies + "]";
	}

}
