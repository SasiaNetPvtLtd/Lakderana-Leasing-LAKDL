import java.io.*;
import java.util.*;
import java.lang.*;

public class LAKDL_AF_CR_BOOK_help_select  {  
	
	public Object Ret_Object = new Object();
	LAKDL_AF_CO_conn_methods m_sn_methods = new LAKDL_AF_CO_conn_methods();
	String m_schema_name = m_sn_methods.schema_name.trim();
	
	
	//------------------------------------------------------------------------
	
	public String m_help_TXT_BRANCH_CODE_sql_new= "";
	public String m_help_TXT_BRANCH_CODE_sql_new_Header= "System Administration - Bank Branch";
	
	public String m_help_TXT_BANK_CODE_sql="";
	public String m_help_TXT_BANK_CODE_sql_Header="System Administration - Bank";
	
	public String m_help_TXT_LOAN_NO_sql="";
	public String m_help_TXT_LOAN_NO_sql_Header="System Administration - Loan No";
	
	public String m_help_TXT_finance_no_sql="";
	public String m_help_TXT_finance_no_sql_Header="Credit - Finance No";
	
	// added by udara 11-07-2019
	public String m_help_TXT_finance_no_all_2_sql="";
	public String m_help_TXT_finance_no_all_2_sql_Header="Credit - Finance No";
	// end by udara 11-07-2019
	
	public String m_help_TXT_finance_no_all_sql="";
	public String m_help_TXT_finance_no_all_sql_Header="Credit - Finance No";
	
	
	
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
		
		
		/*		Purpose  : Select Client Name,Termination No,Termination Date,Client Code
                Used in  : Assert Finance-Leasing/Loans
                Added by : Indika on 22-08-2008
            -----------------------------------------------------------------*/
		
		m_help_TXT_BRANCH_CODE_sql_new=
			" SELECT L.NO ,L.BRANCH_CODE,L.BRANCH_NAME,L.BANK_CODE,L.ADDRESS1,NVL(L.ADDRESS2,'N/A'),NVL(L.CITY_CODE,' '),NVL(L.TEL_NO,'N/A'),NVL(L.FAX_NO,'N/A'),L.DAYS_TO_REALISE,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BRANCH_CODE,P.BRANCH_NAME,P.BANK_CODE,P.ADDRESS1,P.ADDRESS2,P.CITY_CODE,P.TEL_NO,P.FAX_NO,P.DAYS_TO_REALISE,P.DEFAULT_VALUE"+
			" FROM( "+ 
			" SELECT "+
			" BRANCH_CODE,"+
			" BRANCH_NAME,"+
			" BANK_CODE,"+
			" ADDRESS1,"+
			" ADDRESS2,"+
			" CITY_CODE,"+
			" TEL_NO,"+
			" FAX_NO,"+
			" DAYS_TO_REALISE, "+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_BANK_BRANCH "+
		//	" WHERE (UPPER(BRANCH_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(BRANCH_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
		    	" WHERE (UPPER(BRANCH_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(BRANCH_NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND UPPER(BANK_CODE) LIKE UPPER('%"+m_vector.elementAt(1)+"%') AND ACTIVE_STATUS=('"+m_vector.elementAt(2)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_BANK_CODE_sql=
			" SELECT L.NO ,L.BANK_CODE,L.NAME,L.DEFAULT_VALUE"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.BANK_CODE,P.NAME,P.DEFAULT_VALUE "+
			" FROM( "+ 
			" SELECT "+
			" BANK_CODE , "+
			" NAME ,"+
			" DEFAULT_VALUE "+
			" FROM "+m_schema_name+".AF_CO_MAS_BANKS "+
			" WHERE (UPPER(BANK_CODE) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(NAME) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  AND ACTIVE_STATUS=('"+m_vector.elementAt(1)+"') "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
		
		m_help_TXT_LOAN_NO_sql=
			" SELECT L.NO ,L.LOAN_ID,L.BANK,L.BRANCH"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.LOAN_ID,P.BANK,P.BRANCH "+
			" FROM( "+ 
			" SELECT "+
			" LOAN_ID , "+
			" BANK ,"+
			" BRANCH "+
			" FROM "+m_schema_name+".AF_PRO_PLEDGE_CR_BOOK "+
			//" WHERE (UPPER(LOAN_ID) LIKE UPPER('%"+m_vector.elementAt(0)+"%')  "+
			//" WHERE UPPER(LOAN_ID) LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ // commented by udara 11-07-2019
			" WHERE LOAN_ID LIKE UPPER('%"+m_vector.elementAt(0)+"%') "+ // added by udara 11-07-2019
			" AND   BANK LIKE UPPER('%"+m_vector.elementAt(2)+"%') "+ // added by udara 11-07-2019
			" AND   BRANCH LIKE UPPER('%"+m_vector.elementAt(3)+"%') "+ // added by udara 11-07-2019
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";
			
		m_help_TXT_finance_no_sql=
			" SELECT L.NO ,L.FINANCE_NO,L.REG_NO"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.REG_NO "+
			" FROM( "+ 
			" SELECT "+
			" A.FINANCE_NO , "+
			" B.REG_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, AF_CO_PRO_APP_INVOICE_DETAILS B "+
			" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND B.ACTIVE_STATUS<>'C' AND A.FINANCE_NO IS NOT NULL AND (UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(B.REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  "+
			" AND A.APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS','LEGAL')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		
		
		// added by udara 11-07-2019
		m_help_TXT_finance_no_all_2_sql=
			" SELECT L.NO ,L.FINANCE_NO,L.REG_NO,L.LOAN_ID "+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.REG_NO,P.LOAN_ID "+
			" FROM( "+ 
			" SELECT "+
			" A.FINANCE_NO , "+
			" B.REG_NO, "+
			" C.LOAN_ID "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+m_schema_name+".AF_PRO_CR_BOOK C "+
			" WHERE A.APPLICATION_NO=B.APPLICATION_NO "+
			" AND A.FINANCE_NO = C.FINANCE_NO "+
			" AND B.ACTIVE_STATUS<>'C' "+
			" AND A.FINANCE_NO IS NOT NULL "+
			" AND (UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(B.REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  "+
			" AND C.LOAN_ID LIKE '%"+m_vector.elementAt(1)+"%' "+
			" AND A.APPLICATION_STATUS IN ('ACTIVATED','REPOSSESS','LEGAL')"+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
		// end by udara 11-07-2019
		
		
		m_help_TXT_finance_no_all_sql=
			" SELECT L.NO ,L.FINANCE_NO,L.REG_NO"+
			" FROM  "+
			" (SELECT ROWNUM NO,P.FINANCE_NO,P.REG_NO "+
			" FROM( "+ 
			" SELECT "+
			" A.FINANCE_NO , "+
			" B.REG_NO "+
			" FROM "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, AF_CO_PRO_APP_INVOICE_DETAILS B "+
			" WHERE A.APPLICATION_NO=B.APPLICATION_NO AND B.ACTIVE_STATUS<>'C' AND A.FINANCE_NO IS NOT NULL AND (UPPER(A.FINANCE_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%') OR UPPER(B.REG_NO) LIKE UPPER('%"+m_vector.elementAt(0)+"%'))  "+
			"  )P)L  "+
			" WHERE L.NO>=  "+ Start_Val+"  AND L.NO<=  "+End_Val+" ";	
			
			//------------------------------------------------------------------------------------------------------------------------------------------------
			Ret_Object= (Object)Sql_Name;
		return Ret_Object;
		
	}
	
}
