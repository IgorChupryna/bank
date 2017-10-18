package ua.chup.type;



import java.util.List;

public class TypeService {


	private static TypeDao typeDao;

	public TypeService() {
		typeDao = new TypeDao();
	}

	public void persist(Type entity) {
		typeDao.openCurrentSessionwithTransaction();
		typeDao.persist(entity);
		typeDao.closeCurrentSessionwithTransaction();
	}

	public void update(Type entity) {
		typeDao.openCurrentSessionwithTransaction();
		typeDao.update(entity);
		typeDao.closeCurrentSessionwithTransaction();
	}

	public Type findById(String id) {
		typeDao.openCurrentSession();
		Type type = typeDao.findById(id);
		typeDao.closeCurrentSession();
		return type;
	}

	public void delete(String id) {
		typeDao.openCurrentSessionwithTransaction();
		Type type = typeDao.findById(id);
		typeDao.delete(type);
		typeDao.closeCurrentSessionwithTransaction();
	}

	public List<Type> findAll() {
		typeDao.openCurrentSession();
		List<Type> type = typeDao.findAll();
		typeDao.closeCurrentSession();
		return type;
	}

	public void deleteAll() {
		typeDao.openCurrentSessionwithTransaction();
		typeDao.deleteAll();
		typeDao.closeCurrentSessionwithTransaction();
	}

	public TypeDao typeDao() {
		return typeDao;
	}
}
