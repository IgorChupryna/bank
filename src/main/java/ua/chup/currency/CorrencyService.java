package ua.chup.currency;


import java.util.List;

public class CorrencyService {


	private static CorrencyDao correncyDao;

	public CorrencyService() {
		correncyDao = new CorrencyDao();
	}

	public void persist(Corrency entity) {
		correncyDao.openCurrentSessionwithTransaction();
		correncyDao.persist(entity);
		correncyDao.closeCurrentSessionwithTransaction();
	}

	public void update(Corrency entity) {
		correncyDao.openCurrentSessionwithTransaction();
		correncyDao.update(entity);
		correncyDao.closeCurrentSessionwithTransaction();
	}

	public Corrency findById(String id) {
		correncyDao.openCurrentSession();
		Corrency corrency = correncyDao.findById(id);
		correncyDao.closeCurrentSession();
		return corrency;
	}

	public void delete(String id) {
		correncyDao.openCurrentSessionwithTransaction();
		Corrency corrency = correncyDao.findById(id);
		correncyDao.delete(corrency);
		correncyDao.closeCurrentSessionwithTransaction();
	}

	public List<Corrency> findAll() {
		correncyDao.openCurrentSession();
		List<Corrency> corrency = correncyDao.findAll();
		correncyDao.closeCurrentSession();
		return corrency;
	}

	public void deleteAll() {
		correncyDao.openCurrentSessionwithTransaction();
		correncyDao.deleteAll();
		correncyDao.closeCurrentSessionwithTransaction();
	}

	public CorrencyDao bookDao() {
		return correncyDao;
	}
}
