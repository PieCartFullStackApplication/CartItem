package com.pie.kart.cart.Service;

import java.util.List;
import java.util.Optional;

import com.pie.kart.cart.Model.CartItem;

public interface CartItemService {
    public Optional<CartItem> getCartItem(long id);

    public List<CartItem> getAllCartItems();

    public void removeCartItem(long id);

    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(CartItem cartItem);
}
