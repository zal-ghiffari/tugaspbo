package com.mngsoft.viginerezal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class Enkripsi extends AppCompatActivity {

    private EditText pesan;
    private EditText kunci;
    private Button tombolenkripsi;
    private EditText tekssandi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enkripsi);

        pesan = findViewById(R.id.pesan);
        kunci = findViewById(R.id.kunci);
        tombolenkripsi = findViewById(R.id.tombol_enkripsi);
        tekssandi = findViewById(R.id.teks_sandi);
    }

    public void halamanmenu(View v){
        Intent i = new Intent(Enkripsi.this, MainActivity.class);
        startActivity(i);
    }

    public void encryptOrDecryptUsingKeyphraseOnClick(View view) {

        String shiftedString;

        if (view.getId() == R.id.tombol_enkripsi) {

            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            shiftedString = null;
            String textFromCipher = this.pesan.getText().toString();
            String keyphraseFromCipher = this.kunci.getText().toString();

            if (!checkForEmptyInvalidInput(textFromCipher, keyphraseFromCipher)) {
                shiftedString = this.encryptAlgorithm(textFromCipher, keyphraseFromCipher);
                this.tekssandi.setText(shiftedString.toString());
            }
        }
    }

    private String encryptAlgorithm(String text, String keyphrase) {

        keyphrase = keyphrase.toUpperCase();
        StringBuilder sb = new StringBuilder(100);

        for (int i = 0, j = 0; i < text.length(); i++) {

            char upper = text.toUpperCase().charAt(i);
            char orig = text.charAt(i);

            if (Character.isAlphabetic(orig)) {
                if (Character.isUpperCase(orig)) {
                    sb.append((char)((upper + keyphrase.charAt(j) - 130) % 26 + 65));
                    ++j;
                    j %= keyphrase.length();
                } else {
                    sb.append(Character.toLowerCase((char)((upper + keyphrase.charAt(j) - 130) % 26 + 65)));
                    ++j;
                    j %= keyphrase.length();
                }
            } else {
                sb.append(orig);
            }
        }
        return sb.toString();
    }

    private boolean checkForEmptyInvalidInput(String text, String keyphrase) {

        boolean isError = false;

        if (!text.matches(".*[a-zA-Z]+.*")) {
            this.pesan.setError("Tidak ada yang bisa di enkripsi/dekripsi");
            isError = true;
        }

        if (null == keyphrase || keyphrase.isEmpty()) {
            this.kunci.setError("Kunci tidak boleh kosong");
            isError = true;
        }

        boolean valid = this.checkIfKeyphraseValid(keyphrase);
        if (!valid) {
            this.kunci.setError("Kunci haruf huruf semua, tidak boleh angka");
            isError = true;
        }
        return isError;
    }


    private boolean checkIfKeyphraseValid(String keyphrase) {

        boolean valid = true;

        for(int z = 0; z < keyphrase.length(); ++z) {
            char c = keyphrase.charAt(z);
            if (!Character.isAlphabetic(c)) {
                valid = false;
                break;
            }
        }
        return valid;
    }
}
