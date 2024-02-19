package com.equalexperts.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.dfp.DfpField.RoundingMode;
import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


import com.equalexperts.CartCalculationService;
import com.equalexperts.dto.Product;

class TestCartCalculationService {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void test_calculateTaxPayable_for_12_point_5_Percent() {
		CartCalculationService calculationService = new CartCalculationService();
		Double taxPercent = 12.50 ;
		Double cartSubtotal = 111.00;
	    
		Double computed = calculationService.calculateTaxPayable(taxPercent, cartSubtotal);
		Double calculated = Precision.round(cartSubtotal*taxPercent/100, 2, RoundingMode.ROUND_HALF_EVEN.ordinal());
		
		assertEquals(calculated, computed);
	}
	
	@Test
	public void test_calculateTotalPayable_for_tax_rounded_to_two_digits() {
		CartCalculationService calculationService = new CartCalculationService();
		Double subtotal = 99.12;
		Double tax= 11.11;
		Double totalPayable = subtotal+tax;
		assertEquals(totalPayable, calculationService.calculateTotalPayable(subtotal, tax));
		
	}
	
	@Test
	public void test_calculateCartSubtotal_for_two_products() {
		CartCalculationService calculationService = new CartCalculationService();
		Product product = new Product();
		product.setPrice(1.23);
		product.setTitle("frosties");
		Product productBerry = new Product();
		productBerry.setPrice(1.23);
		productBerry.setTitle("berries");
		List<Product>productList = new ArrayList<Product>();
		productList.add(productBerry);
		productList.add(product);
		assertEquals(Double.valueOf(2.46), calculationService.calculateCartSubtotal(productList));
	}
	

}
