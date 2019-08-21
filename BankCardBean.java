
public class BankCardBean {
	
	/**
	 * 卡号
	 */
	private String cardNo;
	/** 
	 * 银行ID
	 */
	private String bankId;
	/**
	 * cvv
	 */
	private String cvv;
	
	private String bankName;
	/**
	 * VALIDITY_PERIOD
	 */
	private String validityPeriod;

	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getValidityPeriod() {
		return validityPeriod;
	}
	public void setValidityPeriod(String validityPeriod) {
		this.validityPeriod = validityPeriod;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
