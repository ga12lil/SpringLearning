import Chain.ParseChain;
import Processor.GitHubProcessor;
import Processor.StackOverflowProcessor;
import Response.ParseResponse;

public final class LinkParser {
    private final ParseChain parseChain;

    public LinkParser() {
        parseChain = new ParseChain();
        parseChain.addProcessor(new GitHubProcessor());
        parseChain.addProcessor(new StackOverflowProcessor());
    }

    public ParseResponse parse(String link) {
        return parseChain.parse(link);
    }
}