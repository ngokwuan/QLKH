package test;

import view.baoCaoView;
import javax.swing.UIManager;

public class test {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new baoCaoView();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
