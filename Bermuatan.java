// INTERFACE: kontrak untuk kendaraan yang bisa mengangkut muatan.
// Class yang meng-implement WAJIB mengisi kapasitasKg().
public interface Bermuatan {
    double kapasitasKg();

    // DEFAULT METHOD
    default boolean bisaMuat(double beratKg) {
        return beratKg <= kapasitasKg();
    }
}

