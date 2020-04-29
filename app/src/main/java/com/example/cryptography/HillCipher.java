package com.example.cryptography;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HillCipher extends AppCompatActivity {
     private static final String TAG = "HillCipher";
    Button button;
    Button crypted, decrypted;
    EditText clearText, key,encrytText;
    String text ,Key1;
    String result;
    int[][] keyMatrix1;
    int[][] message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hill_cipher);
        button=(Button) findViewById(R.id.button20);
        crypted=(Button)findViewById(R.id.button18);
        decrypted=(Button)findViewById(R.id.button19);
        clearText=(EditText) findViewById(R.id.edit_HLClearText);
        key=(EditText)findViewById(R.id.editText9);
        encrytText=(EditText)findViewById((R.id.editText10));

        crypted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = clearText.getText().toString();
                Key1=key.getText().toString();
                if (crypted.isClickable()) {
                   result= HillEncryption(text,Key1);
                }
            encrytText.setText(result);
            }
        });
decrypted.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        text=encrytText.getText().toString();
            Key1=key.getText().toString();
            if (decrypted.isClickable()) {
                result =HillDecription(text,Key1);
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

    public static int TextExtended(char j){
        for (int i = 0; i < Extented.length; i++) {
            if(j == Extented[i]){
                return (i+128);
            }
        }
        return (char)j;
    }

    public static char getAscii(int code) {
        if (code >= 0x80 && code <= 0xFF) {
            return Extented[code - 128];
        }
        return (char) code;
    }
    public static String decTOHex(int a) {

        return String.format("\\x%2X",a);


    }
    static int[][] TextMatrix(String Text,int[][]key2){

        int len = 0;
        if( Text.length() % 2 != 0) {
            Text+= "0";
            len= 1;
        }
        int[][] message = new int[2][Text.length()];
        int a = 0;
        int n = 0;
        for(int i = 0; i< Text.length(); i++) {
            if(i%2== 0) {
                message[0][a]= ((int)Text.charAt(i)) ;
                a++;
                if(Text.charAt(i)>=128) {
                    message[0][a]=TextExtended(Text.charAt(i));
                }a++;


            }
            else {
                message[1][n]= ((int)Text.charAt(i)) ;
                n++;

            }
        }
        return message;

    }


    static int[][]KeyDecryptMatrix(String key, int[][] keymatrix){

        int[][] Key1 = new int[2][2];
        int c = 0;
        for( int i = 0; i<2; i++) {
            for(int j = 0; j< 2; j++) {
                Key1[i][j] = (int)key.charAt(c)%256;
                c++;

            }
        }
        int determinant = Key1[0][0] * Key1[1][1] - Key1[0][1] * Key1[1][0] ;
        determinant = moduloFunc(determinant);


        int inverse = -1;
        for( int i = 0; i< 26 ; i++) {
            int temp = determinant * i;
            if(moduloFunc(temp) == 1) {
                inverse = i;
                break;

            }
        }
        int swaptemp = Key1[0][0];
        Key1[0][0] = Key1[1][1];
        Key1[1][1]= swaptemp;

        Key1[0][1] *= -1;
        Key1[1][0] *= -1;

        Key1[0][1] = moduloFunc(Key1[0][1]);
        Key1[1][0] = moduloFunc(Key1[1][0]);

        for(int i = 0; i < 2 ; i++) {
            for(int j = 0; j < 2; j++) {
                Key1[i][j]*=inverse;
                Key1[i][j]= moduloFunc(Key1[i][j]);

            }
        }
        for(int i = 0; i < 2 ; i++) {
            for(int j = 0; j < 2; j++) {

                Key1[i][j]= moduloFunc(Key1[i][j]);
            }
        }
        return Key1;

    }
    static int[][] KeyMatrix(String Key, int[][] keyMatrix) {




        int[][] keyMatrix1= new int[2][2];
        int k = 0;
        for(int i =0;i<2;i++) {
            for(int j = 0; j<2;j++) {
                keyMatrix1[i][j]= (int)(Key.charAt(k));

                if(Key.charAt(k)>=128) {
                    keyMatrix1[i][j]=TextExtended(Key.charAt(k));
                }
                k++;
            }
        }
        int determinant = keyMatrix1[0][0] * keyMatrix1[1][1] - keyMatrix1[0][1] * keyMatrix1[1][0] ;
        determinant = moduloFunc(determinant);


        int inverse = -1;
        for( int i = 0; i< 256 ; i++) {
            int temp = determinant * i;
            if(moduloFunc(temp) == 1) {
                inverse = i;
                break;

            }
        }
        return keyMatrix1;
    }


    public  static String HillEncryption(String text1,String Key3) {
        int len = 0;
        if( text1.length() % 2 != 0) {
            text1+= "0";
            len= 1;
        }

        int[][] messa=new int[2][text1.length()];
       int [][]message1=TextMatrix(text1,messa);

       int[][] keyMatrix = new int[2][2];
        int [][]key1=KeyMatrix(Key3,keyMatrix);

        StringBuilder EncryptText = new StringBuilder(" ");

        int count = text1.length() / 2;
        if ( len== 0) {
            for( int i = 0; i < count; i++) {
                int temp1 =  ((message1[0][i] * key1[0][0] + message1[1][i] * key1[0][1]));
                EncryptText.append((char) ((temp1 % 256)));

                if(temp1%256 >=128) {
                    EncryptText.append(getAscii(temp1 % 256));
                }
                else if(temp1%256<=32){

                    EncryptText.append(decTOHex(temp1 % 256));

                    int temp2 = ((message1[0][i] * key1[1][0] + message1[1][i] * key1[1][1]));
                    EncryptText.append((char) (temp2 % 256));
                    if(temp2%256 >=128) {
                        EncryptText.append(getAscii(temp2 % 256));
                    }

                    else if(temp2%256<=32){

                        EncryptText.append(decTOHex(temp2 % 256));
                    }

                }
            }

        }

        else  {

            for( int i = 0; i < count-1; i++) {
                int temp1 = message1[0][i] * key1[0][0] + message1[1][i] * key1[0][1];

                EncryptText.append((char) ((temp1 % 256)));

                if(temp1%256 >=128) {
                    EncryptText.append(getAscii(temp1 % 256));
                }
                else if(temp1%256<=32){

                    EncryptText.append(decTOHex(temp1 % 256));

                    int temp2 = message1[0][i] * key1[1][0] + message1[1][i] * key1[1][1];
                    EncryptText.append((char) ((temp2 % 256)));

                    if(temp2%256 >=128) {
                        EncryptText.append(getAscii(temp2 % 256));
                    }

                    else if(temp2%256<=32){

                        EncryptText.append(decTOHex(temp2 % 256));
                    }

                }
            }

        }
        return EncryptText.toString();

    }

    public static String HillDecription(String text,String Key) {
        int len = 0;
        if( text.length() % 2 != 0) {
            text+= "0";
            len= 1;
        }
        int[][] message = new int[2][text.length()];
        int [][] message1=TextMatrix(text,message);

        int[][] keyMatrix = new int[2][2];
        int [][]key1=KeyDecryptMatrix(Key,keyMatrix);


        StringBuilder decryptText = new StringBuilder(" ");
        int count = text.length() / 2;
        if ( len== 0) {
            for( int i = 0; i < count; i++) {
                int temp1 = message1[0][i] * key1[0][0] + message1[1][i] * key1[0][1];
                decryptText.append((char) ((temp1 % 256)));

                if(temp1%256 >=128) {
                    decryptText.append(getAscii(temp1 % 256));
                }
                else if(temp1%256<=32){

                    decryptText.append(decTOHex(temp1 % 256));

                    int temp2 = message1[0][i] * key1[1][0] + message1[1][i] * key1[1][1];
                    decryptText.append((char) ((temp2 % 256)));

                    if(temp2%256 >=128) {
                        decryptText.append(getAscii(temp2 % 256));
                    }
                    else if(temp2%256<=32){

                        decryptText.append(decTOHex(temp2 % 256));

                    }
                }
            }
        }else {

            for( int i = 0; i < count-1; i++) {
                int temp1 = message1[0][i] * key1[0][0] + message1[1][i] * key1[0][1];
                decryptText.append((char) ((temp1 % 256)));
                if(temp1%256 >=128) {
                    decryptText.append(getAscii(temp1 % 256));
                }
                else if(temp1%256<=32){

                    decryptText.append(decTOHex(temp1 % 256));

                    int temp2 = message1[0][i] * key1[1][0] + message1[1][i] * key1[1][1];
                    decryptText.append((char) ((temp2 % 256)));

                    if(temp2%256 >=128) {
                        decryptText.append(getAscii(temp2 % 256));
                    }
                    else if(temp2%256<=32){

                        decryptText.append(decTOHex(temp2 % 256));
                    }
                }
            }
        }
        return decryptText.toString();
    }

    private static int moduloFunc(int a) {
        int result = a% 256;
        if( result<0) {
            result+= 256;

        }
        return result;


    }








}




