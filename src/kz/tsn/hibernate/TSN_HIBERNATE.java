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

        CurrencyCode userRole1 = new CurrencyCode();
        userRole1.setCostInTenge("R114");
        userRole1.setCostInRub("125");
        session.save(userRole1);

        CurrencyCode userRole2 = new CurrencyCode();
        userRole2.setCostInTenge("admin");
        userRole2.setCostInRub("77,12144");
        session.save(userRole2);

        org.hibernate.Transaction tr = session.beginTransaction();
        session.delete(userRole1);
//        session.delete(userRole2);
        tr.commit();

        Currency user = new Currency();
        user.setCountryCount("1,2,3,4");
        user.setCost("Test12");
        user.setName("Test1");
        user.setCountry("1234");
        user.setCurrencyCode(new CurrencyCode("hibernate2", "7,7,7"));
        session.save(user);

        user = (Currency) session.get(Currency.class, user.getId());
        user.setCountryCount("777");
        session.save(user);

        List<Currency> resultsUser = session.createQuery(criteriaUser).getResultList();
        resultsUser.forEach((item) -> {
            System.out.println(item);
        });

        List<CurrencyCode> resultsUserRole = session.createQuery(criteriaUserRole).getResultList();
        resultsUserRole.forEach((item) -> {
            System.out.println(item);
        });

        session.close();

        System.exit(0);
    }

}
