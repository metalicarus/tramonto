package br.com.wsss.tramonto.exception;

public class TramontoException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TramontoException(String msg) {
		super(msg);
	}
	
	public TramontoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
