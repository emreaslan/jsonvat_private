package com.jsonvat.exam.model;

public class Period {
	private String effective_from;
	private Rate rates;

	public String getEffective_from() {
		return effective_from;
	}

	public void setEffective_from(String effective_from) {
		this.effective_from = effective_from;
	}

	public Rate getRates() {
		return rates;
	}

	public void setRates(Rate rates) {
		this.rates = rates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((effective_from == null) ? 0 : effective_from.hashCode());
		result = prime * result + ((rates == null) ? 0 : rates.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Period other = (Period) obj;
		if (effective_from == null) {
			if (other.effective_from != null)
				return false;
		} else if (!effective_from.equals(other.effective_from))
			return false;
		if (rates == null) {
			if (other.rates != null)
				return false;
		} else if (!rates.equals(other.rates))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Period [effective_from=" + effective_from + ", rates=" + rates + "]";
	}

}
