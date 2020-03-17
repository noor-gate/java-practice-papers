package filesystems;

import static org.junit.Assert.*;

import org.junit.Test;

public class Question1Tests {

  /*
  @Test
  public void getSize() throws Exception {
    DocFile file = new DocDataFile("testfile.txt",
          new byte[] { 1, 2, 3, 4, 5, 6, 7 });
    assertEquals(19, file.getSize());
    file = new DocDataFile("",
          new byte[] { });
    assertEquals(0, file.getSize());
  }

  @Test
  public void isDirectory() throws Exception {
    DocFile file = new DocDataFile("testfile.txt", new byte[] { 1, 2, 3 });
    assertFalse(file.isDirectory());
  }

  @Test
  public void isDataFile() throws Exception {
    DocFile file = new DocDataFile("testfile.txt", new byte[] { 1, 2, 3 });
    assertTrue(file.isDataFile());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void asDirectory() throws Exception {
    new DocDataFile("", new byte[] { }).asDirectory();
  }

  @Test
  public void asDataFile() throws Exception {
    DocFile file = new DocDataFile("hello.txt", new byte[] { 5, 4, 3, 2, 1 });
    DocDataFile dataFile = file.asDataFile();
    assertSame(file, dataFile);
  }

  @Test
  public void containsByte() throws Exception {
    DocDataFile file = new DocDataFile("hello.txt", new byte[] { 5, 5, 4, 4, 3 });
    assertTrue(file.containsByte((byte) 5));
    assertTrue(file.containsByte((byte) 4));
    assertTrue(file.containsByte((byte) 3));
    assertFalse(file.containsByte((byte) 2));
    assertFalse(file.containsByte((byte) 1));
  }

  @Test
  public void duplicate() throws Exception {
    DocDataFile file = new DocDataFile("afile.png", new byte[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 });
    DocFile duplicate = file.duplicate();
    assertNotSame(file, duplicate);
    assertEquals(file.getName(), duplicate.getName());
    assertEquals(file.getSize(), duplicate.getSize());
    assertTrue(duplicate.isDataFile());
    for (int i = 0; i <= 10; i++) {
      if (file.containsByte((byte) i)) {
        assertTrue(duplicate.asDataFile().containsByte((byte) i));
      } else {
        assertFalse(duplicate.asDataFile().containsByte((byte) i));
      }
    }

  }
  */

}