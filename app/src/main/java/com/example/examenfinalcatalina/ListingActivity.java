package com.example.examenfinalcatalina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import modele.DbAdapter;
import modele.PaiementHypo;

public class ListingActivity extends AppCompatActivity {
    private DbAdapter dbAdapter;
    private ListView listingView;
    private ArrayAdapter<String> listingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        this.dbAdapter=new DbAdapter(ListingActivity.this);
        setWidgets();
        afficherData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menuverif) {
        getMenuInflater().inflate(R.menu.menuverif,menuverif);
        return super.onCreateOptionsMenu(menuverif);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int option=item.getItemId();
        switch (option){
            case R.id.home:
                voyagerHome();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void voyagerHome() {
        Intent monIntent = new Intent(ListingActivity.this, MainActivity.class);
        startActivity(monIntent);
    }

    private void afficherData(){
        ArrayList<PaiementHypo> registre=dbAdapter.selectionnerHypo();
        listingAdapter=new ArrayAdapter(ListingActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, registre);
        //   for (Personne personne:registre) {
        //    Toast.makeText(ListingActivity.this, personne.toString(),Toast.LENGTH_LONG).show();
        //  }
        listingView.setAdapter(listingAdapter);
    }

    private void setWidgets() {
        listingView=findViewById(R.id.listingView);

    }
}