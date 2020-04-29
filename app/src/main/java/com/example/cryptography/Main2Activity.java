package com.example.cryptography;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {
    Button Crypted, Decrypted, Back;
    EditText ClearText, EncryptedText, Key;

    String newString, InputString;
    int key1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ClearText = (EditText) findViewById(R.id.edit_ClearText);
        EncryptedText = (EditText) findViewById(R.id.editText4);
        Key = (EditText) findViewById(R.id.editText3);
        Crypted = (Button) findViewById(R.id.button9);
        Decrypted = (Button) findViewById(R.id.button10);
        Back = (Button) findViewById(R.id.button11);

        Crypted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key1 = Integer.parseInt(Key.getText().toString());
                InputString = ClearText.getText().toString();
                if (Crypted.isClickable()) {
                    newString = Encrypt(InputString, key1);

                }

                EncryptedText.setText(newString.toUpperCase());
            }
        });

        Decrypted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key1 = Integer.parseInt(Key.getText().toString());
                InputString = EncryptedText.getText().toString();
                if (Decrypted.isClickable()) {
                    newString = Decrypt(InputString, key1);

                }

                ClearText.setText(newString.toUpperCase());
            }
        });
    Back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivity(intent);

        }
    });


    }

    public static char[]Extend ={0x00C7, 0x00FC, 0x00E9, 0x00E2,
            0x00E4, 0x00E0, 0x00E5, 0x00E7, 0x00EA, 0x00EB, 0x00E8, 0x00EF,
            0x00EE, 0x00EC, 0x00C4, 0x00C5, 0x00C9, 0x00E6, 0x00C6, 0x00F4,
            0x00F6, 0x00F2, 0x00FB, 0x00F9, 0x00FF, 0x00D6, 0x00DC, 0x00A2,
            0x00A3, 0x00A5, 0x20A7, 0x0192, 0x00E1, 0x00ED, 0x00F3, 0x00FA,
            0x00F1, 0x00D1, 0x00AA, 0x00BA, 0x00BF, 0x2310, 0x00AC, 0x00BD,
            0x00BC, 0x00A1, 0x00AB, 0x00BB, 0x2591, 0x2592, 0x2593, 0x2502,
            0x2524, 0x2561, 0x2562, 0x2556, 0x2555, 0x2563, 0x2551, 0x2557,
            0x255D, 0x255C, 0x255B, 0x2510, 0x2514, 0x2534, 0x252C, 0x251C,
            0x2500, 0x253C, 0x255E, 0x255F, 0x255A, 0x2554, 0x2569, 0x2566,
            0x2560, 0x2550, 0x256C, 0x2567, 0x2568, 0x2564, 0x2565, 0x2559,
            0x2558, 0x2552, 0x2553, 0x256B, 0x256A, 0x2518, 0x250C, 0x2588,
            0x2584, 0x258C, 0x2590, 0x2580, 0x03B1, 0x00DF, 0x0393, 0x03C0,
            0x03A3, 0x03C3, 0x00B5, 0x03C4, 0x03A6, 0x0398, 0x03A9, 0x03B4,
            0x221E, 0x03C6, 0x03B5, 0x2229, 0x2261, 0x00B1, 0x2265, 0x2264,
            0x2320, 0x2321, 0x00F7, 0x2248, 0x00B0, 0x2219, 0x00B7, 0x221A,
            0x207F, 0x00B2, 0x25A0, 0x00A0 };

    public static final char getAscii(int code) {
        for(int i = 0;i<Extend.length;i++)
            if (code >= 0x80 && code <= 0xFF) {

                return Extend[code-128];
            }
        return (char)code;
    }

    public static int CharEncrypteExtended(char j){

        for (int i = 0; i < Extend.length; i++) {

            if(j == Extend[i]){
                return Extend[i+3];

            }
        }
        return (char)j;
    }
    public static int CharDecrypteExtended(char f){


        for (int i = 0; i < Extend.length; i++) {

            if(f == Extend[0]){
                return (char)(128-3);
            }

            else if(f==Extend[i]) {
                return Extend[i-3 + 256 % 256] ;
            }

            else if(f==Extend[1]) {

                return (char)(129-3);
            }
        }

        return (char)f;

    }

    public static int code(char d) {

        for(int i = 3; i>=0; i--) {
            if(d==128)
                d = Extend[0];
            if(d==129)
                d = Extend[1];
        }

        return(char) d;
    }

        public static String Encrypt(String InputString, int key1) {
            char [] d= InputString.toCharArray();
            for( int i = 0; i<d.length;i++) {

                if(d[i]>32 && d[i]<=127) {

                    d[i]=(char)((d[i]+ key1));

                    if(d[i]>=128 && d[i]<=256) {
                        d[i]= (char) CharEncrypteExtended((d[i]));

                        if(d[i]+key1>=128) {
                            d[i]= (char)code(d[i]);
                        }

                    }
                }
            }
            return String.valueOf(d);
    }


    public static String Decrypt(String InputString, int key1) {
        char[] c = InputString.toCharArray();
        for(int i = 0; i < c.length; i++){

            if(c[i]>32&&c[i]<=127) {
                c[i] = (char) ((c[i] + 256 - key1 % 256) % 256);
            }

            else {
                c[i]=  (char) (((char) CharDecrypteExtended(c[i])));
            }
        }
        return String.valueOf(c);
    }
        }



