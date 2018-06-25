//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Mateus Augusto Miguel
//******************************************************

package com.github.mateusmiguel.cadastrei;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHelper dh;
    EditText et_nome, et_cpf, et_idade, et_tel, et_email;


    // Declarações
    Button btn_insert, btn_list, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.dh = new DBHelper(this);

        et_nome = (EditText) findViewById(R.id.et_nome);
        et_cpf =  (EditText) findViewById(R.id.et_cpf);
        et_idade =  (EditText) findViewById(R.id.et_idade);
        et_tel =  (EditText) findViewById(R.id.et_tel);
        et_email =  (EditText) findViewById(R.id.et_email);
        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_list = (Button) findViewById(R.id.btn_list);
        btn_back = (Button) findViewById(R.id.btn_back);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_nome.getText().length()>0 && et_cpf.getText().length()>0 && et_idade.getText().length()>0 && et_tel.getText().length()>0 && et_email.getText().length()>0){
                    dh.insert(et_nome.getText().toString(),et_cpf.getText().toString(),et_idade.getText().toString(),et_tel.getText().toString(),et_email.getText().toString());
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Sucesso!");
                    adb.setMessage("Cadastro realizado.");
                    adb.show();

                    et_nome.setText("");
                    et_cpf.setText("");
                    et_idade.setText("");
                    et_email.setText("");
                    et_tel.setText("");

                } else {
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Erro!");
                    adb.setMessage("Todos os campos devem ser preenchidos.");
                    adb.show();
                }
            }
        });

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List <PessoaFisica> pessoas = dh.queryGetAll();
                if (pessoas == null) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Mensagem");
                    adb.setMessage("Não há registros cadastradas.");
                    adb.show();
                    return;
                }
                for (int i=0; i< pessoas.size(); i++) {
                    PessoaFisica pessoafisica = (PessoaFisica) pessoas.get(i);
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Registro " + i );
                    adb.setMessage("Nome:" + pessoafisica.getNome() +"\nCPF:" + pessoafisica.getCpf() +"\nIdade: "+ pessoafisica.getIdade() +"\nTelefone: "+ pessoafisica.getTelefone() +"\nE-mail: " + pessoafisica.getEmail());
                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int witch) {
                            dialog.dismiss();
                        }
                    });
                    adb.show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaPrimeiraTela();
            }
        });
    }

    //Funções
    void chamaPrimeiraTela(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, FullscreenActivity.class);
        startActivity(intent);
        finish();
    }
}
