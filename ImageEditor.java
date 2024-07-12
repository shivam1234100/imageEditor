import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.awt.*;
public class ImageEditor {
    public static BufferedImage convertToGrayScale(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                outputImage.setRGB(j, i, inputImage.getRGB(j, i));
            }
        }
        return outputImage;
    }
    public static BufferedImage increaseBrightness(BufferedImage inputImage, double percent) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inputImage.getRGB(j, i));
                int red = (int) (pixel.getRed() * (1 + percent));
                int green = (int) (pixel.getGreen() * (1 + percent));
                int blue = (int) (pixel.getBlue() * (1 + percent));
                if (red > 255) {
                    red = 255;
                }
                if (green > 255) {
                    green = 255;
                }
                if (blue > 255) {
                    blue = 255;
                }

                if (red < 0) {
                    red = 0;
                }
                if (green < 0) {
                    green = 0;
                }
                if (blue < 0) {
                    blue = 0;
                }
                Color newPixel = new Color(red, green, blue);
                outputImage.setRGB(j, i, newPixel.getRGB());
            }
        }
        return outputImage;
    }
    public static BufferedImage rotateImage(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(height, width, inputImage.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                outputImage.setRGB(j, width - i - 1, inputImage.getRGB(i, j));
            }
        }
        return outputImage;
    }
    public static BufferedImage horizontalInvert(BufferedImage inputImage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width , height , BufferedImage.TYPE_3BYTE_BGR);
        for(int i = 0 ; i < height ; i++){
            for(int j = 0 ; j <= width / 2 ; j++){
                Color pixel = new Color(inputImage.getRGB(j , i));
                outputImage.setRGB(j , i , inputImage.getRGB(width - j - 1 , i));
                outputImage.setRGB(width - j - 1 , i , pixel.getRGB());
            }
        }
        return outputImage;
    }
    public static BufferedImage invertVertical(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(width, height, inputImage.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                outputImage.setRGB(i, height - j - 1, inputImage.getRGB(i, j));
            }
        }

        return outputImage;
    }
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name/location of the image: ");
        String imageName = scanner.nextLine();
        File inputFile = new File(imageName);
        try {
            BufferedImage inputImage = ImageIO.read(inputFile);
            System.out.println("Select an operation:");
            System.out.println("1. Convert to grayscale");
            System.out.println("2. Increase brightness");
            System.out.println("3. Rotate image");
            System.out.println("4. Invert vertically");
            System.out.println("5. Invert horizontally");
            int choice = scanner.nextInt();
            BufferedImage outputImage = null;
            switch (choice) {
                case 1:
                    outputImage = convertToGrayScale(inputImage);
                    break;
                case 2:
                    System.out.print("Enter brightness  percentage: ");
                    double brightnessPercent = scanner.nextDouble();
                    outputImage = increaseBrightness(inputImage, brightnessPercent);
                    break;
                case 3:
                    outputImage = rotateImage(inputImage);
                    break;
                case 4:
                    outputImage = invertVertical(inputImage);
                    break;
                case 5:
                    outputImage = horizontalInvert(inputImage);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    scanner.close();
                    return;
            }
            File outputImageFile = new File("outputImage.jpg");
            ImageIO.write(outputImage, "jpg", outputImageFile);
            System.out.println("work done image saved with name outputimage :)");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
           