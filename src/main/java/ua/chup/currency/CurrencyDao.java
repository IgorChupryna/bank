package ua.chup.currency;

import ua.chup.dao.DaoInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class CurrencyDao implements DaoInterface<Currency, String> {

	private Session currentSession;

	private Transaction currentTransaction;

	public CurrencyDao() {
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

	public void persist(Currency entity) {
		getCurrentSession().save(entity);
	}

	public void update(Currency entity) {
		getCurrentSession().update(entity);
	}

	public Currency findById(String id) {
		Currency corrency = (Currency) getCurrentSession().get(Currency.class, id);
		return corrency;
	}

	public void delete(Currency entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Currency> findAll() {
		List<Currency> currency = (List<ua.chup.currency.Currency>) getCurrentSession().createQuery("from Currency").list();
		return currency;
	}

	public void deleteAll() {
		List<Currency> entityList = findAll();
		for (Currency entity : entityList) {
			delete(entity);
		}
	}

}
