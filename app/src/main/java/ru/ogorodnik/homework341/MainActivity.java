package ru.ogorodnik.homework341;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Spinner langSpinner;
    private Spinner colorSpinner;
    private Button buttonOK;
    Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        initViews();

        // Нажатие кнопки OK ---------------------------------------------------------------------
        // Оставил Toast для красоты
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this
                        ,langSpinner.getSelectedItem().toString()
                                + " "
                                + colorSpinner.getSelectedItem().toString()
                                + " "
                        ,Toast.LENGTH_LONG)
                        .show();
                String lang = langSpinner.getSelectedItem().toString();
                switch (lang) {
                    case "EN English":
                    case "EN Английский":
                        lang = "en";
                        changeLang(lang);
                        break;
                    case "RU Russian":
                    case "RU Русский":
                        lang = "ru";
                        changeLang(lang);
                        break;
                    default:
                        break;
                }
                String color = colorSpinner.getSelectedItem().toString();
                switch (color) {
                    case "Green":
                    case "Зеленый":
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_GREEN);
                        break;
                    case "Black":
                    case "Черный":
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_BLACK);
                        break;
                    case "Blue":
                    case "Синий":
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_BLUE);
                        break;
                    default:
                        break;
                }
            }
        });

    }
    private void initViews() {
        langSpinner = findViewById(R.id.langSpinner);
        colorSpinner = findViewById(R.id.colorSpinner);
        buttonOK = findViewById(R.id.buttonOK);
        initSpinnerLanguage();
        initSpinnerColor();
    }

    private void initSpinnerLanguage() {
        ArrayAdapter<CharSequence> adapterLanguage = ArrayAdapter.createFromResource(this, R.array.language, android.R.layout.simple_spinner_item);
        adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        langSpinner.setAdapter(adapterLanguage);
    }

    private void initSpinnerColor() {
        ArrayAdapter<CharSequence> adapterColor = ArrayAdapter.createFromResource(this, R.array.color, android.R.layout.simple_spinner_item);
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapterColor);
    }

    public void changeLang(String lang)
    {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }

}

