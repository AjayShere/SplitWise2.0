package com.split.serviceImpl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.split.bean.AccountBean;
import com.split.bean.AddRequestBean;
import com.split.bean.RequestBean;
import com.split.dao.AccountDao;
import com.split.dao.UserDao;
import com.split.entity.Account;
import com.split.entity.user;
import com.split.service.AccountService;
import com.split.util.CommonUtil;

@Component
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao accountDao;

	@Autowired
	UserDao userDao;

	@Override
	public void addAmount(AddRequestBean addRequestBean) {

		
		System.out.println("In service " );
		Account account = new Account();
		if (addRequestBean.getAmount() != 0 && addRequestBean.getUserId() != 0) {

			user userObj = userDao.getUser(addRequestBean.getUserId());
			
			System.out.println("In service   UserObject"  +userObj.getFirstName());
			if (null != userObj) {
				account.setUser(userObj);
			}
			account.setAmount(addRequestBean.getAmount());
			account.setComment(addRequestBean.getComment());
			account.setIsactive(BigDecimal.ONE);
			account.setLastupdateddate(CommonUtil.getSystemDate());

			accountDao.saveAccount(account);

		}

	}

	@Override
	public void getResult() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AccountBean> getAccountByDate(RequestBean requestBean) {

		String[] array;
		List<AccountBean> accountList;
		Date todayDate = CommonUtil.getSystemDate();
		System.out.println("in service todaydate"     +todayDate);
		requestBean.setFromDate(todayDate);
		
		/*DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(todayDate);
		
		System.out.println("in service"  +strDate);

		System.out.println(strDate);
		array = strDate.split(" ");
		String dat = array[0];
		array = dat.split("-");

		String day = array[2];
		int res = Integer.parseInt(day) - requestBean.getLastDays();

		String fromDate = array[0] + "/" + array[1] + "/" + String.valueOf(res);
		System.out.println("in service  "  +fromDate);
		requestBean.setLastDays(res);
		try {
			Date frmDate = new SimpleDateFormat("yyyy/MM/dd").parse(fromDate);
			System.out.println("in service parsed frmdate" +frmDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(todayDate);
		cal.add(Calendar.DATE, -requestBean.getLastDays());
		
		Date fromDatee =cal.getTime();
		requestBean.setFromDate(fromDatee);
		accountList =accountDao.getAccountByDate(requestBean);

		/*
		 * Date date = Calendar.getInstance().getTime(); DateFormat dateFormat = new
		 * SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); String strDate =
		 * dateFormat.format(date);
		 * 
		 * System.out.println(strDate); String[] array = strDate.split(" ");
		 * 
		 * String dat = array[0]; String[] arr = dat.split("-");
		 * 
		 * String day = arr[2];
		 * 
		 * int res = Integer.parseInt(day) - 15;
		 * 
		 * System.out.println(res); String finalDate = arr[0] + "-" + arr[1] + "-" +
		 * String.valueOf(res); System.out.println(finalDate);
		 */

		return accountList;
	}

	/*
	 * public void getResult() { // TODO Auto-generated method stub
	 * 
	 * List<Account> list =accountDao.getAccount(); List<AddRequestBean>
	 * AddRequestBeanList =new ArrayList<>();
	 * 
	 * 
	 * for(Account list1 : list) {
	 * 
	 * 
	 * }
	 * 
	 * }
	 */
}
