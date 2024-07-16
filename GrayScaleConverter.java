import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    // I strated with the image I wanted (inImage)
    public ImageResource makeGray(ImageResource inImage) {
        // I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        // For each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            // Look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            // Compute inPixel's red + inPixel's blue + inPixel's green
            // Divide that sum by 3 (call it average)
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            // Set pixel's red to average
            pixel.setRed(average);
            // Set pixel's green to average
            pixel.setGreen(average);
            // Set pixel's blue to average
            pixel.setBlue(average);
        }
        // outImage is your answer
        return outImage;
    }
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            gray.draw();
        }
    }
}