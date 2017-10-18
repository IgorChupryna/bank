package ua.chup.account;


import java.util.List;

public class AccountService {


	private static AccountDao accountDao;

	public AccountService() {
		accountDao = new AccountDao();
	}

	public void persist(Account entity) {
		accountDao.openCurrentSessionwithTransaction();
		accountDao.persist(entity);
		accountDao.closeCurrentSessionwithTransaction();
	}

	public void update(Account entity) {
		accountDao.openCurrentSessionwithTransaction();
		accountDao.update(entity);
		accountDao.closeCurrentSessionwithTransaction();
	}

	public Account findById(String id) {
		accountDao.openCurrentSession();
		Account account = accountDao.findById(id);
		accountDao.closeCurrentSession();
		return account;
	}

	public void delete(String id) {
		accountDao.openCurrentSessionwithTransaction();
		Account account = accountDao.findById(id);
		accountDao.delete(account);
		accountDao.closeCurrentSessionwithTransaction();
	}

	public List<Account> findAll() {
		accountDao.openCurrentSession();
		List<Account> account = accountDao.findAll();
		accountDao.closeCurrentSession();
		return account;
	}

	public void deleteAll() {
		accountDao.openCurrentSessionwithTransaction();
		accountDao.deleteAll();
		accountDao.closeCurrentSessionwithTransaction();
	}

	public AccountDao bookDao() {
		return accountDao;
	}
}