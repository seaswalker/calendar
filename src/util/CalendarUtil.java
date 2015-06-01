package util;

/**
 * 计算日历
 * @author skywalker
 *
 */
public class CalendarUtil {
	
	//星期一
	private static final int WEEKINIT = 1;
	//月分天数数组
	private static int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	public static String getCalendar(int year, int month) {
		//必须从公元元年开始计算
		int days = (year - 1) * 365 + (year - 1) / 4 + (year - 1) / 400 - (year - 1) / 100;
		//该年的元月一日是星期几
		int yearWeek = WEEKINIT + days % 7;
		//该月的第一天是星期几
		int monthWeek = yearWeek + getDays(year, month) % 7;
		//如果结果大于7，再取余
		if(monthWeek > 7) {
			monthWeek = monthWeek % 7;
		}
		//结果
		StringBuilder result = new StringBuilder();
		result.append(" ------" + year + "年" + month + "月------\r\n");
		result.append(" Mo Tu We Th Fr Sa Su\r\n");
		//前导字符串
		StringBuilder preStr = new StringBuilder(" ");
		for(int i = 1;i < monthWeek;i ++) {
			preStr.append("   ");
		}
		preStr.deleteCharAt(preStr.length() - 1);
		result.append(preStr);
		for(int i = 0;i < monthDays[month - 1];i ++) {
			//是否换行
			if((monthWeek + i) % 7 == 1) {
				result.append("\r\n");
			}
			//间距
			String space = (i + 1 < 10) ? "  " : " ";
			result.append(space + (i + 1));
		}
		return result.toString();
	}
	
	/**
	 * 计算当前月到元月一日的天数
	 */
	private static int getDays(int year, int month) {
		int days = 0;
		if(month >= 2) {
			for(int i = 0;i < month - 1;i ++) {
				//如果是二月
				if(i == 1) {
					if(isLeapYear(year)) {
						//如果是闰年
						days += 29;
						monthDays[1] = 29;
						continue;
					}
					days +=28;
				}else {
					days += monthDays[i];
				}
			}
		}
		return days;
	}
	
	/**
	 * 判断是否是闰年
	 */
	private static boolean isLeapYear(int year) {
		 if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			 return true;
		 }
		 return false;
	}
	
	public static void main(String[] args) {
		System.out.println(getCalendar(2015, 2));
	}
	
}
