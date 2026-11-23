import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

// [BARU] GUI Swing untuk simulasi kendaraan.
// Class Kendaraan dan turunannya TIDAK diubah: output println() mereka
// ditangkap lalu ditampilkan di JTextArea.
public class KendaraanGUI extends JFrame {
    private final JComboBox<String> cbJenis =
            new JComboBox<>(new String[]{"Motor", "Mobil", "Truk", "Bus"});
    private final JTextField tfNama = new JTextField("Honda Beat");
    private final JTextField tfJarak = new JTextField("50");
    private final JTextField tfMuatan = new JTextField("0");
    private final JTextArea output = new JTextArea(12, 36);

    public KendaraanGUI() {
        super("OOP Kendaraan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        JPanel form = new JPanel(new GridLayout(0, 2, 6, 6));
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        form.add(new JLabel("Jenis kendaraan:")); form.add(cbJenis);
        form.add(new JLabel("Nama:")); form.add(tfNama);
        form.add(new JLabel("Jarak (km):")); form.add(tfJarak);
        form.add(new JLabel("Muatan (kg):")); form.add(tfMuatan);
        JButton btn = new JButton("Jalankan");
        form.add(new JLabel()); form.add(btn);

        output.setEditable(false);
        output.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JScrollPane sp = new JScrollPane(output);
        sp.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        add(form, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);

        // muatan hanya aktif untuk kendaraan yang meng-implement Bermuatan
        cbJenis.addActionListener(e -> {
            Kendaraan k = buatKendaraan("tes");
            tfMuatan.setEnabled(k instanceof Bermuatan);
        });
        tfMuatan.setEnabled(false); // Motor dipilih di awal
        btn.addActionListener(e -> jalankan());

        pack();
        setLocationRelativeTo(null);
    }

    private Kendaraan buatKendaraan(String nama) {
        switch ((String) cbJenis.getSelectedItem()) {
            case "Mobil": return new Mobil(nama);
            case "Truk":  return new Truk(nama);
            case "Bus":   return new Bus(nama);
            default:      return new Motor(nama);
        }
    }

    private void jalankan() {
        double jarak, muatan = 0;
        try {
            jarak = Double.parseDouble(tfJarak.getText().trim());
            if (tfMuatan.isEnabled()) muatan = Double.parseDouble(tfMuatan.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Jarak / muatan harus berupa angka!");
            return;
        }
        String nama = tfNama.getText().trim();
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!");
            return;
        }

        // POLYMORPHISM: variabel bertipe Kendaraan, objeknya Motor/Mobil/Truk/Bus
        Kendaraan k = buatKendaraan(nama);

        // tangkap println() dari info() dan jalan()
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream lama = System.out;
        System.setOut(new PrintStream(buf));
        try {
            k.info();
            k.jalan(); // DYNAMIC BINDING
        } finally {
            System.setOut(lama);
        }

        StringBuilder sb = new StringBuilder(buf.toString());
        sb.append(String.format(Locale.forLanguageTag("id-ID"),
                "Biaya %.0f km : Rp %,.0f%n", jarak, k.biayaPerjalanan(jarak)));

        // INTERFACE: cek lewat tipe Bermuatan
        if (k instanceof Bermuatan) {
            Bermuatan b = (Bermuatan) k;
            sb.append(String.format("Kapasitas   : %.0f kg%n", b.kapasitasKg()));
            sb.append(b.bisaMuat(muatan)
                    ? String.format("Muatan %.0f kg : AMAN%n", muatan)
                    : String.format("Muatan %.0f kg : MELEBIHI KAPASITAS!%n", muatan));
        } else {
            sb.append("(Kendaraan ini tidak bisa mengangkut muatan)\n");
        }
        sb.append("-------------------\n");
        output.append(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KendaraanGUI().setVisible(true));
    }
}
