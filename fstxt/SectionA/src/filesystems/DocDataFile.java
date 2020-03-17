package filesystems;

public final class DocDataFile extends DocFile {

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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DocDataFile)) {
            return false;
        }
        DocDataFile data = (DocDataFile) obj;
        return getName().equals(data.getName())
                && checkEqualArrays(contents, data.contents)
                && hashCode() == data.hashCode();
    }

    private boolean checkEqualArrays(byte[] a, byte[] b) {
        if (a.length != b.length) {
            return false;
        }
        for(int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
