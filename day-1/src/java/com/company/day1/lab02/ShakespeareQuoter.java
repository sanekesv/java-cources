package com.company.day1.lab02;

/**
 * Created by Александр on 06.04.2015.
 */
public class ShakespeareQuoter implements Quoter {

    String quote;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public void sayQuote() {
        System.out.println(quote);
    }
}
