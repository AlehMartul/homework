import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.URISyntaxException;

public class WallTest {
    private static final String OWNER_ID = "5030517";
    private static final String MESSAGE = "Test message";
    private static final String EDITED_MESSAGE = "Edited message";
    private static final String ACCESS_TOKEN =
            "f46c6bf6eafa354c08b3320a858e2a29e17d5d6c7c974a85a14f5e1d4e441c1a9773d4384dd3ddd31965";
    Wall wall = new Wall();

    @Test()
    public void postMessageTest() throws IOException, URISyntaxException {
        wall.post(MESSAGE, OWNER_ID, ACCESS_TOKEN);
        Assert.assertTrue(wall.postIsExist(OWNER_ID, ACCESS_TOKEN));
    }

    @Test(priority = 1)
    public void editMessageTest() throws IOException, URISyntaxException {
        wall.edit(EDITED_MESSAGE, OWNER_ID, ACCESS_TOKEN);
        Assert.assertTrue(wall.postIsEdited(EDITED_MESSAGE, OWNER_ID, ACCESS_TOKEN));
    }

    @Test(priority = 2)
    public void deleteMessageTest() throws IOException, URISyntaxException {
        wall.delete(OWNER_ID, ACCESS_TOKEN);
        Assert.assertFalse(wall.postIsExist(OWNER_ID, ACCESS_TOKEN));
    }
}