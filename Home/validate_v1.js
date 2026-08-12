var servlet_client_url = "https://dev-lakdl.sasianet.com";
var html_client_url    = "https://dev-lakdl.sasianet.com/";
var client_t3_port     = "/myserver/servlet";
var client_name        = "LAKDL_";
var header_name        = "Asset System";


	function checklength(inputStr,size,message)
	{
			var m_length=0;
			var m_difference=0;	
		  var m_fulllength="";
			chk_stat=isEmptymessage(inputStr,message);
			//if (!chk_stat && message!="No") {
			if (!chk_stat) 
		{
					m_length=inputStr.value.length;
					if (m_length > size)
				{
							alert(message+" should not be greater than "+ size.toString()+ " characters");
							inputStr.focus();
					}
		  }			
		return true
		}
	////
	function format_number(object1,size) 
	{
			 var comsize=20;
			 m_format_1=object1.value;
		 	 if (m_format_1 == null || m_format_1 == "") 
		{
				  alert("Please enter a number");
					object1.focus();				
					return false;
			 }
				m_dot_count=0;
				var chk_brk=false;
				var m_integer="";
				var m_decimal="";
				var m_format="";
				m_minus=false;
				for (var i = 0; i < m_format_1.length; i++) 
		{
					var oneChar = m_format_1.charAt(i)
					if (oneChar==null || oneChar == "" || oneChar == " ") 
					{
						 var k=1;
					}
				  else 
					{	
						 m_format=m_format+oneChar; 		
					}
				}
				for (var i = 0; i < m_format.length; i++) 
		{
				var oneChar = m_format.charAt(i)
				if (oneChar== ".") 
					{
				m_dot_count=m_dot_count+1;
				}
	  		if (oneChar== "-") 
					{
				m_minus=true;
				}
				if (isNaN(oneChar) && oneChar != "," && oneChar != "." && oneChar != "-") 
					{
				alert("You have typed an incorrect charactor as a number");
				chk_brk=true;
				i=m_format.length;
				break;			
				}
				if (!isNaN(oneChar) && m_dot_count==0 ) 
					{
				m_integer=m_integer+oneChar;
				}
				if (m_dot_count>0) 
					{
				m_decimal=m_decimal+oneChar;
				}
				}
				if (chk_brk) 
		{
						object1.value="";
		 				object1.focus();
					 return false;
				}
				if (m_dot_count>1) 
		{
				alert("You have typed more than one decimal separator");
				object1.focus();
				return false;
				}
				m_format=m_integer; 
	
				m_length=m_integer.length;
			 	if (m_length > size)
		{
				alert("The length of the number cannot be more than "+size.toString());
				object1.focus();
				return false;
				}
				var m_formatted="";
				var m_new_str="";
				m_end=m_format.length;
				m_stat_pos=m_end-4;
				var m_last_pos=m_end;
				while (m_stat_pos>=0)	
		{
				  m_chk_str=m_format.substr(m_stat_pos,1);
				  if (m_chk_str!=null) 
					{
				 	 m_add_str=","+m_format.substr(m_stat_pos+1,3);
				 	 m_new_str=m_add_str+m_new_str;
				  }
				  m_last_pos=m_stat_pos;
				  m_stat_pos=m_stat_pos-3;
				}
				
				if (m_decimal == "") 
		{
				 	 m_decimal=".00";
				}
				else if (m_decimal == ".") 
		{
				 	 m_decimal=".00";
				}
				else if (m_decimal == ".0") 
		{
				 	 m_decimal=".00";
				}
				else if (m_decimal == ". 0") 
		{
				 	 m_decimal=".00";
				}
				
				m_new_str=m_format.substr(0,m_last_pos+1)+m_new_str+m_decimal;
	      if (m_minus) 
		{
				 m_new_str="-"+m_new_str;
	      }
	
				var m_left_str="";
				rem_len=16-m_new_str.length;
				while (rem_len>0) 
		{
				  m_left_str=m_left_str+"	";
					rem_len=rem_len-1;
				}
				//object1.value=m_left_str+m_new_str;
				object1.value=m_new_str;
				//return m_new_str;
				return true;
				
				}
	//Additional function
	function format_number2(object1,size) 
	{
			 var comsize=20;
			 m_format_1=object1.value;
		/* 	 if (m_format_1 == null || m_format_1 == "") {
				  alert("Please enter a number");
					object1.focus();				
					return false;
			 }*/
				m_dot_count=0;
				var chk_brk=false;
				var m_integer="";
				var m_decimal="";
				var m_format="";
				m_minus=false;
				for (var i = 0; i < m_format_1.length; i++) 
		{
					var oneChar = m_format_1.charAt(i)
					if (oneChar==null || oneChar == "" || oneChar == " ") 
					{
						 var k=1;
					}
				  else 
					{	
						 m_format=m_format+oneChar; 		
					}
				}
				for (var i = 0; i < m_format.length; i++) 
		{
				var oneChar = m_format.charAt(i)
				if (oneChar== ".") 
					{
				m_dot_count=m_dot_count+1;
				}
	  		if (oneChar== "-") 
					{
				m_minus=true;
				}
				if (isNaN(oneChar) && oneChar != "," && oneChar != "." && oneChar != "-") 
					{
				alert("You have typed an incorrect charactor as a number");
				chk_brk=true;
				i=m_format.length;
				break;			
				}
				if (!isNaN(oneChar) && m_dot_count==0 ) 
					{
				m_integer=m_integer+oneChar;
				}
				if (m_dot_count>0) 
					{
				m_decimal=m_decimal+oneChar;
				}
				}
				if (chk_brk) 
		{
						object1.value="";
		 				object1.focus();
					 return false;
				}
				if (m_dot_count>1) 
		{
				alert("You have typed more than one decimal separator");
				object1.focus();
				return false;
				}
				m_format=m_integer; 
	
				m_length=m_integer.length;
			 	if (m_length > size)
		{
				alert("The length of the number cannot be more than "+size.toString());
				object1.focus();
				return false;
				}
				var m_formatted="";
				var m_new_str="";
				m_end=m_format.length;
				m_stat_pos=m_end-4;
				var m_last_pos=m_end;
				while (m_stat_pos>=0)	
		{
				  m_chk_str=m_format.substr(m_stat_pos,1);
				  if (m_chk_str!=null) 
					{
				 	 m_add_str=","+m_format.substr(m_stat_pos+1,3);
				 	 m_new_str=m_add_str+m_new_str;
				  }
				  m_last_pos=m_stat_pos;
				  m_stat_pos=m_stat_pos-3;
				}
				
				if (m_decimal == "") 
		{
				 	 m_decimal=".0000";
				}
				else if (m_decimal == ".") 
		{
				 	 m_decimal=".0000";
				}
				else if (m_decimal == ".0") 
		{
				 	 m_decimal=".0000";
				}
				else if (m_decimal == ". 0") 
		{
				 	 m_decimal=".0000";
				}
				
				m_new_str=m_format.substr(0,m_last_pos+1)+m_new_str+m_decimal;
	      if (m_minus) 
		{
				 m_new_str="-"+m_new_str;
	      }
	
				var m_left_str="";
				rem_len=16-m_new_str.length;
				while (rem_len>0) 
		{
				  m_left_str=m_left_str+"	";
					rem_len=rem_len-1;
				}
				//object1.value=m_left_str+m_new_str;
				object1.value=m_new_str;
				//return m_new_str;
				return true;
				
				}
	
	
	//To check the number but not to display message
	function isnumberok(object1,size) 
	{
			 m_format_1=object1.value;
		 	 if (m_format_1 == null || m_format_1 == "") 
		{
					return false;
			 }
				m_dot_count=0;
				var m_integer="";
				var m_decimal="";
				var m_format="";
				m_minus=false;
				for (var i = 0; i < m_format_1.length; i++) 
		{
					var oneChar = m_format_1.charAt(i)
					if (oneChar==null || oneChar == "" || oneChar == " ") 
					{
						 var k=1;
					}
				  else 
					{	
						 m_format=m_format+oneChar; 		
					}
				}
				for (var i = 0; i < m_format.length; i++) 
		{
				var oneChar = m_format.charAt(i)
				if (oneChar== ".") 
					{
				m_dot_count=m_dot_count+1;
				}
				if (oneChar== "-") 
					{
				m_minus=true;
				}
	
				if (isNaN(oneChar) && oneChar != "," && oneChar != "." && oneChar != "-") 
					{
				return false;
				}
				if (!isNaN(oneChar) && m_dot_count==0 ) 
					{
				m_integer=m_integer+oneChar;
				}
				if (m_dot_count>0) 
					{
				m_decimal=m_decimal+oneChar;
				}
				}
				if (m_dot_count>1) 
		{
				return false;
				}
				m_format=m_integer; 
	
				m_length=m_integer.length;
			 	if (m_length > size)
		{
				return false;
				}
				return true;
				
				}
	
	
	//Function to validate NIC  commented on 2016-10-06
/*	   function val_nic(object)
	{
	   m_objval = object.value;
		 m_length = m_objval.toString().length;
		
		 if(m_length<10)
			{
			  alert(" NIC length should be 10");
				object.focus();
				return false;
				
			}
		 else
			{
			   for(var i = 0; i<m_length; i++)
					{
					  m_char = m_objval.charAt(i);
						 if(i==9)
							{
							   if(m_char != 'v' && m_char != 'V' && m_char != 'x' && m_char != 'X')
									{
									   alert("NIC Final Character should be X or V ");
										 object.focus();
										 return false;
									}
							}
							else if(i>=0 && i<=8)
							{
							   if(isNaN(m_char))
									{
									  i++;
									  alert("NIC contains an Invalid Character at position "+i+" ?");
										object.focus();
										return false;
										//break;
									}
							}
						
					}
			}
		return true;
			
	}
	*/
	
			  //------------------------------------------------------------- 
        //------------------------------------------------------------- 
        //------------------------------------------------------------- 
				function format_noobject_nodecimal(value){//$$##SC
				m_format=value.toString();
				m_dot_count=0;
				var m_integer="";
				var m_decimal="";
				m_minus=false;
				for (var i = 0; i < m_format.length; i++)	{
				  var oneChar = m_format.charAt(i)
				   if (oneChar== ".") 	{
				        m_dot_count=m_dot_count+1;
				   }
				   if (oneChar== "-"){
				     m_minus=true;
				   }
   				 if (isNaN(oneChar) && oneChar != "," && oneChar != "." && oneChar != "-") {
				     alert("You have typed an incorrect charactor as a number");
				     return oneChar
				   }
				   if (!isNaN(oneChar) && m_dot_count==0 ){
				    m_integer=m_integer+oneChar;
				   }
				   if (m_dot_count>0) {
				     m_decimal=m_decimal+oneChar;
				   }
				}//for
				
				if (m_dot_count>1) 	{
				  alert("You have typed more than one decimal separator");
				  return false
				}
				m_format=m_integer; 
	
				var m_formatted="";
				var m_new_str="";
				m_end=m_format.length;
				m_stat_pos=m_end-4;
				var m_last_pos=m_end;
				while (m_stat_pos>=0)	{
				  m_chk_str=m_format.substr(m_stat_pos,1);
				   if  (m_chk_str!=null)	{
				   m_add_str=","+m_format.substr(m_stat_pos+1,3);
				   m_new_str=m_add_str+m_new_str;
				   }
				  m_last_pos=m_stat_pos;
				  m_stat_pos=m_stat_pos-3;
				}
				if (m_decimal == "") {
						m_decimal="";
				}
				else if (m_decimal == ".") 	{
				 	 m_decimal="";
				}
				else if (m_decimal == ".0") {
				 	 m_decimal="";
				}
				else if (m_decimal == ". 0") {
				 	 m_decimal="";
				}
				else {
					m_decimal="";//m_decimal.substr(0,3);
				}
	   //alert('m_decimal='+m_decimal);
			  /*if (m_decimal.length<3) {
				   m_decimal=m_decimal+"0";
	      }	*/		
	      m_new_str=m_format.substr(0,m_last_pos+1)+m_new_str+m_decimal;
				if (m_minus) {
				 m_new_str="-"+m_new_str;
	      }
				value=m_new_str;
				return value;
				}
	      //------------------------------------------------------------------ 
        //------------------------------------------------------------------
        //------------------------------------------------------------------

				
				//Additional funciton
				//------------------------------------------------------------- 
        //------------------------------------------------------------- 
        //------------------------------------------------------------- 
				function format_noobject_nodecimal1(object){//$$##SC
				value=object.value;
				m_format=value.toString();
				m_dot_count=0;
				var m_integer="";
				var m_decimal="";
				m_minus=false;
				for (var i = 0; i < m_format.length; i++)	{
				  var oneChar = m_format.charAt(i)
				   if (oneChar== ".") 	{
				        m_dot_count=m_dot_count+1;
				   }
				   if (oneChar== "-"){
				     m_minus=true;
				   }
   				 if (isNaN(oneChar) && oneChar != "," && oneChar != "." && oneChar != "-") {
				     alert("You have typed an incorrect charactor as a number");
						 object.value="";	
						 object.focus();	
				     return oneChar
						 	
				   }
				   if (!isNaN(oneChar) && m_dot_count==0 ){
				    m_integer=m_integer+oneChar;
				   }
				   if (m_dot_count>0) {
				     m_decimal=m_decimal+oneChar;
				   }
				}//for
				
				if (m_dot_count>1) 	{
				  alert("You have typed more than one decimal separator");
					object.focus();	
				  return false
					
				}
				m_format=m_integer; 
	
				var m_formatted="";
				var m_new_str="";
				m_end=m_format.length;
				m_stat_pos=m_end-4;
				var m_last_pos=m_end;
				while (m_stat_pos>=0)	{
				  m_chk_str=m_format.substr(m_stat_pos,1);
				   if  (m_chk_str!=null)	{
				   m_add_str=","+m_format.substr(m_stat_pos+1,3);
				   m_new_str=m_add_str+m_new_str;
				   }
				  m_last_pos=m_stat_pos;
				  m_stat_pos=m_stat_pos-3;
				}
				if (m_decimal == "") {
						m_decimal="";
				}
				else if (m_decimal == ".") 	{
				 	 m_decimal="";
				}
				else if (m_decimal == ".0") {
				 	 m_decimal="";
				}
				else if (m_decimal == ". 0") {
				 	 m_decimal="";
				}
				else {
					m_decimal=m_decimal.substr(0,3);
				}
	   
			  /*if (m_decimal.length<3) {
				   m_decimal=m_decimal+"0";
	      }	*/		
	      m_new_str=m_format.substr(0,m_last_pos+1)+m_new_str+m_decimal;
				if (m_minus) {
				 m_new_str="-"+m_new_str;
	      }
				value=m_new_str;
				object.value=value;
				}
	      //------------------------------------------------------------------ 
        //------------------------------------------------------------------
        //------------------------------------------------------------------


				
				
				
				
				
				
				
				
				
				
				
				
				function format_noobject(value) 
			{
				m_format=value.toString();
				m_dot_count=0;
				var m_integer="";
				var m_decimal="";
				m_minus=false;
				for (var i = 0; i < m_format.length; i++) 
					{
				var oneChar = m_format.charAt(i)
				if (oneChar== ".") 
					{
				m_dot_count=m_dot_count+1;
				}
				if (oneChar== "-") 
					{
				m_minus=true;
				}
	
				if (isNaN(oneChar) && oneChar != "," && oneChar != "." && oneChar != "-") 
					{
				alert("You have typed an incorrect charactor as a number");
				return oneChar
				}
				if (!isNaN(oneChar) && m_dot_count==0 ) 
					{
				m_integer=m_integer+oneChar;
				}
				if (m_dot_count>0) 
					{
				m_decimal=m_decimal+oneChar;
				}
				}
				if (m_dot_count>1) 
					{
				alert("You have typed more than one decimal separator");
				return false
				}
				m_format=m_integer; 
	
				var m_formatted="";
				var m_new_str="";
				m_end=m_format.length;
				m_stat_pos=m_end-4;
				var m_last_pos=m_end;
				while (m_stat_pos>=0)	
					{
				m_chk_str=m_format.substr(m_stat_pos,1);
				if  (m_chk_str!=null)
					{
				m_add_str=","+m_format.substr(m_stat_pos+1,3);
				m_new_str=m_add_str+m_new_str;
				}
				m_last_pos=m_stat_pos;
				m_stat_pos=m_stat_pos-3;
				}
				if (m_decimal == "") 
					{
				 	 m_decimal=".00";
				}
				else if (m_decimal == ".") 
					{
				 	 m_decimal=".00";
				}
				else if (m_decimal == ".0") 
					{
				 	 m_decimal=".00";
				}
				else if (m_decimal == ". 0") 
					{
				 	 m_decimal=".00";
				}
				else 
					{
				   m_decimal=m_decimal.substr(0,3);
				}
	      if (m_decimal.length<3) 
					{
				   m_decimal=m_decimal+"0";
	      }			
	      
				m_new_str=m_format.substr(0,m_last_pos+1)+m_new_str+m_decimal;
	      if (m_minus) 
					{
				 m_new_str="-"+m_new_str;
	      }
				value=m_new_str;
				return value;
				}
				
				
				//with 4 0's
				function format_noobject0(value,passed_decimalsize) 	{
					var decimalsize=4;
				  if (!isNaN(passed_decimalsize)) {
				    decimalsize=passed_decimalsize;
          }
					m_format=value.toString();
					m_dot_count=0;
					var m_integer="";
					var m_decimal="";
					m_minus=false;
					for (var i = 0; i < m_format.length; i++) 
						{
				  		var oneChar = m_format.charAt(i)
				 			if (oneChar== ".") {
								 m_dot_count=m_dot_count+1;
							}
							if (oneChar== "-") {
					 			 m_minus=true;
							}
						  if (isNaN(oneChar) && oneChar != "," && oneChar != "." && oneChar != "-") {
									alert("You have typed an incorrect charactor as a number");
									return oneChar
							}
							if (!isNaN(oneChar) && m_dot_count==0 ) {
									m_integer=m_integer+oneChar;
							}
							if (m_dot_count>0 && oneChar!= ".") {
									m_decimal=m_decimal+oneChar;
							}
					}
					if (m_dot_count>1) {
							alert("You have typed more than one decimal separator");
							return false
					}
					m_format=m_integer; 
					var m_formatted="";
					var m_new_str="";
					m_end=m_format.length;
					m_stat_pos=m_end-4;
					var m_last_pos=m_end;
					while (m_stat_pos>=0)	{
								m_chk_str=m_format.substr(m_stat_pos,1);
								if  (m_chk_str!=null)	{
										m_add_str=","+m_format.substr(m_stat_pos+1,3);
										m_new_str=m_add_str+m_new_str;
								}
								m_last_pos=m_stat_pos;
								m_stat_pos=m_stat_pos-3;
					}
          
					if (m_decimal.length>decimalsize) {
				  		 m_decimal=m_decimal.substr(0,decimalsize);
          } else {					
		           i=1;
							 bal_length=decimalsize-m_decimal.length;
							 while (i<=bal_length) {
								 m_decimal=m_decimal+"0";
								 i=i+1;
							 }
					}
					if(decimalsize>0){
          m_decimal="."+m_decimal;
          }
					
					m_new_str=m_format.substr(0,m_last_pos+1)+m_new_str+m_decimal;
	      	
					if (m_minus) {
						 m_new_str="-"+m_new_str;
	  	    }
					value=m_new_str;
					return value;
				}
				
				function unformat_number(object) 
				{
				m_format=object.value;
				var m_number="";
				for (var i = 0; i < m_format.length; i++) 
					{
				var oneChar = m_format.charAt(i)
				if (oneChar != ",")
					{
				m_number=m_number+oneChar;
				}
				}
	 			return m_number;
		 	}
	
			 	function unformat_noobject(value) 
	{
		//alert(value);
				m_format=value;
			 var m_number="";
		//alert(m_format);
				for (var i = 0; i < m_format.length; i++) 
						{
				var oneChar = m_format.charAt(i)
				if (oneChar != ",")
					{
				m_number=m_number+oneChar;
				}
				}
	 			return m_number;
		 	}
				
	
			function elsefocus (object)
	{
		 	 object.focus();
		 	}
	
	
	// general purpose function to see if an input value has been entered at all
		function username(username)
	{
		  alert("sas");
			return true
		}
		
		function date(component)
		{
				 now= new Date();
				 component.value=now.getDate();
			return true
		}
	 
		function month(component)
		{
				 now= new Date();
				 component.value=now.getMonth() + 1;
			return true
		}
		function year(component)
		{
				 now= new Date();
				 component.value=1900 + now.getYear();
			return true
		}
	
	function isEmptymessage(inputStr,message) 
	{
		
		
		if (inputStr.value == null || inputStr.value == "") 
		{
			//if (message!="No") { 
			alert(message+" should be entered");
			inputStr.focus();
			//}
			return true;
		}
		return false
	}
	
	function islengthok(inputStr,size)
	{
			var m_length=0;
			var m_difference=0;	
		  var m_fulllength="";
			chk_stat=isEmpty(inputStr);
			if (!chk_stat) 
		{
					m_length=inputStr.value.length;
					if (m_length > size)
				{
						 return false
					}
		  }
			else 
		{
				 return false
			}
		return true
		}
	
	function isEmpty(inputStr) 
	{
		if (inputStr.value == null || inputStr.value == "") 
		{
			return true
		}
		return false
	}
	// general purpose function to see if a suspected numeric input is a positive integer
	function isPosInteger(inputVal) 
	{
		inputStr = inputVal.toString()
		for (var i = 0; i < inputStr.length; i++) 
		{
			var oneChar = inputStr.charAt(i)
			if (oneChar < "0" || oneChar > "9") 
			{
					return false
			}
		}
		return true
	}
	
	function isGreatZero(object) 
	{
		inputStr = unformat_number(object);
		if (inputStr<=0) 
		{
			 return false
		}
		return true
	}
	
	// general purpose function to see if a suspected numeric input is a positive or negative integer
	function isInteger(inputVal) 
	{
		inputStr = inputVal.toString()
		for (var i = 0; i < inputStr.length; i++) 
		{
			var oneChar = inputStr.charAt(i)
			if (i == 0 && oneChar == "-") 
			{
				continue
			}
			if (oneChar < "0" || oneChar > "9") 
			{
				return false
			}
		}
		return true
	}
	// general purpose function to see if a suspected numeric input is a positive or negative number
	function isNumber(inputVal) 
	{
		oneDecimal = false
		inputStr = inputVal.toString()
		for (var i = 0; i < inputStr.length; i++) 
		{
			var oneChar = inputStr.charAt(i)
			if (i == 0 && oneChar == "-") 
			{
				continue
			}
			if (oneChar == "." && !oneDecimal) 
			{
				oneDecimal = true
				continue
			}
			if (oneChar < "0" || oneChar > "9") 
			{
			alert('Please Enter a Number');
		 	return false
			}
		}
		return true
	}
	
	// check the entered month for too high a value
	function checkMonthLength(c_dd,c_mm,c_yyyy) {
		mm=c_mm.value;
		dd=c_dd.value;
	  yyyy=parseInt(c_yyyy.value);
	 	inputyearStr = c_yyyy.value.toString();
		if (inputyearStr.length != 4)
		{
			alert("Please enter year in four digit(YYYY) number format");
			c_yyyy.value="";	
			c_yyyy.focus();
			return false
	 		
		}
			
		if (isNaN(dd)) 
		{
			 alert("Day should be a number");
			 c_dd.focus();
			 c_dd.value="";	
			 return false
	  }
	  else if (dd<1 || dd>31) 
		{
		 	alert("Date must be between 1 and 31");
			 c_dd.focus();
			 c_dd.value="";	
	 		 return false
	
		}
		else if (isNaN(mm)) 
		{
		 alert("Month should be a number");
			 c_mm.focus();
			 c_mm.value="";	
	 		 return false
		
		}
		else if (mm<1 || mm>12) 
		{
		 	alert("Month must be between 1 and 12");
			 c_mm.focus();
			 c_mm.value="";	
	 		 return false
		
		}
		else if (isNaN(yyyy)) 
		{
		 alert("Year should be a number");
		 c_yyyy.value="";
		 c_yyyy.focus();
		 return false
	
		}
		else if (yyyy<1900 || yyyy>3000) 
		{
		 alert("Year should be between 1900 and 3000 ");
		 c_yyyy.value="";
		 c_yyyy.focus();
		 return false
	
		}
		
		
		var months = new
		Array("","January","February","March","April","May","June","July","August","September","October","November","December")
		if (mm==2) 
		{
		 //	checkLeapMonth(c_mm,c_dd,c_yyyy);
		//***************************************checking february*************************
		mm=(c_mm.value);//parseInt
		dd=(c_dd.value);//parseInt
		
		yyyy=parseInt(c_yyyy.value);
	 
						if (yyyy % 4 > 0 && dd > 28) 
						{
							 alert("February of " + yyyy + " has only 28 days.")
								c_dd.value="";
								c_dd.focus();
							return false
						} 
						else if (dd > 29) 
						{
								alert("February of " + yyyy + " has only 29 days.")
								c_dd.value="";		
      					c_dd.focus();
								return false
						}
						else if (dd < 1 || dd > 29) 
						{
						
					  alert("Date between 1 and 29.")
						c_dd.value="";		
						c_dd.focus();
								return false
						}
						else {
								return true
            }
 //************************************************************
		}
		else if ((mm == 4 || mm == 6 || mm == 9 || mm == 11) && dd > 30) 
		{
			if(mm=="04"){	
			 alert(months[04] + " has only 30 days.")
			}
			else if(mm=="06"){
						 alert(months[06] + " has only 30 days.")
			}
			else if(mm=="09"){
						 alert(months[09] + " has only 30 days.")
			}
			else if(mm=="11"){
						 alert(months[11] + " has only 30 days.")
			}
      else {
			   alert("selcted month has only 30 days");
			}		
			c_dd.focus();
			// c_dd.value="";
			 return false
		} 
		else if ( dd > 31) 
		{
				alert(months[mm] + " has only 31 days.")
				 c_dd.focus();
				 c_dd.value="";
				 return false
		}
		else if ( dd < 1) 
		{
				alert("Please check the day.")
				 c_dd.focus();
				 c_dd.value="";
				 return false
		}
	
		return true
	}

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	// check the entered February date for too high a value
/*	function checkLeapMonth(c_mm,c_dd,c_yyyy) 
	{
		mm=parseInt(c_mm.value);
		dd=parseInt(c_dd.value);
		yyyy=parseInt(c_yyyy.value);
	 
		if (yyyy % 4 > 0 && dd > 28) 
		{
			alert("February of " + yyyy + " has only 28 days.")
			 c_dd.focus();
				c_dd.value="";
			return false
		} 
		else if (dd > 29) 
		{
				alert("February of " + yyyy + " has only 29 days.")
				 c_dd.focus();
				 	c_dd.value="";		
				return false
		}
		else if (dd < 1 || dd > 29) 
		{
				alert("Date between 1 and 29.")
				 c_dd.focus();
				 	c_dd.value="";		
				return false
		}
		return true
	}*/
	
	// check the entered month but do not display message
	function isMonthok(c_dd,c_mm,c_yyyy) 
	{
		
		mm=parseInt(c_mm.value);
		dd=parseInt(c_dd.value);
		yyyy=parseInt(c_yyyy.value);
	 	inputyearStr = c_yyyy.value.toString();
		if (inputyearStr.length != 4)
		{
		   alert("Year should be between 1900 and 3000 ");
			 c_yyyy.value="";	
			 c_yyyy.focus();
			return false
	 		
		}
		if (isNaN(dd)) 
		{
			 c_dd.focus();
			 c_dd.value="";	
			 return false
		}
	  else if (dd<1 && dd>31) 
		{
		 	alert("Date must be between 1 and 31");
			 c_dd.focus();
			 c_dd.value="";	
	 		 return false
	  }
	  else if (isNaN(mm)) 
		{
			 c_mm.focus();
			 c_mm.value="";	
	 		 return false
		}
		else if (isNaN(yyyy)) 
		{
		 c_yyyy.value="";
		 c_yyyy.focus();
		 return false
		}
		var months = new
		Array("","January","February","March","April","May","June","July","August","September","October","November","December")
		if (mm==2) 
		{
		 	isLeapMonth(c_mm,c_dd,c_yyyy);
		}
		else if ((mm == 4 || mm == 6 || mm == 9 || mm == 11) && dd > 30) 
		{
			 c_dd.focus();
			 c_dd.value="";
			 return false
		} 
		else if (dd > 31) 
		{
				 c_dd.focus();
				 c_dd.value="";
				 return false
		}
		else if (dd < 1) 
		{
				 c_dd.focus();
				 c_dd.value="";
				 return false
		}
		return true
	}
	// check the entered February date for too high a value but do not display message
	function isLeapMonth(c_mm,c_dd,c_yyyy) 
	{
		mm=parseInt(c_mm.value);
		dd=parseInt(c_dd.value);
		yyyy=parseInt(c_yyyy.value);
	 
		if (yyyy % 4 > 0 && dd > 28) 
		{
			 c_dd.focus();
			c_dd.value="";
			return false
		} 
		else if (dd > 29) 
		{
				c_dd.focus();
				c_dd.value="";		
				return false
		}
		else if (dd < 1) 
		{
				c_dd.focus();
				c_dd.value="";		
				return false
		}
		return true
	}
	
	function numbersToChar(obj) {
	
		var ar_ones=new Array("","one","two","three","four","five","six","seven","eight","nine");
		var ar_tens=new Array("","ten","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety");
		var ar_teens=new Array("","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen");
		var m_value;
		var m_cents="";
	
		var m_full_str="";
		var m_digit=0;
		var m_length=obj.value.length;
		var m_value=obj.value;

		var m_dot=obj.value.indexOf(".");
		
		if (m_dot!= -1 ) {
			m_cents=obj.value.substring(m_dot+1,m_length);
			if (m_cents.length==1) {
				m_cents=m_cents+"0";
			}
			else if (m_cents.length==2) {
				m_cents=m_cents+"00";
			}
			m_length=m_value.substring(0,m_dot).length;
			m_value=obj.value.substring(0,m_dot);
		}
		
				
		for (j=1 ; j<= 15-m_length; j++) {
			m_value="0"+m_value;
		}
		var m_cnt=15-m_length;
		for (i=m_cnt; i<=14; i++) {
			m_digit = m_value.substring(m_cnt,m_cnt+1);

			
			if (parseInt(m_cnt)==3) {  // 100,000,000,000
				if (m_digit!=0) {
					if ((parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) && (parseInt(m_value.substring(m_cnt+2,m_cnt+3))==0)) { 
						m_full_str=m_full_str+ar_ones[m_digit]+" hundred billion ";
						m_cnt+=2;
					}
					else {
						m_full_str=m_full_str+ar_ones[m_digit]+" hundred ";
					}
				}
			}
			
			else if (parseInt(m_cnt)==4) {  // 10,000,000,000
				if (m_digit!=0) {
					if ((parseInt(m_value.substring(m_cnt,m_cnt+1))==1) && (parseInt(m_value.substring(m_cnt+1,m_cnt+2))!=0)) { 
						m_full_str=m_full_str+ar_teens[parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" billion ";
						m_cnt+=1;
					}
					else if ( parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[m_digit]+" billion ";
					}
					
					else {
						m_full_str=m_full_str+ar_tens[m_digit]+" ";
					}
				}
			}
			
			else if (parseInt(m_cnt)==5) {  // 1,000,000,000
				if (m_digit!=0) {
					m_full_str=m_full_str+ar_ones[m_digit]+" billion ";
				}
			}
			
			
			else if (parseInt(m_cnt)==6) {  // 100,000,000
				if (m_digit!=0) {
					if ((parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) && (parseInt(m_value.substring(m_cnt+2,m_cnt+3))==0)) { 
						m_full_str=m_full_str+ar_ones[m_digit]+" hundred million ";
						m_cnt+=2;
					}
					else {
						m_full_str=m_full_str+ar_ones[m_digit]+" hundred ";
					}
				}
			}

			else if (parseInt(m_cnt)==7) {  // 10,000,000
				if (m_digit!=0) {
					if ((parseInt(m_value.substring(m_cnt,m_cnt+1))==1) && (parseInt(m_value.substring(m_cnt+1,m_cnt+2))!=0)) { 
						m_full_str=m_full_str+ar_teens[parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" million ";
						m_cnt+=1;
					}
					else if ( parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[m_digit]+" million ";
						m_cnt+=1;
					}
					else {
						m_full_str=m_full_str+ar_tens[m_digit]+" ";
					}
				}
			}
			
			else if (parseInt(m_cnt)==8) {  // 1,000,000
				if (m_digit!=0) {
					m_full_str=m_full_str+ar_ones[m_digit]+" million ";
					/*if ( parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0 && parseInt(m_value.substring(m_cnt+2,m_cnt+3))==0 &&  parseInt(m_value.substring(m_cnt+3,m_cnt+4))==0 ) { 
						m_cnt+=3;
					}*/
				}
			}
			
			else if (parseInt(m_cnt)==9) {  // 100,000
				if (m_digit!=0) {
					/*if ((parseInt(m_value.substring(m_cnt+1,m_cnt+2))==1) && (parseInt(m_value.substring(m_cnt+2,m_cnt+3))!=0)) { 
						m_full_str=m_full_str+ar_ones[m_digit]+" hundred "+ar_teens[parseInt(m_value.substring(m_cnt+2,m_cnt+3))]+" thousand ";
						m_cnt+=2;
					}*/
					if ((parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) && (parseInt(m_value.substring(m_cnt+2,m_cnt+3))==0)) {
						m_full_str=m_full_str+ar_ones[m_digit]+" hundred thousand ";
						m_cnt+=2;
					}
					else {						
						m_full_str=m_full_str+ar_ones[m_digit]+" hundred ";
					}
				}
			}

			else if (parseInt(m_cnt)==10) {  // 10,000
				if (m_digit!=0) {
					if (parseInt(m_value.substring(m_cnt+1,m_cnt+2))==0) { 
						m_full_str=m_full_str+ar_tens[m_digit]+" thousand ";
						m_cnt+=1;
					}
					else if ((parseInt(m_value.substring(m_cnt,m_cnt+1))==1) && (parseInt(m_value.substring(m_cnt+1,m_cnt+2))!=0)) { 
						m_full_str=m_full_str+ar_teens[parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" thousand ";
						m_cnt+=1;
					}
					else {						
						m_full_str=m_full_str+ar_tens[m_digit]+" ";
					}
				}
			}
			
			else if (parseInt(m_cnt)==11) {  // 1,000
				if (m_digit!=0) {
					m_full_str=m_full_str+ar_ones[m_digit]+" thousand ";
				}
			}
			
			else if (parseInt(m_cnt)==12) {  // 100
				if (m_digit!=0) {
					m_full_str=m_full_str+ar_ones[m_digit]+" hundred ";
				}
			}
			
			else if (parseInt(m_cnt)==13) {  // 10
				if (m_digit!=0) {
					if ((parseInt(m_digit)==1) && parseInt(m_value.substring(m_cnt+1,m_cnt+2))!=0) {
						m_full_str=m_full_str+ar_teens[parseInt(m_value.substring(m_cnt+1,m_cnt+2))]+" ";
						m_cnt+=1;
					}
					else {
						m_full_str=m_full_str+ar_tens[m_digit]+" ";
					}
				}
			}
			
			else if (parseInt(m_cnt)==14) {  // 1
				if (m_digit!=0) {
					m_full_str=m_full_str+ar_ones[m_digit]+" ";
				}
			}
			
			m_cnt++;
		}
		
			
		m_cnt=0;
		for (i=m_cnt; i<=1; i++) {
			m_digit = m_cents.substring(m_cnt,m_cnt+1);
		
			if (parseInt(m_cnt)==0) {  // 10
				if (m_digit!=0) {
					if ((parseInt(m_digit)==1) && parseInt(m_cents.substring(m_cnt+1,m_cnt+2))!=0) {
						m_full_str=m_full_str+"and cents "+ar_teens[parseInt(m_cents.substring(m_cnt+1,m_cnt+2))]+" ";
						m_cnt+=1;
					}
					else {
						m_full_str=m_full_str+"and cents "+ar_tens[m_digit]+" ";
					}
				}
			}
			
			else if (parseInt(m_cnt)==1) {  // 1
				if (m_digit!=0) {
					if (parseInt(m_cents.substring(m_cnt-1,m_cnt))==0) {
						m_full_str=m_full_str+"and cents "+ar_ones[m_digit];						
					}
					else {
						m_full_str=m_full_str+ar_ones[m_digit];	
					}
				}
			}
			
			m_cnt++;
		}
		
		//alert(m_full_str.toUpperCase());
		//m_full_str=changeCase(m_full_str);
		//alert(changeCase(m_full_str));
		//document.Form1.txt_amt_word.focus();		
		//alert(m_full_str.toUpperCase());
		//m_full_str=changeCase(m_full_str);
		m_full_str = changeCase(m_full_str);
		//document.Form1.txt_amt_word.focus();
		return m_full_str
	}
//========================ChkDateValidation()--Siva=====================//

	function ChkDateValidation(datecomponent,monthcomponent,yearcomponent) {

	if (checkMonthLength(datecomponent,monthcomponent,yearcomponent)==true) {

		//		currdate=datecomponent.value;
		//		currmonth=monthcomponent.value;
		//		curryear=yearcomponent.value;

		//		inputyearStr = yearcomponent.value.toString();
		//		if (inputyearStr.length != 4) {
		//			yyyy=curryear;
		//		}
		//		else {
		//			yyyy=yearcomponent.value.toString();
		//		}
		//		inputmonthStr = monthcomponent.value.toString();
		//		if (inputmonthStr.length != 2) {
		//			mm="0"+monthcomponent.value.toString();
		//		}
		//		else {
		//			mm=monthcomponent.value.toString();
		//		}
		//		inputdateStr = datecomponent.value.toString();
		//		if (inputdateStr.length != 2) {
		//			dd="0"+datecomponent.value.toString();
		//		}
		//		else {
		//			dd=datecomponent.value.toString();
		//		}

		//		ToDay= new Date();
		//		ChkDate=new Date(yyyy,(mm-1),dd); 
		//		
		//		if (ChkDate >= ToDay) {
		//			alert("The Date You have Entered Cannot be Greater than Today!");
		//			datecomponent.value="";
		//			monthcomponent.value="";
		//			yearcomponent.value="";
		//			datecomponent.focus()
		//		}
			}
	}
	//==============End of ChkDateValidation()================================//
	
	//=======================ChkValidity() - Siva=============================//
	
	function ChkValidity(ToDateComp,ToMonthComp,ToYearComp,FromDateComp,FromMonthComp,FromYearComp){
			ToDD=ToDateComp.value;			
			ToMM=ToMonthComp.value;
			ToYYYY=ToYearComp.value;
			FromDD=FromDateComp.value;
			FromMM=FromMonthComp.value;
			FromYYYY=FromYearComp.value;
			ToDate=new Date(ToYYYY,ToMM,ToDD);
			FromDate=new Date(FromYYYY,FromMM,FromDD);

			if (ToDate < FromDate)   {
		 			alert("To Date should be greater than From Date");
			}
	}
	
	
	
	
	//====================End of ChkValidity() - Siva=============================//
	
	//=======================Rem
	
	//--------remove leading spaces---------//
	function rem_lead_spaces(mobject) {
		m_objval=mobject.value;
		m_length = m_objval.length;
		cnt = 0;
		m_char =  m_objval.charAt(cnt);
		while (m_char==" " && cnt <= m_length) {
			cnt++;
			m_char =  m_objval.charAt(cnt);
		}
		m_objval=m_objval.substring(cnt,m_length);
		//mobject.value = m_objval;
		return m_objval;
	}
		
	//--------remove trailing spaces---------//
	function rem_trail_spaces(mobject) {
		m_objval=mobject.value;
		m_length = m_objval.length;
		cnt = m_length-1;
		m_char =  m_objval.charAt(cnt);
		while (m_char==" " && cnt >= 0) {
			cnt--;
			m_char =  m_objval.charAt(cnt);
		}
		m_objval=m_objval.substring(0,cnt+1);
		return m_objval;
	}

  function close_window(){	
	  if(confirm("Are you sure you want to close the screen?")){ 
		  window.close();
			//parent.frames[0].close_window();
			window.location.href=""+servlet_client_url+":"+client_t3_port+"/"+client_name+"AF_CO_FollowupAlert?chksql=main_page"; 
 	  }
	}
	//============================= End 
	
		
	
