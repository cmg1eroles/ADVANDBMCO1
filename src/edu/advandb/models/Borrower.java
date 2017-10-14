package edu.advandb.models;

public class Borrower {
	private int cardNo;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	
	public int getCardNo() {
		return cardNo;
	}
	
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Borrower [cardNo=" + cardNo + ", firstName=" + firstName + ", lastName=" + lastName + ", address="
				+ address + ", phone=" + phone + "]";
	}
}
