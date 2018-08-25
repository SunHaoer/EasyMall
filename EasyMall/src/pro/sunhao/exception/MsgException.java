package pro.sunhao.exception;

/**
 * 自定义异常
 * @author Administrator
 *
 */
public class MsgException extends Exception {

	public MsgException() {
		super();
	}

	public MsgException(String arg0) {
		super(arg0);
	}

	public MsgException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
}
