import Task01.e2.Exercise2Class;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Exercise2ClassTest {
    String token;
    int bookingid;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        token = Exercise2Class.getToken();
        bookingid = Exercise2Class.getBookingId();
    }

    @Test
    @Order(1)
    void auth() {
        String username = "admin";
        String password = "password123";
        // perform auth
        String body = Exercise2Class.getRequestAuthBody(username, password);
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/auth")
                .then()
                .assertThat()
                .statusCode(200)
                .body("token", Matchers.notNullValue())
                .extract().response();
        // store token
        token = response.getBody().jsonPath().getString("token");
        Exercise2Class.setToken(token);
    }

    @Test
    @Order(2)
    void createNewBooking() {
        String firstname = "Firstname";
        String lastname = "Lastname";
        int totalprice = 100;
        boolean depositpaid = true;
        String checkin = "2020-12-25";
        String checkout = "2021-01-05";
        String additionalneeds = "Beer";
        String body = Exercise2Class.getRequestBookingBody(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
        // use the returned token to create a new booking.
        Response response = RestAssured.given()
                .cookie("token", token)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/booking")
                .then()
                .assertThat()
                .statusCode(200)
                .body("bookingid", Matchers.notNullValue())
                .body("booking", Matchers.notNullValue())
                .extract().response();

        JSONObject obj = new JSONObject(response.getBody().asPrettyString());
        // store bookingid
        bookingid = obj.getInt("bookingid");
        Exercise2Class.setBookingId(bookingid);
        //Get details of newly created booking, and ensure it has all details as were specified during its creation.
        String actualFirstname = obj.getJSONObject("booking").getString("firstname");
        String actualLastname = obj.getJSONObject("booking").getString("lastname");
        int actualTotalprice = obj.getJSONObject("booking").getInt("totalprice");
        boolean actualDepositpaid = obj.getJSONObject("booking").getBoolean("depositpaid");
        String actualCheckin = obj.getJSONObject("booking").getJSONObject("bookingdates").getString("checkin");
        String actualCheckout = obj.getJSONObject("booking").getJSONObject("bookingdates").getString("checkout");
        String actualAdditionalneeds = obj.getJSONObject("booking").getString("additionalneeds");

        Assertions.assertEquals(firstname, actualFirstname);
        Assertions.assertEquals(lastname, actualLastname);
        Assertions.assertEquals(totalprice, actualTotalprice);
        Assertions.assertEquals(depositpaid, actualDepositpaid);
        Assertions.assertEquals(checkin, actualCheckin);
        Assertions.assertEquals(checkout, actualCheckout);
        Assertions.assertEquals(additionalneeds, actualAdditionalneeds);

        System.out.println("booking created: " + response.getBody().asPrettyString());
    }

    @Test
    @Order(3)
    void updateBooking() {
        String updatedAdditionalneeds = "2 beers";
        // Update the booking details
        JSONObject body = new JSONObject();
        body.put("additionalneeds", updatedAdditionalneeds);
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .body(body.toString())
                .when()
                .patch("/booking/" + Exercise2Class.getBookingId())
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
        // Get details of the updated booking, and ensure it has new details.
        Assertions.assertEquals(updatedAdditionalneeds, response.jsonPath().getString("additionalneeds"));

        System.out.println("current booking id: " + bookingid);
        System.out.println("booking modified: " + response.getBody().asPrettyString());
    }

    @Test
    @Order(4)
    void getAllBookings() {
        Response response = RestAssured.given()
                .when()
                .get("/booking")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        List<String> listJSON = response.jsonPath().getList("bookingid");
        System.out.println("current booking id: " + bookingid);
        System.out.println("All bookings before deletion: " + listJSON);
        Assertions.assertTrue(listJSON.contains(bookingid));
    }

    @Test
    @Order(5)
    void deleteBooking() {
        // Delete the booking.
        RestAssured.given()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .when()
                .delete("/booking/" + Exercise2Class.getBookingId())
                .then()
                .assertThat()
                .statusCode(201)
                .extract().response();
        // Get all bookings
        Response response = RestAssured.given()
                .when()
                .get("/booking")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
        // Check if booking deleted
        List<String> listJSON = response.jsonPath().getList("bookingid");
        System.out.println("All bookings after deletion:  " + listJSON);
        Assertions.assertFalse(listJSON.contains(bookingid));
    }
}

