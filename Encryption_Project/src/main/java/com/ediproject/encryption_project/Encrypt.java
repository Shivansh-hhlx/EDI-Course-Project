/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ediproject.encryption_project;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Admin
 */
public class Encrypt {
    
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
    
    public BigInteger[] encrypt(String oG, BigInteger e, BigInteger n){
        Encrypt obj = new Encrypt();
        int l = oG.length();
        l--;
        oG = oG.toUpperCase();
        StringBuilder eC = new StringBuilder();
        int j=0, k;
        for(int i = 0; i<=l; i++){
            char c = oG.charAt(i);
            int nFactor=0;
            boolean prime=false;
            while(prime==false){
                k=0;
                nFactor = 0;
                for(int factor=j; factor>1; factor-- ){
                    if((j%factor)==0)
                        nFactor++;
                }
                
                if(nFactor==1){
                    eC.append(c);
                    prime=true;
                }
                
                else{
                    int v=(int)((Math.random()*25)+66);
                    char a = (char)v;
                    eC.append(a);

                }
                j++;
            }
        }
        
        byte[] letter = eC.toString().getBytes();
        byte[] new_letter = new byte[letter.length];
        
        int q = letter.length;
        if(q % 2 == 0)
            new_letter = eveScramble(letter);
        
        else
            new_letter = oddScramble(letter);
        
        BigInteger[] temp_Digits = new BigInteger[new_letter.length];
        for(int i = 0; i < new_letter.length; i++){
            temp_Digits[i] = new BigInteger(Integer.toString(new_letter[i]));
        }
        BigInteger[] finalEncrypted = new BigInteger[temp_Digits.length];
        
        for(int i = 0; i < temp_Digits.length; i++)
            finalEncrypted[i] = temp_Digits[i].modPow(e,n);
        return finalEncrypted;
    }
    
}
