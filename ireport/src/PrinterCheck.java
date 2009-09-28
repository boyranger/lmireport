public class PrinterCheck {

	static {
		System.loadLibrary("PRINTS");
	}
	
	public native int chekcInstall();
	public native int getWindowHandle(java.awt.Component c);
	public native void showInstall(int hwnd);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.awt.Frame f =new java.awt.Frame();
		f.setSize(400, 500);
		f.setVisible(true);
		PrinterCheck pc = new PrinterCheck();
		int hwnd = pc.getWindowHandle(f);
		
		pc.showInstall(hwnd);
		//System.out.println(System.getProperty("java.library.path"));
		
	}
}