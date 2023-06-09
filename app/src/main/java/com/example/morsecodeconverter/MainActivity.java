package com.example.morsecodeconverter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText etinput,etoutput;
    Button btnEncode,btnDecode,btnClear;
    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etinput=findViewById(R.id.etinput);
        etoutput=findViewById(R.id.etoutput);
        btnDecode=findViewById(R.id.btnDecode);
        btnEncode=findViewById(R.id.btnEncode);
        btnClear=findViewById(R.id.btnClear);

        final String[] AlphaNumeric =new String[52];//string array for storing alphabets, numbers and special characters
        final String[] AlphaNumeric1 =new String[52];//string array for storing corresponding morse code
        
        //assigning alphabets to the string array Alphanumeric[]
        AlphaNumeric[0]="A";
        AlphaNumeric[1]="B";
        AlphaNumeric[2]="C";
        AlphaNumeric[3]="D";
        AlphaNumeric[4]="E";
        AlphaNumeric[5]="F";
        AlphaNumeric[6]="G";
        AlphaNumeric[7]="H";
        AlphaNumeric[8]="I";
        AlphaNumeric[9]="J";
        AlphaNumeric[10]="K";
        AlphaNumeric[11]="L";
        AlphaNumeric[12]="M";
        AlphaNumeric[13]="N";
        AlphaNumeric[14]="O";
        AlphaNumeric[15]="P";
        AlphaNumeric[16]="Q";
        AlphaNumeric[17]="R";
        AlphaNumeric[18]="S";
        AlphaNumeric[19]="T";
        AlphaNumeric[20]="U";
        AlphaNumeric[21]="V";
        AlphaNumeric[22]="W";
        AlphaNumeric[23]="X";
        AlphaNumeric[24]="Y";
        AlphaNumeric[25]="Z";
        AlphaNumeric[26]="0";
        AlphaNumeric[27]="1";
        AlphaNumeric[28]="2";
        AlphaNumeric[29]="3";
        AlphaNumeric[30]="4";
        AlphaNumeric[31]="5";
        AlphaNumeric[32]="6";
        AlphaNumeric[33]="7";
        AlphaNumeric[34]="8";
        AlphaNumeric[35]="9";
        AlphaNumeric[36]=" ";
        AlphaNumeric[37]="&";
        AlphaNumeric[38] = "\'";
        AlphaNumeric[39] = "@";
        AlphaNumeric[40] = "(";
        AlphaNumeric[41] = ")";
        AlphaNumeric[42] = ":";
        AlphaNumeric[43] = ",";
        AlphaNumeric[44] = "=";
        AlphaNumeric[45] = "!";
        AlphaNumeric[46] = ".";
        AlphaNumeric[47] = "-";
        AlphaNumeric[48] = "+";
        AlphaNumeric[49] = "\"";
        AlphaNumeric[50] = "?";
        AlphaNumeric[51] = "/";
        
        

        //assigning the corresponding morse code for each letter and number to Alphanumeric1[] array
        AlphaNumeric1[0]=".-";
        AlphaNumeric1[1]="-...";
        AlphaNumeric1[2]="-.-.";
        AlphaNumeric1[3]="-..";
        AlphaNumeric1[4]=".";
        AlphaNumeric1[5]="..-.";
        AlphaNumeric1[6]="--.";
        AlphaNumeric1[7]="....";
        AlphaNumeric1[8]="..";
        AlphaNumeric1[9]=".---";
        AlphaNumeric1[10]="-.-";
        AlphaNumeric1[11]=".-..";
        AlphaNumeric1[12]="--";
        AlphaNumeric1[13]="-.";
        AlphaNumeric1[14]="---";
        AlphaNumeric1[15]=".--.";
        AlphaNumeric1[16]="--.-";
        AlphaNumeric1[17]=".-.";
        AlphaNumeric1[18]="...";
        AlphaNumeric1[19]="-";
        AlphaNumeric1[20]="..-";
        AlphaNumeric1[21]="...-";
        AlphaNumeric1[22]=".--";
        AlphaNumeric1[23]="-..-";
        AlphaNumeric1[24]="-.--";
        AlphaNumeric1[25]="--..";
        AlphaNumeric1[26]="-----";
        AlphaNumeric1[27]=".----";
        AlphaNumeric1[28]="..---";
        AlphaNumeric1[29]="...--";
        AlphaNumeric1[30]="....-";
        AlphaNumeric1[31]=".....";
        AlphaNumeric1[32]="-....";
        AlphaNumeric1[33]="--...";
        AlphaNumeric1[34]="---..";
        AlphaNumeric1[35]="----.";
        AlphaNumeric1[36]="/";
        AlphaNumeric1[37] = ".-...";
        AlphaNumeric1[38] = ".----.";
        AlphaNumeric1[39] = ".--.-.";
        AlphaNumeric1[40] = "-.--.-";
        AlphaNumeric1[41] = "-.--.";
        AlphaNumeric1[42] = "---...";
        AlphaNumeric1[43] = "--..--";
        AlphaNumeric1[44] = "-...-";
        AlphaNumeric1[45] = "-.-.--";
        AlphaNumeric1[46] = ".-.-.-";
        AlphaNumeric1[47] = "-....-";
        AlphaNumeric1[48] = ".-.-.";
        AlphaNumeric1[49] = ".-..-.";
        AlphaNumeric1[50] = "..--..";
        AlphaNumeric1[51] = "-..-.";
        

        t1 = new TextToSpeech(getApplicationContext(), i -> {
            if(i != TextToSpeech.ERROR){
                t1.setLanguage(Locale.UK);//language to which the morse code will be decoded and encrypted
            }
        });

        btnEncode.setOnClickListener(view -> {
            String input = etinput.getText().toString();//entered input is stored as String
            String output = "";//Empty string is assigned as output
            int l = input.length();//length of input string is calculated and stored
            int i, j;
            for (i = 0; i < l; i++) {
                String ch = input.substring(i, i + 1);
                for (j = 0; j < 37; j++) {
                    if (ch.equalsIgnoreCase(AlphaNumeric[j])) {//finds the index value of each input character in AlphaNumeric array and case of the character is ignored
                        output = output.concat(AlphaNumeric1[j]).concat(" ");//after finding the index of the character its respective morse code is accessed from Alphanumeric1 and its stor
                    }
                }

            }
            etoutput.setText(output);//displays output
        });

        btnClear.setOnClickListener(view -> {
            etinput.setText("");
            etoutput.setText("");
        });

        btnDecode.setOnClickListener(view -> {
            String input1=etinput.getText().toString();//input string is stored
            String input=input1.concat(" ");//space is added at the end of the input
            int l=input.length();//length of the input is calculated

            int i,j,p;
            int pos=0;//indicated the position
            String letter="";//indicates the letter that is being accessed
            String output="";//final output
            for(i=0;i<l;i++){
                int flag=0;
                String ch=input.substring(i,i+1);//each character is accessed
                if(ch.equalsIgnoreCase(" ")){//if it matches to white space
                    p=i;
                    letter=input.substring(pos,p);//morse code of a letter is stored
                    pos=p+1;
                    flag=1;
                }
                String letter1=letter.trim();//removes space
                if(flag==1){
                    for(j=0;j<=36;j++) {
                        if (letter1.equalsIgnoreCase(AlphaNumeric1[j])) {//finding the respective index of morsecode 
                            output = output.concat(AlphaNumeric[j]);//with the help of index its character is accessed from alphanumeric array
                            break;
                        }
                    }
                }

            }
            etoutput.setText(output);
            speak(output);
        });
    }
    public void speak(String output){
        Toast.makeText(getBaseContext(),output,Toast.LENGTH_LONG).show();
        t1.speak(output,TextToSpeech.QUEUE_FLUSH,null);
    }
}
