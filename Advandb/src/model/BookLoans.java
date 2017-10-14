package model;

import java.sql.Date;

public class BookLoans {
	private int bookId;
	private int branchId;
	private int cardNo;
	private Date dateOut;
	private Date dueDate;
	private Date dateReturned;
	
	public BookLoans(int bookId, int branchId, int cardNo, Date dateOut, Date dueDate, Date dateReturned){
		this.bookId = bookId;
		this.branchId = branchId;
		this.cardNo = cardNo;
		this.dateOut = dateOut;
		this.dueDate = dueDate;
		this.dateReturned = dateReturned;
	}

	public int getBookId() {
		return bookId;
	}

	public int getBranchId() {
		return branchId;
	}

	public int getCardNo() {
		return cardNo;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public Date getDateReturned() {
		return dateReturned;
	}
	
	@Override
	public String toString() {
		return "BookLoans [bookId=" + bookId + ", branchId=" + branchId + ", cardNo=" + cardNo + "dateOut= "+ dateOut.toString() + "dueDate= " + dueDate.toString()+ "dateReturned= " + dateReturned.toString()+"]";
	}
	
}
