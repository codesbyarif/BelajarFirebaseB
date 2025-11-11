package com.example.belajarfirebaseb

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(
    private val listMahasiswa: ArrayList<data_mahasiswa>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    // ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val NIM: TextView = itemView.findViewById(R.id.nimx)
        val Nama: TextView = itemView.findViewById(R.id.namax)
        val Jurusan: TextView = itemView.findViewById(R.id.jurusanx)
        val JenisKelamin: TextView = itemView.findViewById(R.id.jenis_kelaminx)
        val Alamat: TextView = itemView.findViewById(R.id.alamatx)
        val ListItem: LinearLayout = itemView.findViewById(R.id.list_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Membuat View untuk Menyiapkan & Memasang Layout yang digunakan pada RecyclerView
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.view_design,
            parent,
            false
        )
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        // Mengambil Nilai/Value pada RecyclerView berdasarkan Posisi Tertentu
        val mahasiswa = listMahasiswa[position]
        val nim = mahasiswa.nim
        val nama = mahasiswa.nama
        val jurusan = mahasiswa.jurusan
        val jenisKelamin = mahasiswa.jenis_kelamin
        val alamat = mahasiswa.alamat





        holder.NIM.text = "NIM: $nim"
        holder.Nama.text = "Nama: $nama"
        holder.Jurusan.text = "Jurusan: $jurusan"
        holder.JenisKelamin.text = "Jenis Kelamin: $jenisKelamin" // <-- PERBAIKAN 1
        holder.Alamat.text = "Alamat: $alamat"           // <-- PERBAIKAN 2

        // Set OnLongClickListener untuk menampilkan dialog Update/Delete
        holder.ListItem.setOnLongClickListener { view ->
            val action = arrayOf("Update", "Delete")
            val alert = AlertDialog.Builder(view.context)
            alert.setItems(action) { dialog, i ->
                when (i) {
                    0 -> {

                        val bundle = Bundle()
                        bundle.putString("dataNIM", listMahasiswa[position].nim)
                        bundle.putString("dataNama", listMahasiswa[position].nama)
                        bundle.putString("dataJurusan", listMahasiswa[position].jurusan)
                        bundle.putString("dataJenisKelamin", listMahasiswa[position].jenis_kelamin)
                        bundle.putString("dataAlamat", listMahasiswa[position].alamat)
                        bundle.putString("getPrimaryKey", listMahasiswa[position].key)
                        val intent = Intent(view.context, UpdateData::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }
                    1 -> {

                        listener?.onDeleteData(listMahasiswa[position], position)
                    }
                }
            }
            alert.create()
            alert.show()
            true
        }
    }

    override fun getItemCount(): Int {

        return listMahasiswa.size
    }

    // Membuat Interface
    interface dataListener {
        fun onDeleteData(data: data_mahasiswa?, position: Int)
    }

    // Deklarasi objek dari Interface
    var listener: dataListener? = null

    // Membuat Konstruktor, untuk menerima input dari Database
    init {
        this.listener = context as MyListData
    }
}