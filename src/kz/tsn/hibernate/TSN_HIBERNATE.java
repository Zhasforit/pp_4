package kz.tsn.hibernate;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

public class TSN_HIBERNATE {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<CurrencyCode> criteriaCurrencyCode = cb.createQuery(CurrencyCode.class);
        Root<CurrencyCode> rootCurrencyCode = criteriaCurrencyCode.from(CurrencyCode.class);
        //criteriaUserRole.select(rootUserRole);
        criteriaCurrencyCode.select(rootCurrencyCode).where(cb.like(rootCurrencyCode.get("costInTenge"), "%%"));

        CriteriaQuery<Currency> criteriaUser = cb.createQuery(Currency.class);
        Root<Currency> rootUser = criteriaUser.from(Currency.class);
        Predicate[] predicates = new Predicate[3];
        predicates[0] = cb.like(rootUser.get("name"), "%UsdT%");
        predicates[1] = cb.like(rootUser.get("country"), "%Binance%");
        predicates[2] = cb.like(rootUser.get("currencyCode").get("costInTenge"), "%%");
        criteriaUser.select(rootUser).where(predicates);

        CurrencyCode currencyCode1 = new CurrencyCode();
        currencyCode1.setCostInTenge(440);
        currencyCode1.setCostInRub(65);
        session.save(currencyCode1);

        CurrencyCode currencyCode2 = new CurrencyCode();
        currencyCode2.setCostInTenge(430);
        currencyCode2.setCostInRub(60);
        session.save(currencyCode2);

        org.hibernate.Transaction tr = session.beginTransaction();
        session.delete(currencyCode1);
//        session.delete(currencyCode2);
        tr.commit();

        Currency user = new Currency();
        user.setCountryCount(5);
        user.setCost(1);
        user.setName("EUR");
        user.setCountry("Italy");
        user.setCurrencyCode(new CurrencyCode(500, 71));
        session.save(user);

        user = (Currency) session.get(Currency.class, user.getId());
        user.setCountryCount(30);
        session.save(user);

        List<Currency> resultsUser = session.createQuery(criteriaUser).getResultList();
        resultsUser.forEach((item) -> {
            System.out.println(item);
        });

        List<CurrencyCode> resultsCountryCode = session.createQuery(criteriaCurrencyCode).getResultList();
        resultsCountryCode.forEach((item) -> {
            System.out.println(item);
        });

        session.close();

        System.exit(0);
    }

}
