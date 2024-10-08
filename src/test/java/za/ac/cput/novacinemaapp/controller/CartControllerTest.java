package za.ac.cput.novacinemaapp.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.novacinemaapp.domain.*;
import za.ac.cput.novacinemaapp.factory.*;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTest {

    private static String userID;
    private static String ticketID;
    private static Cart cart;

    private final String BASE_URL = "http://localhost:8080/cart";
    private RestTemplate restTemplate = new RestTemplate();
    private static String cartID;

    private HttpEntity<?> performPostRequest(Object object) {
        return new HttpEntity<>(object);
    }

    @Test
    @Order(1)
    void setUp() {
        // Create and persist the user, only keeping the userID as a String
        userID = "Amaan.Allie@example.com"; // Example user ID

        // Create and persist other entities (simulating the creation of Ticket)
        ticketID = "ticket-123"; // Example ticket ID

        // Create a cart with the userID and ticketID
        cart = CartFactory.buildCart(userID, ticketID, "2");
    }

    @Test
    @Order(2)
    void create() {
        HttpEntity<?> cartEntity = performPostRequest(cart);
        String url = BASE_URL + "/create";
        ResponseEntity<Cart> postResponse = restTemplate.postForEntity(url, cartEntity, Cart.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        Cart savedCart = postResponse.getBody();
        System.out.println("Saved data: " + savedCart);
        assertNotNull(savedCart);
        cartID = String.valueOf(savedCart.getCartID());
    }

    @Test
    @Order(3)
    void read() throws URISyntaxException {
        String url = BASE_URL + "/read/" + cartID;
        ResponseEntity<Cart> getResponse = restTemplate.exchange(RequestEntity.get(new URI(url)).build(), Cart.class);

        Cart readCart = getResponse.getBody();
        assertNotNull(readCart);
        assertEquals(cartID, readCart.getCartID());
        System.out.println("Read: " + readCart);
    }

    @Test
    @Order(4)
    void update() {
        String url = BASE_URL + "/update";
        Cart updatedCart = new Cart.Builder().copy(cart).setQuantity("3").build();

        HttpEntity<?> entity = performPostRequest(updatedCart);

        ResponseEntity<Cart> response = restTemplate.postForEntity(url, entity, Cart.class);
        assertNotNull(response.getBody());
        System.out.println("Updated data: " + response.getBody());
    }

    @Test
    @Order(5)
    void getAll() {
        String url = BASE_URL + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        System.out.println("All carts: " + response.getBody());
    }

    @Test
    @Order(6)
    void delete() {
        String url = BASE_URL + "/delete/" + cartID;
        HttpEntity<?> entity = performPostRequest(cart);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}


