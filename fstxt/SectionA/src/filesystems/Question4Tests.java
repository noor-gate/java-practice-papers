package filesystems;

import static org.junit.Assert.*;

import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

public class Question4Tests {

  /*
  DocFile f1 = new DocDataFile("we", new byte[] { 1 });
  DocFile f2 = new DocDataFile("went", new byte[] { 2, 2 });
  DocFile f3 = new DocDataFile("to", new byte[] { 3, 3, 3 });
  DocFile f4 = new DocDataFile("the", new byte[] { 4, 4, 4, 4 });
  DocFile f5 = new DocDataFile("animal", new byte[] { 5, 5, 5, 5, 5 });
  DocFile f6 = new DocDataFile("fair", new byte[] { 6, 6, 6, 6, 6, 6 });
  DocFile f7 = new DocDataFile("the", new byte[] { 7, 7, 7, 7, 100, 7, 7 });
  DocFile f8 = new DocDataFile("birds", new byte[] { 8, 8, 8, 8, 89, 8, 8, 8 });

  DocDirectory d1 = new DocDirectory("and");
  DocDirectory d2 = new DocDirectory("the");
  DocDirectory d3 = new DocDirectory("beasts");
  DocDirectory d4 = new DocDirectory("were");
  DocDirectory d5 = new DocDirectory("there");

  @Before
  public void setUp() throws Exception {
    d1.addFile(d2);
    d2.addFile(d3);
    d3.addFile(d4);
    d3.addFile(d5);
    d5.addFile(f1);
    d5.addFile(f2);
    d5.addFile(f3);
    d4.addFile(f4);
    d1.addFile(f5);
    d1.addFile(f6);
    d2.addFile(f7);
    d3.addFile(f8);
  }

  @Test
  public void getTotalDirectorySize() throws Exception {
    assertEquals("wewenttotheanimalfairthebirdsandthebeastswerethere".length()
          + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8, DocFileUtils.getTotalDirectorySize(d1));
    assertEquals("were".length() + "the".length()
          + 4, DocFileUtils.getTotalDirectorySize(d4));
    assertEquals("there".length() + "wewentto".length()
          + 1 + 2 + 3, DocFileUtils.getTotalDirectorySize(d5));
  }

  @Test
  public void copy() throws Exception {
    assertEquals("were".length() + "the".length() + 4,
          DocFileUtils.getTotalDirectorySize(d4));
    assertFalse(DocFileUtils.searchForByte(d4, (byte) 3).isPresent());

    DocFileUtils.copy(d3, d4, "there");

    assertEquals("were".length() + "there".length()
                + "we".length() + "went".length() + "to".length() + "the".length()
                + 1 + 2 + 3 + 4,
          DocFileUtils.getTotalDirectorySize(d4));
    final Optional<DocDataFile> docDataFile = DocFileUtils.searchForByte(d4, (byte) 3);
    assertTrue(docDataFile.isPresent());
    assertEquals("to", docDataFile.get().getName());
    assertEquals("to".length() + 3, docDataFile.get().getSize());
    assertNotSame(f3, docDataFile.get());

  }

  @Test
  public void searchForByte() throws Exception {
    Optional<DocDataFile> result;

    result = DocFileUtils.searchForByte(f2, (byte) 4);
    assertFalse(result.isPresent());

    result = DocFileUtils.searchForByte(f4, (byte) 4);
    assertTrue(result.isPresent());
    assertSame(f4, result.get());

    result = DocFileUtils.searchForByte(d2, (byte) 100);
    assertTrue(result.isPresent());
    assertSame(f7, result.get());

    result = DocFileUtils.searchForByte(d2, (byte) 89);
    assertTrue(result.isPresent());
    assertSame(f8, result.get());

    result = DocFileUtils.searchForByte(d3, (byte) 100);
    assertFalse(result.isPresent());

  }
  */

}
