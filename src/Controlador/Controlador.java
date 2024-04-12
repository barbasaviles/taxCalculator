package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.*;
import java.io.Console;
import javax.swing.JOptionPane;

public class Controlador implements ActionListener {


    Salario view;


    public Controlador() {
        view = new Salario();        
        view.getComboTypeEmployee().addActionListener(this);
        view.getButtonCalculate().addActionListener(this);

    }

    public void Run() {
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.setTitle("Calculo Salario UNICOR");
        view.setVisible(true);
    }

    private Integer getValueTypeSalary() {
        Integer[] salarys = {0, 22000, 22000, 37770, 51103};
        Integer salary = salarys[view.getComboTypeEmployee().getSelectedIndex()];
        return salary;
    }

    private Integer getValueBond() {
        Integer[] bonds = {0, 40, 0, 0, 0};
        Integer bond = bonds[view.getComboTypeEmployee().getSelectedIndex()];
        return bond;
    }

    private void calculateSalary() {

        String pointSalary = view.getInputPointSalary().getText();

        if (!pointSalary.equals("")) {
            Integer typeSalary = getValueTypeSalary();
            if (typeSalary > 0) {
                Integer bonds = getValueBond() + Integer.parseInt(pointSalary);
                Integer salary = getValueTypeSalary() * bonds;
                
                Integer deduction = Integer.parseInt(view.getInputDeduction().getText());
                System.out.println(deduction);
                float porcDeduction = (float)(deduction/100);
                System.out.println((float)porcDeduction);
                float salaryDeduction = salary * (float)0.06;
                System.out.println(salaryDeduction);
                float salaryNeto = (float)(salary - salaryDeduction);
                System.out.print(salaryNeto);
                view.getInputSalary().setText(salaryNeto+"");
            } else {
                JOptionPane.showMessageDialog(view, "Es requerido Valor Punto Salarial");
            }            
        } else {
            JOptionPane.showMessageDialog(view, "Es requerido Puntos Salariales");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        if (e.getSource() == view.getComboTypeEmployee()) {
            view.getInputValuePoint().setText(getValueTypeSalary().toString());
            view.getInputBond().setText(getValueBond().toString());
        }

        if (e.getSource() == view.getButtonCalculate()) {
            calculateSalary();
        }
    }
}
