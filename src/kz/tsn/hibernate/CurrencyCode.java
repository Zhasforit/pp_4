package kz.tsn.hibernate;

public class CurrencyCode {

    private Integer id;
    private Integer costInTenge;
    private Integer costInRub;

    public CurrencyCode() {
    }

    public CurrencyCode(Integer costInTenge, Integer costInRub) {
        this.costInTenge = costInTenge;
        this.costInRub = costInRub;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCostInTenge() {
        return costInTenge;
    }

    public void setCostInTenge(Integer costInTenge) {
        this.costInTenge = costInTenge;
    }

    public Integer getCostInRub() {
        return costInRub;
    }

    public void setCostInRub(Integer costInRub) {
        this.costInRub = costInRub;
    }

    @Override
    public String toString() {
        return String.format("CurrencyCode [id=%s, costInTenge=%s, costInRb=%s]", id, costInTenge, costInRub);
    }
}
