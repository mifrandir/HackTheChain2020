package htc.chain;

import java.util.Objects;

public class Transaction 
{
	String fromAddress;
	String toAddress;
	int amount; 
	
	public Transaction(String fromAddress, String toAddress, int amount) throws Exception
	{
		if (amount <= 0) {
			throw new IllegalArgumentException("amount must be positive");
		}
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.amount = amount; 
		
	}

     public boolean equals(Object o) //compares a Transaction to a Object 
     {
    	 if (this == o)
    	 {
    		 return true;
    	 }
    	 if (o == null)
    	 {
    		 return false;
    	 }
    	 if (getClass() != o.getClass())
    	 {
    		 return false;	 
    	 }
    	 Transaction transaction = (Transaction) o; 
    	 return Objects.equals(fromAddress, transaction.fromAddress) 
    			 && Objects.equals(toAddress, transaction.toAddress)
    			 && Objects.equals(amount, transaction.amount);
    		 
     }
     @Override
     public int hashCode() { //returns hash 
         return Objects.hash(fromAddress, toAddress, amount);
     }
     
}
