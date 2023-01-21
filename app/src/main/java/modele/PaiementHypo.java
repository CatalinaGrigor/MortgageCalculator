package modele;

public class PaiementHypo {
    private double taux;
    private int ans;
    private double hypo;
    private double paimMens;
    private double paimTotal;
    private double interetTotal;

    public PaiementHypo() {
    }

    public PaiementHypo(int id, double taux, double hypo, int ans, double paimMens, double paimTotal, double interetTotal) {
        this.taux = taux;
        this.hypo = hypo;
        this.ans = ans;
        this.paimMens = paimMens;
        this.paimTotal = paimTotal;
        this.interetTotal = interetTotal;
    }

    public PaiementHypo(Double taux, Double hypo, int ans, Double paimMens, Double paimTotal, Double interetTotal) {
        this.taux = taux;
        this.hypo = hypo;
        this.ans = ans;
        this.paimMens = paimMens;
        this.paimTotal = paimTotal;
        this.interetTotal = interetTotal;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }

    public double getHypo() {
        return hypo;
    }

    public void setHypo(double hypo) {
        this.hypo = hypo;
    }

    public double getPaimMens() {
        return paimMens;
    }

    public void setPaimMens(double paimMens) {
        this.paimMens = paimMens;
    }

    public double getPaimTotal() {
        return paimTotal;
    }

    public void setPaimTotal(double paimTotal) {
        this.paimTotal = paimTotal;
    }

    public double getInteretTotal() {
        return interetTotal;
    }

    public void setInteretTotal(double interetTotal) {
        this.interetTotal = interetTotal;
    }

    @Override
    public String toString() {
        return "PaiementHypo{" +
                "taux=" + taux +
                ", ans=" + ans +
                ", hypo=" + hypo +
                ", paimMens=" + paimMens +
                ", paimTotal=" + paimTotal +
                ", interetTotal=" + interetTotal +
                '}';
    }
}
