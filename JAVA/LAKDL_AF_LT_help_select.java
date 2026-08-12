//Option Id is 6.0  
//This File was created by SVA on 17-05-2006 
//Marketing Help SQL
import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_AF_LT_help_select  {  
	
	//Enter Filds Here
	
	public Object Ret_Object = new Object();
	public String ret_str;
	//String m_schema_name="";
	
	//Put the Sqlnames Bigining with m_*
	
	LAKDL_AF_CO_conn_methods m_sn_methods=new LAKDL_AF_CO_conn_methods();
	String m_schema_name=m_sn_methods.schema_name.trim();
	
	
	public String m_help_TXT_FINANCE_NO_LEGAL_TERMINATION_sql              ="";
	public String m_help_TXT_FINANCE_NO_LEGAL_TERMINATION_sql_Header       ="Finance - Legal Termination Help";  
	
	
	
	
	// Method for Sql Put Sqls Inside
	
	public Object getSql(Object reqObj1,Object reqObj2,Object reqObj3,Object reqObj4) {
		String Sql_Name  = (String) reqObj1;
		String Start_Val = (String) reqObj2;
		String End_Val   = (String) reqObj3;	
		String Criteria	 = (String) reqObj4;
		
		int m_val =(Integer.parseInt(End_Val));
		m_val++;
		End_Val = Integer.toString(m_val);
		
		Vector m_vector = new Vector();
		String m_substring="";
		int start_index, stop_index, cnt, i;
		start_index = 0;
		stop_index = 0;
		cnt = 0;
		int m_length = Criteria.lastIndexOf("@");
		
		if (m_length!=0) {
			//m_debtorsql	= "inside if "  + m_length;
			while (stop_index<m_length) {
				try {
					stop_index = Criteria.trim().indexOf("@",start_index);
					m_substring = Criteria.substring(start_index,stop_index);
					if (m_substring.length()>0) {
						m_vector.addElement(m_substring);
					}
					else {
						m_vector.addElement("");
					}
					start_index = stop_index+1;
				}
				catch (Exception e) {
					stop_index=m_length;
				}
			}
		}
		else if (m_length==0) {
			//m_debtorsql	= "inside else if "  + m_length;
			m_vector.addElement("");
		}
		
		cnt = m_vector.size();
		
		for ( i = cnt ; i < 8 ; i++ ) {
			m_vector.addElement("");
		}
		
		
		//Added by Disnaka Jayasuriya on 2011-12-23 to get finanace number to legal Termination
		m_help_TXT_FINANCE_NO_LEGAL_TERMINATION_sql=
			
			" SELECT L.NO ,L.FINANCE_NO,L.CLIENT_CODE,L.FULL_NAME,L.APPLICATION_STATUS,L.TEL_NO,L.NIC_NO "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.CLIENT_CODE,P.FULL_NAME,P.APPLICATION_STATUS,P.TEL_NO,P.NIC_NO "+
			" FROM( "+ 
			" SELECT "+
			" A.FINANCE_NO, "+
			" A.CLIENT_CODE, "+
			" B.FULL_NAME FULL_NAME, "+ 
			" A.APPLICATION_STATUS, "+
			" NVL(B.TEL_NO,'-') TEL_NO,  "+
			" NVL(B.NIC_NO,'-') NIC_NO  "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
			"  (SELECT X.FINANCE_NO,V.CLIENT_CODE,V.FULL_NAME,V.TEL_NO,V.NIC_NO  "+
			"  FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS X, "+m_schema_name+".AF_CO_MAS_CLIENT V  "+
			"  WHERE V.CLIENT_CODE=X.CLIENT_CODE "+
			"  ) B  "+
			"  WHERE A.FINANCE_NO=B.FINANCE_NO  "+
			"  AND   "+
			"  (UPPER(B.FULL_NAME) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
			"  UPPER(A.CLIENT_CODE) LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
			"  B.TEL_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
			"  B.NIC_NO LIKE UPPER('"+m_vector.elementAt(0)+"%') OR  "+
			"  A.FINANCE_NO LIKE UPPER('"+m_vector.elementAt(0)+"%')  "+
			"  )  "+
			"  AND A.APPLICATION_STATUS=('"+m_vector.elementAt(1)+"')  "+
			"  ORDER BY A.APPLICATION_NO DESC  "+
			
			
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		
		
		
		
		
		// ****Do not Alter The Parts Below
		Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	} 
}

/*----------------------------------------------------------------*/


