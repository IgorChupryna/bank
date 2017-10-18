package ua.chup.tranz;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ua.chup.dao.DaoInterface;

import java.util.List;


public class TranzactionDao implements DaoInterface<Tranzaction, String> {

	private Session currentSession;

	private Transaction currentTransaction;

	public TranzactionDao() {
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

	public void persist(Tranzaction entity) {
		getCurrentSession().save(entity);
	}

	public void update(Tranzaction entity) {
		getCurrentSession().update(entity);
	}

	public Tranzaction findById(String id) {
		Tranzaction tranzaction = (Tranzaction) getCurrentSession().get(Tranzaction.class, id);
		return tranzaction;
	}

	public void delete(Tranzaction entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Tranzaction> findAll() {
		List<Tranzaction> tranzaction = (List<ua.chup.tranz.Tranzaction>) getCurrentSession().createQuery("from Tranzaction").list();
		return tranzaction;
	}

	public void deleteAll() {
		List<Tranzaction> entityList = findAll();
		for (Tranzaction entity : entityList) {
			delete(entity);
		}
	}

}
