package fxuidemo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;





public class MD5
{
  public MD5() {}
  
  public String getHash(String str)
  {
    StringBuilder hexString = new StringBuilder();
    
    try
    {
      MessageDigest md5 = MessageDigest.getInstance("md5");
      md5.reset();
      md5.update(str.getBytes());
      

      byte[] messageDigest = md5.digest();
      
      for (int i = 0; i < messageDigest.length; i++) {
        hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
      }
    }
    catch (NoSuchAlgorithmException e)
    {
      return e.toString();
    }
    //System.out.println(hexString.toString());
    return hexString.toString();
  }
}
