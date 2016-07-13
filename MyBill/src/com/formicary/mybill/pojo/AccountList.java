package com.formicary.mybill.pojo;

import java.util.ArrayList;
import java.util.List;

public class AccountList {
	List<Account> accountres;	
	List<AccountGroup> group;
	List<List<AccountChild>> child;
	
	public AccountList(List<Account> a) {
		accountres = a;
		group = new ArrayList<AccountGroup>();
		child = new ArrayList<List<AccountChild>>();
		dataChange();
	}

	public List<Account> getAccountres() {
		return accountres;
	}

	public void setAccountres(List<Account> accountres) {
		this.accountres = accountres;
	}

	public List<AccountGroup> getGroup() {
		return group;
	}

	public List<List<AccountChild>> getChild() {
		return child;
	}
	
	private void dataChange() {
		int size = accountres.size();
		if(size > 0) {
			AccountGroup tg = new AccountGroup(accountres.get(0));
			List<AccountChild> tc = new ArrayList<AccountChild>();
			tc.add(new AccountChild(accountres.get(0)));
			for(int i = 1; i < size; i++) {
				if(accountres.get(i).getMonth().equals(tg.getMonth())) {
					tg.changeData(accountres.get(i));
					tc.add(new AccountChild(accountres.get(i)));
				} else {
					group.add(tg);
					child.add(tc);
					tg = new AccountGroup(accountres.get(i));
					tc = new ArrayList<AccountChild>();
				}
			}
			group.add(tg);
			child.add(tc);
		}
	}
	
}
