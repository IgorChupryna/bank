package ua.chup.account;

import ua.chup.dao.DaoInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class AccountDao implements DaoInterface<Account, String> {

	private Session currentSession;

	private Transaction currentTransaction;

	public AccountDao() {
	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public void persist(Account entity) {
		getCurrentSession().save(entity);
	}

	public void update(Account entity) {
		getCurrentSession().update(entity);
	}

	public Account findById(String id) {
		Account account = (Account) getCurrentSession().get(Account.class, Integer.parseInt(id));
		return account;
	}

	public void delete(Account entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Account> findAll() {
		List<Account> Account = (List<Account>) getCurrentSession().createQuery("from Account").list();
		return Account;
	}

	public void deleteAll() {
		List<Account> entityList = findAll();
		for (Account entity : entityList) {
			delete(entity);
		}
	}

}
