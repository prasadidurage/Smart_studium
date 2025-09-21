package lk.ijse.b72.finalproject.back_end.exception;

public class ImageExtractionFailedException extends RuntimeException {
    public ImageExtractionFailedException(String imageId) {
        super("Failed to extract image with id: " + imageId);
    }
}
