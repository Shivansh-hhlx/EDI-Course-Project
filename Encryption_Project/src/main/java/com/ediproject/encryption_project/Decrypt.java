/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ediproject.encryption_project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Decrypt {
    byte[] eveScramble(byte[] b){
        String temp1, temp2;
        int l = b.length;
        for(int i = 1; i < l; i += 2){
            temp1 = Byte.toString(b[i - 1]);
            temp2 = Byte.toString(b[i]);
            b[i - 1] = Byte.parseByte(temp1.substring(0,1) + temp2.substring(1));
            b[i] = Byte.parseByte(temp2.substring(0,1) + temp1.substring(1));
        }
        return b;
    }
    
    byte[] oddScramble(byte[] b){
        String temp1, temp2;
        int l = b.length;
        for(int i = 1; i < l; i += 2){
            temp1 = Byte.toString(b[i - 1]);
            temp2 = Byte.toString(b[i]);
            b[i - 1] = Byte.parseByte(temp1.substring(0,1) + temp2.substring(1));
            b[i] = Byte.parseByte(temp2.substring(0,1) + temp1.substring(1));
        }
        return b;
    }
    public String decrypt(File file, BigInteger d, BigInteger n) throws Exception{
        FileInputStream in = new FileInputStream(file);
        ObjectInputStream obread = new ObjectInputStream(in);
        StringBuilder oG = new StringBuilder();
        List<BigInteger[]> arrayObj = (List) obread.readObject();
        for(int q = 0; q < arrayObj.size(); q++){
            BigInteger[] l = (BigInteger[]) arrayObj.get(q);
            BigInteger[] newl = new BigInteger[l.length];
            for(int i = 0; i < l.length; i++)
                newl[i] = l[i].modPow(d, n);
            byte[] b = new byte[newl.length];
            for(int i = 0; i < newl.length; i++)
                b[i] = newl[i].byteValue();
            if(b.length % 2 == 0)
                b = eveScramble(b);
            else
                b = oddScramble(b);
            int check;
            char c;
            for(int i = 2; i < b.length; i++){
                check = 0;
                for(int j = i - 1; j > 1; j--){
                    if(i % j == 0)
                        check++;
                }
                if(check == 0){
                    c = (char) b[i];
                    oG.append(c);
                }
            }
            oG.append("\n");
        }
        return oG.toString();
    }
}
