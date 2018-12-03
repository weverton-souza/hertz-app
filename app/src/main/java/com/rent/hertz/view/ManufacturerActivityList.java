package com.rent.hertz.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rent.hertz.R;
import com.rent.hertz.model.Manufacturer;
import com.rent.hertz.repository.ManufacturerRepository;

public class ManufacturerActivityList extends AppCompatActivity {

    private ListView textView;
    private ManufacturerRepository manufacturerRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturer_list);

        this.textView = findViewById(R.id.txtViewManufacturers);

        findViewById( R.id.btnAddNewManufacturer )
                .setOnClickListener( v -> {
                            loadManufacturer();
                            Intent intent = new Intent(ManufacturerActivityList.this, ManufacturerActivity.class);
                            startActivity(intent);
                        }
                );

        this.loadManufacturer();
    }

    private void loadManufacturer() {
        ManufacturerRepository manufacturerRepository = new ManufacturerRepository(this);

        ArrayAdapter<Manufacturer> arrayAdapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        manufacturerRepository.findAll());

        this.textView.setAdapter(arrayAdapter);
        registerForContextMenu(this.textView);
        manufacturerRepository.close();
    }

    @Override
    protected void onResume()  {
        super.onResume();
        this.loadManufacturer();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.add("Editar").setOnMenuItemClickListener(item -> {
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) menuInfo;
            Manufacturer manufacturer = (Manufacturer) this.textView.getItemAtPosition(info.position);

            Intent intent = new Intent(ManufacturerActivityList.this,
                    ManufacturerActivity.class);
            intent.putExtra("manufacturer", manufacturer);
            startActivity(intent);
            return false;
        });

        menu.add("Deletar").setOnMenuItemClickListener(item -> {
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) menuInfo;
            Manufacturer manufacturer = (Manufacturer) this.textView.getItemAtPosition(info.position);

            this.manufacturerRepository = new ManufacturerRepository(this);
            this.manufacturerRepository.deleteById(manufacturer.getId());
            this.manufacturerRepository.close();
            loadManufacturer();

            Toast.makeText(ManufacturerActivityList.this, "Fabricante deletado!",
                    Toast.LENGTH_SHORT).show();

            return false;
        });
    }
}
