import Task01.e1b.Exercise1bClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class Exercise1bClassTest {
    Exercise1bClass junit = null;

    final int TEST_PREPARATION_FAIL = -1;
    final int TEST_SUCCESS = 0;
    final int MAIN_PAGE_FAIL = 1;
    final int MAIN_MENU_FAIL = 2;
    final int DESKTOP_PAGE_FAIL = 3;
    final int SET_PAGE_SIZE_FAIL = 4;
    final int RESULT_PAGE_SIZE_FAIL = 5;
    final int SET_SORT_FAIL = 6;
    final int RESULT_SORT_FAIL = 7;
    final int ADD_TO_CART_FAIL = 8;
    final int SET_CPU_FAIL = 9;
    final int SET_RAM_FAIL = 10;
    final int SELECT_SOFTWARE_FAIL = 11;
    final int OPEN_CART_FAIL = 12;
    final int CHECK_CART_FAIL = 13;
    final int REMOVE_CART_FAIL = 14;

    @BeforeEach
public void setUp() {
        junit = new Exercise1bClass();
    }

    @Test
    public void mainPage() {
        int actual = junit.mainPage();
        printExitCodeDescription(actual);
        Assertions.assertEquals(0, actual);
    }

    @Test
    public void setCPU() {
        int actual = junit.setCPU();
        printExitCodeDescription(actual);
        Assertions.assertEquals(0, actual);
    }

    @Test
    public void setRAM() {
        int actual = junit.setRAM();
        printExitCodeDescription(actual);
        Assertions.assertEquals(0, actual);
    }

    @Test
    public void setSoftware() {
        int actual = junit.setSoftware();
        printExitCodeDescription(actual);
        Assertions.assertEquals(0, actual);
    }

    @Test
    public void addToCart() {
        int actual = junit.addToCart();
        printExitCodeDescription(actual);
        Assertions.assertEquals(0, actual);
    }

    @Test
    public void openCart() {
        int actual = junit.openCart();
        printExitCodeDescription(actual);
        Assertions.assertEquals(0, actual);
    }

    @Test
    public void removeFromCart() {
        int actual = junit.removeFromCart();
        printExitCodeDescription(actual);
        Assertions.assertEquals(0, actual);
    }
    public void printExitCodeDescription(int errorCode){
        switch (errorCode) {
            case TEST_PREPARATION_FAIL:
                System.out.println("TEST_PREPARATION_FAIL");
                break;
            case TEST_SUCCESS:
                System.out.println("TEST_SUCCESS");
                break;
            case MAIN_PAGE_FAIL:
                System.out.println("MAIN_PAGE_FAIL");
                break;
            case MAIN_MENU_FAIL:
                System.out.println("MAIN_MENU_FAIL");
                break;
            case DESKTOP_PAGE_FAIL:
                System.out.println("DESKTOP_PAGE_FAIL");
                break;
            case SET_PAGE_SIZE_FAIL:
                System.out.println("SET_PAGE_SIZE_FAIL");
                break;
            case RESULT_PAGE_SIZE_FAIL:
                System.out.println("RESULT_PAGE_SIZE_FAIL");
                break;
            case SET_SORT_FAIL:
                System.out.println("SET_SORT_FAIL");
                break;
            case RESULT_SORT_FAIL:
                System.out.println("RESULT_SORT_FAIL");
                break;
            case ADD_TO_CART_FAIL:
                System.out.println("ADD_TO_CART_FAIL");
                break;
            case SET_CPU_FAIL:
                System.out.println("SET_CPU_FAIL");
                break;
            case SET_RAM_FAIL:
                System.out.println("SET_RAM_FAIL");
                break;
            case SELECT_SOFTWARE_FAIL:
                System.out.println("SELECT_SOFTWARE_FAIL");
                break;
            case OPEN_CART_FAIL:
                System.out.println("OPEN_CART_FAIL");
                break;
            case CHECK_CART_FAIL:
                System.out.println("CHECK_CART_FAIL");
                break;
            case REMOVE_CART_FAIL:
                System.out.println("REMOVE_CART_FAIL");
                break;
        }
    }
}
