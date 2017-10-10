package Icy_Tower;

import java.awt.Image;

public abstract class ImageModel {

    Image img = null;
    int x = 0;
    int y = 0;
    double dx = 0;
    double dy = 0;
    double stored_dy = 0;
    int width = 0;
    int height = 0;

    public abstract void update();
}
