package com.example.bd;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bd.dao.AlunoDAO;
import com.example.bd.model.Aluno;
import com.google.firebase.inappmessaging.model.Button;

public class MainActivity extends AppCompatActivity {

    private EditText edtId, edtNome, edtTelefone, edtCpf, edtListar;
    private Button btnLimpar, btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtNome = findViewById(R.id.edtNome);
        edtId = findViewById(R.id.edtId);
        edtCpf = findViewById(R.id.edtCpf);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtListar = findViewById(R.id.edtListar);

    }


    public void limpar(View view){
        edtId.setText(null);
        edtNome.setText(null);
        edtTelefone.setText(null);
        edtCpf.setText(null);
        edtListar.setText(null);
    }

    public void salvar( View view ){
        Aluno a = new Aluno();
        a.setNome(edtNome.getText().toString());
        a.setCpf(edtCpf.getText().toString());
        a.setTelefone(edtTelefone.getText().toString());
        AlunoDAO dao = new AlunoDAO(this);
        long id = dao.insert(a);
        Toast.makeText(getApplicationContext(),
                "Salvo com Sucesso "+id,
                Toast.LENGTH_LONG).show();

    }

}