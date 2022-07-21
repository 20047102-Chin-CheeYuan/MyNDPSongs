package sg.edu.rp.c346.id20047102.myndpsongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;

public class showSongs extends AppCompatActivity {

    Button btn5Stars;
    ArrayList<sg.edu.rp.c346.id20047102.myndpsongs.Song> al;
    ArrayList<sg.edu.rp.c346.id20047102.myndpsongs.Song> filteredList;
    HashSet<String> spinnerSet = new HashSet<String>();
    ListView lv;
    //    ArrayAdapter<sg.edu.rp.c346.id20047102.myndpsongs.Song> aa;
    sg.edu.rp.c346.id20047102.myndpsongs.Song data;
    Boolean state = false;
    Spinner spinYear;
    CustomAdapter caSong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_songs);

        btn5Stars = findViewById(R.id.btn5Stars);
        spinYear = findViewById(R.id.spinnerYear);
        lv = findViewById(R.id.lv);
        al = new ArrayList<sg.edu.rp.c346.id20047102.myndpsongs.Song>();
        filteredList = new ArrayList<sg.edu.rp.c346.id20047102.myndpsongs.Song>();
        caSong = new CustomAdapter(this, R.layout.row, filteredList);
//        aa = new ArrayAdapter<sg.edu.rp.c346.id20047102.myndpsongs.Song>(this, android.R.layout.simple_list_item_1, filteredList);
        lv.setAdapter(caSong);
        sg.edu.rp.c346.id20047102.myndpsongs.DBHelper dbh = new sg.edu.rp.c346.id20047102.myndpsongs.DBHelper(showSongs.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        filteredList.addAll(dbh.getAllSongs());
        caSong.notifyDataSetChanged();

        for (sg.edu.rp.c346.id20047102.myndpsongs.Song element : al
        ) {
            spinnerSet.add(String.valueOf(element.getYear()));
        }

        ArrayList<String> spinnerArray = new ArrayList<String>(spinnerSet);
        spinnerArray.add(0, "All Songs");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray
        );

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinYear.setAdapter(spinnerAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                sg.edu.rp.c346.id20047102.myndpsongs.Song data = filteredList.get(position);
                Intent i = new Intent(showSongs.this,
                        sg.edu.rp.c346.id20047102.myndpsongs.editSongs.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinYear.setEnabled(false);
                state = !state;
                filteredList.clear();
                if (state) {
                    btn5Stars.setText("Reset list");
                    for (sg.edu.rp.c346.id20047102.myndpsongs.Song element : al
                    ) {
                        if (element.getStars().equals("* * * * *")) {
                            filteredList.add(element);
                        }
                    }
                } else {
                    btn5Stars.setText("show all songs with 5 stars");
                    spinYear.setEnabled(true);
                    filteredList.addAll(al);
                }

                caSong.notifyDataSetChanged();
            }
        });

        spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filteredList.clear();
                if (position != 0) {
                    for (sg.edu.rp.c346.id20047102.myndpsongs.Song element : al
                    ) {
                        if (String.valueOf(element.getYear()).equals(spinnerArray.get(position))) {
                            filteredList.add(element);
                        }
                    }
                } else {
                    filteredList.addAll(al);
                }
                caSong.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        sg.edu.rp.c346.id20047102.myndpsongs.DBHelper dbh = new sg.edu.rp.c346.id20047102.myndpsongs.DBHelper(showSongs.this);
        al.clear();
        filteredList.clear();
        al.addAll(dbh.getAllSongs());
        filteredList.addAll(al);
        caSong.notifyDataSetChanged();
    }


}