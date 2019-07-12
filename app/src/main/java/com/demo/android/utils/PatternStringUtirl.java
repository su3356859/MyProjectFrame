package com.demo.android.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具�?
 * @author JKRM-tlj
 *
 */
public class PatternStringUtirl {
	public static final String MOBILE_TESTTWO = "^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9])|(14[0-9]))\\d{8}$";
	public static final Pattern MOBILE_PATTERN = Pattern.compile(MOBILE_TESTTWO);

	/**
	 * 座机号验�?
	 * @param phoneNumber
	 * @return
	 */
	public static boolean isPhoneNumberValid(String phoneNumber) {
		boolean isValid = false;

		String expression = "((^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
		CharSequence inputStr = phoneNumber;

		Pattern pattern = Pattern.compile(expression);

		Matcher matcher = pattern.matcher(inputStr);

		if (matcher.matches()) {
			isValid = true;
		}

		return isValid;

	}

	/**
	 * 手机号码验证
	 */
	public static boolean isMobileNum(String mobiles) {
		return MOBILE_PATTERN.matcher(mobiles).matches();
	}
	
	/**
	 * 密码格式验证
	 * @param password
	 * @return
	 */
	public static boolean isPassWordNO(String password) {
		Pattern p = Pattern
				.compile("^((?=.*[0-9].*)(?=.*[A-Za-z].*))[_0-9A-Za-z]{6,30}$");
		Matcher m = p.matcher(password);
		return m.matches();

	}

	/**
	 * 邮箱验证
	 * 
	 * @param email
	 * @return Boolean
	 */
	public static boolean emailFormat(String email) {
		boolean tag = true;
		final String pattern1 = "^([a-z0-9A-Z]+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);

		return m.matches();
	}

	// 注册密码pwd不能包含中文
	public static boolean isContainChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	public static boolean isNUm(String str) {
		boolean isNum = str.matches("[0-9]+");
		return isNum;
	}

	public static boolean isLetter(String s) {
		char c = s.charAt(0);
		int i = (int) c;
		if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * IDcard身份证号验证
	 * 
	 * @param IDStr
	 * @return
	 * @throws ParseException
	 */
	public static boolean IDCardValidate(String IDStr) throws ParseException {
	  boolean flag = false;
//		String errorInfo = "";// 记录错误信息
//		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
//				"3", "2" };
//		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
//				"9", "10", "5", "8", "4", "2" };
		String Ai = "";
		// 号码的长�?15位或18�?
		if (IDStr.length() != 15 && IDStr.length() != 18) {
//			errorInfo = "身份证号码长度应该为15位或18位�?";
//			return errorInfo;
		  flag = false;
		}
		// 数字 除最后以为都为数�?
		if (IDStr.length() == 18) {
			Ai = IDStr.substring(0, 17);
		} else if (IDStr.length() == 15) {
			Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		}
		if (isNumeric(Ai) == false) {
//			errorInfo = "身份�?5位号码都应为数字 ; 18位号码除�?���?��外，都应为数字�?";
//			return errorInfo;
		  flag = false;
		}
		// 出生年月是否有效 
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份
		if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {
//			errorInfo = "身份证生日无效�?";
//			return errorInfo;
		  flag = false;
		}
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
				|| (gc.getTime().getTime() - s.parse(
						strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
//			errorInfo = "身份证生日不在有效范围�?";
//			return errorInfo;
		  flag = false;
		}
		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
//			errorInfo = "身份证月份无�?;
//			return errorInfo;
		  flag = false;
		}
		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
//			errorInfo = "身份证日期无�?;
//			return errorInfo;
		  flag = false;
		}
		// 地区码是否有�?
		Hashtable<String, String> h = GetAreaCode();
		if (h.get(Ai.substring(0, 2)) == null) {
//			errorInfo = "身份证地区编码错误�?";
//			return errorInfo;
		  flag = false;
		}
//		//  判断�?���?��的�? 
//		int TotalmulAiWi = 0;
//		for (int i = 0; i < 17; i++) { 
//			TotalmulAiWi =TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i)));
//			Integer.parseInt(Wi[i]);
//		} 
//		int modValue = TotalmulAiWi % 11; String
//		strVerifyCode = ValCodeArr[modValue]; Ai = Ai + strVerifyCode; 
//		if(IDStr.length() == 18) { 
//			if (Ai.equals(IDStr) == false) {
//				errorInfo ="身份证无效，不是合法的身份证号码"; 
//				return errorInfo; 
//			} 
//		} else {
//			return "";
//		}
		flag = true;
		return flag;
	}

	
	
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	//国内身份证省级地区码
	private static Hashtable<String, String> GetAreaCode() {
		Hashtable<String, String> hashtable = new Hashtable<String, String>();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	}

	//身份证号码内的生日验�?
	public static boolean isDataFormat(String str) {
		boolean flag = false;
		String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern pattern1 = Pattern.compile(regxStr);
		Matcher isNo = pattern1.matcher(str);
		if (isNo.matches()) {
			flag = true;
		}
		return flag;
	}
}
