package ua.chup.currency;

import ua.chup.dao.DaoInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class CorrencyDao implements DaoInterface<Corrency, String> {

	private Session currentSession;

	private Transaction currentTransaction;

	public CorrencyDao() {
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

	public void persist(Corrency entity) {
		getCurrentSession().save(entity);
	}

	public void update(Corrency entity) {
		getCurrentSession().update(entity);
	}

	public Corrency findById(String id) {
		Corrency corrency = (Corrency) getCurrentSession().get(Corrency.class, id);
		return corrency;
	}

	public void delete(Corrency entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Corrency> findAll() {
		List<Corrency> Corrency = (List<ua.chup.currency.Corrency>) getCurrentSession().createQuery("from Corrency").list();
		return Corrency;
	}

	public void deleteAll() {
		List<Corrency> entityList = findAll();
		for (Corrency entity : entityList) {
			delete(entity);
		}
	}

}
