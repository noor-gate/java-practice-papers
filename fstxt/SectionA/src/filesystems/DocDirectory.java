package filesystems;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DocDirectory extends DocFile {

    private Set<DocFile> files;

    public DocDirectory(String name) {
        super(name);
        files = new HashSet<>();
    }

    @Override
    public int getSize() {
        return getName().length();
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public boolean isDataFile() {
        return false;
    }

    @Override
    public DocDirectory asDirectory() {
        return this;
    }

    @Override
    public DocDataFile asDataFile() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DocFile duplicate() {
        DocDirectory dir = new DocDirectory(getName());
        for (DocFile d : files) {
            dir.addFile(d.duplicate());
        }
        return dir;
    }

    public boolean containsFile(String name) {
        for (DocFile d : files) {
            if (d.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Set<DocFile> getAllFiles() {
        return files;
    }

    public Set<DocDirectory> getDirectories() {
        return files.stream()
                .filter(DocFile::isDirectory)
                .map(f -> (DocDirectory) f)
                .collect(Collectors.toSet());
    }

    public Set<DocDataFile> getDataFiles() {
        return files.stream()
                .filter(DocFile::isDataFile)
                .map(f -> (DocDataFile) f)
                .collect(Collectors.toSet());
    }

    public void addFile(DocFile file) {
        for (DocFile d : files) {
            if (d.getName().equals(file.getName())) {
                throw new IllegalArgumentException();
            }
        }
        files.add(file);
    }

    public boolean removeFile(String filename) {
        for (DocFile d : files) {
            if (d.getName().equals(filename)) {
                files.remove(d);
                return true;
            }
        }
        return false;
    }

    public DocFile getFile(String filename) {
        for (DocFile d : files) {
            if (d.getName().equals(filename)) {
                return d;
            }
        }
        return null;
    }

}
