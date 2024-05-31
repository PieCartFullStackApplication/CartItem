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
    public CartItem updateCartItem(CartItem cartItem) {
        return cartRepo.save(cartItem);
    }
}
