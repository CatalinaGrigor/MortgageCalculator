package modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class DbAdapter {
    private SQLiteDatabase dbase;
    private PaiementHypoDbHelper dbHelper;
    private Context context;

    public DbAdapter(Context context) {
        this.context = context;
        this.dbHelper = new PaiementHypoDbHelper(context, PaiementHypoDbHelper.BD_Nom,
                null, PaiementHypoDbHelper.Version);

    }

    public void openBd() {

        dbase = dbHelper.getWritableDatabase();
    }

    public void fermerBd(){

        dbase.close();
    }

    public void ajouterPaiementHypo(PaiementHypo paiementHypo){
        openBd();

        ContentValues cv=new ContentValues();
        cv.put(PaiementHypoDbHelper.COL_TAUX, paiementHypo.getTaux());
        cv.put(PaiementHypoDbHelper.COL_ANS, paiementHypo.getAns());
        cv.put(PaiementHypoDbHelper.COL_HYPO, paiementHypo.getHypo());
        cv.put(PaiementHypoDbHelper.COL_PAIMMENS, paiementHypo.getPaimMens());
        cv.put(PaiementHypoDbHelper.COL_PAIMTOTAL, paiementHypo.getPaimTotal());
        cv.put(PaiementHypoDbHelper.COL_INTERETTOTAL, paiementHypo.getInteretTotal());
        dbase.insert(PaiementHypoDbHelper.TABLE_PAIMENTHYPO, null, cv);
        Toast.makeText(context, "Ajout reussi", Toast.LENGTH_LONG).show();
        fermerBd();
    }

    public ArrayList<PaiementHypo> selectionnerHypo() {
        openBd();
        //indiquer les colonnes du select
        String[] cols = {PaiementHypoDbHelper.COL_ID, PaiementHypoDbHelper.COL_TAUX,
                PaiementHypoDbHelper.COL_ANS, PaiementHypoDbHelper.COL_HYPO,
                PaiementHypoDbHelper.COL_PAIMMENS,PaiementHypoDbHelper.COL_PAIMTOTAL,
                PaiementHypoDbHelper.COL_INTERETTOTAL};
        Cursor curseur=dbase.query(PaiementHypoDbHelper.TABLE_PAIMENTHYPO,cols,null, null, null, null, null);
        //parcourir le curseur
        curseur.moveToFirst();
        ArrayList<PaiementHypo> registre=new ArrayList<>();
        while (!curseur.isAfterLast()){
            int id =curseur.getInt(0);
            double taux= curseur.getDouble(1);
            int ans= curseur.getInt(2);
            double hypo= curseur.getDouble(3);
            double paimmens= curseur.getDouble(4);
            double paimtotal= curseur.getDouble(5);
            double interettotal= curseur.getDouble(6);
            registre.add(new PaiementHypo(id, taux, hypo, ans, paimmens, paimtotal,interettotal));
            curseur.moveToNext();
        }
        fermerBd();
        return registre;
    }
}
