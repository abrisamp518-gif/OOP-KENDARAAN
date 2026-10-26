    public static void MainKendaraan(String[] args) {
        // Kendaraan k = new Kendaraan("x", 4); // ERROR: abstract class
        // POLYMORPHISM: satu tipe (Kendaraan), isinya beda-beda objek
        Kendaraan[] daftar = {
            new Motor("Honda Beat"),
            new Mobil("Toyota Avanza"),
            new Truk("Hino Dutro")
        };
        double jarak = 50; // km
        for (Kendaraan k : daftar) {
            k.info();
            k.jalan(); // DYNAMIC BINDING: sesuai objek aslinya
            System.out.printf("Biaya %.0f km : Rp %,.0f%n", jarak, k.biayaPerjalanan(jarak));
            System.out.println("-------------------");
        }
    }
}