# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

The shopping cart application is a springboot application :

Please Import the shopping cart application as a maven project

The functionalities that are implemented

1) Add a product to the cart is developed as an API as mentioned below

Local Endpoint : http://localhost:8080/shopping-cart-app/add-to-cart

Request : {
    		"productName":"frosties",
	   		"quantity":3
				
		  } 


Response : 
			{
			    "products": [
			        {
			            "product": "frosties",
			            "quantity": 3
			        }
			    ],
			    "subtotal": 14.97,
			    "tax": 2.25,
			    "total": 17.22
			}

Note : The POstman collection for the same is kept in the resource folder


Assumptions: The product API is called once during the load of the application and the list of available products is assumed to not change 
