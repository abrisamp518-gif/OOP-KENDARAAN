// ABSTRACT CLASS: class induk, tidak bisa di-new langsung
public abstract class Kendaraan {
 protected String nama;
 protected int jumlahRoda;
 public Kendaraan(String nama, int jumlahRoda) {
 this.nama = nama;
 this.jumlahRoda = jumlahRoda;
 }
 // ABSTRACT METHOD: tanpa isi, wajib di-override subclass
 public abstract void jalan();
 public abstract double biayaPerjalanan(double jarakKm);
 // CONCRETE METHOD: diwariskan ke semua subclass
 public void info() {
 System.out.println("Kendaraan : " + nama + " (" + jumlahRoda + " roda)");
 }
}
