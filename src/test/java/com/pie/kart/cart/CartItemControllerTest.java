package com.pie.kart.cart;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pie.kart.cart.Model.CartItem;
import com.pie.kart.cart.Repo.CartRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CartItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CartRepository cartItemRepository;

    @Test
    void testGetCartItem() throws Exception {
        CartItem cartItem = new CartItem();
        cartItem.setTitle("Test Cart Item");
        cartItem.setPrice(100.0);
        cartItem = cartItemRepository.save(cartItem);

        mockMvc.perform(get("/cart/" + cartItem.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cartItem.getId()))
                .andExpect(jsonPath("$.title").value("Test Cart Item"));

        CartItem fetchedCartItem = cartItemRepository.findById(cartItem.getId()).orElse(null);
        assertEquals("Test Cart Item", fetchedCartItem.getTitle());
    }

    @Test
    void testGetAllCartItems() throws Exception {
        CartItem cartItem1 = new CartItem();
        cartItem1.setTitle("Cart Item 1");
        cartItem1.setPrice(100.0);

        CartItem cartItem2 = new CartItem();
        cartItem2.setTitle("Cart Item 2");
        cartItem2.setPrice(50.0);

        cartItemRepository.save(cartItem1);
        cartItemRepository.save(cartItem2);
        Iterable<CartItem> allCartItems = cartItemRepository.findAll();
        assertTrue(allCartItems.iterator().hasNext());
    }

    @Test
    void testRemoveCartItemById() throws Exception {
        CartItem cartItem = new CartItem();
        cartItem.setTitle("Test Cart Item");
        cartItem.setPrice(100.0);
        cartItem = cartItemRepository.save(cartItem);

        mockMvc.perform(delete("/cart/" + cartItem.getId()))
                .andExpect(status().isOk());

        boolean cartItemExists = cartItemRepository.existsById(cartItem.getId());
        assertTrue(!cartItemExists);
    }

    @Test
    void testCreateCartItem() throws Exception {
        CartItem cartItem = new CartItem();
        cartItem.setTitle("New Cart Item");
        cartItem.setPrice(150.0);

        mockMvc.perform(post("/cart/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(cartItem)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("New Cart Item"));

        CartItem createdCartItem = cartItemRepository.findByTitle("New Cart Item");
        assertEquals("New Cart Item", createdCartItem.getTitle());
    }

    @Test
    void testUpdateCartItem() throws Exception {
        CartItem cartItem = new CartItem();
        cartItem.setTitle("Original Cart Item");
        cartItem.setPrice(200.0);
        cartItem = cartItemRepository.save(cartItem);

        CartItem updatedCartItem = new CartItem();
        updatedCartItem.setTitle("Updated Cart Item");
        updatedCartItem.setPrice(250.0);

        mockMvc.perform(put("/cart/update/" + cartItem.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedCartItem)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Cart Item"))
                .andExpect(jsonPath("$.price").value(250.0));

        CartItem fetchedCartItem = cartItemRepository.findById(cartItem.getId()).orElse(null);
        assertEquals("Updated Cart Item", fetchedCartItem.getTitle());
    }
}
