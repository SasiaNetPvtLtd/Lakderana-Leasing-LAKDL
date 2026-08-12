 /* Create by Waruna Sri Jayanath 2011-09-05 */
  var $dialog = null;
  var title_name='';
   jQuery.showModalDialog = function(options) {

    var defaultOptns = {
        url: null,
        dialogArguments: null,
        height: 'auto',
        width: 'auto',
        position: 'center',
        resizable: false,
        scrollable: false,
        onClose: function() { },
        returnValue: null,
        doPostBackAfterCloseCallback: false,
        postBackElementId: null
    };

    var fns = {
        close: function() {
            opts.returnValue = $dialog.returnValue;
            $dialog = null;
            opts.onClose();
            if (opts.doPostBackAfterCloseCallback) {
                postBackForm(opts.postBackElementId);
            }
        },
        adjustWidth: function() { $frame.css("width", "100%"); }
    };

    // build main options before element iteration
 
		
    var opts = $.extend({}, defaultOptns, options);
	
		
    var $frame = $('<iframe id="iframeDialog" frameborder="0" scrolling="False" />');

    if (opts.scrollable)
        $frame.css('overflow', 'true');
		$frame.css('overflow-x', 'hidden');
		$frame.css('overflow-y', 'hidden');
		 
  /*overflow-y:auto; */

    $frame.css({
        'padding': 0,
        'margin': 0,
        'padding-bottom': 0
    });

    var $dialogWindow = $frame.dialog({
        autoOpen: true,
        modal: true,
        width: opts.width,
        height: opts.height,
        resizable: opts.resizable,
        position: opts.position,
        overlay: {
            opacity: 1,
            background: "black"
        },
        close: fns.close,
        resizeStop: fns.adjustWidth
    });

    $frame.attr('src', opts.url);
    fns.adjustWidth();

    $frame.load(function() {
        if ($dialogWindow) {
            var maxTitleLength = 50;
            var title = $(this).contents().find("title").html();

          //  if (title.length > maxTitleLength) {
           //     title = title.substring(0, maxTitleLength) + '...';
           // }
            //$dialogWindow.dialog('option', 'title', title);
			 //$dialogWindow.dialog('option', 'title', 'Help');
			// $dialogWindow.dialog('option', 'title', title_name);
        }
    });

    $dialog = new Object();
    $dialog.dialogArguments = opts.dialogArguments;
    $dialog.dialogWindow = $dialogWindow;
    $dialog.returnValue = null;
}

function postBackForm(targetElementId) {
    //alert('postBackForm');
    var theform;
    theform = document.forms[0];
    theform.__EVENTTARGET.value = targetElementId;
    theform.__EVENTARGUMENT.value = "";
    theform.submit();
}

// function to open THE POPUP
 


function test(m_strat)
{
     var aForm;
     //  aForm =oForm.elements;
     aForm =  document.getElementById('oForm');
 /*  var myObject = new Object();
    myObject.firstName = aForm.oFirstName.value;
    myObject.lastName = aForm.oLastName.value;
*/
 var myObject=new Array(); 
 myObject[0]=aForm.oFirstName;
 myObject[1]=aForm.oLastName;
 myObject[2]=null;
 myObject[3]=null;
 myObject[4]=null;
 
 
 var myObject_value=new Array(); 
 myObject_value[0]=aForm.oFirstName.value;
 myObject_value[1]=aForm.oLastName.value;
 myObject_value[2]=null;
 myObject_value[3]=null;
 myObject_value[4]=null;

 var url = 'popup.jsp';
 var json_text = JSON.stringify(myObject_value, null,1);
//JSON.parse(str) 
//alert(json_text);

       	$.showModalDialog({
		 url: url+'?par='+json_text+'&show=16'+'&start='+m_strat,
		 dialogArguments: myObject_value, //dialogArguments: 'www.waruna.com', //dialogArguments: myObject,
		 height:500,// 500,
		 width: 900,//900,
		 title: 'popup',
		 scrollable: true,
		 onClose: function(){ var returnedValue = this.returnValue; window.parent.set_return_value(myObject,returnedValue);  }//alert(' popup:' + returnedValue);
	});
}



function test2(m_strat) 
{
     var aForm;    //  aForm =oForm.elements;
     aForm =  document.getElementById('Form1');
 /*  var myObject = new Object();
    myObject.firstName = aForm.oFirstName.value;
    myObject.lastName = aForm.oLastName.value;
*/
 var myObject=new Array(); //return values set to this object
 myObject[0]=aForm.TXT_portfolio;
 myObject[1]=null;
 myObject[2]=null;
 myObject[3]=null;
 myObject[4]=null;
  
 var myObject_value=new Array(); //passing values set to this object
 myObject_value[0]=aForm.TXT_portfolio.value;
 myObject_value[1]=null;//aForm.oLastName2.value;
 myObject_value[2]=null;
 myObject_value[3]=null;
 myObject_value[4]=null;
 
 
 var myObject_type=new Array(); //passing values data type
 myObject_type[0]="StringL";
 myObject_type[1]=null;
 myObject_type[2]=null;
 myObject_type[3]=null;
 myObject_type[4]=null;
 
 var qid='1';
 var hid='0';
 var url = 'popup.jsp';
 var json_text = JSON.stringify(myObject_value, null,0);
 var json_obj_type_text=JSON.stringify(myObject_type, null,0);
 var fn_name=arguments.callee.name;
//JSON.parse(str) 
//alert(json_text);
       	$.showModalDialog({
		 url: url+'?par='+json_text+'&type='+json_obj_type_text+'&qid='+qid+'&show=10'+'&start='+m_strat+'&fn_name='+fn_name+'&hid='+hid,
		 dialogArguments: myObject_value, //dialogArguments: 'www.waruna.com', //dialogArguments: myObject,
		 height:350,// 500,
		 width: 900,//900,
		 title: 'popup',
		 scrollable: true,
		 onClose: function(){ var returnedValue = this.returnValue; set_return_value(myObject,returnedValue);  } //alert(' popup:' + returnedValue); window.parent.set_return_value(myObject,returnedValue);
	});
}


function set_return_value(from_obj,return_obj){ //reurn value set to the object in this function

if(return_obj!=null){
    for (i=1;i<return_obj.length;i++){  
          //alert(return_obj[i]);
		   if(from_obj[i-1]!=null){
		   from_obj[i-1].value=return_obj[i];
		   		   }
	      		  }
                      }
}
function callback_fun(fn_name,val){//invoke funtion next,pre button click
 var t=  window[fn_name];   
	 t(val);
}

function callback_fun2(fn_name){//invoke funtion next,pre button click
 var g=  window[fn_name]; 
	 g();
}

function aa(){
  //var t=  window['test2'];
  //alert(arguments.callee.name);
  // t(0); 
}
