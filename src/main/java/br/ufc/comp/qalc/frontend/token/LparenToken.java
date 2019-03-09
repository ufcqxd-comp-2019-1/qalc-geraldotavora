package br.ufc.comp.qalc.frontend.token;

public class LparenToken extends Token {

    public LparenToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    public String getLparenToken(){
        return stringValue;
    }

    @Override
    public String getTokenIdentifier() {
        return "LPAREN";
    }
}
