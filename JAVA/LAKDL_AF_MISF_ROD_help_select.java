// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LAKDL_AF_MK_help_select.java

import java.util.Vector;

public class LAKDL_AF_MISF_ROD_help_select
{
	
	public LAKDL_AF_MISF_ROD_help_select()
	{
		Ret_Object = new Object();
		m_sn_methods = new LAKDL_AF_CO_conn_methods();
		m_schema_name = m_sn_methods.schema_name.trim();
		
		
		/* ROD */
		m_help_TXT_ROD_document_sql           = " ";
		m_help_TXT_ROD_document_sql_Header    = "Credit - Finance Help "; 
		
		/* ROD End*/
		
		
	}
	
	public Object getSql(Object obj, Object obj1, Object obj2, Object obj3)
	{
		String s = (String)obj;
		String s1 = (String)obj1;
		String s2 = (String)obj2;
		String s3 = (String)obj3;
		
		//    int i = Integer.parseInt(s2);
		//  s2 = Integer.toString(++i);
		
		int m_val =(Integer.parseInt(s2));
		m_val++;
		s2 = Integer.toString(m_val);
		
		Vector vector = new Vector();
		String s4 = "";
		int j = 0;
		int k = 0;
		int l = 0;
		int j1 = s3.lastIndexOf("@");
		if(j1 != 0)
			while(k < j1) 
				try
				{
					k = s3.trim().indexOf("@", j);
					String s5 = s3.substring(j, k);
					if(s5.length() > 0)
						vector.addElement(s5);
					else
						vector.addElement("");
					j = k + 1;
				}
				catch(Exception exception)
				{
					k = j1;
				}
		else{
			if(j1 == 0)
				vector.addElement("");
		}
		l = vector.size();
		
		for(int i1 = l; i1 < 8; i1++){
			vector.addElement("");
		}
		
	

		// ROD Document */
		
		/*
		
		m_help_TXT_ROD_document_sql =
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.VEHICLE_NO,P.NAME, P.APPLICATION_STATUS "+
						" FROM "+
						" (SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.VEHICLE_NO,Q.NAME, Q.APPLICATION_STATUS "+
						" FROM "+
						" (SELECT NVL(A.FINANCE_NO,'-') FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,NVL(B.REG_NO,'-') VEHICLE_NO, "+
						" UPPER("+m_schema_name+".af_co_get_client_name(CLIENT_CODE)) AS NAME, A.APPLICATION_STATUS APPLICATION_STATUS "+
						" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
						" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B "+
						//" WHERE A.APPLICATION_STATUS NOT IN ('ACTIVATED','TERMINATED','TERMI','NORM_TERMI') "+ 
						" WHERE A.APPLICATION_STATUS  IN ('ACTIVATED','REPOSSESS') "+ 
						" AND A.APPLICATION_NO=B.APPLICATION_NO(+) "+
						" AND B.ACTIVE_STATUS = 'Y' "+ 
					//	" AND A.FINANCE_NO NOT IN (SELECT FINANCE_NO FROM "+m_schema_name+".AF_CONFIRMATION_REPORT WHERE FINANCE_NO=A.FINANCE_NO)    "+
						//" AND A.BRANCH_CODE = "+m_schema_name+".AF_CO_GET_USER_LOCATION('"+vector.elementAt(1)+"')   "+ // commented by udara 11-05-2015
						" AND UPPER(A.BRANCH_CODE) LIKE UPPER('"+vector.elementAt(2)+"%')   "+
						" ORDER BY A.FINANCE_NO DESC) Q"+
						" WHERE  (UPPER(Q.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
						" OR 		UPPER(Q.APPLICATION_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
						" OR 		UPPER(Q.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
						" OR 		UPPER(Q.VEHICLE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
						" OR 		UPPER(Q.NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') ) "+
						//" AND	UPPER(Q.CLIENT_CODE) LIKE UPPER('"+vector.elementAt(1)+"%')"+
						" ORDER BY Q.FINANCE_NO DESC   ) P "+
						" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		*/
		
		
		m_help_TXT_ROD_document_sql =
			" SELECT P.NO,P.FINANCE_NO,P.APPLICATION_NO,P.CLIENT_CODE,P.VEHICLE_NO,P.NAME, P.APPLICATION_STATUS, P.NIC_NO "+
						" FROM "+
						" (SELECT ROWNUM NO,Q.FINANCE_NO,Q.APPLICATION_NO,Q.CLIENT_CODE,Q.VEHICLE_NO,Q.NAME, Q.APPLICATION_STATUS, Q.NIC_NO "+
						" FROM "+
						" ( "+
						
						" SELECT NVL(A.FINANCE_NO,'-') FINANCE_NO,A.APPLICATION_NO,A.CLIENT_CODE,NVL(B.REG_NO,'-') VEHICLE_NO, "+
						" C.FULL_NAME AS NAME, A.APPLICATION_STATUS APPLICATION_STATUS, C.NIC_NO NIC_NO "+
						" FROM   "+m_schema_name+".AF_CO_PRO_APPLICATION_DETAILS A, "+
						" "+m_schema_name+".AF_CO_PRO_APP_INVOICE_DETAILS B, "+
						" "+m_schema_name+".AF_CO_MAS_CLIENT C  "+
						" WHERE A.CLIENT_CODE = C.CLIENT_CODE "+
						" AND A.APPLICATION_NO=B.APPLICATION_NO(+) "+
						//" AND B.ACTIVE_STATUS = 'Y' "+ 
						//" ORDER BY A.FINANCE_NO DESC // commented by udara 24-12-2018
						
						
						// commented by udara 24-12-2018
						" UNION "+
						
						 " SELECT '-' FINANCE_NO,'-' APPLICATION_NO,C.CLIENT_CODE,'-' VEHICLE_NO, "+ 
                         " C.FULL_NAME AS NAME, '-' APPLICATION_STATUS, C.NIC_NO NIC_NO  "+
                         " FROM   "+m_schema_name+".AF_CO_MAS_CLIENT C  "+ 
							
						// added by udara 24-12-2018
						
						
						" ) Q"+
						" WHERE    (UPPER(Q.FINANCE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%') "+
						" OR 		UPPER(Q.APPLICATION_NO) LIKE UPPER('"+vector.elementAt(0)+"%') "+
						" OR 		UPPER(Q.CLIENT_CODE) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
						" OR 		UPPER(Q.NIC_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
						" OR 		UPPER(Q.VEHICLE_NO) LIKE UPPER('%"+vector.elementAt(0)+"%')  "+
						" OR 		UPPER(Q.NAME) LIKE UPPER('%"+vector.elementAt(0)+"%') ) "+
						" ORDER BY Q.FINANCE_NO DESC   ) P "+
						" WHERE P.NO>= "+ s1+" AND P.NO<= "+s2+" ";
		
		
		
		/* ROD ROD Document End */
		
	
		
		
	
		

		
		

		


		
		
		
		
		
		
		
		Ret_Object = s;
		return Ret_Object;
	}
	
	public Object Ret_Object;
	public String ret_str;
	LAKDL_AF_CO_conn_methods m_sn_methods;
	String m_schema_name;
	

	

	
	/* ROD */
	public String m_help_TXT_ROD_document_sql;
	public String m_help_TXT_ROD_document_sql_Header;
	
	/* ROD END*/
	
	
	
	
}
