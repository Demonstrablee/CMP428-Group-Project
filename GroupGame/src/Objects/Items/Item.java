package Objects.Items;

import java.awt.*;

public abstract class Item {

    public static final int DEFAULT_SIZE = 32;

    public abstract String getName();
    public abstract String getDescription();

    public abstract void use();

    /**
     * Finds the display image based on the item's name.
     * Asset must be of PNG filetype.
     *
     * @return An image, if it exists.
     */
    public Image getDisplayImage() {
        return Toolkit.getDefaultToolkit().getImage("GroupGame/src/images/items/" +
            getName().toLowerCase().replaceAll(" ", "_") + ".png");
    }

}
