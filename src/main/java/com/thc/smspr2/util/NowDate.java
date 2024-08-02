package com.thc.smspr2.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NowDate {
	public String[] getDateString(int minute, int second){
		String[] nowDateString = {"",""};

		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//현재시간
		nowDateString[0] = simpleDateFormat.format(nowDate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(nowDate);
		//시간 더하기
		cal.add(Calendar.MINUTE, minute);
		cal.add(Calendar.SECOND, second);
		nowDateString[1] = simpleDateFormat.format(cal.getTime());

		return nowDateString;
	}
	public String[] getDateStringByDate(int date){
		String[] nowDateString = {"",""};

		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//현재시간
		nowDateString[0] = simpleDateFormat.format(nowDate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(nowDate);
		//시간 더하기
		cal.add(Calendar.DATE, date);
		nowDateString[1] = simpleDateFormat.format(cal.getTime());

		return nowDateString;
	}
	public String[] getDateStringByMonth(int month){
		String[] nowDateString = {"",""};

		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//현재시간
		nowDateString[0] = simpleDateFormat.format(nowDate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(nowDate);
		//시간 더하기
		cal.add(Calendar.MONTH, month);
		nowDateString[1] = simpleDateFormat.format(cal.getTime());

		return nowDateString;
	}

	public String getExpiredTime(int day){
		String nowDateString = "";

		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Calendar cal = Calendar.getInstance();
		cal.setTime(nowDate);
		//시간 더하기
		cal.add(Calendar.DATE, day);
		nowDateString = simpleDateFormat.format(cal.getTime());

		return nowDateString;
	}
	public int getSecondDeference(String stringDate) throws ParseException {
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date paramDate = simpleDateFormat.parse(stringDate);

		long nowDateMil = nowDate.getTime();
		long paramDateMil = paramDate.getTime();

		// 비교
		long diff = paramDateMil - nowDateMil;
		long diffSec = diff / 1000;
		int returnVal = (int) Math.round(diffSec);

		return returnVal;
	}

	public String[] getDay(){
		String[] nowDateString = {"",""};

		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		//현재시간
		nowDateString[0] = simpleDateFormat.format(nowDate);
		nowDateString[1] = nowDate.getDay() + "";

		return nowDateString;
	}

	public int getAge(String birth) {
		Calendar current = Calendar.getInstance();
		if(birth.length() != 10){
			return 0;
		}

		int currentYear = current.get(Calendar.YEAR);
		int currentMonth = current.get(Calendar.MONTH) + 1;
		int currentDay = current.get(Calendar.DAY_OF_MONTH);

		// 만 나이 구하기 2022-1995=27 (현재년-태어난년)
		int age = currentYear - Integer.parseInt(birth.substring(0, 4));
		// 만약 생일이 지나지 않았으면 -1
		if (Integer.parseInt(birth.substring(5, 7)) * 100 + Integer.parseInt(birth.substring(8, 10)) > currentMonth * 100 + currentDay){
			age--;
		}
		return age;
	}

	public boolean getAdult(String birth){
		//String tbuserBirth = birth;
		NowDate nowdate = new NowDate();
		String now = nowdate.getDateString(0,0)[0].substring(0, 10);
		if(birth == null || (birth.length() != 10 && birth.split("-").length !=3)){
			birth = now;
		}
		String[] arrayBirth = birth.split("-");
		String[] arrayNow = now.split("-");

		int age = Integer.parseInt(arrayNow[0]) - Integer.parseInt(arrayBirth[0]);
		// 생일 안 지난 경우 -1
		if (Integer.parseInt(arrayBirth[1]) * 100 + Integer.parseInt(arrayBirth[2]) > Integer.parseInt(arrayNow[1]) * 100 + Integer.parseInt(arrayNow[2])) {
			age--;
		}
		return (age >= 19);
	}
}