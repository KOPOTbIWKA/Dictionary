public class LetterDictionary extends AbstractDictionary {
    public LetterDictionary(String filename) {
        super(filename);
    }

    @Override
    protected boolean isValidKey(String key) {
        return key.matches("[a-zA-Z]{4}");
    }
}