package com.equalexperts;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.equalexperts.dto.Product;
import com.equalexperts.response.ProductInCart;
import com.equalexperts.response.ShoppingCartResponse;
import com.fasterxml.jackson.databind.ObjectMapper;






public class ShoppingCart {

	public ShoppingCart() {
		super();
		this.priceMap = initializeAvailableProducts();
		this.cartproducts = new ArrayList<Product>();
		
	}

	private Map<String, Double> priceMap ; 
	private List<Product> cartproducts ;
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();  
	
	
	/**
	 * This method loads the products and prices
	 */
	public Map<String, Double> initializeAvailableProducts() {
		this.priceMap = new HashMap<String, Double>();
		priceMap.put("cheerios", 0.0);
		priceMap.put("cornflakes",0.0);
		priceMap.put("frosties", 0.0);
		priceMap.put("shreddies", 0.0);
		priceMap.put("weetabix", 0.0);
		return priceMap;
	}
	
	public ShoppingCartResponse addToCart(String productName,Integer quantity) {
		ShoppingCartResponse response=null ;
		if(quantity!=null && quantity>0 && productName!=null && priceMap.containsKey(productName)) {
			//call the API and put value in the map
		
			Double taxPercent = Double.valueOf(12.5);
			CartCalculationService cartCalculationService = new CartCalculationService();
			addProductToInMemList(productName, quantity);
			Double cartSubtotal = cartCalculationService.calculateCartSubtotal(cartproducts);
			Double taxPayable = cartCalculationService.calculateTaxPayable(taxPercent, cartSubtotal);
			Double totalPayable = cartCalculationService.calculateTotalPayable(cartSubtotal, taxPayable);
			response = populateResponse(cartSubtotal, taxPayable, totalPayable);
				
		}
		return response;
	}
	
	private ShoppingCartResponse populateResponse(Double cartSubtotal, Double taxPayable, Double totalPayable) {
		ShoppingCartResponse response = new  ShoppingCartResponse();
		
		Map<String,Integer> productCountMap = new HashMap<String, Integer>();
		for (Product productInCart : cartproducts) {
			if(productCountMap.containsKey(productInCart.getTitle())){
				int productCount = productCountMap.get(productInCart.getTitle());
				productCountMap.put(productInCart.getTitle(),productCount+1 );
			}else {
				productCountMap.put(productInCart.getTitle(),1 );
			}
			
		}
		Set<String> productKeysForCount = productCountMap.keySet();
		List<ProductInCart> productsInCartResponse = new ArrayList<ProductInCart>();
		for (String productkey : productKeysForCount) {
			int totalProdInCart = productCountMap.get(productkey);
			ProductInCart prodInCart = new ProductInCart();
			prodInCart.setQuantity(totalProdInCart);
			prodInCart.setProduct(productkey);
			productsInCartResponse.add(prodInCart);
		}
		response.setProducts(productsInCartResponse);
		response.setSubtotal(cartSubtotal);
		response.setTax(taxPayable);
		response.setTotal(totalPayable);
		return response;
	}
	
	private void addProductToInMemList(String product, Integer quantity) {
		for (int i = 0; i < quantity; i++) {
			Product productToBeAdded = new Product();
			productToBeAdded.setPrice(priceMap.get(product));
			productToBeAdded.setTitle(product);
			cartproducts.add(productToBeAdded);
			
		}
	}
	
	public Double priceLookupForProduct(String product) {  
	  
		HttpClient httpClient = HttpClient.newHttpClient();
		String baseUri = "https://equalexperts.github.io/backend-take-home-test-data/";
	    HttpRequest request = HttpRequest.newBuilder(URI.create(baseUri+product+".json")).GET().build();  
	  
	    try {  
	        HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());  
	        Product lookupProduct = OBJECT_MAPPER.readValue(response.body(), Product.class);
	
	        return lookupProduct.getPrice();
	    }  
	    catch (IOException | InterruptedException e) {  
	        throw new RuntimeException(e);  
	    }  
	}
}
