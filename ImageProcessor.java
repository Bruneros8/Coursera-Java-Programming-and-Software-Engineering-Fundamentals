import edu.duke.*;
import java.io.File;

public class ImageProcessor {
    private String projectDirectory = "E:/Coursera/images/";
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }
    public ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int invertedRed = 255 - inPixel.getRed();
            int invertedGreen = 255 - inPixel.getGreen();
            int invertedBlue = 255 - inPixel.getBlue();
            pixel.setRed(invertedRed);
            pixel.setGreen(invertedGreen);
            pixel.setBlue(invertedBlue);
        }
        return outImage;
    }
    public void batchProcessImages(boolean grayscale) {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource processedImage = (grayscale) ? makeGray(inImage) : makeInversion(inImage);
            String fileName = f.getName();
            String prefix = (grayscale) ? "gray-" : "inverted-";
            String newFileName = projectDirectory + prefix + fileName;
            processedImage.setFileName(newFileName);
            processedImage.save();
            processedImage.draw();
        }
    }
    public static void main(String[] args) {
        ImageProcessor imageProcessor = new ImageProcessor();
        // Batch Grayscale
        System.out.println("Batch Grayscale Processing:");
        imageProcessor.batchProcessImages(true);
        // Batch Inversion
        System.out.println("\nBatch Inversion Processing:");
        imageProcessor.batchProcessImages(false);
    }
}