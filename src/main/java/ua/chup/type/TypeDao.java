package ua.chup.type;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ua.chup.dao.DaoInterface;

import java.util.List;


public class TypeDao implements DaoInterface<Type, String> {

	private Session currentSession;

	private Transaction currentTransaction;

	public TypeDao() {
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

	public void persist(Type entity) {
		getCurrentSession().save(entity);
	}

	public void update(Type entity) {
		getCurrentSession().update(entity);
	}

	public Type findById(String id) {
		Type type = (Type) getCurrentSession().get(Type.class, id);
		return type;
	}

	public void delete(Type entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Type> findAll() {
		List<Type> Type = (List<ua.chup.type.Type>) getCurrentSession().createQuery("from Type").list();
		return Type;
	}

	public void deleteAll() {
		List<Type> entityList = findAll();
		for (Type entity : entityList) {
			delete(entity);
		}
	}

}
