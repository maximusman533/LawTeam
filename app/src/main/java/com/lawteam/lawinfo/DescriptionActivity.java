package com.lawteam.lawinfo;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

//класс формы с информацией об определённом участнике
public class DescriptionActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvGroup;
    TextView tvWorkingOn;
    TextView tvDescription;
    ImageView iwPhoto;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Person selected = Team.team.get(getIntent().getIntExtra("Selected", 1));

        tvName.setText(selected.getName());
        tvGroup.setText("Группа: " + selected.getGroup());
        tvWorkingOn.setText(selected.getWorkingOn());
        tvDescription.setText(selected.getDescription());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   //вызывается при выборе элемента из Action bar
        switch (item.getItemId()) { //выбор действия в соответствии с конкретным элементом меню
            case android.R.id.home: //выход из приложения
                finish();
                return true;
            case R.id.first:    //открытие форма изменения
                Intent intent = new Intent(this, EditActivity.class);
                intent.putExtra("Selected", getIntent().getIntExtra("Selected", 1));
                startActivityForResult(intent, 113);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //вызывается при создании опционального меню
        getMenuInflater().inflate(R.menu.context_menu, menu); //формирование View-элемента из layout-файла меню
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {    //вызывается при создании формы
        super.onCreate(savedInstanceState); //передача параметров для создания при вызове метода родительского класса
        setContentView(R.layout.activity_description); //устанавливает содержимое Activity из layout-файла

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //формирование кнопки возврата

        Intent intent = getIntent();
        Person selected = Team.team.get(intent.getIntExtra("Selected", 1));

        tvName = findViewById(R.id.DescrName);
        tvGroup = findViewById(R.id.DescrGroup);
        tvWorkingOn = findViewById(R.id.DescrWorkingOn);
        tvDescription = findViewById(R.id.DescrDescription);
        iwPhoto = findViewById(R.id.photoDescription);

        switch (selected.getId()) {
            case 0:
                iwPhoto.setImageResource(R.mipmap.nikita);
                break;
            case 1:
                iwPhoto.setImageResource(R.mipmap.maksim);
                break;
            case 2:
                iwPhoto.setImageResource(R.mipmap.mark);
                break;
            case 3:
                iwPhoto.setImageResource(R.mipmap.roman);
                break;
        }

        tvName.setText(selected.getName());
        tvGroup.setText("Группа: " + selected.getGroup());
        tvWorkingOn.setText(selected.getWorkingOn());
        tvDescription.setText(selected.getDescription());
    }
}
