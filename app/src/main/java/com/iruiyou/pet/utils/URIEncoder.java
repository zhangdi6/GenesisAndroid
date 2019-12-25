package com.iruiyou.pet.utils;

import java.io.UnsupportedEncodingException;

public class URIEncoder {
    public static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'()";
    /**
     * Description:
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     * @see
     */
    public static String encodeURI(String str)
            throws UnsupportedEncodingException
    {
        String isoStr = new String(str.getBytes("UTF8"), "ISO-8859-1");
        char[] chars = isoStr.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++ )
        {
            if ((chars[i] <= 'z' && chars[i] >= 'a') || (chars[i] <= 'Z' && chars[i] >= 'A')
                    || chars[i] == '-' || chars[i] == '_' || chars[i] == '.' || chars[i] == '!'
                    || chars[i] == '~' || chars[i] == '*' || chars[i] == '\'' || chars[i] == '('
                    || chars[i] == ')' || chars[i] == ';' || chars[i] == '/' || chars[i] == '?'
                    || chars[i] == ':' || chars[i] == '@' || chars[i] == '&' || chars[i] == '='
                    || chars[i] == '+' || chars[i] == '$' || chars[i] == ',' || chars[i] == '#'
                    || (chars[i] <= '9' && chars[i] >= '0'))
            {
                sb.append(chars[i]);
            }
            else
            {
                sb.append("%");
                sb.append(Integer.toHexString(chars[i]));
            }
        }
        return sb.toString();
    }

    /**
     * Description:
     *
     * @param input
     * @return
     * @see
     */
    public static String encodeURIComponent(String input)
    {
        if (null == input || "".equals(input.trim()))
        {
            return input;
        }

        int l = input.length();
        StringBuilder o = new StringBuilder(l * 3);
        try
        {
            for (int i = 0; i < l; i++ )
            {
                String e = input.substring(i, i + 1);
                if (ALLOWED_CHARS.indexOf(e) == -1)
                {
                    byte[] b = e.getBytes("utf-8");
                    o.append(getHex(b));
                    continue;
                }
                o.append(e);
            }
            return o.toString();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return input;
    }

    private static String getHex(byte buf[])
    {
        StringBuilder o = new StringBuilder(buf.length * 3);
        for (int i = 0; i < buf.length; i++ )
        {
            int n = (int)buf[i] & 0xff;
            o.append("%");
            if (n < 0x10)
            {
                o.append("0");
            }
            o.append(Long.toString(n, 16).toUpperCase());
        }
        return o.toString();
    }
}
