//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
    public static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERS = "0123456789";
    public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String defaultKeyAndValueSeparator = ":";
    public static final String defaultValueEntitySeparator = ",";
    public static final String defaultKeyOrValueQuote = "\"";
    private static final char SEPARATOR = '_';

    public StringUtils() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isBlank(String str) {
        return isEmpty(str) || str.trim().length() == 0;
    }

    public static String capitalizeFirstLetter(String str) {
        return !isEmpty(str) && Character.isLetter(str.charAt(0))?Character.toUpperCase(str.charAt(0)) + str.substring(1):str;
    }

    public static String utf8Encode(String str) {
        if(!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException var2) {
                var2.printStackTrace();
                return null;
            }
        } else {
            return str;
        }
    }

    public static String utf8Encode(String str, String defultReturn) {
        if(!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException var3) {
                return defultReturn;
            }
        } else {
            return str;
        }
    }

    public static String nullStrToEmpty(String str) {
        return str == null?"":str;
    }

    public static String currentDate(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(isBlank(format)?"yyyy-MM-dd HH:mm:ss":format);
        return dateFormat.format(new Date());
    }

    public static String currentDate() {
        return currentDate("");
    }

    public static String currentDateInMills(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(isBlank(format)?"yyyy-MM-dd HH-mm-ss SS":format);
        return dateFormat.format(new Timestamp(System.currentTimeMillis()));
    }

    public static String currentDateInMills() {
        return currentDateInMills("");
    }

    public static String processTime(Long time) {
        long oneDay = 86400000L;
        Date now = new Date();
        Date orginalTime = new Date(time.longValue());
        long nowDay = now.getTime() - (long)((now.getHours() * 3600 + now.getMinutes() * 60 + now.getSeconds()) * 1000);
        long yesterday = nowDay - oneDay;
        String nowHourAndMinute = toDoubleDigit(orginalTime.getHours()) + ":" + toDoubleDigit(orginalTime.getMinutes());
        return time.longValue() >= now.getTime()?"刚刚":(now.getTime() - time.longValue() < 60000L?(now.getTime() - time.longValue()) / 1000L + "秒前 " + nowHourAndMinute + " ":(now.getTime() - time.longValue() < 3600000L?(now.getTime() - time.longValue()) / 1000L / 60L + "分钟前 " + nowHourAndMinute + " ":(now.getTime() - time.longValue() < 86400000L?(now.getTime() - time.longValue()) / 1000L / 60L / 60L + "小时前 " + nowHourAndMinute + " ":(time.longValue() >= nowDay?"今天 " + nowHourAndMinute:(time.longValue() >= yesterday?"昨天 " + nowHourAndMinute:toDoubleDigit(orginalTime.getMonth()) + "-" + toDoubleDigit(orginalTime.getDate()) + " " + nowHourAndMinute + ":" + toDoubleDigit(orginalTime.getSeconds()))))));
    }

    public static String toDoubleDigit(int number) {
        return number >= 0 && number < 10?"0" + Integer.valueOf(number).toString():Integer.valueOf(number).toString();
    }

    public static String getHrefInnerHtml(String href) {
        if(isEmpty(href)) {
            return "";
        } else {
            String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
            Pattern hrefPattern = Pattern.compile(hrefReg, 2);
            Matcher hrefMatcher = hrefPattern.matcher(href);
            return hrefMatcher.matches()?hrefMatcher.group(1):href;
        }
    }

    public static String getRandomNumbersAndLetters(int length) {
        return getRandom("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", length);
    }

    public static String getRandomNumbers(int length) {
        return getRandom("0123456789", length);
    }

    public static String getRandomLetters(int length) {
        return getRandom("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", length);
    }

    public static String getRandomCapitalLetters(int length) {
        return getRandom("ABCDEFGHIJKLMNOPQRSTUVWXYZ", length);
    }

    public static String getRandomLowerCaseLetters(int length) {
        return getRandom("abcdefghijklmnopqrstuvwxyz", length);
    }

    public static String getRandom(String source, int length) {
        return isEmpty(source)?null:getRandom(source.toCharArray(), length);
    }

    public static String getRandom(char[] sourceChar, int length) {
        if(sourceChar != null && sourceChar.length != 0 && length >= 0) {
            StringBuilder str = new StringBuilder("");
            Random random = new Random();

            for(int i = 0; i < length; ++i) {
                str.append(sourceChar[random.nextInt(sourceChar.length)]);
            }

            return str.toString();
        } else {
            return null;
        }
    }

    public static String htmlEscapeCharsToString(String source) {
        return isEmpty(source)?"":source.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }

    public static boolean isEquals(String actual, String expected) {
        return ObjectUtils.isEquals(actual, expected);
    }

    public static Map<String, String> parseKeyAndValueToMap(String source, String keyAndValueSeparator, String valueEntitySeparator, String keyOrValueQuote) {
        if(isEmpty(source)) {
            return null;
        } else {
            if(isEmpty(keyAndValueSeparator)) {
                keyAndValueSeparator = ":";
            }

            if(isEmpty(valueEntitySeparator)) {
                valueEntitySeparator = ",";
            }

            if(isEmpty(keyOrValueQuote)) {
                keyOrValueQuote = "\"";
            }

            HashMap<String, String> keyAndValueMap = new HashMap<String, String>();
            String[] keyAndValueArray = source.split(valueEntitySeparator);
            if(keyAndValueArray != null) {
                String[] arr$ = keyAndValueArray;
                int len$ = keyAndValueArray.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    String valueEntity = arr$[i$];
                    if(!isEmpty(valueEntity)) {
                        int seperator = valueEntity.indexOf(keyAndValueSeparator);
                        if(seperator != -1 && seperator < valueEntity.length()) {
                            MapUtils.putMapNotEmptyKey(keyAndValueMap, RemoveBothSideSymbol(valueEntity.substring(0, seperator).trim(), keyOrValueQuote), RemoveBothSideSymbol(valueEntity.substring(seperator + 1).trim(), keyOrValueQuote));
                        }
                    }
                }
            }

            return keyAndValueMap;
        }
    }

    public static Map<String, String> parseKeyAndValueToMap(String source) {
        return parseKeyAndValueToMap(source, ":", ",", "\"");
    }
    
    public static String RemoveBothSideSymbol(String source, String symbol) {
        if(!isEmpty(source) && !isEmpty(symbol)) {
            int firstIndex = source.indexOf(symbol);
            int lastIndex = source.lastIndexOf(symbol);

            try {
                return source.substring(firstIndex == 0?symbol.length():0, lastIndex == source.length() - 1?source.length() - symbol.length():source.length());
            } catch (IndexOutOfBoundsException var5) {
                return "";
            }
        } else {
            return source;
        }
    }

    public static boolean simpleWildcardMatch(String pattern, String str) {
        return wildcardMatch(pattern, str, "*");
    }

    public static boolean wildcardMatch(String pattern, String str, String wildcard) {
        if(!isEmpty(pattern) && !isEmpty(str)) {
            boolean startWith = pattern.startsWith(wildcard);
            boolean endWith = pattern.endsWith(wildcard);
            String[] array = split(pattern, wildcard);
            boolean currentIndex = true;
            int lastIndex = -1;
            int var12;
            switch(array.length) {
                case 0:
                    return true;
                case 1:
                    var12 = str.indexOf(array[0]);
                    if(startWith && endWith) {
                        return var12 >= 0;
                    } else if(startWith) {
                        return var12 + array[0].length() == str.length();
                    } else {
                        if(endWith) {
                            return var12 == 0;
                        }

                        return str.equals(pattern);
                    }
                default:
                    String[] arr$ = array;
                    int len$ = array.length;

                    for(int i$ = 0; i$ < len$; ++i$) {
                        String part = arr$[i$];
                        var12 = str.indexOf(part);
                        if(var12 <= lastIndex) {
                            return false;
                        }

                        lastIndex = var12;
                    }

                    return true;
            }
        } else {
            return false;
        }
    }

    public static String replaceHtml(String html) {
        if(isBlank(html)) {
            return "";
        } else {
            String regEx = "<.+?>";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(html);
            String s = m.replaceAll("");
            return s;
        }
    }

    public static String abbr(String str, int length) {
        if(str == null) {
            return "";
        } else {
            try {
                StringBuilder e = new StringBuilder();
                int currentLength = 0;
                char[] arr$ = replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray();
                int len$ = arr$.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    char c = arr$[i$];
                    currentLength += String.valueOf(c).getBytes("GBK").length;
                    if(currentLength > length - 3) {
                        e.append("...");
                        break;
                    }

                    e.append(c);
                }

                return e.toString();
            } catch (UnsupportedEncodingException var8) {
                var8.printStackTrace();
                return "";
            }
        }
    }

    public static String rabbr(String str, int length) {
        return abbr(replaceHtml(str), length);
    }

    public static Double toDouble(Object val) {
        if(val == null) {
            return Double.valueOf(0.0D);
        } else {
            try {
                return Double.valueOf(trim(val.toString()));
            } catch (Exception var2) {
                return Double.valueOf(0.0D);
            }
        }
    }

    public static Float toFloat(Object val) {
        return Float.valueOf(toDouble(val).floatValue());
    }

    public static Long toLong(Object val) {
        return Long.valueOf(toDouble(val).longValue());
    }

    public static Integer toInteger(Object val) {
        return Integer.valueOf(toLong(val).intValue());
    }

    public static String toCamelCase(String s) {
        if(s == null) {
            return null;
        } else {
            s = s.toLowerCase();
            StringBuilder sb = new StringBuilder(s.length());
            boolean upperCase = false;

            for(int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                if(c == 95) {
                    upperCase = true;
                } else if(upperCase) {
                    sb.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        }
    }

    public static String toCapitalizeCamelCase(String s) {
        if(s == null) {
            return null;
        } else {
            s = toCamelCase(s);
            return s.substring(0, 1).toUpperCase() + s.substring(1);
        }
    }

    public static String toUnderScoreCase(String s) {
        if(s == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean upperCase = false;

            for(int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                boolean nextUpperCase = true;
                if(i < s.length() - 1) {
                    nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
                }

                if(i > 0 && Character.isUpperCase(c)) {
                    if(!upperCase || !nextUpperCase) {
                        sb.append('_');
                    }

                    upperCase = true;
                } else {
                    upperCase = false;
                }

                sb.append(Character.toLowerCase(c));
            }

            return sb.toString();
        }
    }

    public static void setValueIfNotBlank(String target, String source) {
        if(isNotBlank(source)) {
            ;
        }

    }

    public static String jsGetVal(String objectString) {
        StringBuilder result = new StringBuilder();
        StringBuilder val = new StringBuilder();
        String[] vals = split(objectString, ".");

        for(int i = 0; i < vals.length; ++i) {
            val.append("." + vals[i]);
            result.append("!" + val.substring(1) + "?\'\':");
        }

        result.append(val.substring(1));
        return result.toString();
    }
}
