package br.ufc.comp.qalc.frontend;

import br.ufc.comp.qalc.frontend.token.*;

import java.io.IOException;

/**
 * Analisador Léxico da linguagem.
 * <p>
 * Funciona como uma fonte de tokens, de onde o Analisador Sintático
 * deverá ler.
 *
 * @see Source
 */
public class Scanner {

    /**
     * Último token reconhecido.
     */
    protected Token currentToken;
    /**
     * Fonte de caracteres de onde extrair os tokens.
     */
    protected Source source;

    /**
     * Constrói um Analisador Léxico a partir de uma fonte de caracteres.
     *
     * @param sourceStream Fonte a ser utilizada.
     */
    public Scanner(Source sourceStream) {
        // FIXME Lidar corretamente com as exceções.
        this.source = sourceStream;
    }

    /**
     * Consome caracteres da fonte até que eles componham um lexema de
     * um dos tokens da linguagem, coinstrói um objeto representando esse
     * token associado ao lexema lido e o retorna.
     *
     * @return Token reconhecido.
     * @throws IOException Caso haja problema na leitura da fonte.
     */
    public Token getNextToken() throws IOException {
        // TODO Reconhecimento de tokens

        if (source.getCurrentChar() == Source.EOF) {
            return new EOFToken(source.getCurrentLine(), source.getCurrentColumn());
        } else if (Character.isDigit(source.getCurrentChar())) { // NumberToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (Character.isDigit(source.getCurrentChar()));

            String stringValue = lexema.toString();


            return new NumberToken(currentLine, lexemeStart, stringValue);

        } else if(source.getCurrentChar() == '$'){
            StringBuilder lexem = new StringBuilder();
            lexem.append(source.getCurrentChar());
            source.advance();

            if(Character.isAlphabetic(source.getCurrentChar())) {

                long currentLine = source.getCurrentLine();
                long lexemStart = source.getCurrentColumn();

                do {
                    lexem.append(source.getCurrentChar());
                    source.advance();
                } while (Character.isAlphabetic(source.getCurrentChar()));

                String stringValue = lexem.toString();

                return new VariableIdentifierToken(currentLine, lexemStart, stringValue);
            }

            if(Character.isDigit(source.getCurrentChar()) || source.getCurrentChar() == '?'){
                long currentLine = source.getCurrentLine();
                long lexemStart = source.getCurrentColumn();

                do {
                    lexem.append(source.getCurrentChar());
                    source.advance();
                } while(Character.isDigit(source.getCurrentChar()) || source.getCurrentChar() == '?');

                String stringValue = lexem.toString();

                if(Double.parseDouble(stringValue.substring(1)) == 0){
                    System.out.println("sequência apenas de caracteres 0 não é permitida");
                }else {
                    return new ResultIdentifierToken(currentLine, lexemStart, stringValue);
                }
            }
        } else if(source.getCurrentChar() == '@'){
            StringBuilder lexem = new StringBuilder();
            lexem.append(source.getCurrentChar());
            source.advance();

            if(Character.isAlphabetic(source.getCurrentChar()) || Character.isDigit(source.getCurrentChar())){

                long currentLine = source.getCurrentLine();
                long lexemStart = source.getCurrentColumn();

                do{
                    lexem.append(source.getCurrentChar());
                    source.advance();

                } while(Character.isAlphabetic(source.getCurrentChar()) || Character.isDigit(source.getCurrentChar()));

                String stringValue = lexem.toString();
                return new FunctionIdentifierToken(currentLine, lexemStart, stringValue);
            }
        } else if(source.getCurrentChar() == '='){
            StringBuilder lexem = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemStart = source.getCurrentColumn();
            String stringValue = lexem.append(source.getCurrentChar()).toString();

            return new AtribToken(currentLine, lexemStart, stringValue);

        } else if(source.getCurrentChar() == '+'){
            StringBuilder lexem = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemStart = source.getCurrentColumn();
            String stringValue = lexem.append(source.getCurrentChar()).toString();

            return new PlusToken(currentLine, lexemStart, stringValue);

        } else if(source.getCurrentChar() == '-'){
            StringBuilder lexem = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemStart = source.getCurrentColumn();
            String stringValue = lexem.append(source.getCurrentChar()).toString();

            return new MinusToken(currentLine, lexemStart, stringValue);

        } else if(source.getCurrentChar() == '*'){
            StringBuilder lexem = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemStart = source.getCurrentColumn();
            String stringValue = lexem.append(source.getCurrentChar()).toString();

            return new TimesToken(currentLine, lexemStart, stringValue);
        } else if(source.getCurrentChar() == '/'){
            StringBuilder lexem = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemStart = source.getCurrentColumn();
            String stringValue = lexem.append(source.getCurrentChar()).toString();

            return new DivToken(currentLine, lexemStart, stringValue);

        } else if(source.getCurrentChar() == '%'){
            StringBuilder lexem = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemStart = source.getCurrentColumn();
            String stringValue = lexem.append(source.getCurrentChar()).toString();

            return new ModToken(currentLine, lexemStart, stringValue);

        } else if(source.getCurrentChar() == '^'){
            StringBuilder lexem = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemStart = source.getCurrentColumn();
            String stringValue = lexem.append(source.getCurrentChar()).toString();

            return new PowToken(currentLine, lexemStart, stringValue);

        } else if(source.getCurrentChar() == '('){
            StringBuilder lexem = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemStart = source.getCurrentColumn();
            String stringValue = lexem.append(source.getCurrentChar()).toString();

            return new LparenToken(currentLine, lexemStart, stringValue);

        } else if(source.getCurrentChar() == ')'){
            StringBuilder lexem = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemStart = source.getCurrentColumn();
            String stringValue = lexem.append(source.getCurrentChar()).toString();

            return new RparenToken(currentLine, lexemStart, stringValue);

        } else if(source.getCurrentChar() == ';'){
            StringBuilder lexem = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemStart = source.getCurrentColumn();
            String stringValue = lexem.append(source.getCurrentChar()).toString();

            return new SemiToken(currentLine, lexemStart, stringValue);

        } else if(source.getCurrentChar() == ','){
            StringBuilder lexem = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemStart = source.getCurrentColumn();
            String stringValue = lexem.append(source.getCurrentChar()).toString();

            return new CommaToken(currentLine, lexemStart, stringValue);

        } else if(source.getCurrentChar() == '#'){

            StringBuilder lexem = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemStart = source.getCurrentColumn();

            do{
                lexem.append(source.getCurrentChar());
                source.advance();

            } while(source.getCurrentChar() != '\n');

            String stringValue = lexem.toString();
            return new ComentToken(currentLine, lexemStart, stringValue);

        }else if(source.getCurrentChar() == '\n' || source.getCurrentChar() == '\t' || source.getCurrentChar() == ' '){
            StringBuilder white = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long whiteStart = source.getCurrentColumn();

            do{
                white.append(source.getCurrentChar());
                source.advance();
            } while(source.getCurrentChar() == '\n' || source.getCurrentChar() == '\t' || source.getCurrentChar() == ' ');

            String stringValue = white.toString();

            return new WhiteToken(currentLine, whiteStart, stringValue);
        }

        // TODO Recuperação de erros.

        return null;
    }

    /**
     * Obtém o último token reconhecido.
     *
     * @return O último token reconhecido.
     */
    public Token getCurrentToken() {
        return currentToken;
    }
}
