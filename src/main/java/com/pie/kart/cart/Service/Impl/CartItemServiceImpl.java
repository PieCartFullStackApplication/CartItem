package com.pie.kart.cart.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pie.kart.cart.Model.CartItem;
import com.pie.kart.cart.Repo.CartRepository;
import com.pie.kart.cart.Service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartRepository cartRepo;

    @Override
    public Optional<CartItem> getCartItem(long id) {
        return cartRepo.findById(id);
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartRepo.findAll();

    }

    @Override
    public void removeCartItem(long id) {
        cartRepo.deleteById(id);
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        return cartRepo.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(long id, CartItem cartItem) throws GenericExceptionThrower {
        Optional<CartItem> existingCartItemOptional = cartRepo.findById(id);
        if (existingCartItemOptional.isEmpty()) {
            throw new GenericExceptionThrower("cartItem not found");
        }
        CartItem existingCartItem = existingCartItemOptional.get();
        if (existingCartItem.getId() != id) {
            throw new GenericExceptionThrower("Product ID mismatch");
        }
        existingCartItem.setTitle(cartItem.getTitle());
        existingCartItem.setProductId(cartItem.getProductId());
        existingCartItem.setPrice(cartItem.getPrice());
        existingCartItem.setAvailability(true);
        return cartRepo.save(existingCartItem);
    }
}
