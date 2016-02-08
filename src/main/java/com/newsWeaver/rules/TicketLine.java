package com.newsWeaver.rules;

public class TicketLine {
	
	public TicketLine(){
		
	}
	
	public TicketLine(Integer firstValue,Integer secondValue,Integer thirdvalue){
		this.firstValue=firstValue;
		this.secondValue=secondValue;
		this.thirdvalue=thirdvalue;
		
	}
	public Integer getFirstValue() {
		return firstValue;
	}
	public void setFirstValue(Integer firstValue) {
		this.firstValue = firstValue;
	}
	public Integer getSecondValue() {
		return secondValue;
	}
	public void setSecondValue(Integer secondValue) {
		this.secondValue = secondValue;
	}
	public Integer getThirdvalue() {
		return thirdvalue;
	}
	public void setThirdvalue(Integer thirdvalue) {
		this.thirdvalue = thirdvalue;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	
	public Integer getSum(){
		return this.firstValue+ this.secondValue+this.thirdvalue;
	}
	
	public boolean areAllTheSame(){
		return firstValue==secondValue && secondValue==thirdvalue;
	}
	
	public boolean firstValueDifferent(){
		return firstValue != secondValue && firstValue !=thirdvalue;
	}
	
	public void init(){
		if(getSum()==2){
			setResult(10);
		}

		else if(areAllTheSame()){
			setResult( 5);
		}
		
		else if(firstValueDifferent()){
			setResult( 1);
		}
		else{
		 setResult( 0);
		}
	}

	private Integer firstValue;
	private Integer secondValue;
	private Integer thirdvalue;
	private Integer result;


}
