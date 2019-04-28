package poker.database;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    User user;
    User userEmpty;

    @Before
    public void setUp() {
        this.user = new User("testname", "testpassword");
        this.userEmpty = new User();
    }

    @Test
    public void winningsIs0InTheBeginning() {
        assertTrue(user.getWinnings() == 0);
    }

    @Test
    public void insertsIs0InTheBeginning() {
        assertTrue(user.getMoneyInserted() == 0);
    }

    @Test
    public void emptyUserUsername() {
        assertEquals(userEmpty.getUserName(), "");
    }

    @Test
    public void emptyUserPassword() {
        assertEquals(userEmpty.getPassword(), "");
    }

    @Test
    public void emptyUserWinnings() {
        assertTrue(userEmpty.getWinnings() == 0);
    }

    @Test
    public void emptyUserInserts() {
        assertTrue(userEmpty.getMoneyInserted() == 0);
    }

    @Test
    public void testSetWinnings() {
        user.setWinnings(100);
        assertTrue(user.getWinnings() == 100);
    }

    @Test
    public void testSetMoneyInserted() {
        user.setMoneyInserted(100);
        assertTrue(user.getMoneyInserted() == 100);
    }
}
