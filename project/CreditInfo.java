package project;

import java.io.Serializable;

public class CreditInfo implements Serializable {
    private int totalCredit;
    private int majorCredit;
    private int notMajorCredit;
    private int requiredCredit;
    private int chooseCredit;
    private int designCredit;
    private int englishCredit;
	public int getTotalCredit() {
		return totalCredit;
	}
	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}
	public int getMajorCredit() {
		return majorCredit;
	}
	public void setMajorCredit(int majorCredit) {
		this.majorCredit = majorCredit;
	}
	public int getNotMajorCredit() {
		return notMajorCredit;
	}
	public void setNotMajorCredit(int notMajorCredit) {
		this.notMajorCredit = notMajorCredit;
	}
	public int getRequiredCredit() {
		return requiredCredit;
	}
	public void setRequiredCredit(int requiredCredit) {
		this.requiredCredit = requiredCredit;
	}
	public int getChooseCredit() {
		return chooseCredit;
	}
	public void setChooseCredit(int chooseCredit) {
		this.chooseCredit = chooseCredit;
	}
	public int getDesignCredit() {
		return designCredit;
	}
	public void setDesignCredit(int designCredit) {
		this.designCredit = designCredit;
	}
	public int getEnglishCredit() {
		return englishCredit;
	}
	public void setEnglishCredit(int englishCredit) {
		this.englishCredit = englishCredit;
	}

    
}