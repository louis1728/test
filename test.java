import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class test {
	
	JFrame frame = new JFrame();
	JLabel lbl_adult = new JLabel("大人 : ");
	JLabel lbl_child = new JLabel("小孩 : ");
	JTextField txt_adult = new JTextField(10);
	JTextField txt_child = new JTextField(10);
	JButton btn_submit = new JButton("送出");
	ButtonGroup jbg = new ButtonGroup();
	JRadioButton jrb_morning = new JRadioButton("平日早上");
	JRadioButton jrb_other = new JRadioButton("其他時間");
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	Container cp = frame.getContentPane();
	
	public test() {
		jbg.add(jrb_morning);
		jbg.add(jrb_other);
		panel1.add(lbl_adult);
		panel1.add(txt_adult);
		panel2.add(lbl_child);
		panel2.add(txt_child);
		panel3.add(jrb_morning);
		panel3.add(jrb_other);
		panel4.add(btn_submit);
		cp.setLayout(new BoxLayout(cp, BoxLayout.PAGE_AXIS));
		cp.add(panel1);
		cp.add(panel2);
		cp.add(panel3);
		cp.add(panel4);
		
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int count = 0;
				char[] adult = txt_adult.getText().toCharArray();
				char[] child = txt_child.getText().toCharArray();
				  for(int index=0; index < txt_adult.getText().length(); index++) {
					     if(!Character.isDigit(adult[index])) {
					    	 count ++;
					    	 break;
					     }
				}
				  for(int index=0; index < txt_child.getText().length(); index++) {
					     if(!Character.isDigit(child[index])) {
					    	 count ++;
					    	 break;
					     }
				}
				int adult_number = -1, child_number = -1, time = -1;
				if (txt_adult.getText().equals("") || txt_child.getText().equals("") || count!= 0) {
					JOptionPane.showMessageDialog(null, "工程師是很辛苦的，請您正常操作", "錯誤", JOptionPane.WARNING_MESSAGE);
					count = 0;
				}
				else {
					adult_number = Integer.parseInt(txt_adult.getText());
					child_number = Integer.parseInt(txt_child.getText());
					if (jrb_morning.isSelected())
						time = 0;
					else if (jrb_other.isSelected())
						time = 1;

					if (adult_number == -1 || child_number == -1 || time == -1)
						JOptionPane.showMessageDialog(null, "工程師是很辛苦的，請您正常操作", "錯誤", JOptionPane.WARNING_MESSAGE);
					else
						JOptionPane.showMessageDialog(null,
								"                          金額為 : " + calculate(adult_number, child_number, time) + " 元",
								"結果", JOptionPane.PLAIN_MESSAGE);

				}
			}
		});
		frame.setTitle("結帳");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(350,200);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new test();
	}
	
	public String calculate(int adult, int child, int time){
        long money = 0;
        int all = adult + child;
        int a3 = all/3;
        if(time == 0) {
            if((all % 3) == 0) {
                if (child >= a3)
                    money = adult * 268 + (child - a3) * 120;
                else if(child < a3)
                    money = (adult - (a3 - child)) * 268;
                else if(child == 0)
                    money = (adult - a3) * 268;
                else if(adult == 0)
                    money = (child - a3) * 120;
            }
            else if(all >= 3){
                if(child >= a3)
                    money = adult * 268 + (child-a3)*120;
                else if(child < a3 )
                    money = (adult- (a3 - child)) * 268;
                else if(adult == 0)
                    money = (child - a3) * 120;
            }
            else if(all < 3)
                money = adult *268 + child * 120;
            if(all >= 10)
                money = (long)(money * 0.95);
        }
        else{
            if((all % 3) == 0) {
                if (child >= a3)
                    money = (long)((adult * 368 + ((child-a3) * 120)) * 1.1);
                else if(child < a3)
                    money = (long)((adult - (a3 - child)) * 368 * 1.1);
                else if(child == 0)
                    money = (long)((adult - a3) * 368 * 1.1);
                else if(adult == 0)
                    money = (long)((child - a3) * 150 * 1.1);
            }
            else if(all >= 3){
                if(child >= a3)
                    money = (long)((adult * 368 + (child-a3) * 150) * 1.1);
                else if(child < a3)
                    money = (long)((adult-(a3-child)) * 368 * 1.1);
                else if(adult == 0)
                    money = (long)((child - a3) * 150 * 1.1);
            }
            else if(all < 3)
                money = (long)((adult * 368 + child * 150) * 1.1);
            if(all >= 10)
                money = (long)(money * 0.95);
        }
        return Long.toString(money);
    }
}