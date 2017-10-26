package ua.chup.currency;



import org.hibernate.Query;

import javax.persistence.TypedQuery;
import java.util.List;

public class CurrencyService implements CurrencyServiceInterface {


	private static CurrencyDao correncyDao;

	public CurrencyService() {
		correncyDao = new CurrencyDao();
	}

	public void persist(Currency entity) {
		correncyDao.openCurrentSessionwithTransaction();
		correncyDao.persist(entity);
		correncyDao.closeCurrentSessionwithTransaction();
	}

	public void update(Currency entity) {
		correncyDao.openCurrentSessionwithTransaction();
		correncyDao.update(entity);
		correncyDao.closeCurrentSessionwithTransaction();
	}

	public Currency findById(String id) {
		correncyDao.openCurrentSession();
		Currency corrency = correncyDao.findById(id);
		correncyDao.closeCurrentSession();
		return corrency;
	}

	public void delete(String id) {
		correncyDao.openCurrentSessionwithTransaction();
		Currency corrency = correncyDao.findById(id);
		correncyDao.delete(corrency);
		correncyDao.closeCurrentSessionwithTransaction();
	}

	public List<Currency> findAll() {
		correncyDao.openCurrentSession();
		List<Currency> corrency = correncyDao.findAll();
		correncyDao.closeCurrentSession();
		return corrency;
	}

	public void deleteAll() {
		correncyDao.openCurrentSessionwithTransaction();
		correncyDao.deleteAll();
		correncyDao.closeCurrentSessionwithTransaction();
	}

	public CurrencyDao bookDao() {
		return correncyDao;
	}

	@Override
	public Currency getValueCurr(String tranCur, String accCur) {
		correncyDao.openCurrentSession();
		List<Currency> currency = (List<Currency>)correncyDao.getCurrentSession().createQuery("FROM Currency  WHERE typeIn = :typeIn AND typeOut = :typeOut")
		.setParameter("typeIn",tranCur )
		.setParameter("typeOut", accCur).list();
		Currency c = currency.size()==1?currency.get(0):null;
		if (c==null) throw new MoreThanOneCurrency();
		correncyDao.closeCurrentSession();
		return c;
	}
}
