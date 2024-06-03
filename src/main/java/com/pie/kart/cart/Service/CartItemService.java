package com.pie.kart.cart.Service;

import java.util.List;
import java.util.Optional;

import com.pie.kart.cart.Model.CartItem;
import com.pie.kart.cart.Service.Impl.GenericExceptionThrower;

public interface CartItemService {
    public Optional<CartItem> getCartItem(long id);

    public List<CartItem> getAllCartItems();

    public void removeCartItem(long id);

    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(long id, CartItem cartItem) throws GenericExceptionThrower;
}
