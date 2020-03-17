package filesystems;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class Question2Tests {

  /*
  @Test
  public void testEquals() {
    DocDataFile f1 = new DocDataFile("hello", new byte[] { 1, 2, 3 });
    StringBuilder sb = new StringBuilder();
    sb.append("he");
    sb.append("llo");
    byte[] ba = new byte[3];
    ba[0] = 1;
    ba[1] = 2;
    ba[2] = 3;
    DocDataFile f2 = new DocDataFile(sb.toString(), ba);
    DocDataFile f3 = new DocDataFile("hello", ba);
    DocDataFile f4 = new DocDataFile("hello1", ba);
    DocDataFile f5 = new DocDataFile("hello", new byte[] { 1, 2 });

    Set<Object> objectSet = new HashSet<>();
    objectSet.add(new IOException());
    assertFalse(objectSet.contains(f1));
    objectSet.add(f1);
    assertFalse(objectSet.contains("astring"));
    assertFalse(objectSet.contains(null));
    assertTrue(objectSet.contains(f2));
    assertTrue(objectSet.contains(f3));
    assertFalse(objectSet.contains(f4));
    assertFalse(objectSet.contains(f5));

    List<Object> objectList = new ArrayList<>();
    objectList.add(new IOException());
    assertFalse(objectList.contains(f1));
    objectList.add(f1);
    assertFalse(objectList.contains("astring"));
    assertFalse(objectList.contains(null));
    assertTrue(objectList.contains(f2));
    assertTrue(objectList.contains(f3));
    assertFalse(objectList.contains(f4));
    assertFalse(objectList.contains(f5));

    assertNotEquals(f2, null);

  }
  */

}