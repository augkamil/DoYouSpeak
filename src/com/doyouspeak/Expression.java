package com.doyouspeak;

import java.util.Random;

public class Expression {
	private String expression="";
	private String group="";
	private String[] myExpressions={"lorem", "ipsum", "dolor",
		"sit", "amet",
		"consectetuer", "adipiscing", "elit", "morbi", "vel",
		"ligula", "vitae", "arcu", "aliquet", "mollis",
		"etiam", "vel", "erat", "placerat", "ante",
		"porttitor", "sodales", "pellentesque", "augue", "purus"};	
	
	public Expression(){
		Random random = new Random();
        int exp = random.nextInt(20);
		this.expression = this.myExpressions[exp];
	}
	
	public String getExpression() {
	    return(expression);
	}
	  
	public void setExpression(String expression) {
	    this.expression=expression;
	}
	
	public Expression getGroup(){
		Expression exp = this;
        Random random = new Random();
        int type = random.nextInt(3);
        switch (type) {
            case 0:
                exp.group = "One";
                break;
            case 1:
            	exp.group = "Two";
                break;
            case 2:
            	exp.group = "Three";
                break;
        }
        return exp;
	}
	  
	public String toString() {
	    return(getExpression());
	}
}
