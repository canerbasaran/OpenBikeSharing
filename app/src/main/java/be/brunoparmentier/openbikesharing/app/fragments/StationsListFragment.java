/*
 * Copyright (c) 2014 Bruno Parmentier. This file is part of OpenBikeSharing.
 *
 * OpenBikeSharing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenBikeSharing is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenBikeSharing.  If not, see <http://www.gnu.org/licenses/>.
 */

package be.brunoparmentier.openbikesharing.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import be.brunoparmentier.openbikesharing.app.R;
import be.brunoparmentier.openbikesharing.app.Station;
import be.brunoparmentier.openbikesharing.app.ui.StationActivity;

public class StationsListFragment extends Fragment {
    private ArrayList<Station> stations;

    private ArrayAdapter<String> stationsListAdapter;

    /* newInstance constructor for creating fragment with arguments */
    public static StationsListFragment newInstance(ArrayList<Station> stations) {
        StationsListFragment stationsListFragment = new StationsListFragment();
        Bundle args = new Bundle();
        args.putSerializable("stations", stations);
        stationsListFragment.setArguments(args);
        return stationsListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stations = (ArrayList<Station>) getArguments().getSerializable("stations");
        final ArrayList<String> stationsTitlesList = new ArrayList<String>();

        for (Station station : stations) {
            stationsTitlesList.add(station.getName());
        }

        stationsListAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, stationsTitlesList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stations_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.stationsListView);
        listView.setAdapter(stationsListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getActivity(), StationActivity.class);
                intent.putExtra("station", stations.get(position));
                startActivity(intent);
            }
        });
        return view;
    }
}
