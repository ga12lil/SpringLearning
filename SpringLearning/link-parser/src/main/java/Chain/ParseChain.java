package Chain;
import Processor.ParseProcessor;
import Response.ParseResponse;

import java.util.LinkedList;
import java.util.List;

public final class ParseChain {
    private final List<ParseProcessor> processors = new LinkedList<>();

    public void addProcessor(ParseProcessor parseProcessor) {
        processors.add(parseProcessor);
    }

    public ParseResponse parse(String link) {
        for (ParseProcessor processor : processors) {
            ParseResponse result = processor.parse(link);
            if (result != null) {
                return result;
            }
        }

        return null;
    }
}
