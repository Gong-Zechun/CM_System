package com.petro;

import java.io.IOException;

/**
 * @author AllenGong
 * @version V1.0
 * @date 2016-11-18 17:32
 */
public class UnLoginException extends Exception {
    private static final long serialVersionUID = 1L;

    public UnLoginException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UnLoginException(String message) throws IOException {
        super(message);
        // TODO Auto-generated constructor stub
    }
}
