package model;

public class LibraryBranch {
	private int branchID;
	private String branchName;
	private String branchAddress;
	
	public LibraryBranch(int branchID, String branchName, String branchAddress) {
		super();
		this.branchID = branchID;
		this.branchName = branchName;
		this.branchAddress = branchAddress;
	}

	public int getBranchID() {
		return branchID;
	}

	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	@Override
	public String toString() {
		return "LibraryBranch [branchID=" + branchID + ", branchName=" + branchName + ", branchAddress=" + branchAddress
				+ "]";
	}
}
