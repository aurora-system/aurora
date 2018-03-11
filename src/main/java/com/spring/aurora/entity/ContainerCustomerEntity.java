package com.spring.aurora.entity;

public class ContainerCustomerEntity {
    
	private String customerName;
    private int slimTotal;
    private int roundTotal;

    public ContainerCustomerEntity(String customerName, int slimTotal, int roundTotal) {
		super();
		this.customerName = customerName;
		this.slimTotal = slimTotal;
		this.roundTotal = roundTotal;
	}

	public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

	public int getSlimTotal() {
		return slimTotal;
	}

	public void setSlimTotal(int slimTotal) {
		this.slimTotal = slimTotal;
	}

	public int getRoundTotal() {
		return roundTotal;
	}

	public void setRoundTotal(int roundTotal) {
		this.roundTotal = roundTotal;
	}

}
