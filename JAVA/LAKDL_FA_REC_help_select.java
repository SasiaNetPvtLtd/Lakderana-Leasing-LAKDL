import java.io.*;
import java.util.*;
import java.lang.*;

// DEVELOP BY : INDITHA FOR OFSCL FACTORING    DATE:21-09-2006

public class LAKDL_FA_REC_help_select  {  

	public Object Ret_Object = new Object();
	LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
	String m_schema_name = m_sn_methods.schema_name.trim();
	//Added by Dineth on 2008-10-06
	
	public String m_help_DIV_TXT_FACILITY_INVOICE_ADJUST_sql="";
	public String m_help_DIV_TXT_FACILITY_INVOICE_ADJUST_sql_Header="Operation Process - Invoice Adjustments ";

	public String m_help_DIV_TXT_INV_ALLO_INV_BATCH_sql="";
	public String m_help_DIV_TXT_INV_ALLO_INV_BATCH_sql_Header= "Operation Process - Invoice Settlement Receipt ";
	
	

	public Object getSql(Object reqObj1,Object reqObj2,Object reqObj3,Object reqObj4,Object reqObj5) {
		String Sql_Name  = (String) reqObj1;
		String Start_Val = (String) reqObj2;
		String End_Val   = (String) reqObj3;	
		String Criteria  =	(String) reqObj4;
		String m_user  =   (String) reqObj5;

		int m_val =(Integer.parseInt(End_Val));
		m_val++;
		End_Val = Integer.toString(m_val);
		
		Vector m_vector = new Vector();
		String m_substring="";
		int start_index,stop_index,cnt,i;
		start_index = 0;
		stop_index = 0;
		cnt = 0;
		int m_length = Criteria.lastIndexOf("@");
		
		if (m_length!=0) {
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
			m_vector.addElement("");
		}
		
		cnt = m_vector.size();
		
		for ( i = cnt ; i < 8 ; i++ ) {
			m_vector.addElement("");
		}
		

		

		m_help_DIV_TXT_FACILITY_INVOICE_ADJUST_sql=	
			" SELECT L.NO,L.BATCH_NO,L.TOTAL_BATCH_AMOUNT,L.TOTAL_BATCH_INVOICES,L.INVOICE_BATCH_DATE  "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BATCH_NO,P.TOTAL_BATCH_AMOUNT,P.TOTAL_BATCH_INVOICES,P.INVOICE_BATCH_DATE "+
			" FROM( "+
			"	SELECT A.BATCH_NO,A.TOTAL_BATCH_AMOUNT,A.TOTAL_BATCH_INVOICES,TO_CHAR(A.INVOICE_BATCH_DATE,'DD-MM-YYYY') INVOICE_BATCH_DATE "+
			"	FROM "+m_schema_name+".FA_CR_PRO_INVOICE A "+
			"	WHERE A.FACILITY_NO='"+m_vector.elementAt(0)+"'  AND A.APPROVE_STATUS='ENTER' AND "+
			" "+m_schema_name+".FA_GET_UNAPPROVE_INVOICES(BATCH_NO,'CONF')>0 AND "+
			" ( A.BATCH_NO LIKE UPPER('%"+m_vector.elementAt(1)+"%'))"+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
	

		m_help_DIV_TXT_INV_ALLO_INV_BATCH_sql=
			" SELECT L.NO,L.D_NAME,L.INVOICE_NO,L.BALANCE_AMOUNT "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.D_NAME,P.INVOICE_NO,P.BALANCE_AMOUNT "+
			" FROM( "+
			" SELECT "+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE) D_NAME,INVOICE_NO,BALANCE_AMOUNT "+
			" FROM "+m_schema_name+".FA_CR_PRO_INVOICE_DETAIL "+
			" WHERE INVOICE_STATUS='CONF' "+
			" AND BALANCE_AMOUNT>0 "+
			" AND FACILITY_NO='"+m_vector.elementAt(0)+"' "+
			" AND ( "+
			" (BATCH_NO LIKE ('%"+m_vector.elementAt(1)+"%')) OR "+
			" (INVOICE_NO LIKE ('%"+m_vector.elementAt(1)+"%')) OR "+
			" (UPPER("+m_schema_name+".FA_GET_CLIENT_FULL_NAME(DEBTOR_CODE)) LIKE UPPER('%"+m_vector.elementAt(1)+"%')) "+
			" ) "+
			" ORDER BY DEBTOR_CODE,BALANCE_AMOUNT "+
			"  )P)L  "+
			" WHERE L.NO>=  "+Start_Val+"  AND L.NO<=  "+End_Val+" ";	

	
		
		Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	} 
}


