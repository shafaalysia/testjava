import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<mahasiswa> daftarMahasiswa = new ArrayList<>();
        
        boolean tambahData = true;
        while (tambahData) {
            System.out.print("Masukkan NIM: ");
            String nim = scanner.nextLine();
            
            System.out.print("Masukkan Nama: ");
            String nama = scanner.nextLine();
            
            System.out.print("Masukkan Alamat: ");
            String alamat = scanner.nextLine();
            
            // Buat objek Mahasiswa dengan data yang dimasukkan
            mahasiswa mahasiswa = new mahasiswa(nim, nama, alamat);
            
            // Tambahkan objek Mahasiswa ke ArrayList
            daftarMahasiswa.add(mahasiswa);
            
            // Minta pengguna untuk menambah data lagi atau tidak
            System.out.print("Tambah data lagi? (y/n): ");
            String opsi = scanner.nextLine();
            if (opsi.equalsIgnoreCase("y")) {
                tambahData = true;
            } else if (opsi.equalsIgnoreCase("n")) {
                tambahData = false;
            } else {
                System.out.println("Input tidak valid. Hanya terima 'y' untuk ya atau 'n' untuk tidak.");
                continue; // mengulangi loop agar pengguna diminta memberikan input yang benar
            }
        }
        
        System.out.println("==================================");
        System.out.println("Data Mahasiswa:");
        for (mahasiswa m : daftarMahasiswa) {
            System.out.println(m.getNim() + " | " + m.getNama() + " | " + m.getAlamat());
        }
    }
}