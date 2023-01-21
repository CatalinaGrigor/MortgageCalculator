package modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PaiementHypoDbHelper extends SQLiteOpenHelper {

    //declaration de constante
    public static  final String BD_Nom="bdeb";
    public static final int Version=1;

    //Tables
    public  static final String TABLE_PAIMENTHYPO="paiementhypo";
    public  static final String COL_ID="_id";
    public  static final String COL_TAUX="taux";
    public  static final String COL_HYPO="hypo";
    public  static final String COL_ANS="ans";
    public  static final String COL_PAIMMENS="paimmens";
    public  static final String COL_PAIMTOTAL="paimtotal";
    public  static final String COL_INTERETTOTAL="interettotal";

    //DDL de table


    public static final String DDL_PERSONNE = "create table " + TABLE_PAIMENTHYPO + "(" + COL_ID +
            "	integer primary key autoincrement," + COL_TAUX + " DOUBLE," + COL_HYPO + " DOUBLE,"
            + COL_ANS +" Integer," + COL_PAIMMENS + " DOUBLE," + COL_PAIMTOTAL + " DOUBLE," +
            COL_INTERETTOTAL + " DOUBLE"+")";

    public PaiementHypoDbHelper(@Nullable Context context, @Nullable String BD_Nom,
                                @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, BD_Nom, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PAIMENTHYPO);
        sqLiteDatabase.execSQL(DDL_PERSONNE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PAIMENTHYPO);
        onCreate(sqLiteDatabase);
    }
}
