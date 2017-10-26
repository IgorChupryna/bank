package ua.chup.currency;

import ua.chup.dao.BaseDao;

public interface CurrencyServiceInterface<C extends BaseDao> {
    public C getValueCurr(String tranCur,String accCur);
}
