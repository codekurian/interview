package com.equalexperts;

import java.util.List;

import org.apache.commons.math3.dfp.DfpField.RoundingMode;
import org.apache.commons.math3.util.Precision;

import com.equalexperts.dto.Product;


public class CartCalculationService {
	

	
	public Double calculateTaxPayable(Double taxPercent,Double cartSubtotal) {
		Double precent = taxPercent/100;
		return Precision.round(cartSubtotal*precent, 2, RoundingMode.ROUND_HALF_EVEN.ordinal())  ;
	}
	
	public Double calculateTotalPayable(Double subtotal,Double tax) {
		return Precision.round(subtotal+tax, 2, RoundingMode.ROUND_HALF_EVEN.ordinal()); 
	}
	
	public Double calculateCartSubtotal(List<Product> cartproducts) {
		return Precision.round(cartproducts.stream().mapToDouble(Product::getPrice).sum(), 2, RoundingMode.ROUND_HALF_EVEN.ordinal());
	}
	
	
	

}
