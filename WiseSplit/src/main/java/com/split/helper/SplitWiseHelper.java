package com.split.helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.split.bean.AccountBean;
import com.split.dao.AccountDao;

@Component
public class SplitWiseHelper {

	@Autowired
	AccountDao accountDao;

	public List<AccountBean> getTotalExpenditure() {

		List<Object> newList;
		AccountBean accountBean = null;
		List<AccountBean> accBeanList = new ArrayList<>();

		newList = accountDao.getAccount();

		for (Object itr : newList) {
			accountBean = new AccountBean();
			Object[] row = (Object[]) itr;

			accountBean.setAmount(Long.parseLong(String.valueOf((row[0]))));
			accountBean.setUserId(Integer.parseInt(String.valueOf((row[1]))));
			accountBean.setUserName(String.valueOf((row[2])));
			accBeanList.add(accountBean);

		}
		return accBeanList;

	}

	public List<String> monthlyMoneyTransfer() {

		List<String> expenditureList = new ArrayList<>();
		AccountBean accountBean = null;
		long avgSum;
		long totalSum = 0;
		int count = 0;
		long money;
		List<AccountBean> accBeanList = new ArrayList<>();
		String expenditure = null;

		accBeanList = this.getTotalExpenditure();

		for (AccountBean acc : accBeanList) {
			totalSum = totalSum + acc.getAmount();
			count++;

		}
		avgSum = totalSum / count;

		System.out.println(avgSum);

		for (AccountBean accBean : accBeanList) {
			expenditure = new String();
			money = avgSum - accBean.getAmount();

			if (money < 0) {
				expenditure = accBean.getUserName() + " " + " will get" + Math.abs(money);
				System.out.println(accBean.getUserName() + " " + " will get   " + Math.abs(money));
			} else if (money > 0) {
				expenditure = accBean.getUserName() + " " + " will pay" + Math.abs(money);
				System.out.println(accBean.getUserName() + " " + " will pay  " + Math.abs(money));
			} else {
				expenditure = (accBean.getUserName() + " " + " will not get or pay money");
				System.out.println(accBean.getUserName() + " " + " will not get or pay money");
			}
			expenditureList.add(expenditure);

		}

		return expenditureList;

	}
	
	
	

	public List<String> toAndFromHelper(){
		
		Map<String , Long> transMap = new HashMap<String,Long>();
		
		long avgSum;
		long totalSum = 0;
		int count = 0;
		long money;
		List<AccountBean> accBeanList = new ArrayList<>();
		String expenditure;

		accBeanList = this.getTotalExpenditure();

		for (AccountBean acc : accBeanList) {
			totalSum = totalSum + acc.getAmount();
			count++;

		}
		avgSum = totalSum / count;
		
		for (AccountBean accBean : accBeanList) {
			expenditure = new String();
			money = accBean.getAmount() - avgSum;
			
			transMap.put(accBean.getUserName(), money);
			
		}
		
		
		for(Map.Entry<String, Long> itr : transMap.entrySet()) {
			System.out.println("Key" +itr.getKey() +  "   "  + "value" +"   "+itr.getValue());
		}
		
		
		
		LinkedHashMap<String, Long> reverseSortedMap = new LinkedHashMap<>();
		transMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
		
		
		System.out.println("sorted");
		for(Map.Entry itr : reverseSortedMap.entrySet()) {
			
		System.out.println(itr.getKey()  + "    " +itr.getValue());
		
		
	}
		
		int n = reverseSortedMap.size();
		int size = 2 * n;
		String[] sArray = new String[size];

		int count1 = 0;
		for (Entry<String, Long> itr : reverseSortedMap.entrySet()) {

			sArray[count1] = itr.getKey();
			count1++;
			sArray[count1] = String.valueOf(itr.getValue());
			count1++;

			// System.out.println(count);

		}

		String cal = null;

		List<String> resultList = new ArrayList<>();
		for (int i = 1; i < sArray.length; i = i + 2) {

			// if negative then will pay
			for (int j = sArray.length - 1; j > 2; j = j - 2) {

				if (null != sArray[i] && null != sArray[j]  && Integer.parseInt(sArray[j]) < 0) {

					long amount = Long.parseLong(sArray[i]) - Math.abs(Long.parseLong(sArray[j]));

					if (amount == 0) {

						cal = "User  " + sArray[j - 1] + "  will pay  " + sArray[i] + "   to  " + sArray[i - 1] + " ";
						sArray[i] = null;
						sArray[j] = null;

						System.out.println(cal);
						resultList.add(cal);
					}

					if (amount > 0) {

						sArray[i] = String.valueOf(amount);

						cal = "User  " + sArray[j - 1] + "  will pay  " + Math.abs(Long.parseLong(sArray[j])) + "   to  " + sArray[i - 1] + " ";
						sArray[j] = null;
						System.out.println(cal);
						resultList.add(cal);

					}

					if (amount < 0) {

						sArray[j] = String.valueOf(amount);

						cal = "User  " + sArray[j - 1] + "  will pay  " + Math.abs(Long.parseLong(sArray[i])) + "   to  " + sArray[i - 1] + " ";
						sArray[i] = null;
						System.out.println(cal);
						resultList.add(cal);

					}

				}

			}
			
			
		}
		
		return resultList;
	
	

}
}
