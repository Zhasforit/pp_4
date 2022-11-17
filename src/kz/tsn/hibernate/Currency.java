package kz.tsn.hibernate;

public class Currency {

    private Integer id;
    private CurrencyCode currencyCode;
    private String name;
    private String country;
    private Integer cost;
    private String note;
    private String countryCount;

    public Currency() {
    }

    public Currency(Integer id) {
        this.id = id;
    }

    public Currency(Integer id, CurrencyCode currencyCode, String name) {
        this.id = id;
        this.currencyCode = currencyCode;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCountryCount() {
        return countryCount;
    }

    public void setCountryCount(String countryCount) {
        this.countryCount = countryCount;
    }

    public String toString() {
        return String.format("код-%d %s, название-%s", currencyCode.getId(), currencyCode.getCostInTenge(), name);
    }

}
