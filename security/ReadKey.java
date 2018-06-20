package security;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadKey
{
  public ReadKey() {}
  
  public String read(String fileName)
  {
    StringBuilder sb = new StringBuilder();
    
    try
    {
      BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));Throwable localThrowable2 = null;
      try { String s;
        while ((s = in.readLine()) != null)
          sb.append(s);
        in.close();
      }
      catch (Throwable localThrowable1)
      {
        localThrowable2 = localThrowable1;throw localThrowable1;

      }
      finally
      {

        if (in != null) if (localThrowable2 != null) try { in.close(); } catch (Throwable x2) { localThrowable2.addSuppressed(x2); } else in.close();
      }
    } catch (IOException e) { e = 
      



        e;sb.append(""); } finally {}
    return sb.toString();
  }
}
