package com.example.trabalho1bimandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome, editTextEmail, editTextIdade, editTextDisciplina, editTextNota1, editTextNota2;
    private Button buttonEnviar, buttonLimpar;
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Certifique-se de que está chamando o layout correto

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextIdade = findViewById(R.id.editTextIdade);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextNota1 = findViewById(R.id.editTextNota1);
        editTextNota2 = findViewById(R.id.editTextNota2);
        buttonEnviar = findViewById(R.id.buttonEnviar);
        buttonLimpar = findViewById(R.id.buttonLimpar);
        textViewResultado = findViewById(R.id.textViewResultado);

        // Configurando o evento de clique do botão "Enviar"
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampos();
            }
        });

        // Configurando o evento de clique do botão "Limpar"
        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });
    }

    private void validarCampos() {
        String nome = editTextNome.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String idadeStr = editTextIdade.getText().toString().trim();
        String disciplina = editTextDisciplina.getText().toString().trim();
        String nota1Str = editTextNota1.getText().toString().trim();
        String nota2Str = editTextNota2.getText().toString().trim();

        StringBuilder erros = new StringBuilder();
        double nota1=0;
        double nota2=0;
        // Validação do Nome
        if (nome.isEmpty()) {
            erros.append("O campo de nome está vazio.\n");
        }

        // Validação do Email
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            erros.append("O email é inválido.\n");
        }

        // Validação da Idade
        int idade;
        try {
            idade = Integer.parseInt(idadeStr);
            if (idade <= 0) {
                erros.append("A idade deve ser um número positivo.\n");
            }
        } catch (NumberFormatException e) {
            erros.append("A idade deve ser um número.\n");
        }

        // Validação da Disciplina
        if (disciplina.isEmpty()) {
            erros.append("O campo de disciplina está vazio.\n");
        }

        // Validação das Notas
        try {
            nota1 = Double.parseDouble(nota1Str);
            if (nota1 < 0 || nota1 > 10) {
                erros.append("Nota 1 deve estar entre 0 e 10.\n");
            }
        } catch (NumberFormatException e) {
            erros.append("Nota 1 deve ser um número.\n");
        }

        try {
            nota2 = Double.parseDouble(nota2Str);
            if (nota2 < 0 || nota2 > 10) {
                erros.append("Nota 2 deve estar entre 0 e 10.\n");
            }
        } catch (NumberFormatException e) {
            erros.append("Nota 2 deve ser um número.\n");
        }

        // Exibir erros ou prosseguir com o envio
        if (erros.length() > 0) {
            Toast.makeText(this, erros.toString(), Toast.LENGTH_LONG).show();
        } else {
            calcularMedia(nota1, nota2);
        }
    }

    private void calcularMedia(double nota1, double nota2) {
        double media = (nota1 + nota2) / 2;
        textViewResultado.setText("Média: " + media);
        textViewResultado.setVisibility(View.VISIBLE);

        // Mensagem de aprovação/reprovação
        if (media >= 6) {
            textViewResultado.append("\nStatus: Aprovado");
        } else {
            textViewResultado.append("\nStatus: Reprovado");
        }
    }

    private void limparCampos() {
        editTextNome.setText("");
        editTextEmail.setText("");
        editTextIdade.setText("");
        editTextDisciplina.setText("");
        editTextNota1.setText("");
        editTextNota2.setText("");
        textViewResultado.setText("");
        textViewResultado.setVisibility(View.GONE);
    }
}
