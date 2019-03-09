package br.ufc.comp.qalc.frontend.token;

public class ComentToken extends Token {

    public ComentToken(long line, long start, String value) throws IllegalArgumentException {
        super(line, start, value);
    }

    @Override
    public void interpretAttributes() {
        if(stringValue != null && stringValue.charAt(0) == '#'){
            stringValue = stringValue.substring(1);
        }
    }

    public String getComentToken(){
        interpretAttributes();
        return stringValue;
    }

    @Override
    public String getTokenIdentifier() {
        return "COM";
    }
}
