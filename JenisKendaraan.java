// INHERITANCE: tiap class mewarisi (extends) Kendaraan
class Motor extends Kendaraan {
    public Motor(String nama) {
        super(nama, 2); // memanggil constructor induk
    }
    @Override // OVERRIDING: mengisi method abstract
    public void jalan() {
        System.out.println(nama + " melaju lincah menembus kemacetan.");
    }
    @Override // bensin motor: Rp 400 per km
    public double biayaPerjalanan(double jarakKm) {
        return jarakKm * 400;
    }
}

// [BARU] Mobil sekarang juga meng-implement interface Bermuatan
class Mobil extends Kendaraan implements Bermuatan {
    public Mobil(String nama) {
        super(nama, 4);
    }
    @Override
    public void jalan() {
        System.out.println(nama + " melaju nyaman dengan AC menyala.");
    }
    @Override // bensin mobil: Rp 1.200 per km
    public double biayaPerjalanan(double jarakKm) {
        return jarakKm * 1200;
    }
    @Override // [BARU] isi method dari interface Bermuatan
    public double kapasitasKg() {
        return 500;
    }
}

class Truk extends Mobil {
    public Truk(String nama) {
        super(nama);
        this.jumlahRoda = 6; // roda truk lebih banyak
    }
    @Override
    public void jalan() {
        super.jalan(); // memanggil jalan() milik Mobil dulu
        System.out.println("   ...sambil mengangkut muatan berat.");
    }
    @Override // truk lebih boros: 2x lipat biaya mobil
    public double biayaPerjalanan(double jarakKm) {
        return super.biayaPerjalanan(jarakKm) * 2;
    }
    @Override // [BARU] truk mengangkut jauh lebih banyak
    public double kapasitasKg() {
        return 5000;
    }
}

// [BARU] INHERITANCE bertingkat: Bus mewarisi Mobil (Mobil -> Bus)
class Bus extends Mobil {
    public Bus(String nama) {
        super(nama);
        this.jumlahRoda = 6;
    }
    @Override
    public void jalan() {
        System.out.println(nama + " melaju membawa banyak penumpang.");
    }
    @Override // bus: 1,5x biaya mobil
    public double biayaPerjalanan(double jarakKm) {
        return super.biayaPerjalanan(jarakKm) * 1.5;
    }
    @Override // kapasitas bagasi bus
    public double kapasitasKg() {
        return 1000;
    }
}
