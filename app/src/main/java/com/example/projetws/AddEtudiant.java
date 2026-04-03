package com.example.projetws;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddEtudiant extends AppCompatActivity {

    EditText nom, prenom;
    Spinner ville;
    RadioButton m, f;
    Button btnAjouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_etudiant);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        ville = findViewById(R.id.ville);
        m = findViewById(R.id.m);
        f = findViewById(R.id.f);

        // ✔️ نفس ID لي ف XML
        btnAjouter = findViewById(R.id.add);

        btnAjouter.setOnClickListener(v -> ajouterEtudiant());
    }

    private void ajouterEtudiant() {

        String n = nom.getText().toString();
        String p = prenom.getText().toString();
        String vll = ville.getSelectedItem().toString();

        final String sexe;
        if (m.isChecked()) {
            sexe = "Homme";
        } else {
            sexe = "Femme";
        }

        if (n.isEmpty() || p.isEmpty()) {
            Toast.makeText(this, "Remplir les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        String URL = "http://10.0.2.2/projet/ws/createEtudiant.php";

        StringRequest request = new StringRequest(Request.Method.POST, URL,
                response -> Toast.makeText(AddEtudiant.this, "Ajout f MySQL ✅", Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(AddEtudiant.this, "Erreur ❌", Toast.LENGTH_SHORT).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nom", n);
                params.put("prenom", p);
                params.put("ville", vll);
                params.put("sexe", sexe);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}