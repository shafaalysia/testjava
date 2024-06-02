import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TabelPerpustakaan extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public TabelPerpustakaan() {
        //bikin nama sm ukuran tabel 
        setTitle("TABEL PERPUSTAKAAN");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // membuat panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // membuat label
        JLabel label = new JLabel("Tabel Perpustakaan", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(label, BorderLayout.NORTH);

        // membuat tabel
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // membuat tombol
        JButton button = new JButton("TAMPILKAN TABEL");
        panel.add(button, BorderLayout.SOUTH);

        // menambahkan action listener untuk tombol
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTableData();
            }
        });

        add(panel);
    }

    //bikin method untuk akses data yang telah dibuat di dbeaver
    private void loadTableData() {
        String filePath = "C:\\Users\\SHAFA SHEFINA\\Downloads\\perpustakaan text-1717306887838.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            //mengosongkan data dan kolom yang ada
            tableModel.setRowCount(0); 
            tableModel.setColumnCount(0); 
            boolean isFirstRow = true;
            boolean isSecondRow = false;

            //melakukan looping untuk membaca semua baris dan berhenti jika sudah selesai.
            while ((line = br.readLine()) != null) {
                //Mengecek apakah baris tersebut dimulai dengan karakter '|'. Baris yang valid dimulai dengan '|'.
                if (line.startsWith("|")) {
                    // Menghilangkan karakter '|' dan memotong whitespace di kedua ujung
                    String[] data = line.split("\\|");
                    // Memisahkan baris menjadi array string data berdasarkan karakter '|'. Menghapus spasi tambahan di sekitar elemen array.
                    for (int i = 0; i < data.length; i++) {
                        data[i] = data[i].trim();
                    }
                    //kalau baris pertama bernilai benar
                    if (isFirstRow) {
                        // Menambahkan kolom ke tabel, mengabaikan elemen kosong pertama dan terakhir
                        for (int i = 1; i < data.length - 1; i++) {
                            tableModel.addColumn(data[i]);
                        }
                        isFirstRow = false;
                        isSecondRow = true;
                    //kalau baris kedua bernilai benar
                    } else if (isSecondRow) {
                        // mengabaikan baris kedua yang berisi garis pemisah
                        isSecondRow = false;
                    } else {
                        // Menambahkan baris ke tabel, mengabaikan elemen kosong pertama dan terakhir
                        if (data.length > 1) {
                            Object[] rowData = new Object[data.length - 2];
                            System.arraycopy(data, 1, rowData, 0, data.length - 2);
                            tableModel.addRow(rowData);
                        }
                    }
                }
            }
        //untuk memastikan agar semua file tidak terbaca
        } catch (IOException ex) {
            ex.printStackTrace(); // Cetak informasi kesalahan
            JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //set visible true agar bisa dilihat
                new TabelPerpustakaan().setVisible(true);
            }
        });
    }
}
