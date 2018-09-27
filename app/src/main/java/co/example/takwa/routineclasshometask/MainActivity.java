package co.example.takwa.routineclasshometask;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etDay, etAlgorithm, etGraphics, etCompiler;
    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        etDay = findViewById(R.id.dayId);
        etAlgorithm = findViewById(R.id.algorithmId);
        etGraphics = findViewById(R.id.graphicsId);
        etCompiler = findViewById(R.id.compilerId);
        tvShow = findViewById(R.id.showId);
    }

    public void insert(View view) {
        String day = etDay.getText().toString().trim();
        String algo = etAlgorithm.getText().toString().trim();
        String graph = etGraphics.getText().toString().trim();
        String comp = etCompiler.getText().toString().trim();

        if (day.isEmpty()) {
            etDay.setError("enter DAY");
            etDay.requestFocus();
            return;
        }
        if (algo.isEmpty()) {
            etAlgorithm.setError("enter DAY");
            etAlgorithm.requestFocus();
            return;
        }
        if (graph.isEmpty()) {
            etGraphics.setError("enter DAY");
            etGraphics.requestFocus();
            return;
        }
        if (comp.isEmpty()) {
            etCompiler.setError("enter DAY");
            etCompiler.requestFocus();
            return;
        }

        DatabaseConnector databaseConnector = new DatabaseConnector(this);
        if (databaseConnector.insertData(day, algo, graph, comp)) {
            Toast.makeText(this, "Data saved!!", Toast.LENGTH_SHORT).show();
            emptyField();
        } else {
            Toast.makeText(this, "Failed!!", Toast.LENGTH_SHORT).show();
        }
    }


    private void emptyField() {
        etDay.setText("");

        etAlgorithm.setText("");
        etGraphics.setText("");
        etCompiler.setText("");
    }

    public void Update(View view) {
        String day = etDay.getText().toString().trim();
        String algo = etAlgorithm.getText().toString().trim();
        String graph = etGraphics.getText().toString().trim();
        String comp = etCompiler.getText().toString().trim();

        if (day.isEmpty()) {
            etDay.setError("enter DAY");
            etDay.requestFocus();
            return;
        }
        if (algo.isEmpty()) {
            etAlgorithm.setError("enter DAY");
            etAlgorithm.requestFocus();
            return;
        }
        if (graph.isEmpty()) {
            etGraphics.setError("enter DAY");
            etGraphics.requestFocus();
            return;
        }
        if (comp.isEmpty()) {
            etCompiler.setError("enter DAY");
            etCompiler.requestFocus();
            return;
        }

        DatabaseConnector databaseConnector = new DatabaseConnector(this);
        if (databaseConnector.updateData(day, algo, graph, comp) == -1) {
            Toast.makeText(this, "Failed!!", Toast.LENGTH_SHORT).show();
            emptyField();
        } else {
            Toast.makeText(this, "Data successfully updated!!", Toast.LENGTH_SHORT).show();
        }

    }

    public void Delete(View view) {
        String day = etDay.getText().toString().trim();
        if (day.isEmpty()) {
            etDay.setError("enter DAY");
            etDay.requestFocus();
            return;
        }
        DatabaseConnector databaseConnector = new DatabaseConnector(this);
        if (databaseConnector.deleteData(day) == -1) {
            Toast.makeText(this, "Failed to delete!!", Toast.LENGTH_SHORT).show();
            emptyField();
        } else {
            Toast.makeText(this, "Success!!", Toast.LENGTH_SHORT).show();
            emptyField();
        }


    }

    public void display(View view) {
        DatabaseConnector databaseConnector = new DatabaseConnector(this);
        Cursor cursor = databaseConnector.viewData();
        StringBuilder stringBuilder = new StringBuilder();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                stringBuilder.append(" Day : ").append(cursor.getString(0)).append("\n").append(" Algorithms : ").append(cursor.getString(1)).append("\n").append(" Graphics : ").append(cursor.getString(2)).append("\n").append(" Compiler : ").append(cursor.getString(3)).append("\n\n");
                cursor.moveToNext();
            }
            tvShow.setText(stringBuilder);


        } else {

            stringBuilder.append(" ");
            tvShow.setText(stringBuilder);
            Toast.makeText(this, "Result not found..", Toast.LENGTH_SHORT).show();


        }


    }


    public void searchRow(View view) {
        String day = etDay.getText().toString().trim();
        if (day.isEmpty()) {
            etDay.setError("enter DAY");
            etDay.requestFocus();
            return;
        }
        DatabaseConnector databaseConnector = new DatabaseConnector(this);
        Cursor cursor = databaseConnector.searchRow(day);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            etAlgorithm.setText(cursor.getString(1));
            etGraphics.setText(cursor.getString(2));
            etCompiler.setText(cursor.getString(3));

        } else {
            Toast.makeText(this, "Wrong input, check again", Toast.LENGTH_SHORT).show();
        }

    }
}
