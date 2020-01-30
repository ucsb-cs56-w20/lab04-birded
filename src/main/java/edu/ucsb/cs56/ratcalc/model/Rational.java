package edu.ucsb.cs56.ratcalc.model;
/** A class to represent a rational number
    with a numerator and denominator
    @author P. Conrad for CS56 F16
    */

    public class Rational {

        private int num;
        private int denom;
    
        /** 
        greatest common divisor of a and b
        @param a first number
        @param b second number
        @return gcd of a and b
        */
        public static int gcd(int a, int b) {
        if (a==0)
            return b;
        else if (b==0)
            return a;
        else
            return gcd(b%a, a);
        }
    
    
        /**
        least common multiple of a and b
        @param a first number
        @param b second number
        @return lcm of a and b
        */
        public static int lcm(int a, int b){
            return Math.abs(a * b) / gcd(a,b);
        }
    
        /**
        sum of this number plus r
        @param r rational number
        @return sum of this numer and r
        */
        public Rational plus(Rational r){
            int a = lcm(this.denom,r.denom);
            int b = this.num*(a/this.denom)+r.num*(a/r.denom);
            return new Rational(b,a);
        }
    
        /**
        a+b
        @param a rational number a
        @param b rational number b
        @return a+b
        */
        public static Rational sum(Rational a, Rational b){
            int d = lcm(a.denom,b.denom);
            int n = a.num*(d/a.denom)+b.num*(d/b.denom);
            return new Rational(n,d);
        }
    
        /**
        this number minus r
        @param r rational number
        @return this number minus r
        */
        public Rational minus(Rational r){
            r.num = -1 * r.num;
            return this.plus(r);
        }
    
        /**
        a-b
        @param a rational number a
        @param b rational number b
        @return a-b
        */
        public static Rational difference(Rational a, Rational b){
            b.num = -1 * b.num;
            return sum(a,b);
        }
        
        /**
        reciprocal of this number
        @return the reciprocal of this number
        */
        public Rational reciprocalOf(){
            if(this.num == 0){
                throw new ArithmeticException("Denominator of reciprocal cannot contain zero");
            }
            return new Rational(this.denom,this.num);
        }
        
        /**
        divide this number by r
        @param r the number to divide by
        @return this number divided by r
        */
        public Rational dividedBy(Rational r){
            return this.times(r.reciprocalOf());
        }
    
        /**
        the quotient of a and b
        @param a the number to divide
        @param b the number to divide by
        @return a divided by b
        */
        public static Rational quotient(Rational a, Rational b){
            return a.times(b.reciprocalOf());
        }
    
        public Rational() {
            this.num = 1;
            this.denom = 1;
        }
    
        public Rational(int num, int denom) {
            if (denom== 0) {
                throw new IllegalArgumentException("denominator may not be zero");
            }
            this.num = num;
            this.denom = denom;
            if (num != 0) {
                int gcd = Rational.gcd(num,denom);
                this.num /= gcd;
                this.denom /= gcd;
            }
            if(this.denom < 0){ 
                this.num = this.num * -1;
                this.denom = this.denom * -1;
            }
        }
    
        public String toString() {
        if (denom == 1 || num == 0)
            return "" + num;
        return num + "/" + denom;
        }
    
        public int getNumerator() { return this.num; }
        public int getDenominator() { return this.denom; }
    
        public Rational times(Rational r) {
        return new Rational(this.num * r.num,
                    this.denom * r.denom);
        }
    
        public static Rational product(Rational a, Rational b) {
        return new Rational(a.num * b.num,
                    a.denom * b.denom);
        }
    
        
        /** 
        For testing getters.  
        @param args unused
         */
    
        public static void main (String [] args) {
        Rational r = new Rational(5,7);
        System.out.println("r.getNumerator()=" + r.getNumerator());
        System.out.println("r.getDenominator()=" + r.getDenominator());
        }
    
        
    }