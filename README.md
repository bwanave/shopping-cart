# Shopping Cart Implementation
###### Shopping cart implementation supporting below features
#
### Feature 1: Add products to the shopping cart.

##### Given:

An empty shopping cart.

And a product, Dove Soap with a unit price of 39.99

##### When:

The user adds 5 Dove Soaps to the shopping cart

##### Then:

The shopping cart should contain 5 Dove Soaps each with a unit price of 39.99

And the shopping cart’s total price should equal 199.95

# 

### Feature 2: Add additional products of the same type to the shopping cart.

##### Given:

An empty shopping cart

And a product, Dove Soap with a unit price of 39.99

##### When:

The user adds 5 Dove Soaps to the shopping cart

And then adds another 3 Dove Soaps to the shopping cart

##### Then:

The shopping cart should contain 8 Dove Soaps each with a unit price of 39.99

And the shopping cart’s total price should equal 319.92

#

### Feature 3: Calculate the tax rate of the shopping cart with multiple items

##### Given:

An empty shopping cart

And a product, Dove Soap with a unit price of 39.99

And another product, Axe Deo with a unit price of 99.99

And a tax rate of 12.5%

##### When:

The user adds 2 Dove Soaps to the shopping cart

And then adds 2 Axe Deo’s to the shopping cart

##### Then:

The shopping cart should contain 2 Dove Soaps each with a unit price of 39.99

And the shopping cart should contain 2 Axe Deo’s each with a unit price of 99.99

And the total tax amount should equal 35.00

And the shopping cart’s total price should equal 314.96

#

### Feature4: Add products to the shopping cart, which have "Buy X, Get Y free" offer.

##### Given:

An empty shopping cart

And a product, Dove Soap with a unit price of 39.99 and a associated Buy 2 Get 1 Free offer., tax 12.5

And a product, Axe Deo with a unit price of 89.99 and no associated offer.

##### When:

The user adds 3 Dove Soaps to the shopping cart

##### Then:

The shopping cart should contain 3 Dove Soaps each with a unit price of 39.99

And the shopping cart's total price should equal 89.98

And the shopping cart's total discount should equal 39.99

And the total tax amount should equal 10.00

#

### Feature5:

##### When:

The user adds 5 Dove Soaps to the shopping cart

##### Then:

The shopping cart should contain 5 Dove Soaps each with a unit price of 39.99

And the shopping cart's total price should equal 179.96

And the shopping cart's total discount should equal 39.99

And the total tax amount should equal 20.00

#

### Feature6:

##### When:

The user adds 3 Dove Soaps to the shopping cart

And the user adds 2 Axe Deos to the shopping cart

##### Then:

The shopping cart should contain 3 Dove Soaps each with a unit price of 39.99

And the shopping cart should contain 2 Axe Deos each with a unit price of 89.99

And the shopping cart's total price should equal 292.46

And the shopping cart's total discount should equal 39.99

And the total tax amount should equal 32.50
