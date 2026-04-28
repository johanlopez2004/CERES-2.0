
package com.example.opcv.view.gardens;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.opcv.R;

public class CompostCalculatorActivity extends AppCompatActivity {

    EditText inputKg;
    Button btnCalcular;
    TextView resultRelleno, resultCompost, resultEvitado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compost_calculator);

        inputKg = findViewById(R.id.inputKg);
        btnCalcular = findViewById(R.id.btnCalcular);
        resultRelleno = findViewById(R.id.resultRelleno);
        resultCompost = findViewById(R.id.resultCompost);
        resultEvitado = findViewById(R.id.resultEvitado);

        ImageButton btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            finish();
        });
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String kgText = inputKg.getText().toString();

                if (kgText.isEmpty()) {
                    inputKg.setError("Ingresa un valor");
                    return;
                }

                double kg = Double.parseDouble(kgText);


                double relleno = kg * 0.0519 * 28;

                double compostCH4 = kg * 0.004 * 28;
                double compostN2O = kg * 0.00024 * 265;
                double compost = compostCH4 + compostN2O;

                double evitado = relleno - compost;


                resultRelleno.setText("Relleno: " + String.format("%.3f", relleno) + " kg CO2e");
                resultCompost.setText("Compost: " + String.format("%.3f", compost) + " kg CO2e");
                resultEvitado.setText("Evitado: " + String.format("%.3f", evitado) + " kg CO2e");
            }
        });
    }
}