package com.githstuff;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(){
        this.numerator=1;
        this.denominator=0;
    }

    public Fraction(int numerator){
        this.numerator= numerator;
        this.denominator = 1;
    }

    public Fraction(int numerator, int denominator){
        if (denominator==0){
            throw new IllegalArgumentException("Denominator must not be zero.");
        } else{ if(denominator<0){
            this.numerator= numerator * -1;
            this.denominator = denominator * -1;
        }else{
            this.numerator= numerator;
            this.denominator = denominator;
        }
        }
    }

    public int getNumerator(){
        return numerator;
    }

    public int getDenominator(){
        return denominator;
    }

    public String toString(){
        if(this.numerator==0){
            return "0";
        }
        return numerator+"/"+denominator;
    }

    public double toDouble(){
        return (double)numerator/denominator;
    }

    public Fraction add(Fraction other){
        int newNumerator =
                (this.numerator * other.getDenominator())
                        + (other.getNumerator() * this.denominator);
        int newDenominator= this.getDenominator() * other.getDenominator();
        return new Fraction(newNumerator, newDenominator).toLowestTerms();
    }

    public Fraction subtract(Fraction other){
        int newNumerator =
                (this.numerator * other.getDenominator())
                        - (other.getNumerator()* this.denominator);
        int newDenominator= this.getDenominator() * other.getDenominator();
        return new Fraction(newNumerator,newDenominator).toLowestTerms();
    }

    public Fraction multiply(Fraction other){
        int newNumerator = this.numerator * other.getNumerator();
        int newDenominator= this.getDenominator() * other.getDenominator();
        return new Fraction(newNumerator,newDenominator).toLowestTerms();
    }

    public Fraction divide(Fraction other){
        int newNumerator = this.numerator * other.getDenominator();
        int newDenominator= this.getDenominator() * other.getNumerator();
        return new Fraction(newNumerator,newDenominator).toLowestTerms();
    }

    public boolean equals (Fraction other){
        if (this.toDouble()==other.toDouble()){
            return true;
        }else return false;
    }

    public Fraction toLowestTerms(){
        int gcd= this.gcd(this.numerator,this.denominator);
        int newNumerator=this.numerator/gcd;
        int newDenominator = this.denominator/gcd;
        return new Fraction(newNumerator,newDenominator);
    }

    public  int gcd(int num, int denom){

        int gcd;
        if (num==denom) {
            return num;
        }
        if (num==0){
            return denom;
        }
        if (denom==0){
            return num;
        }
        int q = num/denom;
        int r = num%denom;
        return gcd(denom,r);

    }
}
