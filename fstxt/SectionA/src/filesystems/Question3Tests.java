package filesystems;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public class Question3Tests {


  DocFile f1 = new DocDataFile("f1", new byte[] { 1 });
  DocFile f2 = new DocDataFile("f2", new byte[] { 2, 2 });
  DocFile f3 = new DocDataFile("f3", new byte[] { 3, 3, 3 });
  DocFile f4 = new DocDataFile("f4", new byte[] { 4, 4, 4, 4 });
  DocFile f5 = new DocDataFile("f5", new byte[] { 5, 5, 5, 5, 5 });
  DocFile f6 = new DocDataFile("f6", new byte[] { 6, 6, 6, 6, 6, 6 });
  DocFile f7 = new DocDataFile("f7", new byte[] { 7, 7, 7, 7, 7, 7, 7 });
  DocFile f8 = new DocDataFile("f8", new byte[] { 8, 8, 8, 8, 8, 8, 8, 8 });

  DocDirectory d1 = new DocDirectory("d1");
  DocDirectory d2 = new DocDirectory("d2");
  DocDirectory d3 = new DocDirectory("d3");
  DocDirectory d4 = new DocDirectory("dir4");

  final Comparator<DocFile> docFileComparator = (file1, file2) -> file1.getName()
        .compareTo(file2.getName());

  @Before
  public void setUp() throws Exception {
    d1.addFile(f1);
    d1.addFile(f2);
    d1.addFile(d2);
    d1.addFile(d3);
    d2.addFile(f3);
    d2.addFile(f4);
    d2.addFile(f5);
    d2.addFile(d4);
    d3.addFile(f6);
    d3.addFile(f7);
    d3.addFile(f8);
  }

  @Test
  public void getSize() throws Exception {
    assertEquals(2, d1.getSize());
    assertEquals(4, d4.getSize());
  }

  @Test
  public void isDirectory() throws Exception {
    assertTrue(d1.isDirectory());
    assertTrue(d2.isDirectory());
    assertTrue(d3.isDirectory());
    assertTrue(d4.isDirectory());
  }

  @Test
  public void isDataFile() throws Exception {
    assertFalse(d1.isDataFile());
    assertFalse(d2.isDataFile());
    assertFalse(d3.isDataFile());
    assertFalse(d4.isDataFile());
  }

  @Test
  public void asDirectory() throws Exception {
    assertSame(d1, d1.asDirectory());
    assertSame(d2, d2.asDirectory());
    assertSame(d3, d3.asDirectory());
    assertSame(d4, d4.asDirectory());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void asDataFile() throws Exception {
    d1.asDataFile();
  }

  @Test
  public void duplicate() throws Exception {
    DocFile duplicate = d1.duplicate();
    checkDeepDuplicate(duplicate, d1);
  }

  private void checkDeepDuplicate(DocFile first, DocFile second) {
    assertNotSame(first, second);
    assertEquals(first.getName(), second.getName());
    if (first.isDataFile()) {
      assertTrue(second.isDataFile());
    } else {
      assertTrue(first.isDirectory());
      assertTrue(second.isDirectory());
      List<DocFile> firstFiles = new ArrayList<>();
      firstFiles.addAll(first.asDirectory().getAllFiles());
      firstFiles.sort(docFileComparator);
      List<DocFile> secondFiles = new ArrayList<>();
      secondFiles.addAll(second.asDirectory().getAllFiles());
      secondFiles.sort(docFileComparator);
      assertEquals(firstFiles.size(), secondFiles.size());
      for (int i = 0; i < firstFiles.size(); i++) {
        checkDeepDuplicate(firstFiles.get(i), secondFiles.get(i));
      }
    }
  }

  @Test
  public void containsFile() throws Exception {
    assertTrue(d2.containsFile("f3"));
    assertFalse(d3.containsFile("d2"));
    assertTrue(d2.containsFile("dir4"));
    assertFalse(d1.containsFile("nothere"));
  }

  @Test
  public void getDirectories() throws Exception {
    assertTrue(d4.getDirectories().isEmpty());
    List<DocDirectory> directories = new ArrayList<>();
    directories.addAll(d2.getDirectories());
    assertEquals(1, directories.size());
    assertSame(d4, directories.get(0));
  }

  @Test
  public void getDataFiles() throws Exception {
    List<DocDataFile> dataFiles = new ArrayList<>();
    dataFiles.addAll(d1.getDataFiles());
    Collections.sort(dataFiles, docFileComparator);
    assertEquals(2, dataFiles.size());
    assertSame(f1, dataFiles.get(0));
    assertSame(f2, dataFiles.get(1));
  }

  @Test
  public void getAllFiles() throws Exception {
    List<DocFile> files = new ArrayList<>();
    files.addAll(d2.getAllFiles());
    Collections.sort(files, docFileComparator);
    assertEquals(4, files.size());
    assertSame(d4, files.get(0));
    assertSame(f3, files.get(1));
    assertSame(f4, files.get(2));
    assertSame(f5, files.get(3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addFile() throws Exception {
    try {
      d4.addFile(new DocDirectory("somedir"));
    } catch (IllegalArgumentException exception) {
      assertTrue(false);
    }
    d2.addFile(new DocDataFile("dir4", new byte[] { 1 }));
  }

  @Test
  public void removeFile() throws Exception {
    assertFalse(d2.removeFile("d1"));
    assertEquals(4, d2.getAllFiles().size());
    assertTrue(d2.removeFile("dir4"));
    assertEquals(3, d2.getAllFiles().size());
  }

  @Test
  public void getFile() throws Exception {
    assertSame(d4, d2.getFile("dir4"));
    assertSame(f1, d1.getFile("f1"));
  }


}