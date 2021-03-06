package com.aiqcomponents.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.aiqcomponents.sample.databinding.ActivityMainBinding;
import com.aiqfome.aiqcomponents.adapters.BaseItem;
import com.aiqfome.aiqcomponents.adapters.IconItem;
import com.aiqfome.aiqcomponents.progressbutton.DrawableButton;
import com.aiqfome.aiqcomponents.progressbutton.DrawableButtonExtensionsKt;
import com.aiqfome.aiqcomponents.selector.SelectorController;
import com.aiqfome.aiqcomponents.textinput.TextInputController;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;

public class MainActivity extends AppCompatActivity {

    List<Country> countries = new ArrayList<>();

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupTextInputCountryPhone();
        setupTextInputColorAndName();
        setupSelectorRegion();
        setupSelectorCity();

        binding.layoutButtonsSample.btnProgressLeft.setOnClickListener(v -> showProgressLeft(binding.layoutButtonsSample.btnProgressLeft));
        binding.layoutButtonsSample.btnProgressCenter.setOnClickListener(v -> showProgressCenter(binding.layoutButtonsSample.btnProgressCenter));
        binding.layoutButtonsSample.btnProgressRight.setOnClickListener(v -> showProgressRight(binding.layoutButtonsSample.btnProgressRight));
        binding.layoutButtonsSample.btnProgressCustom.setOnClickListener(v -> showProgressCustom(binding.layoutButtonsSample.btnProgressCustom));
    }

    private void setupTextInputCountryPhone() {
        countries.add(new Country(VectorDrawableCompat.
                create(this.getResources(), R.drawable.ic_flag_br, this.getTheme()),
                "+55", "Brazil"));

        countries.add(new Country(VectorDrawableCompat.
                create(this.getResources(), R.drawable.ic_flag_cl, this.getTheme()),
                "+56", "Chile"));

        List<IconItem<Country>> countriesView = new ArrayList<>();

        for (Country c : countries)
            countriesView.add(new IconItem<>(c, c.getName(), c.getIdd(), c.getIcon()));

        TextInputController countriesController = new TextInputController<Country>(
                getSupportFragmentManager(),
                "Countries",
                countriesView,
                true,
                true) {

            @Override
            public void onItemSelected(Country country) {
                Toast.makeText(MainActivity.this, country.getName(), Toast.LENGTH_SHORT)
                        .show();
            }
        };

        binding.layoutInputsSample.tiCountryPhone.setup(countriesController);
    }

    private void setupTextInputColorAndName() {

        List<IconItem<Integer>> colorsView = new ArrayList<>();
        colorsView.add(new IconItem<>(1, "Blue", VectorDrawableCompat.
                create(this.getResources(), R.drawable.ic_blue, this.getTheme())));

        colorsView.add(new IconItem<>(2, "Red", VectorDrawableCompat.
                create(this.getResources(), R.drawable.ic_red, this.getTheme())));

        TextInputController colorsController = new TextInputController<Integer>(
                getSupportFragmentManager(),
                "Colors",
                colorsView,
                false) {

            @Override
            public void onItemSelected(Integer colorValue) { }
        };

        binding.layoutInputsSample.tiColorName.setup(colorsController);
    }

    private void setupSelectorRegion() {

        List<Region> regionList = new ArrayList<>();
        List<BaseItem<Region>> regionViews = new ArrayList<>();

        regionList.add(new Region("Paraná", "PR"));
        regionList.add(new Region("São Paulo", "SP"));
        regionList.add(new Region("Mato Grosso", "MT"));

        for (Region r : regionList)
            regionViews.add(new BaseItem<>(r, r.getName(), r.getAcronym()));

        SelectorController<Region> selectorController = new SelectorController<Region>(
                getSupportFragmentManager(),
                "Regions",
                regionViews,
                true,
                true,
                true,
                true
                ) {

            @Override
            public void onItemSelected(Region object) {
                Toast.makeText(MainActivity.this,
                        "Region: " + object.getAcronym(),
                        Toast.LENGTH_SHORT).show();
            }
        };

        binding.layoutInputsSample.selectorRegion.setup(selectorController);
    }

    private void setupSelectorCity() {

        List<Region> regionList = new ArrayList<>();
        List<BaseItem<Region>> cityViews = new ArrayList<>();

        regionList.add(new Region("Maringá", ""));
        regionList.add(new Region("Curitiba", ""));
        regionList.add(new Region("Rio de Janeiro", ""));

        for (Region r : regionList)
            cityViews.add(new BaseItem<>(r, r.getName(), r.getAcronym()));

        SelectorController<Region> selectorController = new SelectorController<Region>(
                getSupportFragmentManager(),
                "Cities",
                cityViews,
                true) {

            @Override
            public void onItemSelected(Region object) {
                Toast.makeText(MainActivity.this,
                        "City: " + object.getAcronym(),
                        Toast.LENGTH_SHORT).show();
            }
        };

        binding.layoutInputsSample.selectorCity.setup(selectorController);
    }

    private void showProgressLeft(final Button button) {
        DrawableButtonExtensionsKt.showProgress(button, progressParams -> {
            progressParams.setButtonTextRes(R.string.loading);
            progressParams.setProgressColor(Color.WHITE);
            progressParams.setGravity(DrawableButton.GRAVITY_TEXT_START);
            return Unit.INSTANCE;
        });
        button.setEnabled(false);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            button.setEnabled(true);
            DrawableButtonExtensionsKt.hideProgress(button, R.string.progress_left_text);
        }, 3000);
    }

    private void showProgressCenter(final Button button) {
        DrawableButtonExtensionsKt.showProgress(button, progressParams -> {
            progressParams.setProgressColor(Color.WHITE);
            progressParams.setGravity(DrawableButton.GRAVITY_CENTER);
            return Unit.INSTANCE;
        });
        button.setEnabled(false);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            button.setEnabled(true);
            DrawableButtonExtensionsKt.hideProgress(button, R.string.progress_center_text);
        }, 3000);
    }

    private void showProgressRight(final Button button) {
        DrawableButtonExtensionsKt.showProgress(button, progressParams -> {
            progressParams.setButtonTextRes(R.string.loading);
            progressParams.setProgressColor(Color.WHITE);
            return Unit.INSTANCE;
        });
        button.setEnabled(false);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            button.setEnabled(true);
            DrawableButtonExtensionsKt.hideProgress(button, R.string.progress_right_text);
        }, 3000);
    }

    private void showProgressCustom(final Button button) {
        DrawableButtonExtensionsKt.showProgress(button, progressParams -> {
            progressParams.setButtonTextRes(R.string.loading);
            progressParams.setProgressColors(new int[] {Color.WHITE, Color.MAGENTA, Color.GREEN});
            progressParams.setGravity(DrawableButton.GRAVITY_TEXT_END);
            progressParams.setProgressRadiusRes(R.dimen.progressRadius);
            progressParams.setProgressStrokeRes(R.dimen.progressStroke);
            progressParams.setTextMarginRes(R.dimen.textMarginStyled);
            return Unit.INSTANCE;
        });
        button.setEnabled(false);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            button.setEnabled(true);
            DrawableButtonExtensionsKt.hideProgress(button, R.string.progress_custom_text);
        }, 3000);
    }
}
