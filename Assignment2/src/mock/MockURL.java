package mock;

public class MockURL {
	 public String getRemoteDataFromURLHtml(String URL)
     {
           return "<html><head></head><body>HelloWorld</body></html>";
      }
	 
	 public String getRemoteDataFromURLTxt(String URL)
     {
           return "hola world!";
      }
}
