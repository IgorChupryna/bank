package ua.chup.tranz;


import java.util.List;

public class TranzactionService {


	private static TranzactionDao tranzactionDao;

	public TranzactionService() {
		tranzactionDao = new TranzactionDao();
	}

	public void persist(Tranzaction entity) {
		tranzactionDao.openCurrentSessionwithTransaction();
		tranzactionDao.persist(entity);
		tranzactionDao.closeCurrentSessionwithTransaction();
	}

	public void update(Tranzaction entity) {
		tranzactionDao.openCurrentSessionwithTransaction();
		tranzactionDao.update(entity);
		tranzactionDao.closeCurrentSessionwithTransaction();
	}

	public Tranzaction findById(String id) {
		tranzactionDao.openCurrentSession();
		Tranzaction tranzaction = tranzactionDao.findById(id);
		tranzactionDao.closeCurrentSession();
		return tranzaction;
	}

	public void delete(String id) {
		tranzactionDao.openCurrentSessionwithTransaction();
		Tranzaction tranzaction = tranzactionDao.findById(id);
		tranzactionDao.delete(tranzaction);
		tranzactionDao.closeCurrentSessionwithTransaction();
	}

	public List<Tranzaction> findAll() {
		tranzactionDao.openCurrentSession();
		List<Tranzaction> tranzaction = tranzactionDao.findAll();
		tranzactionDao.closeCurrentSession();
		return tranzaction;
	}

	public void deleteAll() {
		tranzactionDao.openCurrentSessionwithTransaction();
		tranzactionDao.deleteAll();
		tranzactionDao.closeCurrentSessionwithTransaction();
	}

	public TranzactionDao bookDao() {
		return tranzactionDao;
	}
}
