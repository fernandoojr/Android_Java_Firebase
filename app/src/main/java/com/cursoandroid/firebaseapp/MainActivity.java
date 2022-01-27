package com.cursoandroid.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuario = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        // Gerar identificador unico
        DatabaseReference usuarios = referencia.child("usuarios");

        Usuario usuario = new Usuario();
        usuario.setNome("Luisa");
        usuario.setSobrenome("Oliveira");
        usuario.setIdade(18);
        usuarios.push().setValue(usuario);


        // Logar usuario

        usuario.signInWithEmailAndPassword("fernandofojr@gmail.com","fe12345")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.i("signIn", "Sucesso ao realizar login!");
                        }else{
                            Log.i("singIn", "Erro ao realizar login!");
                        }
                    }
                });

        // Deslogar usuario
        usuario.signOut();

        // Verifica usuario logado
        if(usuario.getCurrentUser() != null){
            Log.i("CurrentUser", "Usuario logado");
        }else{
            Log.i("CurrentUser", "Usuario nao logado");
        }

        // Cadastro de usuario
        usuario.createUserWithEmailAndPassword("fernandofojr@gmail.com","fe12345")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.i("CreateUser", "Sucesso ao cadastrar o usuário!");
                        }else{
                            Log.i("CreateUser", "Erro ao cadastrar o usuário!");
                        }
                    }
                });



        // Salvar dados no Firebase

        DatabaseReference produtos = referencia.child("produtos");
        DatabaseReference usuarios = referencia.child("usuarios");

        Usuario usuario = new Usuario();
        usuario.setNome("Marcia");
        usuario.setSobrenome("Santos");
        usuario.setIdade(43);
        usuarios.child("002").setValue(usuario);

        Produto produto = new Produto();
        produto.setDescricao("Iphone");
        produto.setMarca("Apple");
        produto.setPreco(3599.00);
        produtos.child("001").setValue(produto);



        // Recuperar dados
        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("FIREBASE", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
         */
    }
}
