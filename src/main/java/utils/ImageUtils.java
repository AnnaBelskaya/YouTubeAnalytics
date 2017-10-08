package utils;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class ImageUtils {
    public static String default_image = "https://cdn3.iconfinder.com/data/icons/avatars-9/145/Avatar_Panda-256.png";

    private ImageUtils(){}

    public static ImageView crop(String image) {
        final ImageView imageView = new ImageView(image);
        imageView.setFitHeight(140);
        imageView.setPreserveRatio(true);
        double x = 70;
        double y = 70;
        final Circle clip = new Circle(x,y,70);
        imageView.setClip(clip);
        return imageView;
    }
}
