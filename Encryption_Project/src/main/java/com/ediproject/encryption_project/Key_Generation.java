/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ediproject.encryption_project;

import static java.lang.String.valueOf;
import java.math.*;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class Key_Generation {
    public BigInteger prime_number(){
        BigInteger number = BigInteger.probablePrime(50, new Random());
        return number;
    }
    
    public BigInteger getEKey(BigInteger N_Dash){
        BigInteger E;
        do{
            E = new BigInteger(100, new Random());
        }while( ( E.compareTo( N_Dash ) != -1 ) || ( E.gcd( N_Dash ).compareTo( BigInteger.valueOf(1) ) != 0 ));
        return E;
    }
    
    public BigInteger getDKey(BigInteger n_dash, BigInteger e){
        BigInteger d = e.modInverse(n_dash);
        return d;
    }
}
