import javax.swing.*;
import java.awt.*;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ContaBancariaClientGUI extends JFrame {
    private ContaBancaria conta;
    private JLabel lblSaldo;
    private JTextField txtValor;

    public ContaBancariaClientGUI() {
        // Tenta conectar ao Servidor RMI
        try {
            conta = (ContaBancaria) Naming.lookup("//localhost:1099/ContaBancariaService");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar no servidor RMI!\nCertifique-se de que o Servidor está rodando.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // Configurações da Janela
        setTitle("Cliente RMI - Conta Bancária");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Componentes da Interface
        lblSaldo = new JLabel("Saldo Atual: Carregando...", SwingConstants.CENTER);
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 16));
        atualizarSaldo();

        JPanel painelInput = new JPanel(new FlowLayout());
        painelInput.add(new JLabel("Valor R$:"));
        txtValor = new JTextField(10);
        painelInput.add(txtValor);

        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnDepositar = new JButton("Depositar");
        JButton btnSacar = new JButton("Sacar");
        painelBotoes.add(btnDepositar);
        painelBotoes.add(btnSacar);

        // Ações dos Botões
        btnDepositar.addActionListener(e -> realizarOperacao("deposito"));
        btnSacar.addActionListener(e -> realizarOperacao("saque"));

        // Adiciona tudo na janela
        add(lblSaldo);
        add(painelInput);
        add(painelBotoes);
    }

    private void realizarOperacao(String tipo) {
        try {
            double valor = Double.parseDouble(txtValor.getText().replace(",", "."));
            
            if (tipo.equals("deposito")) {
                conta.deposita(valor);
                JOptionPane.showMessageDialog(this, "Depósito de R$ " + valor + " realizado com sucesso!");
            } else if (tipo.equals("saque")) {
                boolean sucesso = conta.retira(valor);
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Saque de R$ " + valor + " realizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente para o saque!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
            txtValor.setText("");
            atualizarSaldo();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um valor numérico válido.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao comunicar com o servidor.", "Erro RMI", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarSaldo() {
        try {
            lblSaldo.setText(String.format("Saldo Atual: R$ %.2f", conta.saldo()));
        } catch (RemoteException e) {
            lblSaldo.setText("Erro ao ler saldo");
        }
    }

    public static void main(String[] args) {
        // Executa a interface gráfica na thread correta do Swing
        SwingUtilities.invokeLater(() -> {
            new ContaBancariaClientGUI().setVisible(true);
        });
    }
}
