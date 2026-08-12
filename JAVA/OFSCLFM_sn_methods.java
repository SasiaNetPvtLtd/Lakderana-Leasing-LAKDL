// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 10/29/2013 4:32:31 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   OFSCLFM_sn_methods.java

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import sun.misc.BASE64Decoder;

public class OFSCLFM_sn_methods
{

    public OFSCLFM_sn_methods()
    {
        servlet_client_url = "http://www.OFSCLFM.lk";
        html_client_url = "http://www.OFSCLFM.lk/intranet";
        client_t3_port = "/myserver/servlet";
        schema_name = "OFSCLFM";
        schema_name_deal = "OFSCLFM";
        client_name = "OFSCLFM_";
        commonAdSchema = "MELSTRAADMIN";
        html_image_url = "http://www.OFSCLFM.lk/intranet/client-images";
        html_sign_url = "http://www.OFSCLFM.lk/intranet/client-signatures";
    }

    public String met_formdata(String paramString1, String paramString2)
    {
        String str1 = "";
        try
        {
            if(paramString1.indexOf(paramString2) > -1)
            {
                int i = 0;
                int j = 0;
                int k = 0;
                if(paramString1.indexOf(paramString2) == 0)
                {
                    i = paramString2.length();
                    j = paramString1.indexOf((new StringBuilder()).append(paramString2).append("=").toString()) + i + 1;
                    k = j;
                } else
                {
                    i = paramString2.length() + 1;
                    if(paramString1.indexOf((new StringBuilder()).append("&").append(paramString2).append("=").toString()) != -1)
                    {
                        j = paramString1.indexOf((new StringBuilder()).append("&").append(paramString2).append("=").toString()) + i + 1;
                        k = j;
                    } else
                    {
                        j = paramString1.length();
                        k = j;
                    }
                }
                for(; k <= paramString1.length() - 1 && paramString1.substring(k, k + 1).compareTo("&") != 0; k++)
                    str1 = (new StringBuilder()).append(str1).append(paramString1.substring(k, k + 1)).toString();

                str1 = str1.replace('+', ' ');
                Vector localVector1 = new Vector();
                localVector1.addElement("%7E");
                localVector1.addElement("%21");
                localVector1.addElement("%23");
                localVector1.addElement("%24");
                localVector1.addElement("%25");
                localVector1.addElement("%5E");
                localVector1.addElement("%26");
                localVector1.addElement("%28");
                localVector1.addElement("%29");
                localVector1.addElement("%60");
                localVector1.addElement("%3D");
                localVector1.addElement("%7B");
                localVector1.addElement("%7D");
                localVector1.addElement("%7C");
                localVector1.addElement("%5B");
                localVector1.addElement("%5D");
                localVector1.addElement("%3A");
                localVector1.addElement("%22");
                localVector1.addElement("%3B");
                localVector1.addElement("%27");
                localVector1.addElement("%3C");
                localVector1.addElement("%3E");
                localVector1.addElement("%3F");
                localVector1.addElement("%2C");
                localVector1.addElement("%20");
                localVector1.addElement("%2F");
                localVector1.addElement("%5C");
                localVector1.addElement("%2B");
                Vector localVector2 = new Vector();
                localVector2.addElement("~");
                localVector2.addElement("!");
                localVector2.addElement("#");
                localVector2.addElement("$");
                localVector2.addElement("%");
                localVector2.addElement("^");
                localVector2.addElement("&");
                localVector2.addElement("(");
                localVector2.addElement(")");
                localVector2.addElement("`");
                localVector2.addElement("=");
                localVector2.addElement("{");
                localVector2.addElement("}");
                localVector2.addElement("|");
                localVector2.addElement("[");
                localVector2.addElement("]");
                localVector2.addElement(":");
                char arrayOfChar[] = {
                    '"'
                };
                String str2 = new String(arrayOfChar);
                localVector2.addElement(str2);
                localVector2.addElement(";");
                localVector2.addElement("'");
                localVector2.addElement("<");
                localVector2.addElement(">");
                localVector2.addElement("?");
                localVector2.addElement(",");
                localVector2.addElement(" ");
                localVector2.addElement("/");
                localVector2.addElement("/");
                localVector2.addElement("+");
                for(int m = 0; m < str1.length(); m++)
                    if(str1.substring(m, m + 1).equals("%"))
                    {
                        String str3 = str1.substring(m, m + 3);
                        int n = localVector1.indexOf(str3);
                        if(n >= 0)
                        {
                            String str4 = (String)localVector2.elementAt(n);
                            str1 = (new StringBuilder()).append(str1.substring(0, m)).append(str4).append(str1.substring(m + 3)).toString();
                        }
                    }

            }
        }
        catch(Exception localException)
        {
            System.out.println(localException.toString());
        }
        return str1;
    }

    public String met_unformat_number(String paramString)
    {
        String str1 = "";
        for(int i = 0; i < paramString.length(); i++)
        {
            String str2 = paramString.substring(i, i + 1);
            if(!str2.equals(","))
                str1 = (new StringBuilder()).append(str1).append(str2).toString();
        }

        return str1;
    }

    public String Add_Space(int paramInt)
    {
        String str = "";
        for(int i = 0; i < paramInt; i++)
            str = (new StringBuilder()).append(str).append(" ").toString();

        return str;
    }

    public static String convert(long paramLong)
    {
        if(paramLong == 0L)
            return "Zero";
        String str1 = Long.toString(paramLong);
        String str2 = "000000000000";
        DecimalFormat localDecimalFormat = new DecimalFormat(str2);
        str1 = localDecimalFormat.format(paramLong);
        int i = Integer.parseInt(str1.substring(0, 3));
        int j = Integer.parseInt(str1.substring(3, 6));
        int k = Integer.parseInt(str1.substring(6, 9));
        int m = Integer.parseInt(str1.substring(9, 12));
        String str3;
        switch(i)
        {
        case 0: // '\0'
            str3 = "";
            break;

        case 1: // '\001'
            str3 = (new StringBuilder()).append(convertLessThanOneThousand(i)).append(" Billion ").toString();
            break;

        default:
            str3 = (new StringBuilder()).append(convertLessThanOneThousand(i)).append(" Billion ").toString();
            break;
        }
        String str4 = str3;
        String str5;
        switch(j)
        {
        case 0: // '\0'
            str5 = "";
            break;

        case 1: // '\001'
            str5 = (new StringBuilder()).append(convertLessThanOneThousand(j)).append(" Million ").toString();
            break;

        default:
            str5 = (new StringBuilder()).append(convertLessThanOneThousand(j)).append(" Million ").toString();
            break;
        }
        str4 = (new StringBuilder()).append(str4).append(str5).toString();
        String str6;
        switch(k)
        {
        case 0: // '\0'
            str6 = "";
            break;

        case 1: // '\001'
            str6 = "One Thousand ";
            break;

        default:
            str6 = (new StringBuilder()).append(convertLessThanOneThousand(k)).append(" Thousand ").toString();
            break;
        }
        str4 = (new StringBuilder()).append(str4).append(str6).toString();
        String str7 = convertLessThanOneThousand(m);
        str4 = (new StringBuilder()).append(str4).append(str7).toString();
        return str4.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }

    private static String convertLessThanOneThousand(int paramInt)
    {
        String str;
        if(paramInt % 100 < 20)
        {
            str = numNames[paramInt % 100];
            paramInt /= 100;
        } else
        {
            str = numNames[paramInt % 10];
            paramInt /= 10;
            str = (new StringBuilder()).append(tensNames[paramInt % 10]).append(str).toString();
            paramInt /= 10;
        }
        if(paramInt == 0)
            return str;
        else
            return (new StringBuilder()).append(numNames[paramInt]).append(" Hundred").append(str).toString();
    }

    public String numbersToChar(String paramString)
    {
        String str1 = "";
        String str2 = "00";
        String str3 = "";
        int i = 0;
        int j = -1;
        int k = 0;
        long l = 0L;
        int m = 0;
        k = paramString.length();
        str1 = paramString.toString();
        j = paramString.indexOf(".");
        if(j != -1)
        {
            str2 = paramString.substring(j + 1, k);
            i = str2.length();
            k = str1.substring(0, j).length();
            str1 = paramString.substring(0, j);
            m = Integer.parseInt(str2);
        }
        l = Long.parseLong(str1);
        if(m != 0)
            str3 = (new StringBuilder()).append(convert(l)).append(" and ").append(convert(m)).append("  Cents").toString();
        else
        if(m == 0)
            str3 = convert(l);
        return str3;
    }

    public Connection met_user_validate(HttpServletRequest paramHttpServletRequest)
    {
        try
        {
            String str1 = "norights";
            int i = 0;
            username = "Undefined";
            String str2 = "";
            str1 = paramHttpServletRequest.getHeader("authorization");
            String str3 = paramHttpServletRequest.getHeader("authorization").substring(6, paramHttpServletRequest.getHeader("authorization").length());
            BASE64Decoder localBASE64Decoder = new BASE64Decoder();
            byte arrayOfByte[] = localBASE64Decoder.decodeBuffer(str3);
            String str4 = new String(arrayOfByte);
            int j = str4.indexOf(":");
            username = (new String(arrayOfByte)).substring(0, j).toUpperCase();
            int k = str4.length();
            str2 = (new String(arrayOfByte)).substring(j + 1, k);
            if(username == "")
                username = "Undefined";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            out_conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.234.132:1521:OFSCLDB", username, str2);
        }
        catch(Exception localException) { }
        return out_conn;
    }

    public Connection get_direct_connection()
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            out_conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.234.132:1521:OFSCLDB", "OFSCLFM", "SNORA7623ADMIN");
        }
        catch(Exception localException) { }
        return out_conn;
    }

    NumberFormat nf;
    Connection out_conn;
    String servlet_client_url;
    String html_client_url;
    String client_t3_port;
    String schema_name;
    String schema_name_deal;
    String client_name;
    String username;
    String commonAdSchema;
    String html_image_url;
    String html_sign_url;
    private static final String tensNames[] = {
        "", " Ten", " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety"
    };
    private static final String numNames[] = {
        "", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine", 
        " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen", " Nineteen"
    };

}