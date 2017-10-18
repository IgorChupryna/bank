package ua.chup.responce;



import java.util.List;

public class ResponceService {


	private static ResponceDao responceDao;

	public ResponceService() {
		responceDao = new ResponceDao();
	}

	public void persist(Responce entity) {
		responceDao.openCurrentSessionwithTransaction();
		responceDao.persist(entity);
		responceDao.closeCurrentSessionwithTransaction();
	}

	public void update(Responce entity) {
		responceDao.openCurrentSessionwithTransaction();
		responceDao.update(entity);
		responceDao.closeCurrentSessionwithTransaction();
	}

	public Responce findById(String id) {
		responceDao.openCurrentSession();
		Responce responce = responceDao.findById(id);
		responceDao.closeCurrentSession();
		return responce;
	}

	public void delete(String id) {
		responceDao.openCurrentSessionwithTransaction();
		Responce responce = responceDao.findById(id);
		responceDao.delete(responce);
		responceDao.closeCurrentSessionwithTransaction();
	}

	public List<Responce> findAll() {
		responceDao.openCurrentSession();
		List<Responce> responce = responceDao.findAll();
		responceDao.closeCurrentSession();
		return responce;
	}

	public void deleteAll() {
		responceDao.openCurrentSessionwithTransaction();
		responceDao.deleteAll();
		responceDao.closeCurrentSessionwithTransaction();
	}

	public ResponceDao responceDao() {
		return responceDao;
	}
}
