package com.example.cryptography;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViginerCipher extends AppCompatActivity {
    Button crypted, decrypted,button;
    EditText clearText, key,encrytText;
String newKey;
    String message ,key1;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viginer_cipher);
        crypted=(Button)findViewById(R.id.button15);
        decrypted=(Button)findViewById(R.id.button16);
        button = (Button) findViewById(R.id.button17);
        clearText=(EditText) findViewById(R.id.edit_VGClearText);
        key=(EditText)findViewById(R.id.editText7);
        encrytText=(EditText)findViewById((R.id.editText6));

crypted.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        message=clearText.getText().toString();
        key1=key.getText().toString();
        if(crypted.isClickable()) {
            newKey = keyGenerate(message, key1);
            result = Encrypt(message,newKey);
        }
    encrytText.setText(result);
    }


});

        decrypted.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v) {
            message = encrytText.getText().toString();
            key1 = key.getText().toString();
            if (crypted.isClickable()) {
                newKey = keyGenerate(message, key1);
                result = Decrypt(message, newKey);
                                           }
            clearText.setText(result);
                                       }

        });


button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);

            }
        });


    }


    public static char[] Extented = {0x00C7, 0x00FC, 0x00E9, 0x00E2,
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
        if (code >= 0x80 && code <= 0xFF) {
            return Extented[code - 128];
        }
        return (char) code;
    }



    public static final char printChar(int i) {
        return getAscii(i);
    }

    public static int TextExtended(char j){
        for (int i = 0; i < Extented.length; i++) {
            if(j == Extented[i]){
                return (i+128);
            }
        }
        return (char)j;
    }



    public static String keyGenerate(String message, String key1) {
        int x = message.length();

        for (int i = 0; ; i++)
        {
            if (x == i)
                i = 0;
            if (key1.length() == message.length())
                break;
            key1+=(key1.charAt(i));
        }
        return key1;


    }

    public static String Encrypt(String message, String newKey) {

        char[]d = message.toCharArray();
        for(int i = 0;i<d.length;i++) {

            if(d[i]+newKey.charAt(i)>32 && d[i]+newKey.charAt(i)<=127) {
                d[i] = (char) ((d[i]+newKey.charAt(i))%256);
            }
            else if(d[i]+newKey.charAt(i)>=128&&d[i]+newKey.charAt(i)<=256) {
                d[i]=getAscii (d[i]+newKey.charAt(i));
            }
            else if(d[i]>=128 && d[i]<=256) {
                d[i]= getAscii(((TextExtended(d[i]) + newKey.charAt(i))));

            }

            else {
                d[i]=getAscii((d[i] +TextExtended(newKey.charAt(i))));
            }
        }

        return String.valueOf(d);

    }

    public static String Decrypt(String message, String newKey)
    {


        char[]d = message.toCharArray();
        for(int i = 0;i<d.length;i++) {

            if(d[i]>32&&d[i]<=127) {
                d[i] = (char) ((d[i]-newKey.charAt(i)+256)%256);
            }
            else if(d[i]>=128&&d[i]<=256) {
                d[i]=(char) ((TextExtended(d[i])-newKey.charAt(i)+256)%256) ;
            }


            else if(d[i]+newKey.charAt(i)>256){
                d[i]=getAscii((TextExtended(d[i])-TextExtended(newKey.charAt(i)) +256)%256) ;
            }
            else if(d[i]-newKey.charAt(i)>=128&&d[i]-newKey.charAt(i)<=256) {
                d[i]=getAscii(d[i]-newKey.charAt(i));
            }
            else if(d[i]-newKey.charAt(i)<0){

                System.out.println("invalid");
            }
        }
        return String.valueOf(d);
    }

}
