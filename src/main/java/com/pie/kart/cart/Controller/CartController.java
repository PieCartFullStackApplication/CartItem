package com.pie.kart.cart.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pie.kart.cart.Model.CartItem;
import com.pie.kart.cart.Service.CartItemService;
import com.pie.kart.cart.Service.Impl.GenericExceptionThrower;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
    @Autowired
    private CartItemService CartItemService;

    @GetMapping("/{id}")
    public CartItem getCartItem(@PathVariable long id) {
        return CartItemService.getCartItem(id).get();
    }

    @GetMapping
    public List<CartItem> getAllCartItems() {
        return CartItemService.getAllCartItems();
    }

    @DeleteMapping("/{id}")
    public void removeCartItemById(@PathVariable long id) {
        CartItemService.removeCartItem(id);
    }

    @PostMapping("/add")
    public CartItem createCartItem(@RequestBody CartItem CartItem) {
        return CartItemService.createCartItem(CartItem);
    }

    @PutMapping("/update/{id}")
    public CartItem updateCartItem(@PathVariable long id, @RequestBody CartItem CartItem) throws GenericExceptionThrower {
        return CartItemService.updateCartItem(id, CartItem);
    }
}
