package com.cursoandroid.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference usuarios = referencia.child("usuarios");
        Usuario usuario = new Usuario();
        usuario.setNome("Marcia");
        usuario.setSobrenome("Santos");
        usuario.setIdade(43);
        usuarios.child("002").setValue(usuario);


        DatabaseReference produtos = referencia.child("produtos");
        Produto produto = new Produto();
        produto.setDescricao("Iphone");
        produto.setMarca("Apple");
        produto.setPreco(3599.00);
        produtos.child("001").setValue(produto);
    }
}
