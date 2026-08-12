//Option Id is 2.0  
//This File was created by SVA on 17-05-2006 
//Marketing Common Methods for SQL
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import oracle.jdbc.driver.*;
import java.sql.*;
import sun.misc.BASE64Decoder;

public class LAKDL_AF_CO_FU_methods
 {
	//java.text.NumberFormat nf;
			
 public String met_unformat_number(String numstr) {
			
			String m_number="";
			for (int i = 0; i < numstr.length(); i++) {
				String oneChar = numstr.substring(i,i+1);
				if (!oneChar.equals(",")) {
					m_number=m_number+oneChar;
				}
			}
      //return nf.format(numstr);
      return m_number;

 }
	
 public String getFollowupCat(String Schema,String Active,String CatType) {
	 
		return " SELECT A.CATEGORY_CODE, A.CATEGORY_NAME "+
					 " FROM   "+Schema+".AF_CO_MAS_FOLLOWUP_CATEGORY A "+
					 " WHERE  A.ACTIVE_STATUS='"+Active+"' AND CATEGORY_CODE LIKE '"+CatType+"%' "+
			     " ORDER BY	DEFAULT_VALUE DESC ";
   	
 }
	

	
 
}




