package com.example.examenfinalcatalina;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import modele.DbAdapter;
import modele.PaiementHypo;

public class MainActivity extends AppCompatActivity {
    private EditText txtTaux, txtAns, txtHypo;
    private Button  btnVerif;
    private TextView txtTauxR, txtHypoR, txtPaimMens, txtMontantTotal, txtTotalInteret;
    private DbAdapter dbAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWidgets();
        setListeners();
        dbAdapter=new DbAdapter(MainActivity.this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int option=item.getItemId();
        switch (option){
            case R.id.calculer:
                calculer();
                break;

            case R.id.effacer:
                reset();

        }
        return super.onOptionsItemSelected(item);
    }

    private void setListeners() {
        btnVerif.setOnClickListener(mClickListener);
    }
    private View.OnClickListener mClickListener=new View.OnClickListener(){
        @Override
        public void onClick(View v){

                Intent monIntent=new Intent(MainActivity.this, ListingActivity.class);
                startActivity(monIntent);

        }
    };

    private void calculer() {

        double taux = 0;
        int ans = 0;
        double hypo = 0;

        boolean isError = false;
        try {
            taux = Double.parseDouble(txtTaux.getText().toString());
            ans = parseInt(txtAns.getText().toString());
            hypo = Double.parseDouble(txtHypo.getText().toString());
        }
        catch(Exception e) {
            showAlertError(R.string.altChampVide,txtTaux.getId());
            isError = true;
        }

       if ( taux <= 0.0||taux>50) {
            showAlertError(R.string.altTauxMauvais, txtTaux.getId());
            isError = true;
        }
        if(ans<=0.0||ans>30)  {
            showAlertError(R.string.altAnsMauvais,txtTaux.getId());
            isError = true;
        }
        if(hypo<=0.0){
            showAlertError(R.string.altHypoMauvais,txtTaux.getId());
            isError = true;
        }

        Double paimMens, paimTotal, interetTotal;
        Double tim;

        if (!isError) {

            tim=(taux/100)/12;
            paimMens = (tim * hypo) / (1- ( 1/Math.pow((1+ tim),(12 * ans))));
            paimTotal=paimMens*12*ans;
            interetTotal=paimTotal-hypo;

            afficherResultat(paimMens, paimTotal,interetTotal);
            insererBD(taux,ans, hypo, paimMens,paimTotal,interetTotal);

        }
    }

    private void insererBD(Double taux,int ans, Double hypo, Double paimMens, Double paimTotal, Double interetTotal) {

        dbAdapter.ajouterPaiementHypo(new PaiementHypo(taux,hypo,ans,paimMens,paimTotal,interetTotal));
    }


    private void showAlertError(int megId, final int fieldId) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.altTitreErreur)
                .setMessage(megId)
                .setNeutralButton("close",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                findViewById(fieldId).requestFocus();

                            }
                        }).show();

    }


    private  void afficherResultat(Double paimMens, Double paimTotal, Double totalInteret){
        txtPaimMens.setText(paimMens.toString());
        txtMontantTotal.setText(paimTotal.toString());
        txtTotalInteret.setText(totalInteret.toString());
        txtTauxR.setText(txtTaux.getText().toString());
        txtHypoR.setText(txtHypo.getText().toString());
    }



    private void reset() {
        txtTaux.setText("");
        txtAns.setText("");
        txtHypo.setText("");
        txtTauxR.setText("");
        txtHypoR.setText("");
        txtPaimMens.setText("");
        txtMontantTotal.setText("");
        txtTotalInteret.setText("");
        txtTaux.requestFocus();
    }


    private void setWidgets() {
        // access the activity widgets by R.id
        //Edit
        txtTaux = (EditText) findViewById(R.id.txtTaux);
        txtHypo = (EditText) findViewById(R.id.txtHypo);
        txtAns = (EditText) findViewById(R.id.txtAns);
        txtTaux.requestFocus();// request focus for taux

        btnVerif = (Button) findViewById(R.id.btnVerif);


        // items for display
        txtTauxR = (TextView) findViewById(R.id.txtTauxR);
        txtHypoR = (TextView) findViewById(R.id.txtHypoR);
        txtPaimMens = (TextView) findViewById(R.id.txtPaimMens);
        txtMontantTotal = (TextView) findViewById(R.id.txtMontantTotal);
        txtTotalInteret = (TextView) findViewById(R.id.txtTotalInteret);

    }
}