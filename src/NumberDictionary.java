public class NumberDictionary extends AbstractDictionary {
    public NumberDictionary(String filename) {
        super(filename);
    }

    @Override
    protected boolean isValidKey(String key) {
        return key.matches("[0-9]{5}");
    }
}