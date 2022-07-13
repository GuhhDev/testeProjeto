package Exceptions;

public class ValorNaoInformadoException extends NullPointerException {

    @Override
    public String toString() {
        return "valor(es) esta(o) nulo(s). Favor, verificar.";
    }
}
