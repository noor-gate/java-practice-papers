package filesystems;

public abstract class DocFile {

  private final String name;

  /**
   * Construct a file with the given name.
   * @param name The name of the file.
   */
  DocFile(String name) {
    this.name = name;
  }

  /**
   * Get the name of the file.
   * @return The name of the file.
   */
  public final String getName() {
    return name;
  }

  /**
   * Get the size of the file.
   * @return The size of the file in bytes.
   */
  public abstract int getSize();

  /**
   * Determine whether the file is a directory.
   * @return true iff the file is a directory.
   */
  public abstract boolean isDirectory();

  /**
   * Determine whether the file is a data file.
   * @return true iff the file is a data file.
   */
  public abstract boolean isDataFile();

  /**
   * Return the file as a directory.  Fails unless
   * isDirectory() holds.
   * @return The file as a directory.
   */
  public abstract DocDirectory asDirectory();

  /**
   * Return the file as a data file.  Fails unless
   * isDataFile() holds.
   * @return The file as a data file.
   */
  public abstract DocDataFile asDataFile();

  /**
   * Returns a duplicated version of the file.
   * @return A duplicated version of the file.
   */
  public abstract DocFile duplicate();

}
