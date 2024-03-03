package com.example.aplikasikasirsederhana;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner itemSpinner;
    private TextView itemPriceValue, adminFeeValue, receiptTextView;
    private RadioButton membershipRadioButton, nonMembershipRadioButton;
    private Button processButton;

    private String[] items = {"Acnes", "Skintific", "Azarine", "You"};
    private int[] itemPrices = {35000, 98000, 46000, 25000};
    private int[] adminFees = {2000, 2500, 3000, 3500}; // Biaya admin berbeda untuk setiap item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemSpinner = findViewById(R.id.itemSpinner);
        itemPriceValue = findViewById(R.id.itemPriceValue);
        adminFeeValue = findViewById(R.id.adminFeeValue);
        receiptTextView = findViewById(R.id.receiptTextView);
        membershipRadioButton = findViewById(R.id.membershipRadioButton);
        nonMembershipRadioButton = findViewById(R.id.nonMembershipRadioButton);
        processButton = findViewById(R.id.processButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        itemSpinner.setAdapter(adapter);

        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // Update harga per item dan biaya admin saat item dipilih
                int price = itemPrices[position];
                int adminFee = adminFees[position];
                itemPriceValue.setText("Rp " + price);
                adminFeeValue.setText("Rp " + adminFee);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Proses pembelian
                int selectedItemPosition = itemSpinner.getSelectedItemPosition();
                int price = itemPrices[selectedItemPosition];
                int adminFee = adminFees[selectedItemPosition];
                double total = price;

                if (membershipRadioButton.isChecked()) {
                    // Diskon 5% jika dipilih membership
                    total *= 0.95;
                }

                // Tambah biaya admin
                total += adminFee;

                receiptTextView.setText("Nama Barang: " + items[selectedItemPosition] +
                        "\nBanyak Barang: 1" +
                        "\nTotal Harga Barang: Rp " + price +
                        "\nDiskon: " + (membershipRadioButton.isChecked() ? "5%" : "0%") +
                        "\nPemotongan: Rp 0" +
                        "\nTotal Bayar: Rp " + total);
            }
        });
    }
}

