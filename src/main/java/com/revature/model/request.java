package com.revature.model;

public class request {

		private int accountID;
		private String status;
		private int amount;
		private int managerID;
		
		public request(int accountID, String status, int amount, int managerID) {
			super();
			this.accountID = accountID;
			this.status = status;
			this.amount = amount;
			this.managerID = managerID;
		}

		public int getAccountID() {
			return accountID;
		}

		public void setAccountID(int accountID) {
			this.accountID = accountID;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public int getManagerID() {
			return managerID;
		}

		public void setManagerID(int managerID) {
			this.managerID = managerID;
		}
		
		
		
		
		
		
}
