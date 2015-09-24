package io.pivotal.cc.app;

import java.io.Serializable;

@SuppressWarnings("serial")
public class HeatMapItem implements Serializable, Comparable<HeatMapItem>{
	private String state;
	private int value;
	private String heatMapColor;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getHeatMapColor() {
		return heatMapColor;
	}
	public void setHeatMapColor(String heatMapColor) {
		this.heatMapColor = heatMapColor;
	}

	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public int compareTo(HeatMapItem o) {
		int compare = Integer.valueOf(getValue()).compareTo(((HeatMapItem)o).getValue());
		if (compare==0) return 1;
		return compare;
	}
	
	
}
