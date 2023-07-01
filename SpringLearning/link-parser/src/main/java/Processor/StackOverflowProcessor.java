package Processor;
import Response.StackOverflowResponse;
import java.net.URI;
import java.net.URISyntaxException;
import org.jetbrains.annotations.Nullable;

public class StackOverflowProcessor implements ParseProcessor{
    @Override
    public StackOverflowResponse parse(String link){
        try {
            URI uri = new URI(link);
            if (!isStackOverflowDomain(uri)) {
                return null;
            }

            return extractDataFromUri(uri);
        } catch (URISyntaxException e) {
            return null;
        }
    }
    private static boolean isStackOverflowDomain(URI uri) {
        return uri.getAuthority().equals("stackoverflow.com");
    }

    private @Nullable StackOverflowResponse extractDataFromUri(URI uri) {
        String[] pathSegments = uri.getPath().split("/");
        if (pathSegments.length != 4 || !pathSegments[1].equals("questions")) {
            return null;
        }
        int id = Integer.parseInt(pathSegments[2]);

        return new StackOverflowResponse(id);
    }
}
