package util;

/**
 * ��������
 * @author skywalker
 *
 */
public class CalendarUtil {
	
	//����һ
	private static final int WEEKINIT = 1;
	//�·���������
	private static int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	public static String getCalendar(int year, int month) {
		//����ӹ�ԪԪ�꿪ʼ����
		int days = (year - 1) * 365 + (year - 1) / 4 + (year - 1) / 400 - (year - 1) / 100;
		//�����Ԫ��һ�������ڼ�
		int yearWeek = WEEKINIT + days % 7;
		//���µĵ�һ�������ڼ�
		int monthWeek = yearWeek + getDays(year, month) % 7;
		//����������7����ȡ��
		if(monthWeek > 7) {
			monthWeek = monthWeek % 7;
		}
		//���
		StringBuilder result = new StringBuilder();
		result.append(" ------" + year + "��" + month + "��------\r\n");
		result.append(" Mo Tu We Th Fr Sa Su\r\n");
		//ǰ���ַ���
		StringBuilder preStr = new StringBuilder(" ");
		for(int i = 1;i < monthWeek;i ++) {
			preStr.append("   ");
		}
		preStr.deleteCharAt(preStr.length() - 1);
		result.append(preStr);
		for(int i = 0;i < monthDays[month - 1];i ++) {
			//�Ƿ���
			if((monthWeek + i) % 7 == 1) {
				result.append("\r\n");
			}
			//���
			String space = (i + 1 < 10) ? "  " : " ";
			result.append(space + (i + 1));
		}
		return result.toString();
	}
	
	/**
	 * ���㵱ǰ�µ�Ԫ��һ�յ�����
	 */
	private static int getDays(int year, int month) {
		int days = 0;
		if(month >= 2) {
			for(int i = 0;i < month - 1;i ++) {
				//����Ƕ���
				if(i == 1) {
					if(isLeapYear(year)) {
						//���������
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
	 * �ж��Ƿ�������
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
