package com.rent.hertz.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rent.hertz.R;
import com.rent.hertz.model.Manufacturer;
import com.rent.hertz.repository.ManufacturerRepository;

public class ManufacturerActivity extends AppCompatActivity {

    private ManufacturerRepository manufacturerRepository;
    private  EditText txtDescriptionManufacturer;
    private EditText txtNameManufacturer;
    private String idManufacturer = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String idManufacturer = null;
        setContentView(R.layout.activity_manufacturer);

        Intent intent = getIntent();
        Manufacturer manufacturer = (Manufacturer) intent.getSerializableExtra("manufacturer");

        Toolbar toolbar = findViewById(R.id.toolbar);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);
        this.txtDescriptionManufacturer = findViewById( R.id.edtTextDescriptionManufaturer );
        this.txtNameManufacturer =  findViewById( R.id.edtTextNameManufacturer );
        setSupportActionBar(toolbar);

        if(manufacturer != null) {
            this.idManufacturer = manufacturer.getId();
            this.txtDescriptionManufacturer.setText(manufacturer.getDescription());
            this.txtNameManufacturer.setText(String.valueOf( manufacturer.getName()));
        }

        btnSave.setOnClickListener(v -> {

                    this.manufacturerRepository = new ManufacturerRepository(this);
                    this.manufacturerRepository.saveOrUpdate(
                            new Manufacturer()
                                    .setId(this.idManufacturer)
                                    .setDescription( this.txtDescriptionManufacturer.getText().toString() )
                                    .setName(  this.txtNameManufacturer.getText().toString() )
                    );

                    this.manufacturerRepository.close();

                    startActivity(new Intent(ManufacturerActivity.this,
                            ManufacturerActivityList.class));

                    Toast.makeText(ManufacturerActivity.this, "Fabricante salva!",
                            Toast.LENGTH_SHORT).show();
                }
        );

        btnCancel.setOnClickListener( v -> {
                    startActivity(new Intent(ManufacturerActivity.this,
                            ManufacturerActivityList.class));
                }
        );

    }
}
