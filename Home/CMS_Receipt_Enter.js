
function onLoad_Functions(){
	check_box_enable('','NO');
	show_settlement_mode_details();
	
}

function show_settlement_mode_details(){
	
	element=document.getElementById('SETT_MODE');	
	
	div_settlement_details.innerHTML="";
	
	if (element.value=="CASH") {
		
		//div_settlement_details.innerHTML=
		document.getElementById('div_settlement_details').innerHTML=
		
			
			'<table align="center" border="0"  width="100%" class=table>'+
			'<tr  class="tr_input">'+
			'<td  width="9%" align="left" >Value Date *</td>'+
			'<td  width="21%" align="left" ><input name="VAL_DAY"   type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_MONTH"  type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_YEAR"   type=text maxlength="4"  style="width:45px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)"><a href style="{cursor:hand; }" onclick=load_calendar("2")>Calendar</a> '+
			'</td >'+
			'<td  style="visibility:hidden;" width="9%" align="left" >Tendered Amount</td>'+
			'<TD  style="visibility:hidden;" width="31%" align="left" ><input class="txt_input_number" type="text" name=TEN_AMOUNT maxlength="23" size=25 onchange =calculate_balance(this) onblur=get_returned_value(this,25) disabled></td>'+
			'<td  style="visibility:hidden;" width="9%" align="left" >Returned Amount</td>'+
			'<TD  style="visibility:hidden;" width="21%" align="left"><input class="txt_input_number" type="text" name=RET_AMOUNT maxlength="23" size=25 onchange =calculate_balance(this) onblur=get_returned_value(this,25) disabled></td>'+
			'</tr >'+
			'</table >';
			
		
		
	}
	else if (element.value=="CHEQUE") {
		
		
		document.getElementById('div_settlement_details').innerHTML=
		
		
			'<table align="center" border="0"  width="100%" class="table">'+
			'<tr  class="tr_input">'+
			'<td  width="9%" align="left" >Value Date *</td>'+
			'<td  width="21%" align="left" ><input name="VAL_DAY"   type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_MONTH"  type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_YEAR"   type=text maxlength="4"  style="width:45px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)"><a href style="{cursor:hand; }" onclick=load_calendar("2")>Calendar</a> '+
			'</td >'+
			'<td  width="9%" align="left" id="CDATE" >Cheque Date *</td>'+
			'<td  width="31%" align="left" ><input name="CHEQUE_DATE_DD"   type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)">'+
			'<input name="CHEQUE_DATE_MM"  type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)">'+
			'<input name="CHEQUE_DATE_YY"   type=text maxlength="4"  style="width:45px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)"><a href style="{cursor:hand; }" onclick=load_calendar("3")>Calendar</a> '+
			'</td >'+
			'<td  width="9%" align="left" id="CRNO" >Cheque/Ref. No *</td>'+
			'<TD  width="21%" align="left"><input class="txt_input" type="text" name=CHEQUE_NO maxlength="8" size="22" onblur=validate_cheque_no() ></td>'+
			'</tr >'+
			
			'<tr class=tr_input>'+
			'<td width="9%" align="left" ID=PACC >Payer Account </td>'+
			'<td width="21%" align="left" ><input name=PAY_ACCOUNT   type=text maxlength=21 onblur=account_help()  class=txt_input  > '+
			'<input type=button name=accno_help value=... class=but_input onclick=account_help() ></td>'+
			'<td width="9%" align="left" ID=PBRANCH>Payer Branch *</td>'+
			'<td width="31%" align="left" > <input name="PAY_BRANCH"   type="text" maxlength="10"  onblur="branch_help()" class="txt_input" > '+ 
			'<input type="button" name="BUT_PAY_BRANCH" value="..." class="but_input" onclick="branch_help()" ></td>'+
			'<td width="9%" align="left" >Payer Branch Name</td>'+
			'<td width="21%" align="left" ><input name="PAY_BRANCH_NAME"   type="text" maxlength="100"  onblur=\"\" class=\"txt_input\" style=\"width: 200px\" disabled></td>'+
			'</tr>'+
			
			'</table >';
			
		
		
		
		
	}
	
	//[Commented by milinda for remove mandotory fields for direct deposite level]
	/*else if (element.value=="STD_ORD" || element.value=="DIR_DEP") {
		
		document.getElementById('div_settlement_details').innerHTML=
		
		
			'<table align="center" border="0"  width="100%" class=table>'+
			'<tr  class="tr_input">'+
			'<td  width="9%" align="left" >Value Date *</td>'+
			'<td  width="21%" align="left" ><input name="VAL_DAY"   type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_MONTH"  type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_YEAR"   type=text maxlength="4"  style="width:45px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)"><a href style="{cursor:hand;}" onclick=load_calendar("2")>Calendar</a> '+
			'</td >'+
			'<td  width="9%" align="left" id="CDATE" >Cheque Date *</td>'+
			'<td  width="31%" align="left" ><input name="CHEQUE_DATE_DD"   type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)">'+
			'<input name="CHEQUE_DATE_MM"  type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)">'+
			'<input name="CHEQUE_DATE_YY"   type=text maxlength="4"  style="width:45px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)"><a href style="{cursor:hand; }" onclick=load_calendar("3")>Calendar</a> '+
			'</td >'+
			'<td  width="9%" align="left" id="CRNO" >Cheque/Ref. No *</td>'+
			'<TD  width="21%" align="left"><input class="txt_input" type="text" name=CHEQUE_NO maxlength="8" size="22" onblur=validate_cheque_no() ></td>'+
			'</tr >'+
			
			'<tr class=tr_input>'+
			'<td width="9%" align="left" ID=PACC >Payer Account </td>'+
			'<td width="21%" align="left" ><input name=PAY_ACCOUNT   type=text maxlength=21 onblur=account_help()  class=txt_input  > '+
			'<input type=button name=accno_help value=... class=but_input onclick=account_help() ></td>'+
			'<td width="9%" align="left" ID=PBRANCH>Payer Branch *</td>'+
			'<td width="31%" align="left" > <input name="PAY_BRANCH"   type="text" maxlength="10"  onblur="branch_help()" class="txt_input" > '+ 
			'<input type="button" name="BUT_PAY_BRANCH" value="..." class="but_input" onclick="branch_help()" ></td>'+
			'<td width="9%" align="left" >Payer Branch Name</td>'+
			'<td width="21%"align="left"  ><input name="PAY_BRANCH_NAME"   type="text" maxlength="100"  onblur=\"\" class=\"txt_input\" style=\"width: 200px\" disabled></td>'+
			'</tr>'+
			
			'<tr class=tr_input>'+
			'<td width="9%" align="left" ID=ACC >Account Number *</td>'+
			'<td width="21%" align="left" ><input name=TXT_ACCOUNT_NO   type=text maxlength=21 onblur=\"account_number_help()\"  class=txt_input  > '+
			'<input type=button name=BUT_ACCOUNT_NO value=... class=but_input onclick=account_number_help() ></td>'+
			'<td width="9%" align="left" >Branch Name</td>'+
			'<td colspan="3" align="left" ><input name="BRANCH_NAME"   type="text" maxlength="200"  onblur=\"\" class=\"txt_input\" style=\"width: 250px\" disabled></td>'+
			'</tr>'+
			'</table >';
			
	}*/
	else if (element.value=="STD_ORD" ) {
		
		document.getElementById('div_settlement_details').innerHTML=
		
		
			'<table align="center" border="0"  width="100%" class=table>'+
			'<tr  class="tr_input">'+
			'<td  width="9%" align="left" >Value Date *</td>'+
			'<td  width="21%" align="left" ><input name="VAL_DAY"   type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_MONTH"  type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_YEAR"   type=text maxlength="4"  style="width:45px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)"><a href style="{cursor:hand;}" onclick=load_calendar("2")>Calendar</a> '+
			'</td >'+
			'<td  width="9%" align="left" id="CDATE" >Cheque Date *</td>'+
			'<td  width="31%" align="left" ><input name="CHEQUE_DATE_DD"   type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)">'+
			'<input name="CHEQUE_DATE_MM"  type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)">'+
			'<input name="CHEQUE_DATE_YY"   type=text maxlength="4"  style="width:45px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)"><a href style="{cursor:hand; }" onclick=load_calendar("3")>Calendar</a> '+
			'</td >'+
			'<td  width="9%" align="left" id="CRNO" >Cheque/Ref. No *</td>'+
			'<TD  width="21%" align="left"><input class="txt_input" type="text" name=CHEQUE_NO maxlength="8" size="22" onblur=validate_cheque_no() ></td>'+
			'</tr >'+
			
			'<tr class=tr_input>'+
			'<td width="9%" align="left" ID=PACC >Payer Account </td>'+
			'<td width="21%" align="left" ><input name=PAY_ACCOUNT   type=text maxlength=21 onblur=account_help()  class=txt_input  > '+
			'<input type=button name=accno_help value=... class=but_input onclick=account_help() ></td>'+
			'<td width="9%" align="left" ID=PBRANCH>Payer Branch *</td>'+
			'<td width="31%" align="left" > <input name="PAY_BRANCH"   type="text" maxlength="10"  onblur="branch_help()" class="txt_input" > '+ 
			'<input type="button" name="BUT_PAY_BRANCH" value="..." class="but_input" onclick="branch_help()" ></td>'+
			'<td width="9%" align="left" >Payer Branch Name</td>'+
			'<td width="21%"align="left"  ><input name="PAY_BRANCH_NAME"   type="text" maxlength="100"  onblur=\"\" class=\"txt_input\" style=\"width: 200px\" disabled></td>'+
			'</tr>'+
			
			'<tr class=tr_input>'+
			'<td width="9%" align="left" ID=ACC >Account Number *</td>'+
			'<td width="21%" align="left" ><input name=TXT_ACCOUNT_NO   type=text maxlength=21 onblur=\"account_number_help()\"  class=txt_input  > '+
			'<input type=button name=BUT_ACCOUNT_NO value=... class=but_input onclick=account_number_help() ></td>'+
			'<td width="9%" align="left" >Branch Name</td>'+
			'<td colspan="3" align="left" ><input name="BRANCH_NAME"   type="text" maxlength="200"  onblur=\"\" class=\"txt_input\" style=\"width: 250px\" disabled></td>'+
			'</tr>'+
			'</table >';
			
	}
	//[Added milinda for direcr deposit section]
	else if ( element.value=="DIR_DEP") {
		
		document.getElementById('div_settlement_details').innerHTML=
		
		
			'<table align="center" border="0"  width="100%" class=table>'+
			'<tr  class="tr_input">'+
			'<td  width="9%" align="left" >Value Date *</td>'+
			'<td  width="21%" align="left" ><input name="VAL_DAY"   type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_MONTH"  type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_YEAR"   type=text maxlength="4"  style="width:45px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)"><a href style="{cursor:hand;}" onclick=load_calendar("2")>Calendar</a> '+
			'</td >'+
			'<td  width="9%" align="left" id="CDATE" >Cheque Date </td>'+
			'<td  width="31%" align="left" ><input name="CHEQUE_DATE_DD"   type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)">'+
			'<input name="CHEQUE_DATE_MM"  type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)">'+
			'<input name="CHEQUE_DATE_YY"   type=text maxlength="4"  style="width:45px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)"><a href style="{cursor:hand; }" onclick=load_calendar("3")>Calendar</a> '+
			'</td >'+
			'<td  width="9%" align="left" id="CRNO" >Cheque/Ref. No </td>'+
			'<TD  width="21%" align="left"><input class="txt_input" type="text" name=CHEQUE_NO maxlength="8" size="22" onblur=validate_cheque_no() ></td>'+
			'</tr >'+
			
			'<tr class=tr_input>'+
			'<td width="9%" align="left" ID=PACC >Payer Account </td>'+
			'<td width="21%" align="left" ><input name=PAY_ACCOUNT   type=text maxlength=21 onblur=account_help()  class=txt_input  > '+
			'<input type=button name=accno_help value=... class=but_input onclick=account_help() ></td>'+
			'<td width="9%" align="left" ID=PBRANCH>Payer Branch </td>'+
			'<td width="31%" align="left" > <input name="PAY_BRANCH"   type="text" maxlength="10"  onblur="branch_help()" class="txt_input" > '+ 
			'<input type="button" name="BUT_PAY_BRANCH" value="..." class="but_input" onclick="branch_help()" ></td>'+
			'<td width="9%" align="left" >Payer Branch Name</td>'+
			'<td width="21%"align="left"  ><input name="PAY_BRANCH_NAME"   type="text" maxlength="100"  onblur=\"\" class=\"txt_input\" style=\"width: 200px\" disabled></td>'+
			'</tr>'+
			
			'<tr class=tr_input>'+
			'<td width="9%" align="left" ID=ACC >Account Number *</td>'+
			'<td width="21%" align="left" ><input name=TXT_ACCOUNT_NO   type=text maxlength=21 onblur=\"account_number_help()\"  class=txt_input  > '+
			'<input type=button name=BUT_ACCOUNT_NO value=... class=but_input onclick=account_number_help() ></td>'+
			'<td width="9%" align="left" >Branch Name</td>'+
			'<td colspan="3" align="left" ><input name="BRANCH_NAME"   type="text" maxlength="200"  onblur=\"\" class=\"txt_input\" style=\"width: 250px\" disabled></td>'+
			'</tr>'+
			'</table >';
			
	}
	
	get_sysdate();
}	
function show_settlement_mode_details_bk(){
	
	element=document.getElementById('SETT_MODE')
	
	
	div_settlement_details.innerHTML="";
	
	if (element.value=="CASH") {
		
		//div_settlement_details.innerHTML=
		document.getElementById('div_settlement_details').innerHTML=
		'<fieldset>'+
			'<legend><strong>Settlemt Details:</strong></legend>'+						
			'<table align="center" border="0"  width="100%" class=table>'+
			'<tr  class="tr_input">'+
			'<td  width="9%" align="left" >Value Date *</td>'+
			'<td  width="21%" align="left" ><input name="VAL_DAY"   type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_MONTH"  type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_YEAR"   type=text maxlength="4"  style="width:45px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)"><a href style="{cursor:hand; }" onclick=load_calendar("2")>Calendar</a> '+
			'</td >'+
			'<td  style="display:none;" width="9%" align="left" >Tendered Amount</td>'+
			'<TD  style="display:none;" width="31%" align="left" ><input class="txt_input_number" type="text" name=TEN_AMOUNT maxlength="23" size=25 onchange =calculate_balance(this) onblur=get_returned_value(this,25) disabled></td>'+
			'<td  style="display:none;" width="9%" align="left" >Returned Amount</td>'+
			'<TD  style="display:none;" width="21%" align="left"><input class="txt_input_number" type="text" name=RET_AMOUNT maxlength="23" size=25 onchange =calculate_balance(this) onblur=get_returned_value(this,25) disabled></td>'+
			'</tr >'+
			'</table >'+
			'</fieldset>';
		
		
	}
	else if (element.value=="CHEQUE") {
		
		
		document.getElementById('div_settlement_details').innerHTML=
		'<fieldset>'+
			'<legend><strong>Settlemt Details:</strong></legend>'+						
			'<table align="center" border="0"  width="100%" class="table">'+
			'<tr  class="tr_input">'+
			'<td  width="9%" align="left" >Value Date *</td>'+
			'<td  width="21%" align="left" ><input name="VAL_DAY"   type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_MONTH"  type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_YEAR"   type=text maxlength="4"  style="width:45px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)"><a href style="{cursor:hand; }" onclick=load_calendar("2")>Calendar</a> '+
			'</td >'+
			'<td  width="9%" align="left" id="CDATE" >Cheque Date *</td>'+
			'<td  width="31%" align="left" ><input name="CHEQUE_DATE_DD"   type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)">'+
			'<input name="CHEQUE_DATE_MM"  type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)">'+
			'<input name="CHEQUE_DATE_YY"   type=text maxlength="4"  style="width:45px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)"><a href style="{cursor:hand; }" onclick=load_calendar("3")>Calendar</a> '+
			'</td >'+
			'<td  width="9%" align="left" id="CRNO" >Cheque/Ref. No *</td>'+
			'<TD  width="21%" align="left"><input class="txt_input" type="text" name=CHEQUE_NO maxlength="8" size="22" onblur=validate_cheque_no() ></td>'+
			'</tr >'+
			
			'<tr class=tr_input>'+
			'<td width="9%" align="left" ID=PACC >Payer Account </td>'+
			'<td width="21%" align="left" ><input name=PAY_ACCOUNT   type=text maxlength=21 onblur=account_help()  class=txt_input  > '+
			'<input type=button name=accno_help value=... class=but_input onclick=account_help() ></td>'+
			'<td width="9%" align="left" ID=PBRANCH>Payer Branch *</td>'+
			'<td width="31%" align="left" > <input name="PAY_BRANCH"   type="text" maxlength="10"  onblur="branch_help()" class="txt_input" > '+ 
			'<input type="button" name="BUT_PAY_BRANCH" value="..." class="but_input" onclick="branch_help()" ></td>'+
			'<td width="9%" align="left" >Payer Branch Name</td>'+
			'<td width="21%" align="left" ><input name="PAY_BRANCH_NAME"   type="text" maxlength="100"  onblur=\"\" class=\"txt_input\" style=\"width: 200px\" disabled></td>'+
			'</tr>'+
			
			'</table >'+
			'</fieldset>';
		
		
		
		
	}
	
	
	else if (element.value=="STD_ORD" || element.value=="DIR_DEP") {
		
		document.getElementById('div_settlement_details').innerHTML=
		'<fieldset>'+
			'<legend><strong>Settlemt Details:</strong></legend>'+						
			'<table align="center" border="0"  width="100%" class=table>'+
			'<tr  class="tr_input">'+
			'<td  width="9%" align="left" >Value Date *</td>'+
			'<td  width="21%" align="left" ><input name="VAL_DAY"   type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_MONTH"  type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)">'+
			'<input name="VAL_YEAR"   type=text maxlength="4"  style="width:45px" class="txt_input" onBlur="validate_date_future_date(document.Form1.VAL_DAY,document.Form1.VAL_MONTH,document.Form1.VAL_YEAR)"><a href style="{cursor:hand;}" onclick=load_calendar("2")>Calendar</a> '+
			'</td >'+
			'<td  width="9%" align="left" id="CDATE" >Cheque Date *</td>'+
			'<td  width="31%" align="left" ><input name="CHEQUE_DATE_DD"   type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)">'+
			'<input name="CHEQUE_DATE_MM"  type=text maxlength="2"  style="width:25px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)">'+
			'<input name="CHEQUE_DATE_YY"   type=text maxlength="4"  style="width:45px" class="txt_input" onBlur="date_validation(document.Form1.CHEQUE_DATE_DD,document.Form1.CHEQUE_DATE_MM,document.Form1.CHEQUE_DATE_YY)"><a href style="{cursor:hand; }" onclick=load_calendar("3")>Calendar</a> '+
			'</td >'+
			'<td  width="9%" align="left" id="CRNO" >Cheque/Ref. No *</td>'+
			'<TD  width="21%" align="left"><input class="txt_input" type="text" name=CHEQUE_NO maxlength="8" size="22" onblur=validate_cheque_no() ></td>'+
			'</tr >'+
			
			'<tr class=tr_input>'+
			'<td width="9%" align="left" ID=PACC >Payer Account </td>'+
			'<td width="21%" align="left" ><input name=PAY_ACCOUNT   type=text maxlength=21 onblur=account_help()  class=txt_input  > '+
			'<input type=button name=accno_help value=... class=but_input onclick=account_help() ></td>'+
			'<td width="9%" align="left" ID=PBRANCH>Payer Branch *</td>'+
			'<td width="31%" align="left" > <input name="PAY_BRANCH"   type="text" maxlength="10"  onblur="branch_help()" class="txt_input" > '+ 
			'<input type="button" name="BUT_PAY_BRANCH" value="..." class="but_input" onclick="branch_help()" ></td>'+
			'<td width="9%" align="left" >Payer Branch Name</td>'+
			'<td width="21%"align="left"  ><input name="PAY_BRANCH_NAME"   type="text" maxlength="100"  onblur=\"\" class=\"txt_input\" style=\"width: 200px\" disabled></td>'+
			'</tr>'+
			
			'<tr class=tr_input>'+
			'<td width="9%" align="left" ID=ACC >Account Number *</td>'+
			'<td width="21%" align="left" ><input name=TXT_ACCOUNT_NO   type=text maxlength=21 onblur=\"account_number_help()\"  class=txt_input  > '+
			'<input type=button name=BUT_ACCOUNT_NO value=... class=but_input onclick=account_number_help() ></td>'+
			'<td width="9%" align="left" >Branch Name</td>'+
			'<td colspan="3" align="left" ><input name="BRANCH_NAME"   type="text" maxlength="200"  onblur=\"\" class=\"txt_input\" style=\"width: 250px\" disabled></td>'+
			'</tr>'+
			'</table >'+
			'</fieldset>';
	}
	
	get_sysdate();
}	


function show_payee_type_div_old(){
	document.getElementById('div_third_party_details').innerHTML="";
	
	if(document.getElementById('PAY_TYPE').value=="THIRD"){ 
		document.getElementById('div_third_party_details').innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr class=tr_input>'+
		'<td width=\"10%\" align="left" id=pay_name>Third Party Name</td>'+
			'<td width=\"31%\" align="left" ><input name=\"CLIENT_NAME_1\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" ></td>'+
			'<td width=\"10%\" align="left" id=pay_address>Third Party Address</td>'+
			'<td width=\"40%\" align="left" > <input name=\"CLIENT_ADDRESS_1\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" >'+
			'</td>'+
			'</tr>'+
			/*'<tr>'+			
			'<td width=\"9%\" id=pay_name>Third Party Name *</td>'+
			'<td width=\"30%\"><input name=\"CLIENT_NAME_1\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" ></td>'+
			'<td id=pay_address>Third Party Address</td>'+
			'<td> <input name=\"CLIENT_ADDRESS_1\" type=\"text\" style=\"width:350px;\" maxlength=\"200\" class=\"txt_input\" >'+
			'</td>'+
			'</tr>'+*/
			'<tr>'+
			'<td width=\"11%\" id=party_type align="left">Third Party Type</td>'+ 
			'<td width=\"31%\" align="left"> <SELECT name=\"party_ty\" class=\"txt_input\" onChange=\"\" > '+
			'<OPTION value=\"SALE\">Sale of Vehicle</OPTION>'+ 
			'<OPTION value=\"TRADE\">Trade in Vehicle</OPTION>'+ 
			'</SELECT>'+ 
			'</td>'+
			//'</tr>'+ 
			
			//'<tr>'+			
			'<td width=\"12%\" id=pay_name align="left">Third Party Nic *</td>'+
			'<td width=\"31%\" align="left"><input name=\"NIC_1\" type=\"text\" style=\"width:250px;\" maxlength=\"10\" class=\"txt_input\" ></td>'+
			'</tr>'+
			
			'<tr>'+
			'<td width=\"12%\" id=pay_address align="left">Third Party Profession *</td>'+
			'<td width=\"31%\" align="left"> <input name=\"PROFESSION_1\" type=\"text\" style=\"width:350px;\" maxlength=\"200\" class=\"txt_input\" >'+
			'</td>'+
			//'</tr>'+
			
			//'<tr>'+			
			'<td width=\"12%\" id=pay_name align="left">Third Party Contact Number *</td>'+
			'<td width=\"31%\" align="left"><input name=\"CONNUM_1\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" ></td>'+
			'</tr>'+
			'</table>';
	}
}
function show_payee_type_div(){
	document.getElementById('div_third_party_details').innerHTML="";
	
	if(document.getElementById('PAY_TYPE').value=="THIRD"){ 
		document.getElementById('div_third_party_details').innerHTML+=
		'<table align=\"center\" width=\"100%\" class= \"\" padding=\"0\"  border=\"0\">'+ //class=\"table\"
			'<tr class=tr_input>'+
				'<td width=\"20%\" align="left" id=pay_name>Third Party Name</td>'+
				'<td width=\"30%\" align="left" ><input name=\"CLIENT_NAME_1\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" ></td>'+
				'<td width=\"20%\" align="left" id=pay_address>Third Party Address</td>'+
				'<td width=\"30%\" align="left" > <input name=\"CLIENT_ADDRESS_1\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" >'+
				'</td>'+
			'</tr>'+
			
			'<tr style=\"display:none;\" >'+
				'<td id=party_type align="left">Third Party Type</td>'+ 
				'<td  align="left"> <SELECT name=\"party_ty\" class=\"txt_input\" onChange=\"\" > '+
					'<OPTION value=\"SALE\">Sale of Vehicle</OPTION>'+ 
					'<OPTION value=\"TRADE\">Trade in Vehicle</OPTION>'+ 
					'</SELECT>'+ 
				'</td>'+
				'<td  id=pay_name align="left">Third Party Nic *</td>'+
				'<td  align="left"><input name=\"NIC_1\" type=\"text\" style=\"width:250px;\" maxlength=\"10\" class=\"txt_input\" ></td>'+
			'</tr>'+
			
			'<tr style=\"display:none;\" >'+
				'<td  id=pay_address align="left">Third Party Profession *</td>'+
				'<td  align="left"> <input name=\"PROFESSION_1\" type=\"text\" style=\"width:350px;\" maxlength=\"200\" class=\"txt_input\" >'+
				'</td>'+
				'<td  id=pay_name align="left">Third Party Contact Number *</td>'+
				'<td  align="left"><input name=\"CONNUM_1\" type=\"text\" style=\"width:250px;\" maxlength=\"200\" class=\"txt_input\" ></td>'+
			'</tr>'+
		'</table>';
	}
}
function show_div_other_charges(){
	document.getElementById('div_other_charges').innerHTML="";
	//document.getElementById('AMOUNT').disabled=true;
	if(document.getElementById('OTHER_CHARGES').value=="Y"){ 
		document.getElementById('div_other_charges').innerHTML+='<table align=\"center\" width=\"100%\" class=\"table\" border=\"0\"><tr class=tr_input>'+
		'<td width=\"9%\" align="left" id=pay_name>Rental Invoices</td>'+
			'<td width=\"31%\" align="left" ><input name=\"TXT_RENTAL_OTHER_INV\" type=\"text\" maxlength=\"23\" class=\"txt_input_number\" onBlur=\"calculate_other_charges(this,document.Form1.TXT_OTHER_INV) ,check_number(this,25)\" value=\"0.00\" ></td>'+
			'<td width=\"9%\" align="left" id=pay_address>Other Invoices</td>'+
			'<td width=\"21%\" align="left" > <input name=\"TXT_OTHER_INV\" type=\"text\"  maxlength=\"23\" class=\"txt_input_number\"      onBlur=\"calculate_other_charges(this,document.Form1.TXT_RENTAL_OTHER_INV) ,check_number(this,25)\" value=\"0.00\" >'+
			'</td>'+
			'</tr>'+	
			'</table>';
	}
}







