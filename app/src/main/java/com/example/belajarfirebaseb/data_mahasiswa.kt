package com.example.belajarfirebaseb

class data_mahasiswa {
    //Deklarasi Variable
    var nim: String? = null
    var nama: String? = null
    var jurusan: String? = null
    var jenis_kelamin: String? = null
    var alamat: String? = null
    var key: String? = null

    //Membuat Konstuktor kosong untuk membaca data snapshot
    constructor() {}

    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User
    constructor(nim: String?, nama: String?, jurusan: String?, jenis_kelamin: String?, alamat: String?) {
        this.nim = nim
        this.nama = nama
        this.jurusan = jurusan
        this.jenis_kelamin = jenis_kelamin
        this.alamat = alamat
    }
}