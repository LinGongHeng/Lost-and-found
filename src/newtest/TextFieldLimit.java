//�����ı��������ʽ
package newtest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextFieldLimit implements KeyListener {

login login;

public TextFieldLimit (login login) 
 {
  this.login = login;
 }

//��������ѧ��ֻ��Ϊ0-9���֣�����ֻ����8λ
public void keyPressed(KeyEvent e) { 
 char c = e.getKeyChar();
// login.jTextField.setText(login.jTextField.getText().replaceAll("[^0-9]",""));
 int len = login.jTextField.getText().length();
 System.out.println(len);
 if (len < 8) 
  {
	 return;
  }
 else
    {
	 e.consume(); 
    }
}
public void keyReleased(KeyEvent e) { }
public void keyTyped(KeyEvent e) { }
}
