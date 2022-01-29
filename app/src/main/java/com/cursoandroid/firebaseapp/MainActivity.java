package com.cursoandroid.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private ImageView imgFoto;
    private Button btnUpload;
    //private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    //private FirebaseAuth usuario = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgFoto = findViewById(R.id.imgFoto);
        btnUpload = findViewById(R.id.btnUpload);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imgFoto.setDrawingCacheEnabled(true);
                imgFoto.buildDrawingCache();
                Bitmap bitmap = imgFoto.getDrawingCache();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);

                byte[] dadosImagem = baos.toByteArray();

                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens");
                StorageReference imagemRef = imagens.child("celular.jpeg");

                //carregar imagem
                Glide.with(MainActivity.this).using(new FirebaseImageLoader()).load(imagemRef).into(imgFoto);

                //String nomeArquivo = UUID.randomUUID().toString();
                //final StorageReference imagemRef = imagens.child(nomeArquivo+".jpeg");

                /*
                //Deletar imagem
                imagemRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Remoção da imagem falhou "+ e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Sucesso ao deletar imagem", Toast.LENGTH_LONG).show();
                    }
                });

                 */
/*

                //Fazer upload da imagem
                UploadTask uploadTask = imagemRef.putBytes(dadosImagem);

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Upload da imagem falhou "+ e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri url = taskSnapshot.getDownloadUrl();
                        /*imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Uri url = task.getResult();
                            }
                        });
                         */

                        /*Toast.makeText(MainActivity.this, "Upload da imagem feito "+url.toString(), Toast.LENGTH_LONG).show();
                    }
                });

                         */
            }
        });

        // Trabalhando com Realtime Database
        //DatabaseReference usuarios = referencia.child("usuarios");
        //DatabaseReference usuarioPesquisa = usuarios.child("-Mua5e_45FCLfGGLumCi");
        //Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Fernando");
        //Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(2);

        //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(40);
        //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(18).endAt(40);
        /*Query usuarioPesquisa = usuarios.orderByChild("nome").startAt("F").endAt("F"+"\uf8ff");


        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Usuario dadosUsuario = dataSnapshot.getValue(Usuario.class);
                //Log.i("Dados usuario: ", "Nome: "+dadosUsuario.getNome()+"  Sobrenome: "+dadosUsuario.getSobrenome());
                Log.i("Dados usuario: ", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        // Gerar identificador unico


        Usuario usuario = new Usuario();
        usuario.setNome("Fabiana");
        usuario.setSobrenome("Vilas Boas");
        usuario.setIdade(41);
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
