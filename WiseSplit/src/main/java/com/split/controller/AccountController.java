package com.split.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.split.bean.AccountBean;
import com.split.bean.AddRequestBean;
import com.split.bean.ExpenditureBean;
import com.split.bean.RequestBean;
import com.split.bean.ResponseBean;
import com.split.dao.AccountDao;
import com.split.entity.Account;
import com.split.helper.SplitWiseHelper;
import com.split.service.AccountService;
import com.split.util.LoggerExt;

import io.swagger.annotations.ApiOperation;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;

	@Autowired
	LoggerExt logger;

	@Autowired
	AccountDao accountDao;
	
	@Autowired
	SplitWiseHelper splitWiseHelper;

	
	@ApiOperation(value="Add Money", notes="Adds Money to account", httpMethod = "POST")
	@RequestMapping(value = "v1/addMoney", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> addMoney(@RequestBody AddRequestBean addRequestBean) {

		ResponseBean obj = new ResponseBean();
		System.out.println("in controller");

		System.out.println("Amount : " + addRequestBean.getAmount());
		System.out.println("Comment : " + addRequestBean.getComment());
		System.out.println("UserId : " + addRequestBean.getUserId());

		try {

			if (addRequestBean.getUserId() != 0) {
				accountService.addAmount(addRequestBean);
			}
		} catch (Exception ex) {
			logger.debug("AccountController", "Exceptio occured in AccountController Class");
		}

		obj.setStatus("MoneyAddedSuccessfully");

		return new ResponseEntity<ResponseBean>(obj, HttpStatus.OK);

		/*
		 * Account obj = new Account();
		 * 
		 * user objj = new user();
		 * 
		 * objj.setEmailId("User1@gmail.com"); objj.setFirstName("User1");
		 * objj.setLastName("User1");
		 * 
		 * obj.setAmount(2000); obj.setComment("SecondComment");
		 * obj.setIsactive(BigDecimal.ONE);
		 * obj.setLastupdateddate(CommonUtil.getSystemDate()); obj.setUser(objj);
		 * 
		 * AaccountDao.saveAccount(obj);
		 */

	}

	@ApiOperation(value="get Calculation", notes="Calculaates individual expenditure", httpMethod = "GET")
	@RequestMapping(value = "/v1/calculation", method = RequestMethod.GET)
	public ResponseEntity<List<AccountBean>> monthlyCalculation() {

		List<Account> newList;
		newList = accountDao.getResult();

		List<AccountBean> list = null;
		AccountBean bean = null;

		for (Account itr : newList) {
			bean = new AccountBean();
			bean.setAmount(itr.getAmount());
			//bean.setComment(itr.getComment());
			System.out.println("from controller " + bean.getAmount());

		}
		list.add(bean);

		return new ResponseEntity<List<AccountBean>>(list, HttpStatus.OK);
	}

	@ApiOperation(value="Calculation Account", notes="Calculation Account", httpMethod = "GET")
	@RequestMapping(value = "v1/calculationAccount", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> monthlyCalculationAccount() {

		ExpenditureBean expenditureBean = null;
		List<Object> newList;

		newList = accountDao.getAccount();

		return new ResponseEntity<>(newList, HttpStatus.OK);
	}

	// ErrorEndpoint

	/*
	 * // error =={ "timestamp": "2018-08-08T17:26:43.630+0000", "status": 500,
	 * "error": "Internal Server Error", "message":
	 * "[Ljava.lang.Object; cannot be cast to com.split.bean.AccountBean", "path":
	 * "/v1/calculationAccount" }
	 */

	/*
	 * @RequestMapping(value = "v1/calculationAccount", method = RequestMethod.GET)
	 * public ResponseEntity<List<CalculationBean>> monthlyCalculationAccount() {
	 * 
	 * List<AccountBean> newList; newList = accountDao.getAccount();
	 * 
	 * List<CalculationBean> list = new ArrayList<>(); CalculationBean bean = null;
	 * 
	 * for (AccountBean itr : newList) { bean = new CalculationBean();
	 * bean.setAmount(itr.getAmount()); bean.setComment(itr.getComment());
	 * System.out.println("from controllleer" +bean.getAmount()); list.add(bean); }
	 * 
	 * return new ResponseEntity<List<CalculationBean>>(list, HttpStatus.OK); }
	 */

	@ApiOperation(value="Calculation Account By Date", notes="Calculation account by Date", httpMethod = "POST")
	@RequestMapping(value = "v1/calculationAccountByDate", method = RequestMethod.POST)
	public List<AccountBean> monthlyCalculationAccountByDate(@RequestBody RequestBean requestBean) {

		List<AccountBean> newList;
		newList = accountService.getAccountByDate(requestBean);

		List<AccountBean> list = new ArrayList<>();
		AccountBean bean = null;

		for (AccountBean itr : newList) {
			bean = new AccountBean();
			bean.setAmount(itr.getAmount());
			//bean.setComment(itr.getComment());
			list.add(bean);
		}

		return newList;
	}

	@ApiOperation(value="expenditure Calculation", notes="Expenditure Calculation", httpMethod = "GET")
	@RequestMapping(value = "v1/expenditureCalculations", method = RequestMethod.GET)
	public ResponseEntity<List<AccountBean>> monthlyExpenditureCalculation() {

		ExpenditureBean expenditureBean = null;
		List<AccountBean> accBeanList = null;
		//List<Object> newList;
		
		//List<AccountBean> accBeanList = new ArrayList<>();
		//AccountBean accountBean = null;
		long avgSum;
		long totalSum = 0;
		int count = 0;
		long money;

		/*newList = accountDao.getAccount();
		
		for(Object itr :newList) {
			accountBean =new  AccountBean();
			Object[] row = (Object[]) itr;
			
			accountBean.setAmount(Long.parseLong(String.valueOf((row[0]))));
			accountBean.setUserId(Integer.parseInt(String.valueOf((row[1]))));
			accountBean.setUserName(String.valueOf((row[2])));
			accBeanList.add(accountBean);
			
		}
		*/
		accBeanList =splitWiseHelper.getTotalExpenditure();
		
		
		/*for(AccountBean acc : accBeanList) {
			totalSum = totalSum + acc.getAmount();
			count++;
			
		}
		avgSum = totalSum/count;
		
		System.out.println(avgSum);
		
		for(AccountBean accBean : accBeanList) {
			
			money = avgSum - accBean.getAmount();
			
			if(money < 0) {
				System.out.println(accBean.getUserName() + " " + " will get" +Math.abs(money));
			}
			else if(money > 0) {
				System.out.println(accBean.getUserName() + " " + " will pay" +Math.abs(money));
			}
			else {
				System.out.println(accBean.getUserName() + " " + " will not get or pay money");
			}
			
			
		}
		*/
		
		
		
		
		return new ResponseEntity<>(accBeanList, HttpStatus.OK);
	}
	
	private static void printResult(Object result) {
	    if (result == null) {
	      System.out.print("NULL");
	    } else if (result instanceof Object[]) {
	      Object[] row = (Object[]) result;
	     // System.out.print("[");
	      for (int i = 0; i < row.length; i++) {
	        //printResult(row[i]);
	        System.out.println("row of  value" +row[i]);
	      }
	     // System.out.print("]");
	    } /*else if (result instanceof Long || result instanceof Double
	        || result instanceof String) {
	      System.out.print(result.getClass().getName() + ": " + result);
	    } */else {
	      System.out.print(result);
	    }
	    System.out.println();
	  }
	
	
	@ApiOperation(value="Amount Transfer Calculation", notes="Amount Cvcalulation tranfer", httpMethod = "GET")
	@RequestMapping(value = "v1/moneyTransfer", method = RequestMethod.GET)
	public ResponseEntity<List<String>> monthlyMoneyTransfer() {

		/*ExpenditureBean expenditureBean = null;
		List<AccountBean> accBeanList = null;*/
		List<String> newList;
		
		//List<AccountBean> accBeanList = new ArrayList<>();
		//AccountBean accountBean = null;
		/*long avgSum;
		long totalSum = 0;
		int count = 0;
		long money;*/

		/*newList = accountDao.getAccount();
		
		for(Object itr :newList) {
			accountBean =new  AccountBean();
			Object[] row = (Object[]) itr;
			
			accountBean.setAmount(Long.parseLong(String.valueOf((row[0]))));
			accountBean.setUserId(Integer.parseInt(String.valueOf((row[1]))));
			accountBean.setUserName(String.valueOf((row[2])));
			accBeanList.add(accountBean);
			
		}
		*/
		newList = splitWiseHelper.monthlyMoneyTransfer();
		
		
		/*for(AccountBean acc : accBeanList) {
			totalSum = totalSum + acc.getAmount();
			count++;
			
		}
		avgSum = totalSum/count;
		
		System.out.println(avgSum);
		
		for(AccountBean accBean : accBeanList) {
			
			money = avgSum - accBean.getAmount();
			
			if(money < 0) {
				System.out.println(accBean.getUserName() + " " + " will get" +Math.abs(money));
			}
			else if(money > 0) {
				System.out.println(accBean.getUserName() + " " + " will pay" +Math.abs(money));
			}
			else {
				System.out.println(accBean.getUserName() + " " + " will not get or pay money");
			}
			
			
		}
		*/
		
		return new ResponseEntity<>(newList, HttpStatus.OK);
	}
	
	
	@ApiOperation(value="Money From And To", notes="Money From and to", httpMethod = "GET")
	@RequestMapping(value = "v1/fromAndTo", method = RequestMethod.GET)
	public ResponseEntity<List<String>> fromAndTo() {
		 
		List<String> resultList = splitWiseHelper.toAndFromHelper();
		
		return new ResponseEntity<>(resultList, HttpStatus.OK);

		
	}
	

}
