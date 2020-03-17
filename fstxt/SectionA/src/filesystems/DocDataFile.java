package filesystems;

public class DocDataFile extends DocFile {

    private byte[] contents;

    public DocDataFile(String name, byte[] contents) {
        super(name);
        this.contents = contents;
    }

    public boolean containsByte(byte b) {
        for (byte bs : contents) {
            if (bs == b) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getSize() {
        return getName().length() + contents.length;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isDataFile() {
        return true;
    }

    @Override
    public DocDirectory asDirectory() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DocDataFile asDataFile() {
        return this;
    }

    @Override
    public DocFile duplicate() {
        return new DocDataFile(getName(), contents);
    }

}
