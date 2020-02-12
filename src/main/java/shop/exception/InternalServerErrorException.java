package shop.exception;

public class InternalServerErrorException extends RuntimeException { //! RuntimeException - это checked exception, и оно никак не контролируется и в случае возникновения ошибки, будет обрабатываться верхним компонентом (ErrorHandler)

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause);
    }
}
