package org.board.mvc.reply.exception;

public class ReplyNumberNotFoundException extends RuntimeException {
    
    public ReplyNumberNotFoundException() {
        super();
    }

    public ReplyNumberNotFoundException(String message) {
        super(message);
    }
}
