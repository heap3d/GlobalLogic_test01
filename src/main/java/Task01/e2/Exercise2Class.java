package Task01.e2;

import org.json.JSONObject;

public class Exercise2Class {
    static String token;
    static int bookingid;

    public static void setBookingId(int inputBookingId) {
        bookingid = inputBookingId;
    }

    public static int getBookingId() {
        return bookingid;
    }

    public static void setToken(String inputToken) {
        token = inputToken;
    }

    public static String getToken() {
        return token;
    }

    public static String getRequestAuthBody(String username, String password) {
        JSONObject body = new JSONObject();
        body.put("username", username);
        body.put("password", password);

        return body.toString();
    }

    public static String getRequestBookingBody(
            String firstname,
            String lastname,
            int totalprice,
            boolean depositpaid,
            String checkin,
            String checkout,
            String additionalneeds) {

        JSONObject body = new JSONObject();
        body.put("firstname", firstname);
        body.put("lastname", lastname);
        body.put("totalprice", totalprice);
        body.put("depositpaid", depositpaid);
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);
        body.put("bookingdates", bookingDates);
        body.put("additionalneeds", additionalneeds);

        return body.toString();
    }
}
