package academy.kata.SpringBoot.exeptions;

public class NoSuchUserExeption extends RuntimeException {

    public NoSuchUserExeption(String message) {
        super(message);
    }
}
