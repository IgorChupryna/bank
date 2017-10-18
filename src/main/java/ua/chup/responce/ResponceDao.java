package ua.chup.responce;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ua.chup.dao.DaoInterface;

import java.util.List;


public class ResponceDao implements DaoInterface<Responce, String> {

	private Session currentSession;

	private Transaction currentTransaction;

	public ResponceDao() {
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

	public void persist(Responce entity) {
		getCurrentSession().save(entity);
	}

	public void update(Responce entity) {
		getCurrentSession().update(entity);
	}

	public Responce findById(String id) {
		Responce type = (Responce) getCurrentSession().get(Responce.class, id);
		return type;
	}

	public void delete(Responce entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Responce> findAll() {
		List<Responce> Responce = (List<ua.chup.responce.Responce>) getCurrentSession().createQuery("from Responce").list();
		return Responce;
	}

	public void deleteAll() {
		List<Responce> entityList = findAll();
		for (Responce entity : entityList) {
			delete(entity);
		}
	}

}
