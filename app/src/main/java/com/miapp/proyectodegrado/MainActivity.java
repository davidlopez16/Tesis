package com.miapp.proyectodegrado;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //estas variables permitiran obtener los controles creados y asi poder manipularlos
    EditText edit_actividad;
    EditText edit_unidad;
    EditText edit_cantidad;
    EditText edit_precioMO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //mapeamos las variables creadas con los controladores. De esta manera podemos setear valores
        // u obtenerlos
        edit_actividad = (EditText) findViewById(R.id.edit_actividad);
        edit_unidad = (EditText) findViewById(R.id.edit_unidad);
        edit_cantidad = (EditText) findViewById(R.id.edit_cantidad);
        edit_precioMO = (EditText) findViewById(R.id.edit_precioMO);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Estamos asignando el menu al activity
        getMenuInflater().inflate(R.menu.menu_presupuesto, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //de acuerdo al icono seleccionado se debe realizar la accion
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_add:

                //aca debemos trabajar con todos los controladores que definen el presupuesto para poderlo ingresar
                String actividad = edit_actividad.getText().toString();
                String unidad = edit_unidad.getText().toString();
                String cantidad = edit_cantidad.getText().toString();
                String precioMO = edit_precioMO.getText().toString();

                //validamos que se ingresen todos los campos
                if (actividad.length() > 0 && unidad.length() > 0 && cantidad.length() > 0 && precioMO.length() > 0) {
                    // abrimos la base de datos de presupuesto
                    UsualSQLiteHelper usuario = new UsualSQLiteHelper(this, "DBPresupuesto", null, 1);
                    SQLiteDatabase db = usuario.getWritableDatabase();

                    db.execSQL("INSERT INTO Presupuesto (Actividad, Unidad, Cantidad, PrecioMO) VALUE (" + actividad + "," + unidad + "," + cantidad + "," + precioMO + " )");
                    db.close();
                    Toast.makeText(this, "El usuario se ha creado exitosamente", Toast.LENGTH_SHORT).show();
                    edit_actividad.setText("");
                    edit_unidad.setText("");
                    edit_cantidad.setText("");
                    edit_precioMO.setText("");
                } else{
                    Toast.makeText(this, "Debe ingresar todos los valores", Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);


        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
      //getMenuInflater().inflate(R.menu.main, menu);
       // return true;
   // }
    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      //  int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
          //  return true;
        //}

       // return super.onOptionsItemSelected(item);
    //}

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
