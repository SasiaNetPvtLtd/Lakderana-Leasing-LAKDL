		
			function view_report(sub_model,model_code){	 //To view report
			
					m_sql = "m_view_sql"; 
					
				 //modifed 2007-01-25--------------------------------
					m_criteria = sub_model+"@"+model_code+"@"+"Y@"; 
					
			   // m_criteria = modle_code+"@"+"Y@"; 
				 //--------------------------------------------------		   
					
					view_report1('1','10','0'); 
			} 

		
			function view_report1(Start,End,Hid_No,Max) { 
			vend=parseInt(End)+10
			vend1=parseInt(End)+1
			
			//alert('A'+vend1+'B'+vend)
			vpre=parseInt(Start)-10
			vpre1=parseInt(Start)-1
			//alert('vpre1'+vpre1+'vpre'+vpre)
			    oBj = new MyDialog(); 
			    oBj.valout[1]  = " "; 
			    oBj.valout[2]  = " "; 
			    oBj.valout[3]  = " "; 
				 
				 //modifed 2007-01-25------------------------------------------------------------------------------------------------------------------------------------------
					 popupwin = window.showModalDialog(servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_PRO_Report_Servlet?class_in="+client_name+"AF_MK_Report_select"+ 
				 //popupwin = window.showModalDialog(servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_PRO_Report_Servlet?class_in="+client_name+"AF_MAS_help_select"+ 
			   //------------------------------------------------------------------------------------------------------------------------------------------------------------
					
					"&Sql_in="+m_sql+"&Start_in="+Start+ 
			    "&End_in="+End+"&Crit_In="+m_criteria+ 
			    "&Hid_No="+Hid_No,oBj,"dialogWidth:60em; dialogHeight:20em; bottom:yes; status:no; right:yes;"); 
				 

				if(oBj.valout[1] !=" "){ 
				if(oBj.valout[1] !="Close"){ 
				if(oBj.valout[1]!="Prev"){ 
				if(oBj.valout[1]!="Next"){ 

				} 
				else{ 
					Next1(oBj.valout[2],oBj.valout[3],Hid_No); 
			
			//		Next1(vend1,vend,Hid_No); 
					return false; 
				}  
				} 
				else{	
					Prev1(oBj.valout[2],oBj.valout[3],Hid_No); 

			//	Prev1(vpre,vpre1,Hid_No); 
				}	 
				}		 
				}	 
			} 
			 
	
			function Prev1(Start,End,Hid_No){ 
			    view_report1(Start,End,Hid_No); 
			} 
			 
			function Next1(Start,End,Hid_No){ 
			    view_report1(Start,End,Hid_No); 
			} 
			 


