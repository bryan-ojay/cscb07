package test;

import org.junit.*;
import static org.junit.Assert.*;
import a2.Echo;

public class EchoTest {
  Echo echo = new Echo();
  
  @Test
  public void testEcho() {
    assertEquals("test string", echo.execute("\"test string\""));
  }
  
  @Test
  public void testEchoWithQuote() {
    //should return an error, since there's a quote in the string
    assertEquals(null, echo.execute("\"test\"string\""));
  }
  
  @Test
  public void testEchoNoQuotes() {
    //should return an error, string has no quotes
    assertEquals(null, echo.execute("test string"));
  }
  
  
}
