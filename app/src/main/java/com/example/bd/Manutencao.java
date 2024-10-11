package com.example.bd;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bd.dao.AlunoDAO;
import com.example.bd.model.Aluno;

public class Manutencao extends AppCompatActivity {
    private EditText edtNome;
    private EditText edtCpf;
    private EditText edtTelefone;
    private EditText edtId;
    private AlunoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manutencao);
        edtNome = findViewById(R.id.edtNome);
        edtCpf = findViewById(R.id.edtCpf);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtId = findViewById(R.id.edtId);

    }

    private boolean validarId() {
        String idStr = edtId.getText().toString();
        if (idStr.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor, insira um ID válido", Toast.LENGTH_SHORT).show();
            return false;
        }
        try {
            Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "ID deve ser um número", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void alterar(View view) {
        if (!validarId()) return;

        Aluno a = new Aluno();
        a.setId(Integer.parseInt(edtId.getText().toString()));
        a.setNome(edtNome.getText().toString());
        a.setCpf(edtCpf.getText().toString());
        a.setTelefone(edtTelefone.getText().toString());
        dao = new AlunoDAO(this);
        dao.update(a);
        Toast.makeText(getApplicationContext(), "Aluno alterado!", Toast.LENGTH_LONG).show();
    }

    public void consultar(View view) {
        if (!validarId()) return;

        dao = new AlunoDAO(this);
        Aluno a = dao.read(Integer.parseInt(edtId.getText().toString()));
        if (a != null) {
            edtNome.setText(a.getNome());
            edtCpf.setText(a.getCpf());
            edtTelefone.setText(a.getTelefone());
        } else {
            Toast.makeText(getApplicationContext(), "Aluno não encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    public void excluir(View view) {
        if (!validarId()) return;

        Aluno a = new Aluno();
        a.setId(Integer.parseInt(edtId.getText().toString()));
        dao = new AlunoDAO(this);
        dao.delete(a);
        Toast.makeText(getApplicationContext(), "Aluno excluído!", Toast.LENGTH_LONG).show();
    }

    public void voltar(View view) {
        finish();
    }
}
