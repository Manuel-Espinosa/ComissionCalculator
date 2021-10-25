import javax.swing.*;
import java.awt.event.*;

class CommissionCalculator extends JFrame{
    double total;
    JFrame frame;
    JPanel panel;
    JButton calculateButton;
    JButton addButton;
    JButton ereaseButton;
    JLabel productLabel;
    JTextArea pay;
    JTextArea cartTextArea;
    JLabel totalLabel;
    JComboBox<Product> productComboBox;

    JTextField totalTextField;

    public static void main(String[] args) {
        CommissionCalculator commissionCalculator = new CommissionCalculator();
        commissionCalculator.createWindow();
    }

    public void createWindow() {

        frame = new JFrame("Calculadora de comisiones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();

        this.productLabel = new JLabel("Seleccione producto");
        productLabel.setBounds(5, 10, 200, 30);

        Product products[] = new Product[4];
        products[0] = new Product("Articulo 1", 239.99);
        products[1] = new Product("Articulo 2", 129.75);
        products[2] = new Product("Articulo 3", 99.95);
        products[3] = new Product("Articulo 4", 350.89);
        this.productComboBox = new JComboBox<>(products);
        productComboBox.setBounds(130, 10, 100, 30);

        this.addButton = new JButton("Agregar");
        addButton.setBounds(250, 10, 100, 30);
        

        this.cartTextArea = new JTextArea();
        cartTextArea.setBounds(400, 10, 200, 200);

        this.ereaseButton = new JButton("Borrar");
        ereaseButton.setBounds(100, 50, 100, 30);

        this.calculateButton = new JButton("Calcular");
        calculateButton.setBounds(200, 50, 100, 30);

        this.totalLabel = new JLabel("Total a pagar:");
        totalLabel.setBounds(5, 80, 200, 30);

        this.pay = new JTextArea();
        pay.setBounds(100, 85, 100, 20);

        ListenForButton buttonListener = new ListenForButton();
        ereaseButton.addActionListener(buttonListener);
        addButton.addActionListener(buttonListener);
        calculateButton.addActionListener(buttonListener);

        frame.add(panel);
        frame.setSize(650, 300);
        panel.setLayout(null);
        panel.setSize(650, 300);
        panel.add(productComboBox);
        panel.add(productLabel);
        panel.add(addButton);
        panel.add(cartTextArea);
        panel.add(calculateButton);
        panel.add(ereaseButton);
        panel.add(totalLabel);
        panel.add(pay);
        frame.setVisible(true);

    }

    public void addProduct(Product product){
        total = total + product.price;
    }
    public void clear(){
        total = 0;
    }

    private class ListenForButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addButton) {
                Product option = (Product) productComboBox.getSelectedItem();
                cartTextArea.append(option.printInfo() + "\n");
                addProduct(option);
            }else if (e.getSource() == ereaseButton){
                cartTextArea.setText("");
                pay.setText("");
                clear();
            }else if(e.getSource() == calculateButton){
                double commission = total * 0.09;
                double totalPay = 200 + commission;
                pay.append("$" + totalPay); 

            }
        }
    }


}

class Product {
    String name;
    double price;

    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }
    public String toString(){
        return name;
    }
    public String printInfo(){
        return name + " $" + price;
    }
}
