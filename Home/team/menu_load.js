/*
   Deluxe Menu Data File
   Created by Deluxe Tuner v2.4
   http://deluxe-menu.com
*/


// -- Deluxe Tuner Style Names
var tstylesNames=["Font",];
var tXPStylesNames=[];
// -- End of Deluxe Tuner Style Names

//--- Common
var tlevelDX=35;
var texpanded=1;
//
var texpandItemClick=1;
var tnoWrap=0;

var tmoveImage       = ""+html_client_url+"/team/img/movepic.gif";
var tmoveImageHeight = 12;


var titemCursor      = "pointer";

var titemTarget      = "_blank";

//var ticonWidth       = 21;
//var ticonHeight      = 15;
var ticonAlign       = "left";


var tmenuBorderColor = "#FFFFFF";
var tmenuBorderStyle = "solid";


//var tXPIconWidth  = 31;
//var tXPIconHeight = 32;


var tXPBorderColor = '#FFFFFF';


var Disabled="#AAAAAA";
//var tpressedFontColor="#006633";
var tpointsBImage="";

var tXPAlign="left";
var tXPMenuSpace=20;

//--- Advanced
var tajax=1;




//
var tcloseExpanded=0;
var tcloseExpandedXP=0;
var ttoggleMode=1;


var statusString="link";
var tblankImage="blank.gif";
var tpathPrefix_img=""+html_client_url+"/team/img/";
var tpathPrefix_link="";

//--- Dimensions
var tmenuWidth="320px";
var tmenuHeight=0;

//--- Positioning
var tabsolute=1;
var tleft=0;
var ttop=0;

//--- Font
var tfontStyle="normal 8pt Tahoma";
var tfontColor=["#000000","#000000"];
var tfontDecoration=["none","none"];
var tfontColorDisabled="#AAAAAA";
var tpressedFontColor="#AA0000";

//--- Appearance
var tmenuBackColor="#FFFFFF";
var tmenuBackImage="";
//var tmenuBorderColor="#000";
var tmenuBorderWidth=0;
//var tmenuBorderStyle="none";

//--- Item Appearance
var titemAlign="left";
var titemHeight=20;
var titemBackColor=["#ffffff","#D6DFF7"];
var titemBackImage=["",""];

//--- Icons & Buttons
var ticonWidth=14;
var ticonHeight=14;
var ticonAlign="left";
var texpandBtn=["expand.gif","expand.gif","collapse.gif"];
var texpandBtnW=8;
var texpandBtnH=8;
var texpandBtnAlign="left";

//--- Lines
var tpoints=2;
var tpointsImage="";
var tpointsVImage="";
var tpointsCImage="";

//--- Floatable Menu
var tfloatable=0;
var tfloatIterations=10;
var tfloatableX=1;
var tfloatableY=1;

//--- Movable Menu
var tmoveable=0;
var tmoveHeight=12;
var tmoveColor="#AA0000";
//var tmoveImage="";

//--- XP-Style
var tXPStyle=1;
var tXPIterations=8;
var tXPTitleBackColor="";
var tXPTitleBackImg="back1.gif";
var tXPTitleLeft="left1.gif";
var tXPTitleLeftWidth=8;
var tXPIconWidth=25;
var tXPIconHeight=25;
var tXPExpandBtn=["xp_expand1.gif","xp_expand1.gif","xp_collapse1.gif","xp_collapse1.gif"];
var tXPBtnWidth=25;
var tXPBtnHeight=25;
var tXPFilter=1;
var tXPBorderWidth = 1;
//var tXPBorderColor = '#31A17E';

//--- Dynamic Menu
var tdynamic=0;

//--- State Saving
var tsaveState=0;
var tsavePrefix="menu1";

var tstyles = [
    ["tfontStyle=bold 8pt Tahoma","tfontColor=#1C512C,#1C512C"],
];



/*

var tmenuItems =
[
    ["+DHTML Tree: XP Style", "", "xpicon1.gif","","", "XP Title Tip",,"0"],
    ["|Home", "http://DHTML-tree.com", "icon1.gif", "icon1o.gif", "", "Home Page Tip","_blank"],
    ["|+Product Info", "", "icon2.gif", "icon2o.gif", "", "Product Info Tip"],
        ["||What's New", "testlink.htm", "iconarr.gif"],
        ["||Features", "testlink.htm", "iconarr.gif"],
        ["||Installation", "testlink.htm", "iconarr.gif"],
        ["||Functions", "testlink.htm", "iconarr.gif"],
        ["||Supported Browsers", "testlink.htm", "iconarr.gif"],
    ["|Samples", "", "icon3.gif", "icon3o.gif", "", "Samples Tip"],
        ["||Sample 1", "testlink.htm", "iconarr.gif"],
        ["||Sample 2", "testlink.htm", "iconarr.gif"],
        ["||Sample 3", "testlink.htm", "iconarr.gif"],
        ["||Sample 4", "testlink.htm", "iconarr.gif"],
        ["||Sample 5", "testlink.htm", "iconarr.gif"],
        ["||Sample 6", "testlink.htm", "iconarr.gif"],
        ["||More Samples", "", "icon3.gif", "icon3o.gif"],
            ["|||New Sample 1", "testlink.htm", "iconarr.gif"],
            ["|||New Sample 2", "testlink.htm", "iconarr.gif"],
            ["|||New Sample 3", "testlink.htm", "iconarr.gif"],
            ["|||New Sample 4", "testlink.htm", "iconarr.gif"],
            ["|||New Sample 5", "testlink.htm", "iconarr.gif"],
    ["|Purchase", "testlink.htm", "icon4.gif", "icon4o.gif", "", "Purchase Tip"],
    ["|Support", "", "icon5.gif", "icon5o.gif", "", "Support Tip"],
        ["||Write Us", "mailto:dhtml@dhtml-menu.com", "iconarr.gif"],

    ["Samples Gallery", "", "","","", "XP Title Tip",,"1","0"],
        ["|Samples Block 1", "", "icon3.gif", "icon3o.gif"],
            ["||New Sample 1", "testlink.htm", "iconarr.gif"],
            ["||New Sample 2", "testlink.htm", "iconarr.gif"],
            ["||New Sample 3", "testlink.htm", "iconarr.gif"],
            ["||New Sample 4", "testlink.htm", "iconarr.gif"],
            ["||New Sample 5", "testlink.htm", "iconarr.gif"],
        ["|Samples Block 2", "", "icon3.gif", "icon3o.gif"],
            ["||New Sample 1", "testlink.htm", "iconarr.gif"],
            ["||New Sample 2", "testlink.htm", "iconarr.gif"],
            ["||New Sample 3", "testlink.htm", "iconarr.gif"],
            ["||New Sample 4", "testlink.htm", "iconarr.gif"],
            ["||New Sample 5", "testlink.htm", "iconarr.gif"],
        ["|Samples Block 3", "", "icon3.gif", "icon3o.gif"],
            ["||New Sample 1", "testlink.htm", "iconarr.gif"],
            ["||New Sample 2", "testlink.htm", "iconarr.gif"],
            ["||New Sample 3", "testlink.htm", "iconarr.gif"],
            ["||New Sample 4", "testlink.htm", "iconarr.gif"],
            ["||New Sample 5", "testlink.htm", "iconarr.gif"],
];

*/



dtree_init();

