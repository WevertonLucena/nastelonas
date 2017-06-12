package br.com.lucenasistemas.nastelonas.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import br.com.lucenasistemas.nastelonas.R;

public class SettingsActivity extends PreferenceActivity
    implements Preference.OnPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SettingsFragment fragment = new SettingsFragment();
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content,fragment)
                .commit();

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }

    public static class SettingsFragment extends PreferenceFragment{

        private CheckBoxPreference acao;
        private CheckBoxPreference romance;
        private CheckBoxPreference terror;
        private Resources res;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.config);

            res = getActivity().getResources();

            acao = (CheckBoxPreference) findPreference(res.getString(R.string.pref_acao));
            romance = (CheckBoxPreference) findPreference(res.getString(R.string.pref_romance));
            terror = (CheckBoxPreference) findPreference(res.getString(R.string.pref_terror));

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            preferences.getBoolean(res.getString(R.string.pref_acao),true);

        }
    }
}
