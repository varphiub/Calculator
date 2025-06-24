package Calculator;

import java.awt.*;
import java.awt.event.*;

public class MyCalc extends WindowAdapter implements ActionListener {
    Frame f;
    Label l1;
    Button[] numButtons = new Button[10];
    Button badd, bsub, bmult, bdiv, bmod, bcalc, bclr, bpts, bneg, bback;
    double xd;
    double num1, num2, check;

    MyCalc() {
        f = new Frame("MY CALCULATOR");
        f.setSize(360, 500);
        f.setLayout(new BorderLayout());

        l1 = new Label("", Label.RIGHT);
        l1.setBackground(Color.YELLOW);
        l1.setFont(new Font("Arial", Font.PLAIN, 24));
        l1.setPreferredSize(new Dimension(360, 60));
        f.add(l1, BorderLayout.NORTH);

        // Panel for buttons
        Panel pad = new Panel();
        pad.setLayout(new GridLayout(5, 4, 8, 8));
        pad.setBackground(Color.LIGHT_GRAY);

        // Number buttons
        for (int i = 1; i <= 9; i++) {
            numButtons[i] = new Button(String.valueOf(i));
            numButtons[i].addActionListener(this);
        }
        numButtons[0] = new Button("0");
        numButtons[0].addActionListener(this);

        // Operator and function buttons
        badd = new Button("+"); badd.addActionListener(this);
        bsub = new Button("-"); bsub.addActionListener(this);
        bmult = new Button("*"); bmult.addActionListener(this);
        bdiv = new Button("/");  bdiv.addActionListener(this);
        bmod = new Button("%");  bmod.addActionListener(this);
        bcalc = new Button("="); bcalc.addActionListener(this);
        bclr = new Button("CE"); bclr.addActionListener(this);
        bpts = new Button(".");  bpts.addActionListener(this);
        bneg = new Button("+/-");bneg.addActionListener(this);
        bback = new Button("←"); bback.addActionListener(this);

        // Add buttons to panel in a standard calculator layout
        pad.add(bclr);   // Row 1
        pad.add(bback);
        pad.add(bmod);
        pad.add(bdiv);

        pad.add(numButtons[7]); // Row 2
        pad.add(numButtons[8]);
        pad.add(numButtons[9]);
        pad.add(bmult);

        pad.add(numButtons[4]); // Row 3
        pad.add(numButtons[5]);
        pad.add(numButtons[6]);
        pad.add(bsub);

        pad.add(numButtons[1]); // Row 4
        pad.add(numButtons[2]);
        pad.add(numButtons[3]);
        pad.add(badd);

        pad.add(bneg);          // Row 5
        pad.add(numButtons[0]);
        pad.add(bpts);
        pad.add(bcalc);

        f.add(pad, BorderLayout.CENTER);

        f.addWindowListener(this);
        f.setVisible(true);
    }

    public void windowClosing(WindowEvent e) {
        f.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        String zt = l1.getText();
        Object src = e.getSource();
        // Number buttons
        for (int i = 0; i <= 9; i++) {
            if (src == numButtons[i]) {
                l1.setText(zt + i);
                return;
            }
        }
        // Decimal
        if (src == bpts) {
            if (!zt.contains(".")) {
                l1.setText(zt + ".");
            }
            return;
        }
        // Negative
        if (src == bneg) {
            if (zt.isEmpty()) {
                l1.setText("-");
            } else if (zt.startsWith("-")) {
                l1.setText(zt.substring(1));
            } else {
                l1.setText("-" + zt);
            }
            return;
        }
        // Backspace
        if (src == bback) {
            if (!zt.isEmpty()) {
                l1.setText(zt.substring(0, zt.length() - 1));
            }
            return;
        }
        // Clear
        if (src == bclr) {
            num1 = 0;
            num2 = 0;
            check = 0;
            xd = 0;
            l1.setText("");
            return;
        }

        // Operators
        if (src == badd || src == bsub || src == bmult || src == bdiv || src == bmod) {
            try {
                num1 = Double.parseDouble(zt);
            } catch (NumberFormatException ex) {
                l1.setText("Invalid Format");
                return;
            }
            l1.setText("");
            if (src == badd) check = 1;
            else if (src == bsub) check = 2;
            else if (src == bmult) check = 3;
            else if (src == bdiv) check = 4;
            else if (src == bmod) check = 5;
            return;
        }

        // Calculate
        if (src == bcalc) {
            try {
                num2 = Double.parseDouble(zt);
            } catch (Exception ex) {
                l1.setText("ENTER NUMBER FIRST");
                return;
            }
            switch ((int) check) {
                case 1: xd = num1 + num2; break;
                case 2: xd = num1 - num2; break;
                case 3: xd = num1 * num2; break;
                case 4: 
                    if (num2 == 0) {
                        l1.setText("DIV BY ZERO");
                        return;
                    }
                    xd = num1 / num2; break;
                case 5: 
                    if (num2 == 0) {
                        l1.setText("DIV BY ZERO");
                        return;
                    }
                    xd = num1 % num2; break;
                default:
                    l1.setText("Select Op");
                    return;
            }
            l1.setText(String.valueOf(xd));
        }
    }

    public static void main(String args[]) {
        new MyCalc();
    }
}
