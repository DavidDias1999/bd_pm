package com.example.bd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.bd.model.Aluno;
import com.example.bd.util.ConnectionFactory;

public class AlunoDAO {
    private ConnectionFactory conexao;
    private SQLiteDatabase banco;
    public AlunoDAO(Context context){
        //ConnectionFactory com o banco de dados
        conexao = new ConnectionFactory(context,"banco.db",null, 1);
        banco = conexao.getWritableDatabase();
    }
    // método inserir
    public long insert(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("cpf", aluno.getCpf());
        values.put("telefone", aluno.getTelefone());
        return(banco.insert("aluno", null, values));
    }


}
